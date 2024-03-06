package edu.montana.csci.csci468.parser;

import edu.montana.csci.csci468.parser.expressions.*;
import edu.montana.csci.csci468.parser.statements.*;
import edu.montana.csci.csci468.tokenizer.CatScriptTokenizer;
import edu.montana.csci.csci468.tokenizer.Token;
import edu.montana.csci.csci468.tokenizer.TokenList;
import edu.montana.csci.csci468.tokenizer.TokenType;
import spark.Request;

import javax.swing.plaf.nimbus.State;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static edu.montana.csci.csci468.tokenizer.TokenType.*;

public class CatScriptParser {

    private TokenList tokens;
    private FunctionDefinitionStatement currentFunctionDefinition;

    public CatScriptProgram parse(String source) {
        tokens = new CatScriptTokenizer(source).getTokens();

        // first parse an expression
        CatScriptProgram program = new CatScriptProgram();
        program.setStart(tokens.getCurrentToken());
        Expression expression = null;
        try {
            expression = parseExpression();
        } catch(RuntimeException re) {
            // ignore :)
        }
        if (expression == null || tokens.hasMoreTokens()) {
            tokens.reset();
            while (tokens.hasMoreTokens()) {
                program.addStatement(parseProgramStatement());
            }
        } else {
            program.setExpression(expression);
        }

        program.setEnd(tokens.getCurrentToken());
        return program;
    }

    public CatScriptProgram parseAsExpression(String source) {
        tokens = new CatScriptTokenizer(source).getTokens();
        CatScriptProgram program = new CatScriptProgram();
        program.setStart(tokens.getCurrentToken());
        Expression expression = parseExpression();
        program.setExpression(expression);
        program.setEnd(tokens.getCurrentToken());
        return program;
    }

    //============================================================
    //  Statements
    //============================================================

    private Statement parseProgramStatement() {
        Statement printStmt = parsePrintStatement();
        if (printStmt != null) {
            return printStmt;
        }
        Statement assignStmt = parseAssignmentStatement();
        if(assignStmt!= null){
            return assignStmt;
        }
        Statement varStmt = parseVariableStatement();
        if(varStmt != null){
            return varStmt;
        }
        return new SyntaxErrorStatement(tokens.consumeToken());
    }
    private Statement parseAssignmentStatement(){
        if(tokens.match(IDENTIFIER)){
            Token IDToken = tokens.consumeToken();
           Token operator = tokens.consumeToken();
           final Expression rightHandSide = parseExpression();
            AssignmentStatement assignmentStatement = new AssignmentStatement();
            assignmentStatement.setExpression(rightHandSide);
            assignmentStatement.setVariableName(IDToken.getStringValue());
            assignmentStatement.setStart(IDToken);
            assignmentStatement.setEnd(rightHandSide.getEnd());
            return assignmentStatement;
        }else{
            return null;
        }
    }
    private Statement parsePrintStatement() {
        if (tokens.match(PRINT)) {

            PrintStatement printStatement = new PrintStatement();
            printStatement.setStart(tokens.consumeToken());

            require(LEFT_PAREN, printStatement);
            printStatement.setExpression(parseExpression());
            printStatement.setEnd(require(RIGHT_PAREN, printStatement));

            return printStatement;
        } else {
            return null;
        }
    }
    private Statement parseVariableStatement(){
        if(tokens.match(VAR)){
            Token varToken = tokens.consumeToken(); //set start
            Token IDToken = tokens.consumeToken();
            VariableStatement variableStatement = new VariableStatement();
            if(tokens.match(COLON)){
                Token Colon = tokens.consumeToken();
                Expression typeLiteral= parseTypeExpression();
                variableStatement.setExplicitType(typeLiteral.getType());
                require(EQUAL,variableStatement);

                final Expression expression = parseExpression();
                variableStatement.setExpression(expression);
                variableStatement.setVariableName(IDToken.getStringValue());
                variableStatement.setStart(varToken);
                variableStatement.setEnd(expression.getEnd());
                return variableStatement;
            }else{
                require(EQUAL,variableStatement);
                final Expression expression = parseExpression();
                variableStatement.setExplicitType(expression.getType());
                variableStatement.setExpression(expression);
                variableStatement.setVariableName(IDToken.getStringValue());
                variableStatement.setStart(varToken);
                variableStatement.setEnd(expression.getEnd());
                return variableStatement;
            }
        }
        return null;
    }

