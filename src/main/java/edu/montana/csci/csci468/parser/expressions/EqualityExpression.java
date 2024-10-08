package edu.montana.csci.csci468.parser.expressions;

import edu.montana.csci.csci468.bytecode.ByteCodeGenerator;
import edu.montana.csci.csci468.eval.CatscriptRuntime;
import edu.montana.csci.csci468.parser.CatscriptType;
import edu.montana.csci.csci468.parser.SymbolTable;
import edu.montana.csci.csci468.tokenizer.Token;
import edu.montana.csci.csci468.tokenizer.TokenType;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

import java.lang.management.OperatingSystemMXBean;
import java.util.Objects;

public class EqualityExpression extends Expression {

    private final Token operator;
    private final Expression leftHandSide;
    private final Expression rightHandSide;

    public EqualityExpression(Token operator, Expression leftHandSide, Expression rightHandSide) {
        this.leftHandSide = addChild(leftHandSide);
        this.rightHandSide = addChild(rightHandSide);
        this.operator = operator;
    }

    public Expression getLeftHandSide() {
        return leftHandSide;
    }

    public Expression getRightHandSide() {
        return rightHandSide;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + operator.getStringValue() + "]";
    }

    public boolean isEqual() {
        return operator.getType().equals(TokenType.EQUAL_EQUAL);
    }

    @Override
    public void validate(SymbolTable symbolTable) {
        leftHandSide.validate(symbolTable);
        rightHandSide.validate(symbolTable);
    }

    @Override
    public CatscriptType getType() {
        return CatscriptType.BOOLEAN;
    }

    //==============================================================
    // Implementation
    //==============================================================

    @Override
    public Object evaluate(CatscriptRuntime runtime) {
        if(isEqual()) {
            if (getLeftHandSide().getType().equals(CatscriptType.INT) && getRightHandSide().getType().equals(CatscriptType.INT)) {
                Integer lhsvalue = (Integer) leftHandSide.evaluate(runtime);
                Integer rhsvalue = (Integer) rightHandSide.evaluate(runtime);
                if (lhsvalue.equals(rhsvalue)) {
                    return true;
                } else {
                    return false;
                }
            } else if (getLeftHandSide().getType().equals(CatscriptType.BOOLEAN) && getRightHandSide().getType().equals(CatscriptType.BOOLEAN)) {
                Boolean lhsvalue = (Boolean) leftHandSide.evaluate(runtime);
                Boolean rhsvalue = (Boolean) rightHandSide.evaluate(runtime);
                if (lhsvalue.equals(rhsvalue)) {
                    return true;
                } else {
                    return false;
                }
            } else if (leftHandSide.getType().equals(CatscriptType.NULL) && rightHandSide.getType().equals(CatscriptType.NULL)) {
                return true;
            } else {
                return false;
            }
        }else{
            if (getLeftHandSide().getType().equals(CatscriptType.INT) && getRightHandSide().getType().equals(CatscriptType.INT)) {
                Integer lhsvalue = (Integer) leftHandSide.evaluate(runtime);
                Integer rhsvalue = (Integer) rightHandSide.evaluate(runtime);
                if (lhsvalue.equals(rhsvalue)) {
                    return false;
                } else {
                    return true;
                }
            } else if (getLeftHandSide().getType().equals(CatscriptType.BOOLEAN) && getRightHandSide().getType().equals(CatscriptType.BOOLEAN)) {
                Boolean lhsvalue = (Boolean) leftHandSide.evaluate(runtime);
                Boolean rhsvalue = (Boolean) rightHandSide.evaluate(runtime);
                if (lhsvalue.equals(rhsvalue)) {
                    return false;
                } else {
                    return true;
                }
            } else if (leftHandSide.getType().equals(CatscriptType.NULL) && rightHandSide.getType().equals(CatscriptType.NULL)) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void transpile(StringBuilder javascript) {
        super.transpile(javascript);
    }

    @Override
    public void compile(ByteCodeGenerator code) {
        if(isEqual()){
            Label pushTrue = new Label();
            Label pushFalse = new Label();
            leftHandSide.compile(code);
            code.addMethodInstruction(Opcodes.INVOKESTATIC,ByteCodeGenerator.internalNameFor(Integer.class),"valueOf",
                    "(Ljava/lang/Object;)Ljava/lang/Integer");
            rightHandSide.compile(code);
            code.addMethodInstruction(Opcodes.INVOKEVIRTUAL,ByteCodeGenerator.internalNameFor(Integer.class),
                    "equals","(Ljava/lang/Object/equals(Ljava/lang/Object;)Z");
            code.addJumpInstruction(Opcodes.IFNE,pushTrue);
            code.pushConstantOntoStack(false);
            code.addJumpInstruction(Opcodes.NOP,pushFalse);
            code.addLabel(pushTrue);
            code.pushConstantOntoStack(true);
            code.addLabel(pushFalse);
        }else{
            Label pushTrue = new Label();
            Label pushFalse = new Label();
            leftHandSide.compile(code);
            code.addMethodInstruction(Opcodes.INVOKESTATIC,ByteCodeGenerator.internalNameFor(Integer.class),"valueOf",
                    "(Ljava/lang/Object;)Ljava/lang/Integer");
            rightHandSide.compile(code);
            code.addMethodInstruction(Opcodes.INVOKEVIRTUAL,ByteCodeGenerator.internalNameFor(Integer.class),
                    "equals","(Ljava/lang/Object/equals(Ljava/lang/Object;)Z");
            code.addJumpInstruction(Opcodes.IFNE,pushTrue);
            code.pushConstantOntoStack(true);
            code.addJumpInstruction(Opcodes.NOP,pushFalse);
            code.addLabel(pushTrue);
            code.pushConstantOntoStack(false);
            code.addLabel(pushFalse);
        }

    }


}
