package main.cfg;

import java.util.List;

public class CFGIfNode extends CFGNode{

    private List<CFGNode> children;

    public CFGIfNode(Expression expression, List<CFGNode> children) {
        super(expression);
        this.children = children;
    }

    public static CFGIfNode readNode(List<String> statement) {
        // 'if' statement is the first string, 'end' is the last string
        // the other instructions are from positions 1 to length - 2
        String ifStatement = statement.get(0);
        Expression expression = Expression.parseExpression(ifStatement.substring("if".length()));
        List<CFGNode> children = CFGNode.readNodes(statement.subList(1, statement.size() - 1));

        return new CFGIfNode(expression, children);
    }

    public List<CFGNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "If{" + expression.toString() + ", " + children.toString() + "}";
    }

}
