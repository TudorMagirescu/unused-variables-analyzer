package main.cfg;

import java.util.HashSet;
import java.util.Set;

public class Expression {

    private final Set<Character> variables;
    private final String expression;

    public Expression(String expression) {
        variables = new HashSet<>();
        this.expression = expression;
    }

    public void addVariable(char variable) {
        variables.add(variable);
    }

    public static Expression parseExpression(String expression) {
        Expression expr = new Expression(expression.trim());

        for(Character ch : expression.toCharArray())
            if(ch >= 'a' && ch <= 'z')
                expr.addVariable(ch);

        return expr;
    }

    public String getExpressionString() {
        return expression;
    }

    public String toString() {
        return "Expression{" + expression + "}";
    }

    public Set<Character> getVariables() {
        return variables;
    }

}
