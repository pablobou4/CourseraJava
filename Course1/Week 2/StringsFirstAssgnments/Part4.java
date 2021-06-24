
/**
 *  This program reads the lines from the file at this URL location, 
 *  http://www.dukelearntoprogram.com/course2/data/manylinks.html, 
 *  and prints each URL on the page that is a link to youtube.com.
 *  Assume that a link to youtube.com has no spaces in it and would be 
 *  in the format (where [stuff] represents characters that are not verbatim): 
 *  “http:[stuff]youtube.com[stuff]”

 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {

    // Create a void method which checks if a word in an url contains a link from YouTube
    // If it does, print the link
    public void printYoutubeLinks(String sURL) {
        // Create url variable
        URLResource url = new URLResource(sURL);
        // For each word, check if "youtube.com" is in 
        for (String word : url.words()) {
            // Check if it is a yt link
            
            if (word.toLowerCase().indexOf("youtube.com") != -1) {
                int initInd = word.indexOf("\"");
                int endInd = word.indexOf("\"", initInd+1);
                System.out.println(word.substring(initInd+1, endInd));
            }
        }

    }

    public void testURL() {
        String ss = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        // Call the method that prints youtube links in the url
        printYoutubeLinks(ss);
    }
    
    public static void main() {
        Part4 sURL = new Part4();
        sURL.testURL();
    }
    
    public String mystery(String dna) {
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
        if (pos == -1) {
        return dna;
        }
        while (count < 3) {
        count += 1;
        newDna = newDna + dna.substring(startPos,pos);
        startPos = pos+1;
        pos = dna.indexOf("T", startPos);
        if (pos == -1) {
          break;
        }
        }
        newDna = newDna + dna.substring(startPos);
        return newDna;
    }

}
