package bug_Detector;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("C://Users//pavan//Downloads//cloudstack-4.9");

        String dir = myObj.nextLine();  
            new Analyser(dir);
        }
}
