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
        new HashCodeAnalyser(this.files).analyse();
        new DuplicateCatchAnalyser(this.files).analyse();
        new UnnecessaryIfCondition(this.files).analyse();
    }

    /**
     * Get a list of errors by calling getErrors function
     */
    public void getErrors() throws IOException {
        BugList.getInstance().getErrors();
    }
  
  
}
