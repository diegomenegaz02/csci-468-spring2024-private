package edu.montana.csci.csci468.parser.statements;

import edu.montana.csci.csci468.bytecode.ByteCodeGenerator;
import edu.montana.csci.csci468.eval.CatscriptRuntime;
import edu.montana.csci.csci468.parser.CatscriptType;
import edu.montana.csci.csci468.parser.ErrorType;
import edu.montana.csci.csci468.parser.ParseError;
import edu.montana.csci.csci468.parser.SymbolTable;
import edu.montana.csci.csci468.parser.expressions.Expression;

import java.util.List;

public class VariableStatement extends Statement {
    private Expression expression;
    private String variableName;
    private CatscriptType explicitType;
    private CatscriptType type;

    public Expression getExpression() {
        return expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public void setExpression(Expression parseExpression) {
        this.expression = addChild(parseExpression);
    }

    public void setExplicitType(CatscriptType type) {
        this.explicitType = type;
    }

    public CatscriptType getExplicitType() {
        return explicitType;
    }

    public boolean isGlobal() {
        return getParent() instanceof CatScriptProgram;
    }

    @Override
    public void validate(SymbolTable symbolTable) {
        expression.validate(symbolTable);
        if (symbolTable.hasSymbol(variableName)) {
            addError(ErrorType.DUPLICATE_NAME);
        } else {
            if(explicitType!= null){
                type = explicitType;
            }else{
                type = expression.getType();
            }
            symbolTable.registerSymbol(variableName, type);
        }
    }

    public CatscriptType getType() {
        return type;
    }

    //==============================================================
    // Implementation
    //==============================================================
    @Override
    public void execute(CatscriptRuntime runtime) {
        this.addChild(expression);
        if(getType().equals(CatscriptType.INT) || getExplicitType().equals(CatscriptType.INT)){
           Integer val = (Integer) expression.evaluate(runtime);
           runtime.setValue(variableName,val);
       }else if(getType().equals(CatscriptType.STRING) || getExplicitType().equals(CatscriptType.STRING)){
           String val = (String) expression.evaluate(runtime);
           runtime.setValue(variableName,val);
       }else if(getType().equals(CatscriptType.OBJECT) || getExplicitType().equals(CatscriptType.OBJECT)){
           Object val = (Object) expression.evaluate(runtime);
           runtime.setValue(variableName,val);
       }else if(expression!=null){
           List<Integer> val = (List<Integer>) expression.evaluate(runtime);
           runtime.setValue(variableName,val);

       }

    }

    @Override
    public void transpile(StringBuilder javascript) {
        super.transpile(javascript);
    }

    @Override
    public void compile(ByteCodeGenerator code) {
        super.compile(code);
    }
}
