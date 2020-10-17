package bug_Detector;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("C://Users//pavan//Downloads//cloudstack-4.9");

        String dir = myObj.nextLine();  
            new Analyser(dir);
        }
}
