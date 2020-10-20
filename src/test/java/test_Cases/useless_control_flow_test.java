package test_Cases;

import bug_Detector.Analyser;
import org.junit.Test;

/**
 * Test cases to test the functionality of useless control flow
 */
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertNotEquals;
import static test_Cases.TestHelper.captureOutput;

public class useless_control_flow_test {
    @Test
    public void uselesscontrolflow() throws Exception {
        String file = "src/files_To_Test/useless_control_flow.java";

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
