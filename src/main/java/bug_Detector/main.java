package bug_Detector;

import java.util.Scanner;

/**
 * Main class
 */

public class Main {

    public static void main(String args[]) {

        // Getting user input of the path to the target project

        System.out.print("Enter project directory path: ");
        Scanner myObj = new Scanner(System.in);

        String dir = myObj.nextLine();
        new Analyser(dir);  // Calling anayzer
        }

}
