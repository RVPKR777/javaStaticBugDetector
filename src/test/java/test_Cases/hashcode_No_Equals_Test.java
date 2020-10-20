package test_Cases;

import bug_Detector.Analyser;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static test_Cases.TestHelper.captureOutput;
import static org.junit.Assert.assertNotEquals;

public class hashcode_No_Equals_Test {

    @Test
    public void hashcode_No_Equals_Test1() throws Exception {
        String file = "src/files_To_Test/hashcode_No-Equals.java";

        captureOutput(new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {
                System.out.print(new Analyser(file));
                String sample = outContent.toString().replaceAll("\\s+","");
                assertNotEquals("", sample);
            }
        });
    }

    @Test
    public void hashcode_No_Equals_Test2() throws Exception {
        String file = "src/files_To_Test/hashcodeequals.java";

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
