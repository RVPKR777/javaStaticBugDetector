package bug_Detector;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Path;

/**
 * This class analyzes the files and get appropriate errors/bugs with their location.
 */

public final class Analyser {
    private ArrayList<Path> files;
   
    public Analyser(String dir) {
          try {
              this.files = new TraverseDir(dir).getFiles();
              this.analyse();
              this.getErrors();
          } catch (IOException ex) {
              System.out.println(ex);
          }
    }

    /**
     * Calls various analyser functions
     */
    public void analyse() {
        new hashcode_No_Equals(this.files).analyse();
        new inadequate_Log_Info(this.files).analyse();
        new useless_Control_Flow(this.files).analyse();
    }

    /**
     * Get a list of errors by calling getErrors function
     */
    public void getErrors() throws IOException {
        BugList.getInstance().getErrors();
    }
  
  
}
