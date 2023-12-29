package main;

import main.cfg.*;

import java.util.*;

public class Analyzer {

    private final CFGRoot cfgRoot;
    private final Map<Character, String> currentUnusedAssignments;
    private final List<String> unusedAssignments;

    public Analyzer(CFGRoot cfgRoot) {
        this.cfgRoot = cfgRoot;
        this.currentUnusedAssignments = new HashMap<>();
        this.unusedAssignments = new ArrayList<>();
    }

    public List<String> analyze() {
        analyze(cfgRoot, false);
        for(Character unusedVariable : currentUnusedAssignments.keySet())
            unusedAssignments.add(createExpression(unusedVariable, currentUnusedAssignments.get(unusedVariable)));
        return unusedAssignments;
    }

    private void analyze(CFGNode node, boolean secondIteration) {
        processExpression(node.getExpression());

        if(node instanceof CFGRoot rootNode) {
            for(CFGNode child : rootNode.getChildren())
                analyze(child, secondIteration);
        }

        else if(node instanceof CFGIfNode ifNode) {
            for(CFGNode child : ifNode.getChildren())
                analyze(child, secondIteration);
        }

        else if(node instanceof CFGWhileNode whileNode) {
            for(CFGNode child : whileNode.getChildren())
                analyze(child, secondIteration);
            if(!secondIteration)
                analyze(node, true);
        }

        else if(node instanceof CFGVariableAssignmentNode assignmentNode && !secondIteration) {
            char variable = assignmentNode.getVariable();
            if(currentUnusedAssignments.containsKey(variable)) {
                unusedAssignments.add(createExpression(variable, currentUnusedAssignments.get(variable)));
                currentUnusedAssignments.remove(variable);
            }
            currentUnusedAssignments.put(variable, assignmentNode.getExpression().getExpressionString());
        }
    }

    private void processExpression(Expression expression) {
        for(Character operator : expression.getVariables())
            currentUnusedAssignments.remove(operator);
    }

    private static String createExpression(char variable, String expressionString) {
        return variable + " = " + expressionString;
    }

}
