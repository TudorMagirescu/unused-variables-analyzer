package main.cfg;

import java.util.Arrays;
import java.util.List;

public class CFGRoot extends CFGNode {

    private final List<CFGNode> children;

    public CFGRoot(Expression expression, List<CFGNode> nodes) {
        super(expression);
        this.children = nodes;
    }

    public static CFGRoot readNode(String program) {
        List<String> statements = Arrays.stream(program.split("\r\n"))
                .map(String::trim)
                .filter(line -> !line.isBlank())
                .toList();

        List<CFGNode> nodes = CFGNode.readNodes(statements);
        return new CFGRoot(new Expression(""), nodes);
    }

    public List<CFGNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Root{" + children.toString() + "}";
    }

}
