import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {

    @Test
    public void testFiler1() throws IOException{
        String contents= Files.readString(Path.of("./test-filer1.md"));
        List<String> expect = List.of("`google.com","google.com","ucsd.edu");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testFiler2() throws IOException{
        String contents= Files.readString(Path.of("./test-filer2.md"));
        List<String> expect = List.of("a.com","a.com(())","example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }    

    @Test
    public void testFiler3() throws IOException{
        String contents= Files.readString(Path.of("./test-filer3.md"));
        List<String> expect = List.of("https://ucsd-cse15l-w22.github.io/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }


}