    //============================================================
    //  Expressions
    //============================================================

    private Expression parseExpression() {
        return parseEqualityExpression();
    }

    private Expression parseAdditiveExpression() {
        Expression expression = parseFactorExpression();
        while (tokens.match(PLUS, MINUS)) {
            Token operator = tokens.consumeToken();
            final Expression rightHandSide = parseFactorExpression();
            AdditiveExpression additiveExpression = new AdditiveExpression(operator, expression, rightHandSide);
            additiveExpression.setStart(expression.getStart());
            additiveExpression.setEnd(rightHandSide.getEnd());
            expression = additiveExpression;
        }
        return expression;
    }
    private Expression parseFactorExpression() {
        Expression expression = parseUnaryExpression();
        while (tokens.match(STAR, SLASH)) {
            Token operator = tokens.consumeToken();
            final Expression rightHandSide = parseUnaryExpression();
            FactorExpression factorExpression = new FactorExpression(operator, expression, rightHandSide);
            factorExpression.setStart(expression.getStart());
            factorExpression.setEnd(rightHandSide.getEnd());
            expression = factorExpression;
        }
        return expression;
    }
    private Expression parseEqualityExpression(){
        Expression expression = parseComparisonExpression();
        if(tokens.match(EQUAL_EQUAL, BANG_EQUAL)){
            Token Equality = tokens.consumeToken();
            final Expression rightHandSide = parseComparisonExpression();
            EqualityExpression equalityExpression = new EqualityExpression(Equality,expression,rightHandSide);
            equalityExpression.setStart(expression.getStart());
            equalityExpression.setEnd(expression.getEnd());
            expression = equalityExpression;
        }
        return expression;
    }
    private Expression parseComparisonExpression(){
        Expression expression = parseAdditiveExpression();
        if (tokens.match(GREATER,GREATER_EQUAL,LESS,LESS_EQUAL)){
            Token comparison = tokens.consumeToken();
            final Expression rightHandSide = parseAdditiveExpression();
            ComparisonExpression comparisonExpression= new ComparisonExpression(comparison,expression,rightHandSide);
            comparisonExpression.setStart(expression.getStart());
            comparisonExpression.setEnd(rightHandSide.getEnd());
            expression = comparisonExpression;
        }
        return expression;
    }

    private Expression parseUnaryExpression() {
        if (tokens.match(MINUS, NOT)) {
            Token token = tokens.consumeToken();
            Expression rhs = parseUnaryExpression();
            UnaryExpression unaryExpression = new UnaryExpression(token, rhs);
            unaryExpression.setStart(token);
            unaryExpression.setEnd(rhs.getEnd());
            return unaryExpression;
        } else {
            return parsePrimaryExpression();
        }
    }

