
/**
 * This assignment is to write the code from the lesson to use a StorageResource to store the
 * genes you find instead of printing them out. This will help you see if you really understood
 * how to put the code together, and might identify a part that you did not fully understand.
 * If you get stuck, then you can go back and watch the coding videos that go with this lesson
 * again.
 * 
 * @author pablobou4
 * @version 22/06/2021
 */

import edu.duke.StorageResource;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;

public class Part1 {
    
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        // This method returns the index of the first occurrence of stopCodon that appears
        // past startIndex and is a multiple of 3 away from startIndex. 
        // If there is not such stopCodon, returns the length of the dna strand.

        // Find the index of the first occurrence of stopCodon after startIndex
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        // Look for a valid stopCodon until we find it or there are not more
        while (stopIndex != -1) {
            // Check if it is a multiple of 3, if not, search again from stopIndex
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            } else {
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }
        return dna.length(); // if there is no stopCodon
    }
    
    public String findGene(String dna) {
        // Find the index of the first occurrence of ATG
        int startIndex = dna.indexOf("ATG");
        // If there is no occurrence, return the empty string
        if (startIndex == -1) {
            return "";
        }
        // Find the index of the first occurrence of TAA
        int indTAA = findStopCodon(dna, startIndex, "TAA");
        // Find the index of the first occurrence of TAG
        int indTAG = findStopCodon(dna, startIndex, "TAG");
        // Find the index of the first occurrence of TGA
        int indTGA = findStopCodon(dna, startIndex, "TGA");
        // Return the gene formed from ATG and the closest valid stopCodon
        int tmpMin = Math.min(indTAA, indTAG);
        int indMin = Math.min(tmpMin, indTGA);
        if (indMin == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, indMin + 3);
    }
    
    
    public StorageResource getAllGenes(String dna) {
        // Create an empty StorageResource, call it geneList
        StorageResource geneList = new StorageResource();
        // Create auxDNA 
        String auxDNA = dna;
        // Set startIndex to 0
        int startIndex = 0;
        // create count variable to print
        int count = 0; 
        // Repeat the following steps
        while (true) {
            // Find the next gene after startIndex
            String currGene = findGene(auxDNA);
            // If no gene was found,leave this loop
            if (currGene.isEmpty()) {
                break;
            }
            // Add that gene to geneList
            geneList.add(currGene);
            // print count
            count += 1;
            //System.out.println("Gene #" + count);
            // Set startIndex to just past the end of the gene
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            // Consider only the dna string after startIndex
            auxDNA = dna.substring(startIndex+1);
        }
        // Answer is geneList
        return geneList;
    }
    
    
    
    public void testOn(String dna) {
        // Method to test getAllGenes
        System.out.println("dna string: " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
        }
    }
    
    public void test() {
    //testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
    
    
    public float cgRatio(String dna) {
        // This method returns the ratio of C’s and G’s in dna as a fraction of the entire 
        // strand of DNA. For example if the String were “ATGCCATAG,” then cgRatio would 
        // return 4/9 or .4444444.
        // Init count variable as 0
        int count = 0;
        // Set startIndex to 0
        int startIndex = 0;
        // Create tmp variables to store the index of next C and G
        int tmpInd = 0;
        // As long as there are more C's or G's, keep looking for more
        while (true) {
            // Set tmpInd to next C occurrence
            tmpInd = dna.indexOf("C", startIndex);
            // Check if there is any G occurrence before the C one
            if (dna.indexOf("G", startIndex) <= tmpInd) {
                tmpInd = dna.indexOf("G", startIndex);
            }
            // If there is no occurrence, get out of the loop
            if (tmpInd == -1) {
                break;
            }
            // Add +1 to count
            count += 1;
            // Update startIndex to tmpInd + 1
            startIndex = tmpInd + 1;            
        }
        return (float) count / dna.length();
    }
    
    public void testCG() {
        String dna = "CGAHCGAKCCKOGC";
        System.out.println(cgRatio(dna));
    }
    
    public int countCTG(String dna) {
       // Count CTG occurrences in string
       // Init count variable to 0
       int count = 0;
       // Set startIndex to 0
       int startIndex = 0;
       // Create aux variable tmpInd 
       int tmpInd = 0;
       // As long as there are more CTG occurrences, keep looking for more
       while (true) {
           // Set tmpInd to next CTG occurrence
           tmpInd = dna.indexOf("CTG", startIndex);
           // If there is no occurrence, break
           if (tmpInd == -1) {
               break;
           }
           // Add +1 to count
           count += 1;
           // Update startIndex to tmpInd + 3
           startIndex = tmpInd + 3;
       }
       // return the count
       return count;
    }
    
    public void testCountCTG() {
        String dna = "CTGAJAJAJCTGCTG";
        System.out.println(countCTG(dna));
    }
    
    public void processGenes(StorageResource sr) {
        // This method processes all the strings in sr to find out information about them.
        // print all the Strings in sr that are longer than 9 characters
        // print the number of Strings in sr that are longer than 9 characters
        // print the Strings in sr whose C-G-ratio is higher than 0.35
        // print the number of strings in sr whose C-G-ratio is higher than 0.35
        // print the length of the longest gene in sr
        
        // Init some variables
        int count9 = 0;
        int countCG = 0;
        String longestGene = "";
        // Iter through sr
        for (String g: sr.data()) {
            // print all the Strings in sr that are longer than 9 characters
            // and add +1 to count9
            if (g.length() > 60) {
                //System.out.println(g + " has more than 60 characters.");
                count9 += 1;
            }
            // print the Strings in sr whose C-G-ratio is higher than 0.35
            // and add +1 to countCG
            if (cgRatio(g) > 0.35) {
                //System.out.println(g + " has CG-ratio greater than 0.35.");
                countCG += 1;
            }
            // Update longestGene if g is greater than current longestGene
            if (g.length() > longestGene.length()) {
                longestGene = g;
            }
        }
        // print the number of Strings in sr that are longer than 9 characters
        System.out.println("Number of strings longer than 60 characters: " + count9);
        // print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("Number of strings with CG-rate > 0.35: " + countCG);
        // print the length of the longest gene in sr
        System.out.println("Longest gene in sr: " + longestGene);
        // print length of longest gene
        System.out.println("Lenght of longest gene: " + longestGene.length());
        
    }
    
    public void testProcessGenesOn(String dna) {
        //Write code in testProcessGenes to call processGenes five times with StorageResources
        //made from each of your five DNA string test cases.

        // Create StorageResource object
        StorageResource sr = new StorageResource();
        // Add a dna string to it
        sr.add(dna);
        // Call processGenes
        processGenes(sr);
        }
        
    public void testProcessGenes() {
        System.out.println("Longer than 9 characters");
        testProcessGenesOn("ABDEFGHIJL");
        System.out.println("Smaller than 9 characters");
        testProcessGenesOn("ABDEFGH");
        System.out.println("CG-ratio higher than 0.35");
        testProcessGenesOn("ABDEFGHCG");
        System.out.println("CG-ratio smaller than 0.35");
        testProcessGenesOn("ABDEFGHddDDD");
        
    }
    
    public void frTestProcessGenesOn() {
        // Modify the method testProcessGenes to call processGenes with a StorageResource of
        // the genes found in the file brca1line.fa
        // Create StorageResource object
        StorageResource sr = new StorageResource();
        // Load file
        FileResource fr = new FileResource("./dna/GRch38dnapart.fa");
        // Transform to string
        String dna = fr.asString();
        // Transform to upperCase
        dna = dna.toUpperCase();
        // Print number of ctg occurrences in dna
        System.out.println("Number of CTG occurrences: " + countCTG(dna));
        //System.out.println(dna);
        // Find the genes in dna and add them to sr
        sr = getAllGenes(dna);
        // Call processGenes
        processGenes(sr);
        }
    
}
