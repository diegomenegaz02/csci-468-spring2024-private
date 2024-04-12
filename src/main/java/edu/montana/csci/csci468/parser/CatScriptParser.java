package edu.montana.csci.csci468.parser;

import edu.montana.csci.csci468.eval.CatscriptRuntime;
import edu.montana.csci.csci468.parser.expressions.*;
import edu.montana.csci.csci468.parser.statements.*;
import edu.montana.csci.csci468.tokenizer.CatScriptTokenizer;
import edu.montana.csci.csci468.tokenizer.Token;
import edu.montana.csci.csci468.tokenizer.TokenList;
import edu.montana.csci.csci468.tokenizer.TokenType;
import spark.Request;

import javax.swing.plaf.nimbus.State;
import java.lang.reflect.Parameter;
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
    private Statement parseStatement(){
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
        Statement forStmt = parseForStatement();
        if(forStmt != null){
            return forStmt;
        }
        Statement ifStmt = parseIfStatement();
        if(ifStmt != null){
            return ifStmt;
        }
        if(currentFunctionDefinition!=null){
            Statement stmt = parseReturnStatement();
            return stmt;

        }

        return new SyntaxErrorStatement(tokens.consumeToken());
    }

    private Statement parseProgramStatement() {
        Statement funcDefStmt = parseFunctionDefinition();
        if(funcDefStmt!=null){
            return funcDefStmt;
        }
        return parseStatement();
    }

    private Statement parseFunctionDefinition() {
        if(tokens.match(FUNCTION)){
            tokens.consumeToken();
            LinkedList<Statement> body = new LinkedList<>();
            FunctionDefinitionStatement functionDefinitionStatement= new FunctionDefinitionStatement();

            Token name = tokens.consumeToken();
            functionDefinitionStatement.setName(name.getStringValue());
            currentFunctionDefinition = functionDefinitionStatement;
            if(tokens.match(LEFT_PAREN)) {
                tokens.consumeToken();
                while (!tokens.match(RIGHT_PAREN)) {
                    //Param Parsing
                    if (tokens.match(EOF)) {
                        functionDefinitionStatement.addError(ErrorType.UNEXPECTED_TOKEN, tokens.getCurrentToken());
                        return functionDefinitionStatement;
                    }
                    Token Identifier = tokens.consumeToken();
                    if (tokens.match(COLON)) {
                        tokens.consumeToken();
                        TypeLiteral type = parseTypeExpression();
                        functionDefinitionStatement.addParameter(Identifier.getStringValue(), type);

                    }else{
                        TypeLiteral type = new TypeLiteral();
                        type.setType(CatscriptType.OBJECT);
                        functionDefinitionStatement.addParameter(Identifier.getStringValue(), type);
                    }
                    if (tokens.match(COMMA)) {
                        tokens.consumeToken();
                    }
                    //Get Rid of Comma

                }//END OF WHILE
                tokens.consumeToken(); // SHould be Right Param
                if(tokens.match(COLON)){
                    tokens.consumeToken();
                    TypeLiteral typeLiteral = parseTypeExpression();
                    functionDefinitionStatement.setType(typeLiteral);
                }else{
                    TypeLiteral typeLiteral = new TypeLiteral();
                    typeLiteral.setType(CatscriptType.OBJECT);
                    functionDefinitionStatement.setType(typeLiteral);
                }
                if (tokens.match(LEFT_BRACE)) {
                    tokens.consumeToken();
                    while(!tokens.match(RIGHT_BRACE)) {
                        if(tokens.match(EOF)){
                            functionDefinitionStatement.addError(ErrorType.UNEXPECTED_TOKEN,tokens.getCurrentToken());
                            return functionDefinitionStatement;
                        }
                            Statement statement = parseProgramStatement();
                            body.push(statement);
                    }
                    tokens.consumeToken();
                }
                functionDefinitionStatement.setBody(body);
                return functionDefinitionStatement;
            }
        }

        return null;
    }
    private Statement parseReturnStatement(){
        if(tokens.match(RETURN)){
            tokens.consumeToken();
            ReturnStatement returnStatement = new ReturnStatement();
            returnStatement.setFunctionDefinition(currentFunctionDefinition);
            if(!tokens.match(RIGHT_BRACE)){
                Expression expression = parseExpression();
                returnStatement.setExpression(expression);

            }else{
                returnStatement.setExpression(null);

                return returnStatement;
            }

            return returnStatement;

        }else{
            return null;
        }
    }

    private Statement parseIfStatement(){
        if(tokens.match(IF)){
            List<Statement> statementList = new ArrayList<>();
            tokens.consumeToken();
            IfStatement ifStatement = new IfStatement();
            if(tokens.match(LEFT_PAREN)){
                tokens.consumeToken();
                final Expression expression = parseExpression();
                ifStatement.setExpression(expression);
                tokens.consumeToken();
                if(tokens.match(LEFT_BRACE)){
                    while(!tokens.match(RIGHT_BRACE)){
                        tokens.consumeToken();
                        Statement statement = parseProgramStatement();
                        statementList.add(statement);
                        if(tokens.match(EOF)){
                            ifStatement.addError(ErrorType.UNEXPECTED_TOKEN,tokens.getCurrentToken());
                            return ifStatement;
                        }
                    }
                    ifStatement.setTrueStatements(statementList);
                    tokens.consumeToken();
                }
                if(tokens.match(ELSE)){
                    List<Statement> elseStatements = new ArrayList<>();
                    //Another if statement
                    tokens.consumeToken();

                    //IF or LEFT_BRACE to determine
                    if(tokens.match(LEFT_BRACE)){
                        tokens.consumeToken();
                        if(tokens.match(EOF)){
                            ifStatement.addError(ErrorType.UNEXPECTED_TOKEN,tokens.getCurrentToken());
                            return ifStatement;
                        }
                        while(!tokens.match(RIGHT_BRACE)){
                            Statement statement = parseProgramStatement();
                            elseStatements.add(statement);
                        }
                        require(RIGHT_BRACE,ifStatement,ErrorType.UNEXPECTED_TOKEN);
                        ifStatement.setElseStatements(elseStatements);
                        return ifStatement;
                    }else if (tokens.match(IF)){
                        parseIfStatement();
                        return ifStatement;
                    }
                }else{
                    return ifStatement;
                }
            }
        }
        return null;
    }

    private Statement parseAssignmentStatement(){
        if(tokens.match(IDENTIFIER)){
            Token IDToken = tokens.consumeToken();
            if(tokens.match(EQUAL)){
                Token operator = tokens.consumeToken();

                final Expression rightHandSide = parseExpression();
                AssignmentStatement assignmentStatement = new AssignmentStatement();
                assignmentStatement.setExpression(rightHandSide);
                assignmentStatement.setVariableName(IDToken.getStringValue());
                assignmentStatement.setStart(IDToken);
                assignmentStatement.setEnd(rightHandSide.getEnd());
                return assignmentStatement;
            }
            else if(tokens.match(LEFT_PAREN)){
                tokens.reset();
                FunctionCallExpression functionCallExpression = parseFunctionCallExpression();
                FunctionCallStatement functionCallStatement = new FunctionCallStatement(functionCallExpression);
                return functionCallStatement;
            }

    }return null;

    }


    private Statement parseForStatement() {
        List<Statement> statementList= new ArrayList<>();
        if(tokens.match(FOR)) {
            ForStatement forStatement = new ForStatement();
            tokens.consumeToken();
            if(tokens.match(LEFT_PAREN)){
                tokens.consumeToken();
                Token Id = tokens.consumeToken();
                forStatement.setVariableName(Id.getStringValue());
                if(tokens.match(IN)){
                    tokens.consumeToken();
                    Expression expression = parseExpression();
                    forStatement.setExpression(expression);
                    require(RIGHT_PAREN,forStatement,ErrorType.UNEXPECTED_TOKEN);
                    if(tokens.match(LEFT_BRACE)){
                        while(!tokens.match(RIGHT_BRACE)) {
                            tokens.consumeToken();
                            Statement statement = parseProgramStatement();
                            statementList.add(statement);
                            if(tokens.match(EOF)){
                                forStatement.addError(ErrorType.UNEXPECTED_TOKEN,tokens.getCurrentToken());
                                return forStatement;
                            }

                        }
                        forStatement.setBody(statementList);
                        tokens.consumeToken();
                        return forStatement;
                    }
                }else {
                    forStatement.hasError(ErrorType.UNEXPECTED_TOKEN);
                }
            }else{
                forStatement.hasError(ErrorType.UNEXPECTED_TOKEN);
            }
        }
        return null;

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
                TypeLiteral typeLiteral= parseTypeExpression();
                variableStatement.setExplicitType(typeLiteral.getType());
                require(EQUAL,variableStatement);
                if(tokens.match(LEFT_BRACKET)){
                    final Expression expression = parseListExpression();
                    variableStatement.setExpression(expression);
                    variableStatement.setVariableName(IDToken.getStringValue());
                    variableStatement.setStart(varToken);
                    variableStatement.setEnd(expression.getEnd());
                    return variableStatement;
                }else{
                    final Expression expression = parseExpression();
                    if(!expression.getType().equals(variableStatement.getExplicitType()) && variableStatement.getExplicitType()!=CatscriptType.OBJECT){
                        variableStatement.addError(ErrorType.INCOMPATIBLE_TYPES);
                    }
                    variableStatement.setExpression(expression);
                    variableStatement.setVariableName(IDToken.getStringValue());
                    variableStatement.setStart(varToken);
                    variableStatement.setEnd(expression.getEnd());
                    return variableStatement;
                }
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
        if (tokens.match(MINUS) || tokens.match(NOT)) {
            Token token = tokens.consumeToken();
            Expression rhs = parseUnaryExpression();
            UnaryExpression unaryExpression = new UnaryExpression(token, rhs);
            unaryExpression.setStart(token);
            unaryExpression.setEnd(rhs.getEnd());
            return unaryExpression;
        }else{
            return parsePrimaryExpression();
        }
    }

    private Expression parsePrimaryExpression() {
        if (tokens.match(INTEGER)) {
            Token integerToken = tokens.consumeToken();
            IntegerLiteralExpression integerExpression = new IntegerLiteralExpression(integerToken.getStringValue());
            integerExpression.setToken(integerToken);
            return integerExpression;
        } else if(tokens.match(STRING) || tokens.getCurrentToken().getStringValue().equals("\"")) {
            Token stringToken = tokens.consumeToken();
            StringLiteralExpression stringExpression = new StringLiteralExpression(stringToken.getStringValue());
            stringExpression.setToken(stringToken);
            if(tokens.match(PLUS)){
                tokens.consumeToken();
                if(tokens.match(STRING) || tokens.match(INTEGER) ){
                    Token secondString = tokens.consumeToken();
                    String x = stringToken.getStringValue() + secondString.getStringValue();
                    StringLiteralExpression alt = new StringLiteralExpression(x);
                    alt.setStart(stringToken);
                    alt.setEnd(secondString);
                    return alt;
                }else{
                    return stringExpression;
                }
            }
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
    private FunctionCallExpression parseFunctionCallExpression(){
        if(tokens.match(IDENTIFIER)) {
            Token IDToken = tokens.consumeToken();
            if (tokens.match(LEFT_PAREN)) {
                List<Expression> arguments = new ArrayList<>();
                tokens.consumeToken();
                while (!tokens.match(RIGHT_PAREN)) {
                    Expression curExpression = parseExpression();
                    arguments.add(curExpression);
                    if (tokens.match(COMMA)) {
                        tokens.consumeToken();
                    }

                }
                FunctionCallExpression functionCallExpression = new FunctionCallExpression(IDToken.getStringValue(), arguments);
                return functionCallExpression;
            } else {
                return null;
            }
        }
            return null;
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
            tokens.consumeToken();
            ListLiteralExpression listLiteralExpression = new ListLiteralExpression(expressionList);
            return listLiteralExpression;
        }
        return null;
    }

    private TypeLiteral parseTypeExpression(){
        Token token = tokens.getCurrentToken();
        String tokenString = token.getStringValue();
        TypeLiteral typeLiteral = new TypeLiteral();
        if(tokens.match(STRING) || (tokenString.equals("string"))){

            Token string = tokens.consumeToken();
            typeLiteral.setType(CatscriptType.STRING);
            return typeLiteral;
        }else if(tokens.match(INTEGER)|| (tokenString.equals("int")))  {

            Token integer = tokens.consumeToken();
            typeLiteral.setType(CatscriptType.INT);
            typeLiteral.setStart(integer);
            typeLiteral.setEnd(integer);
            return typeLiteral;
        }else if(tokens.match(TRUE) || (tokens.match(FALSE)) || tokenString.equals("bool")){

            Token bool = tokens.consumeToken();
            typeLiteral.setType(CatscriptType.BOOLEAN);
            typeLiteral.setStart(bool);
            typeLiteral.setEnd(bool);
            return typeLiteral;
        }else if(tokenString.equals("object")){
            Token obj = tokens.consumeToken();
            typeLiteral.setType(CatscriptType.OBJECT);
            typeLiteral.setStart(obj);
            typeLiteral.setEnd(obj);
            return typeLiteral;
        }else if(tokenString.equals("list")){
            Token obj = tokens.consumeToken();

            require(LESS,typeLiteral,ErrorType.UNEXPECTED_TOKEN);
            Token current = tokens.getCurrentToken();
            String String = current.getStringValue();
            if(tokens.match(INTEGER)|| (String.equals("int"))){
                typeLiteral.setType(CatscriptType.ListType.getListType(CatscriptType.INT));
            }else if(tokens.match(STRING)|| (String.equals("string"))){
                typeLiteral.setType(CatscriptType.ListType.getListType(CatscriptType.STRING));
            }else if(tokens.match(TRUE) || tokens.match(FALSE) || String.equals("bool")){

            }else{
                typeLiteral.setType(CatscriptType.ListType.getListType(CatscriptType.OBJECT));
            }
            tokens.consumeToken();
            tokens.consumeToken();
            typeLiteral.setStart(obj);
            typeLiteral.setEnd(obj);
            return typeLiteral;
        }else{
            typeLiteral.addError(ErrorType.UNEXPECTED_TOKEN);
            return typeLiteral;
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
