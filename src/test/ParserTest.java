package test;

import main.Analyzer;
import main.cfg.CFGRoot;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class ParserTest {

    @Test
    public void test() throws Exception {
        String program = readProgram("src/test/examples/input-program-02.txt");
        List<String> expectedResult = expectedResult("src/test/examples/expected-output-02.txt");

        CFGRoot parsedProgram = CFGRoot.readNode(program);
        List<String> myResult = new Analyzer(parsedProgram).analyze();

        assertTrue(listsAreTheSame(expectedResult, myResult));
    }

    private String readProgram(String path) throws Exception {
        Scanner scanner = new Scanner(new File(path)).useDelimiter("\\A");
        return scanner.next();
    }

    private List<String> expectedResult(String path) throws Exception {
        Scanner scanner = new Scanner(new File(path)).useDelimiter("\r\n");
        List<String> result = new ArrayList<>();

        while(scanner.hasNext())
            result.add(scanner.next().trim());

        return result;
    }

    private boolean listsAreTheSame(List<String> l1, List<String> l2) {
        Collections.sort(l1);
        Collections.sort(l2);
        return l1.equals(l2);
    }
}
