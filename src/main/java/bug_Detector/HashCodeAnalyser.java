
package bug_Detector;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


public class HashCodeAnalyser {
    private final ArrayList<Path> files;
    private final BugList bugs;
    private final String equalBugType = "Equals";
    
    public HashCodeAnalyser(ArrayList<Path> file) {
        this.bugs = BugList.getInstance();
        this.files = file;
    }
    
    public void analyse() {
        final int[] errorLine = {0};
        final boolean[] hashPresent = {false}, equalPresent = {false};
        
        this.files.forEach((Path file) -> {
            equalPresent[0] = false;
            hashPresent[0] = false;
            errorLine[0] = 0;
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(MethodDeclaration mthodDeclaration, Object args) {
                        super.visit(mthodDeclaration, args);
                        if (mthodDeclaration.getNameAsString().equals("equals") && mthodDeclaration.getTypeAsString().equals("boolean")) {
                            NodeList<Parameter> nodes = mthodDeclaration.getParameters();
                            if ((nodes.size() == 1) && (nodes.get(0).getTypeAsString().equals("Object"))) {
                                equalPresent[0] = true;

                            }
                        } else if (mthodDeclaration.getNameAsString().equals("hashCode") &&
                                mthodDeclaration.getTypeAsString().equals("int") &&
                                mthodDeclaration.getParameters().isEmpty()) {
                                    hashPresent[0] = true;
                                    errorLine[0] = (mthodDeclaration.getRange().isPresent() ? mthodDeclaration.getRange().get().begin.line : 0);
                        }
                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            if (!equalPresent[0] && hashPresent[0]) {
                bugs.addBug(file.toString(), errorLine[0], this.equalBugType);
            }
        });
    }
}
