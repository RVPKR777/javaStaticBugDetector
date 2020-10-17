
package bug_Detector;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import com.github.javaparser.ast.expr.Expression;
import java.util.List;


public class UnnecessaryIfCondition {
    private final ArrayList<Path> files;
    private final BugList bugs;

    public UnnecessaryIfCondition(ArrayList<Path> file) {
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
        final int[] errorLine = {0};
        final boolean[] hashPresent = {false}, equalPresent = {false};

        this.files.forEach(file -> {
            equalPresent[0] = false;
            hashPresent[0] = false;
            errorLine[0] = 0;
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(IfStmt ifStmt, Object arg) {
                        super.visit(ifStmt, arg);
                        int lineNumber = 0;
                        Node nod = ifStmt.getChildNodes().get(0);
                        List<Node> childNodes = ifStmt.getChildNodes();
                        //List<Statement> ListStatementOfCatchBlock = ((BlockStmt) nodeInCatches).getStatements();

                        for (Node nodeInIfs : childNodes) {
                            if (nodeInIfs instanceof BlockStmt) {
                                //System.out.println(childNodes);
                                List<Statement> listStatementOfIfBlock = ((BlockStmt) nodeInIfs).getStatements();
                                if(listStatementOfIfBlock.isEmpty()) {
                                    lineNumber = nodeInIfs.getRange().isPresent() ? nodeInIfs.getRange().get().begin.line : 0;
                                    bugs.addBug(file.toString(), lineNumber, "Unnecessary If");
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
        final int[] errorLine = {0};
        final boolean[] hashPresent = {false}, equalPresent = {false};

        this.files.forEach(file -> {
            equalPresent[0] = false;
            hashPresent[0] = false;
            errorLine[0] = 0;
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
                                //System.out.println(childNodes);
                                List<Statement> listStatementOfIfBlock = ((BlockStmt) nodeInIfs).getStatements();
                                if(listStatementOfIfBlock.isEmpty()) {
                                    lineNumber = nodeInIfs.getRange().isPresent() ? nodeInIfs.getRange().get().begin.line : 0;
                                    bugs.addBug(file.toString(), lineNumber, "Unnecessary For");
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
        final int[] errorLine = {0};
        final boolean[] hashPresent = {false}, equalPresent = {false};

        this.files.forEach(file -> {
            equalPresent[0] = false;
            hashPresent[0] = false;
            errorLine[0] = 0;
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
                                //System.out.println(childNodes);
                                List<Statement> listStatementOfIfBlock = ((BlockStmt) nodeInIfs).getStatements();
                                if(listStatementOfIfBlock.isEmpty()) {
                                    lineNumber = nodeInIfs.getRange().isPresent() ? nodeInIfs.getRange().get().begin.line : 0;
                                    bugs.addBug(file.toString(), lineNumber, "Unnecessary For");
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
        final int[] errorLine = {0};
        final boolean[] hashPresent = {false}, equalPresent = {false};

        this.files.forEach(file -> {
            equalPresent[0] = false;
            hashPresent[0] = false;
            errorLine[0] = 0;
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
                                    bugs.addBug(file.toString(), lineNumber, "Unnecessary While");
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
        final int[] errorLine = {0};
        final boolean[] hashPresent = {false}, equalPresent = {false};

        this.files.forEach(file -> {
            equalPresent[0] = false;
            hashPresent[0] = false;
            errorLine[0] = 0;
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
                                //System.out.println(childNodes);
                                List<Statement> listStatementOfIfBlock = ((BlockStmt) nodeInIfs).getStatements();
                                if(listStatementOfIfBlock.isEmpty()) {
                                    lineNumber = nodeInIfs.getRange().isPresent() ? nodeInIfs.getRange().get().begin.line : 0;
                                    bugs.addBug(file.toString(), lineNumber, "Unnecessary While");
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
