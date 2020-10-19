
package bug_Detector;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Method to find classes with hashcode but not equals
 */

public class hashcode_No_Equals {
    private final ArrayList<Path> files;
    private final BugList bugs;
    private final String equalBugType = "HE_HASHCODE_NO_EQUALS";
    
    public hashcode_No_Equals(ArrayList<Path> file) {
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
                    public void visit(MethodDeclaration methodDeclaration, Object args) {
                        super.visit(methodDeclaration, args);
                        if (methodDeclaration.getNameAsString().equals("hashCode") &&
                                methodDeclaration.getTypeAsString().equals("int") &&
                                methodDeclaration.getParameters().isEmpty()) {
                            hashPresent[0] = true;
                            errorLine[0] = (methodDeclaration.getRange().isPresent() ? methodDeclaration.getRange().get().begin.line : 0);
                        }
                        else if (methodDeclaration.getNameAsString().equals("equals") && methodDeclaration.getTypeAsString().equals("boolean")) {
                            NodeList<Parameter> nodes = methodDeclaration.getParameters();
                            if ((nodes.size() == 1) && (nodes.get(0).getTypeAsString().equals("Object"))) {
                                equalPresent[0] = true;

                            }
                        }
                        else{

                        }
                    }
                }.visit(JavaParser.parse(file), null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            if (equalPresent[0] != true && hashPresent[0] == true) {
                bugs.addBug(file.toString(), errorLine[0], this.equalBugType);
            }
        });
    }
}
