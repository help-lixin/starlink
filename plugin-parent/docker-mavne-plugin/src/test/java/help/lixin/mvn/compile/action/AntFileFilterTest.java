package help.lixin.mvn.compile.action;

import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Reference;
import org.junit.Test;

import java.io.File;

public class AntFileFilterTest {

    @Test
    public void testFilter() throws Exception {
        String dir = "/tmp/583871138";
        String[] includes = {"/target/*.jar"};
        DirectoryScanner ds = new DirectoryScanner();
        ds.setIncludes(includes);
        ds.setBasedir(new File(dir));
        ds.setCaseSensitive(true);
        ds.scan();
        System.out.println("FILES:");
        String[] files = ds.getIncludedFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
        System.out.println();
    }
}
