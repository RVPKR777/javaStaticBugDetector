# Java_Static_Bug_Detector

## Submitted To:
### Professor: JINQIU YANG

## Team Information:

| Name  | Student ID | Email |
| ------------- | ------------- | ---------|
| Venkata Pavan Kumar Reddy Ravi  | 40083392  | pavan.03121996@gmail.com|
 

## Description
In the world of software development, bug detection is a very important factor as it helps the developers to find, analyse their mistakes and debug them. Bugs can be detected in 2 ways, static and dynamic bug detection. The main difference between static and dynamic bug detection is static bug detection doesn’t need to run the code where as in dynamic bug detection the code need to be run and bug detection will take place at runtime. Static bug detection can be done in any IDE like eclipse and Intellij.
we implemented a bug detection tool in java programming language to find few bugs which normally occurs similar to SpotBugs. Basically, we create a parser which goes through the target code and reports anybugs if found. We used JavaParser which is an open source library and developed java bug detection tool. The main bug types that the parser reports are:
| NO  | Bug |
| --- | -------------| 
| 1   | Hashcode() but no equals()  |
| 2  | Useless control flow  |
| 3   | Inadequate logging information in catch block  |

## Tools Used
### 1 - Maven : Version 3.1
### 2 - Java : JDK 8
### 3 - JUnit : JUnit 5
### 4 - JavaParser : latest version
### 5 - IntelliJ : 2020 version

## Build Information:
1 - Install java in the computer.<br />
2 - Install maven in the computer.<br />
3 - Download Intellij latest version and configure maven and java settings. In most cases it will automatically detect and adjust to the best available settings.<br />
4 - Clone the project from this repository.<br />
5 - Import the project into the workspace of intellij as a maven project.<br />
6 - Do maven clean install.<br />
7 - Run the main class and enter the path of the directory.<br />
