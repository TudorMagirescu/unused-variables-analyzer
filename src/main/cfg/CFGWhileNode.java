package main.cfg;

import java.util.List;

public class CFGWhileNode extends CFGNode{

    private final List<CFGNode> children;

    public CFGWhileNode(Expression expression, List<CFGNode> children) {
        super(expression);
        this.children = children;
    }

    public static CFGWhileNode readNode(List<String> statement) {
        // 'while' statement is the first string, 'end' is the last string
        // the other instructions are from positions 1 to length - 2
        String ifStatement = statement.get(0);
        Expression expression = Expression.parseExpression(ifStatement.substring("while".length()));
        List<CFGNode> children = CFGNode.readNodes(statement.subList(1, statement.size() - 1));

        return new CFGWhileNode(expression, children);
    }

    public List<CFGNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "While{" + expression.toString() + ", " + children.toString() + "}";
    }

}
