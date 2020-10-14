package bug_Detector.helpers;


import bug_Detector.patterns.BugPattern;

import java.io.File;
import java.util.List;

public interface Checker {
    List<BugPattern> check(File projectDir);
}
