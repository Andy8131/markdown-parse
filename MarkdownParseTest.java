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
        List<String> expect = List.of("another link`","cod[e","code]");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testFiler2() throws IOException{
        String contents= Files.readString(Path.of("./test-filer1.md"));
        List<String> expect = List.of("nested link","a nested parenthesized url",
        "some escaped [ brackets ]");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }    

    @Test
    public void testFiler3() throws IOException{
        String contents= Files.readString(Path.of("./test-filer1.md"));
        List<String> expect = List.of("this title text is really long and takes up more than one line");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }


}
