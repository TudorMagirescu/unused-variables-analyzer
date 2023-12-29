package main.cfg;

import java.util.List;

public class CFGVariableAssignmentNode extends CFGNode {

    private final char variable;

    public CFGVariableAssignmentNode(Expression expression, char variable) {
        super(expression);
        this.variable = variable;
    }

    public static CFGVariableAssignmentNode readNode(List<String> statement) {
        String variableAssignment = statement.get(0);
        String[] tokens = variableAssignment.split("=");

        char variable = tokens[0].charAt(0);
        Expression expression = Expression.parseExpression(tokens[1]);

        return new CFGVariableAssignmentNode(expression, variable);
    }

    public char getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return "VariableAssignment{" + variable + ", " + expression.toString() + "}";
    }

}
