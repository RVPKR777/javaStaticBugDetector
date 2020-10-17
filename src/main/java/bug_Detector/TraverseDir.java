/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bug_Detector;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.function.BiPredicate;


public class TraverseDir {
    private final String directory;
    private final ArrayList<Path> files;
    TraverseDir(String dir) {
        this.directory = dir;
        files = new ArrayList<>();
    }
    
    public ArrayList<Path> getFiles() throws IOException {
        Files.find(Paths.get(this.directory), Integer.MAX_VALUE, new BiPredicate<Path, BasicFileAttributes>() {
            @Override
            public boolean test(Path filePath, BasicFileAttributes fileAttr) {
                return fileAttr.isRegularFile() && filePath.getFileName().toString().endsWith(".java");
            }
        }).forEach(path -> 
                this.performOperation(path)
        );
        return this.files;
    }
    
    private void performOperation(Path path) {
        this.files.add(path);
    }
}
