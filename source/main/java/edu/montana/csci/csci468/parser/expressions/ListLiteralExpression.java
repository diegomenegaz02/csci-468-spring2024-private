package edu.montana.csci.csci468.parser.expressions;

import edu.montana.csci.csci468.bytecode.ByteCodeGenerator;
import edu.montana.csci.csci468.eval.CatscriptRuntime;
import edu.montana.csci.csci468.parser.CatscriptType;
import edu.montana.csci.csci468.parser.SymbolTable;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.analysis.Value;

import java.lang.reflect.Array;
import java.util.*;

public class ListLiteralExpression extends Expression {
    List<Expression> values;
    private CatscriptType type;

    public ListLiteralExpression(List<Expression> values) {
        this.values = new LinkedList<>();
        for (Expression value : values) {
            this.values.add(addChild(value));
        }
    }

    public List<Expression> getValues() {
        return values;
    }

    @Override
    public void validate(SymbolTable symbolTable) {
        for (Expression value : values) {
            value.validate(symbolTable);
        }
        if (values.size() > 0) {
            // TODO - generalize this looking at all objects in list
            type = CatscriptType.getListType(values.get(0).getType());
        } else {
            type = CatscriptType.getListType(CatscriptType.OBJECT);
        }
    }

    @Override
    public CatscriptType getType() {
        return type;
    }

    //==============================================================
    // Implementation
    //==============================================================

    @Override
    public Object evaluate(CatscriptRuntime runtime) {
        List<Integer> list = new ArrayList<>();
        for(Expression expression : getValues()){
            Integer x = (Integer) expression.evaluate(runtime);
            list.add(x);
        }
       return list;
    }

    @Override
    public void transpile(StringBuilder javascript) {
        super.transpile(javascript);
    }

    @Override
    public void compile(ByteCodeGenerator code) {
        code.addTypeInstruction(Opcodes.NEW,ByteCodeGenerator.internalNameFor(ArrayList.class));
        code.addInstruction(Opcodes.DUP);
        code.addMethodInstruction(Opcodes.INVOKESPECIAL,ByteCodeGenerator.internalNameFor(ArrayList.class),"constructor",
                "java/util/ArrayList.<init>()V");
        code.addInstruction(Opcodes.ASTORE);
        for(Expression val : values){
            code.addInstruction(Opcodes.DUP);
            //INVOKESTATIC - Value Of
            //STORE
            val.compile(code);
            if(getType().equals(CatscriptType.INT)||getType().equals(CatscriptType.BOOLEAN)){
                code.addInstruction(Opcodes.ILOAD);
            }else{
                code.addInstruction(Opcodes.ALOAD);
            }
            code.addMethodInstruction(Opcodes.INVOKESTATIC,ByteCodeGenerator.internalNameFor(Integer.class),"valueOf",
                    "(Ljava/lang/Object;)Ljava/lang/Integer");
            code.addMethodInstruction(Opcodes.INVOKEINTERFACE,ByteCodeGenerator.internalNameFor(Object.class),"AddtoList",
                    "(Ljava/util/List/add(Ljava/lang/Object;)Z");
            code.addInstruction(Opcodes.POP);

        }

    }


}
