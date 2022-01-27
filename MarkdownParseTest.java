import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class MarkdownParseTest {

    // private List<List<String>> results;
    // private List<String> testFiles;


    // public void init() {
    //     testFiles = new ArrayList<>();
    //     testFiles = Arrays.asList("test-file0.md", "test-file1.md");
    //     results = new ArrayList<>();
    // }


    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }
    
    // @Test
    // public void readFile(){
    //     // ArrayList<String> result = MarkdownParse.getLinks(Files.readString(Path.of("test-file1.md")));
    //     try{
    //         String toReturn = Files.readString(Path.of("./test-file.md"));
    //         fail();
    //     }catch(Exception e){
    //         //pass
    //     }
    // }

    @Test
    public void test1()throws IOException{
        Path fileName = Path.of("test-file1.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        System.out.println(links);
        // [https://something.com, some-page.html]
        String expected =  "[https://something.com, some-page.html]";
        assertEquals(expected, links);
    }
}