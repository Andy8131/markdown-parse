// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownParse {
    public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if(dirOrFile.isDirectory()) {
            for(File f: dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
            }
            return result;
        }
        else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if(lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            return result;
        }
    }
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {

            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket == -1){
                break;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket == -1){
                break;
            }

            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(openParen == -1){
                break;
            }
            int closeParen = markdown.indexOf(")", openParen);
            if(closeParen == -1){
                break;
            }
            //bug fix

            int closeParen2 = markdown.indexOf(")", closeParen+1);
            if(closeParen2-closeParen ==1){
                closeParen = closeParen2;
            }
            int counter = 1;
            for(int w = openParen; w <closeParen; w++){
                if(markdown.substring(openParen+counter,openParen+counter+1).equals("(")){
                    counter++;
                }
            } 

            if(counter > 1){
                openParen = openParen+counter-1;
            }                  
            currentIndex = closeParen + 1;  

            if(markdown.substring(openParen + 1, closeParen).contains(".")){

                if(openParen-nextCloseBracket == counter){
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
            }

            // System.out.println(currentIndex);
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
        File ourFile = new File(args[0]);
        if(ourFile.isDirectory()){
            // Map<String, List<String>> s = getLinks(ourFile);
            // s.values();
            for(String fileName: getLinks(ourFile).keySet()){
                System.out.println(fileName + getLinks(ourFile).get(fileName));
            }
            
        }
        else{
            Path fileName = Path.of(args[0]);
            String contents = Files.readString(fileName);
            ArrayList<String> links = getLinks(contents);
            System.out.println(links);
        };
    }

}
//for(($i-1); $i -lt 10; $i++){}