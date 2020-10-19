package hashcodenoequalstest;

import bug_Detector.Analyser;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import static hashcodenoequalstest.TestHelper.captureOutput;
import static org.junit.Assert.assertEquals;

public class hashcode_No_Equals_Test {

    @Test
    public void testOutput() throws Exception {
        String file = "src/files_To_Test/hashcode_No-Equals.java";

        captureOutput(new CaptureTest() {
            @Override
            public void test(ByteArrayOutputStream outContent, ByteArrayOutputStream errContent) throws Exception {
                System.out.print(new Analyser(file));
                String sample = outContent.toString().replaceAll("\\s+","");
                assertEquals("HE_HASHCODE_NO_EQUALS:src\\files_To_Test\\hashcode_No-Equals.java:2bug_Detector.Analyser@491cc5c9", sample);
            }
        });
    }
    /*@Test
    public void equalhashcodetest() throws IOException {
        String file = "src/files_To_Test/hashcode_No-Equals.java";
        System.setOut(new PrintStream(String.valueOf(new Analyser(file))));
        assertEquals("HE_HASHCODE_NO_EQUALS : src\\files_To_Test\\hashcode_No-Equals.java : 2" , outContent.toString());
    }

     */

}