    private Expression parsePrimaryExpression() {
        if (tokens.match(INTEGER)) {
            Token integerToken = tokens.consumeToken();
            IntegerLiteralExpression integerExpression = new IntegerLiteralExpression(integerToken.getStringValue());
            integerExpression.setToken(integerToken);
            return integerExpression;
        } else if(tokens.match(STRING)) {
            Token stringToken = tokens.consumeToken();
            StringLiteralExpression stringExpression = new StringLiteralExpression(stringToken.getStringValue());
            stringExpression.setToken(stringToken);
            return stringExpression;
        } else if(tokens.match(IDENTIFIER)) {
            Token IDToken = tokens.consumeToken();
            if(tokens.match(LEFT_PAREN)){
                ArrayList<Expression> arguments = new ArrayList<>();
                tokens.consumeToken();
                while(!tokens.match(RIGHT_PAREN)){
                    Expression curExpression = parseExpression();
                    arguments.add(curExpression);
                    if(tokens.match(COMMA)){
                        tokens.consumeToken();
                    } else if (tokens.match(EOF)) {
                        Expression functionCallExpression = new FunctionCallExpression(IDToken.getStringValue(),arguments);
                        functionCallExpression.addError(ErrorType.UNTERMINATED_ARG_LIST);
                        return functionCallExpression;
                    }
                }
                ListLiteralExpression listLiteralExpression = new ListLiteralExpression(arguments);
                FunctionCallExpression functionCallExpression = new FunctionCallExpression(IDToken.getStringValue(),listLiteralExpression.getValues());
                return functionCallExpression;
            }else {
                IdentifierExpression identifierExpression = new IdentifierExpression(IDToken.getStringValue());
                identifierExpression.setToken(IDToken);
                return identifierExpression;
            }
        } else if(tokens.match(FALSE)) {
            Token falseToken = tokens.consumeToken();
            BooleanLiteralExpression booleanLiteralExpression = new BooleanLiteralExpression(false);
            booleanLiteralExpression.setToken(falseToken);
            return booleanLiteralExpression;
        } else if(tokens.match(TRUE)) {
            Token trueToken = tokens.consumeToken();
            BooleanLiteralExpression booleanLiteralExpression = new BooleanLiteralExpression(true);
            booleanLiteralExpression.setToken(trueToken);
            return booleanLiteralExpression;
        }else if(tokens.match(NULL)) {
            Token trueToken = tokens.consumeToken();
            NullLiteralExpression nullLiteralExpression = new NullLiteralExpression();
            nullLiteralExpression.setToken(trueToken);
            return nullLiteralExpression;
        }else if(tokens.match(LEFT_BRACKET) || tokens.match(LEFT_BRACE)) {
            Expression listLiteralExpression = parseListExpression();
            return listLiteralExpression;
        }else if(tokens.match(LEFT_PAREN)){
                tokens.consumeToken();
                Expression expressionToParse = parseExpression();
                require(RIGHT_PAREN,expressionToParse);
                ParenthesizedExpression parenthesizedExpression = new ParenthesizedExpression(expressionToParse);
                return parenthesizedExpression;
        }else{
            SyntaxErrorExpression syntaxErrorExpression = new SyntaxErrorExpression(tokens.consumeToken());
            return syntaxErrorExpression;
        }
    }
    private Expression parseListExpression(){
        if(tokens.match(LEFT_BRACKET) || tokens.match(LEFT_BRACE)){
            List<Expression> expressionList = new ArrayList<>();
            Token lb = tokens.consumeToken();
            while(!tokens.match(RIGHT_BRACKET)){
                Expression curExpression = parseExpression();
                expressionList.add(curExpression);
                if(tokens.match(COMMA)){
                    tokens.consumeToken();
                } else if (tokens.match(EOF)) {
                    Expression listLiteralExpression = new ListLiteralExpression(expressionList);
                    listLiteralExpression.addError(ErrorType.UNTERMINATED_LIST);
                    return listLiteralExpression;
                }
            }
            ListLiteralExpression listLiteralExpression = new ListLiteralExpression(expressionList);
            return listLiteralExpression;
        }
        return null;
    }

    private Expression parseTypeExpression(){
        Token token = tokens.getCurrentToken();
        String tokenString = token.getStringValue();
        if(tokens.match(STRING) || (tokenString.equals("string"))){
            TypeLiteral typeLiteral = new TypeLiteral();
            Token string = tokens.consumeToken();
            typeLiteral.setType(CatscriptType.STRING);
            return typeLiteral;
        }else if(tokens.match(INTEGER)|| (tokenString.equals("int")))  {
            TypeLiteral typeLiteral = new TypeLiteral();
            Token integer = tokens.consumeToken();
            typeLiteral.setType(CatscriptType.INT);
            typeLiteral.setStart(integer);
            typeLiteral.setEnd(integer);
            return typeLiteral;
        }else if(tokens.match(TRUE) || (tokens.match(FALSE)) || tokenString.equals("bool")){
            TypeLiteral typeLiteral = new TypeLiteral();
            Token bool = tokens.consumeToken();
            typeLiteral.setType(CatscriptType.BOOLEAN);
            typeLiteral.setStart(bool);
            typeLiteral.setEnd(bool);
            return typeLiteral;
        }else{
            SyntaxErrorExpression syntaxErrorExpression = new SyntaxErrorExpression(tokens.consumeToken());
            return syntaxErrorExpression;
        }

    }
    //============================================================
    //  Parse Helpers
    //============================================================
    private Token require(TokenType type, ParseElement elt) {
        return require(type, elt, ErrorType.UNEXPECTED_TOKEN);
    }

    private Token require(TokenType type, ParseElement elt, ErrorType msg) {
        if(tokens.match(type)){
            return tokens.consumeToken();
        } else {
            elt.addError(msg, tokens.getCurrentToken());
            return tokens.getCurrentToken();
        }
    }

}
