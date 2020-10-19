package bug_Detector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

/**
 * Class used to do all input and output operations
 */

class Bugs {
    private final String file;
    private final int line;
    private final String bugType;
    
    public Bugs(String f, int l, String bt) {
        this.bugType = bt;
        this.file = f;
        this.line = l;
    }
    
    @Override
    public String toString() 
    {
        return this.bugType + " in " + this.file + ": " + this.line;
    }
}

public class BugList {
    private static BugList instance = null; 
    private final ArrayList<Bugs> bugs;
    
    private BugList() {
        this.bugs = new ArrayList<>();
    }
    
    
    public static BugList getInstance() 
    { 
        if (instance == null) {
            instance = new BugList(); 
        }
        return BugList.instance; 
    } 
    
    public void addBug(String file, int lineNo, String bugType) {
        this.bugs.add(new Bugs(file, lineNo, bugType));
    }

    public void getErrors() throws IOException {
        this.bugs.forEach(bug -> System.out.println(bug));
    }

}
