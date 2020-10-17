package assignment;

import java.io.IOException;
import java.util.ArrayList;

import java.nio.file.Path;


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
    
    public void analyse() {
        new HashCodeAnalyser(this.files).analyse();
        new DuplicateCatchAnalyser(this.files).analyse();
        new UnnecessaryIfCondition(this.files).analyse();
    }
    
    public void getErrors() {
        BugList.getInstance().getErrors();
    }
  
  
}
