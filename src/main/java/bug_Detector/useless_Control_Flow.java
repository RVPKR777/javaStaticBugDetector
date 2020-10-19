
package bug_Detector;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to analyse useless control flow
 */

public class useless_Control_Flow {
    private final ArrayList<Path> files;
    private final BugList bugs;

    public useless_Control_Flow(ArrayList<Path> file) {
        this.bugs = BugList.getInstance();
        this.files = file;
    }

    public void analyse() {
        this.analyseIf();
        this.analyseDoWhile();
        this.analyseFor();
        this.analyseForEach();
        this.analyseWhile();
    }

    public void analyseIf() {

        this.files.forEach(file -> {
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(IfStmt ifStmt, Object arg) {
                        super.visit(ifStmt, arg);
                        int lineNumber = 0;
                        Node nod = ifStmt.getChildNodes().get(0);
                        List<Node> childNodes = ifStmt.getChildNodes();
                        for (Node nodeInIfs : childNodes) {
                            if (nodeInIfs instanceof BlockStmt) {
                                List<Statement> listStatementOfIfBlock = ((BlockStmt) nodeInIfs).getStatements();
                                if(listStatementOfIfBlock.isEmpty()) {
                                    lineNumber = nodeInIfs.getRange().isPresent() ? nodeInIfs.getRange().get().begin.line : 0;
                                    bugs.addBug(file.toString(), lineNumber, "USELESS_CONTROL_FLOW");
                                }
                            }
                        }

                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }



    public void analyseFor() {

        this.files.forEach(file -> {
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(ForStmt forStmt, Object arg) {
                        super.visit(forStmt, arg);
                        int lineNumber = 0;
                        Node nod = forStmt.getChildNodes().get(0);
                        List<Node> childNodes = forStmt.getChildNodes();
                        for (Node nodeInIfs : childNodes) {
                            if (nodeInIfs instanceof BlockStmt) {
                                List<Statement> listStatementOfIfBlock = ((BlockStmt) nodeInIfs).getStatements();
                                if(listStatementOfIfBlock.isEmpty()) {
                                    lineNumber = nodeInIfs.getRange().isPresent() ? nodeInIfs.getRange().get().begin.line : 0;
                                    bugs.addBug(file.toString(), lineNumber, "USELESS_CONTROL_FLOW");
                                }
                            }
                        }

                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    public void analyseForEach() {
        this.files.forEach(file -> {
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(ForeachStmt forStmt, Object arg) {
                        super.visit(forStmt, arg);
                        int lineNumber = 0;
                        Node nod = forStmt.getChildNodes().get(0);
                        List<Node> childNodes = forStmt.getChildNodes();

                        for (Node nodeInIfs : childNodes) {
                            if (nodeInIfs instanceof BlockStmt) {
                                List<Statement> listStatementOfIfBlock = ((BlockStmt) nodeInIfs).getStatements();
                                if(listStatementOfIfBlock.isEmpty()) {
                                    lineNumber = nodeInIfs.getRange().isPresent() ? nodeInIfs.getRange().get().begin.line : 0;
                                    bugs.addBug(file.toString(), lineNumber, "USELESS_CONTROL_FLOW");
                                }
                            }
                        }

                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    public void analyseWhile() {

        this.files.forEach(file -> {
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(WhileStmt whileStmt, Object arg) {
                        super.visit(whileStmt, arg);
                        int lineNumber = 0;
                        Node nod = whileStmt.getChildNodes().get(0);
                        List<Node> childNodes = whileStmt.getChildNodes();

                        for (Node nodeInIfs : childNodes) {
                            if (nodeInIfs instanceof BlockStmt) {
                                //System.out.println(childNodes);
                                List<Statement> listStatementOfIfBlock = ((BlockStmt) nodeInIfs).getStatements();
                                if(listStatementOfIfBlock.isEmpty()) {
                                    lineNumber = nodeInIfs.getRange().isPresent() ? nodeInIfs.getRange().get().begin.line : 0;
                                    bugs.addBug(file.toString(), lineNumber, "USELESS_CONTROL_FLOW");
                                }
                            }
                        }

                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void analyseDoWhile() {

        this.files.forEach(file -> {
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(DoStmt whileStmt, Object arg) {
                        super.visit(whileStmt, arg);
                        int lineNumber = 0;
                        Node nod = whileStmt.getChildNodes().get(0);
                        List<Node> childNodes = whileStmt.getChildNodes();

                        for (Node nodeInIfs : childNodes) {
                            if (nodeInIfs instanceof BlockStmt) {
                                List<Statement> listStatementOfIfBlock = ((BlockStmt) nodeInIfs).getStatements();
                                if(listStatementOfIfBlock.isEmpty()) {
                                    lineNumber = nodeInIfs.getRange().isPresent() ? nodeInIfs.getRange().get().begin.line : 0;
                                    bugs.addBug(file.toString(), lineNumber, "USELESS_CONTROL_FLOW");
                                }
                            }
                        }

                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
