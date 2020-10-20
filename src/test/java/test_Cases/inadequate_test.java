package test_Cases;

import bug_Detector.Analyser;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertNotEquals;
import static test_Cases.TestHelper.captureOutput;

/**
 * Testcases to test the functionality of inadequate method
 */

public class inadequate_test {

    @Test
    public void inadequatetest() throws Exception {
        String file = "src/files_To_Test/inadequate2.java";

        captureOutput(new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {
                System.out.print(new Analyser(file));
                String sample = outContent.toString().replaceAll("\\s+","");
                assertNotEquals("", sample);
            }
        });
    }


}
