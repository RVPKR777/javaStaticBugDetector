/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bug_Detector;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import java.util.List;


public class UnnecessaryIfCondition {
    private final ArrayList<Path> files;
    private final BugList bugs;
    
    public UnnecessaryIfCondition(ArrayList<Path> file) {
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
                    public void visit(IfStmt ifStmt, Object arg) {
                        super.visit(ifStmt, arg);
                        int lineNumber = 0;
                        Node nod = ifStmt.getChildNodes().get(0);
                        List<Node> childNodes = ifStmt.getChildNodes();
                        //List<Statement> ListStatementOfCatchBlock = ((BlockStmt) nodeInCatches).getStatements();
                       
                       for (Node nodeInIfs : childNodes) {
                        if (nodeInIfs instanceof BlockStmt) {
                            System.out.println(childNodes);
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
}
