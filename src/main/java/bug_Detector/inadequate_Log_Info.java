
package bug_Detector;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
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


/**
 * Class used to find duplicate logging information in catch block
 */

public class inadequate_Log_Info {
    private final ArrayList<Path> files;
    private final BugList bugs;
    
    public inadequate_Log_Info(ArrayList<Path> file) {
        this.bugs = BugList.getInstance();
        this.files = file;
    }
    
    public void analyse() {
        
        this.files.forEach(file -> {
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

                                                    if (isBugPresent) {
                                                        int lineNumber = method.getRange().isPresent() ? method.getRange().get().begin.line : 0;
                                                        bugs.addBug(file.toString(), lineNumber, "INADEQUATE_LOGGING_INFO_IN_CATCH_BLOCKS");
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
