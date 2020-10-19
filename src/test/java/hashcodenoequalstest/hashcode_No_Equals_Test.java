package hashcodenoequalstest;

import bug_Detector.Analyser;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import static hashcodenoequalstest.TestHelper.captureOutput;
import static org.junit.Assert.assertEquals;

public class hashcode_No_Equals_Test {

    @Test
    public void hashcode_No_Equals_Test1() throws Exception {
        String file = "src/files_To_Test/hashcode_No-Equals.java";

        captureOutput(new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {
                System.out.print(new Analyser(file));
                String sample = outContent.toString().replaceAll("\\s+","");
                assertEquals("HE_HASHCODE_NO_EQUALS:src\\files_To_Test\\hashcode_No-Equals.java:2bug_Detector.Analyser@612fc6eb", sample);
            }
        });
    }


}
