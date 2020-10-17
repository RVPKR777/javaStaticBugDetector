package bug_Detector;

import bug_Detector.helpers.Checker;
import bug_Detector.helpers.DuplicateLoggingStatementInCatchBlockOfSameTryChecker;
import bug_Detector.helpers.EqualsHashcodeChecker;
import bug_Detector.patterns.BugPattern;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class main {
    public static void main(String[] args) {


        File projectDir = new File("C://Users//pavan//Downloads//cloudstack-4.9.0");

        // Create a hashset of bug patterns so that we won't have any duplicates
        Set<BugPattern> bugPatterns = new HashSet<>();

        Checker checker = new EqualsHashcodeChecker();
        bugPatterns.addAll(checker.check(projectDir));

        checker = new DuplicateLoggingStatementInCatchBlockOfSameTryChecker();
        bugPatterns.addAll(checker.check(projectDir));

        System.out.println(bugPatterns);

    }
}
