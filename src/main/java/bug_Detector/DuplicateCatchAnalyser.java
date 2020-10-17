/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bug_Detector;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class DuplicateCatchAnalyser {
    private final ArrayList<Path> files;
    private final BugList bugs;
    
    public DuplicateCatchAnalyser(ArrayList<Path> file) {
        this.bugs = BugList.getInstance();
        this.files = file;
    }
    
    public void analyse() {
        final int[] errorLine = {0};
        final boolean[] hashPresent = {false}, equalPresent = {false};
        
        this.files.forEach(file -> {
            equalPresent[0] = false;
            hashPresent[0] = false;
            errorLine[0] = 0;
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(TryStmt tryStmt, Object arg) {
                        super.visit(tryStmt, arg);
                        ArrayList<String> arrLst = new ArrayList<>();

                        for (CatchClause ctchClause : tryStmt.getCatchClauses()) {
                            List<Node> childNodes = ctchClause.getChildNodes();
                            for (Node nodeInCatches : childNodes) {

                                if (nodeInCatches instanceof BlockStmt) {
                                    List<Statement> ListStatementOfCatchBlock = ((BlockStmt) nodeInCatches).getStatements();
                                    
                                    for (Statement statementOfCatchBlock : ListStatementOfCatchBlock) {
                                        List<Node> listNodeOfOneBlockStatementOfEachCatchBlock = statementOfCatchBlock.getChildNodes();

                                        for (Node nodeOfOneBlockStatementOfEachCatchBlock : listNodeOfOneBlockStatementOfEachCatchBlock) {
                                            if (nodeOfOneBlockStatementOfEachCatchBlock instanceof MethodCallExpr) {
                                                MethodCallExpr method = ((MethodCallExpr) nodeOfOneBlockStatementOfEachCatchBlock);
                                                String methodName = method.getNameAsString();
                                                boolean isBugPresent = false;
                                                if (methodName.matches("warn|println|info|debug|error")) {
                                                    if (method.getArguments().size() == 1 &&
                                                        method.getArgument(0) instanceof StringLiteralExpr) {
                                                        String args = method.getArgument(0).toString();

                                                        if (!(arrLst.contains(args))) {
                                                            arrLst.add(args);
                                                        } else {
                                                            isBugPresent = true;
                                                        }
                                                    }

                                                    // Report the bug if need be
                                                    if (isBugPresent) {
                                                        int lineNumber = method.getRange().isPresent() ? method.getRange().get().begin.line : 0;
                                                        bugs.addBug(file.toString(), lineNumber, "Duplicate Catch");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        arrLst.clear();

                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
