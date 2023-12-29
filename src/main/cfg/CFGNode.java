package main.cfg;

import java.util.ArrayList;
import java.util.List;

abstract public class CFGNode {

    protected Expression expression;

    public CFGNode(Expression expression) {
        this.expression = expression;
    }

    public static List<CFGNode> readNodes(List<String> program) {
        int startIndex = 0;
        int balance = 0;
        List<CFGNode> nodes = new ArrayList<>();

        for(int endIndex = 0; endIndex < program.size(); endIndex++) {
            if(program.get(endIndex).length() >= "if".length() && program.get(endIndex).startsWith("if"))
                balance++;
            else if(program.get(endIndex).length() >= "while".length() && program.get(endIndex).startsWith("while"))
                balance++;
            else if(program.get(endIndex).length() >= "end".length() && program.get(endIndex).startsWith("end"))
                balance--;

            if(balance == 0) {
                nodes.add(readNode(program.subList(startIndex, endIndex + 1)));
                startIndex = endIndex + 1;
            }
        }

        return nodes;
    }

    public static CFGNode readNode(List<String> statement) {
        if(statement.get(0).startsWith("while"))
            return CFGWhileNode.readNode(statement);
        if(statement.get(0).startsWith("if"))
            return CFGIfNode.readNode(statement);
        return CFGVariableAssignmentNode.readNode(statement);
    }

    public Expression getExpression() {
        return expression;
    }

    abstract public String toString();

}
