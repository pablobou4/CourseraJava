
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        // Check if the dna string is either all uppercase or all lowercase
        if (dna == dna.toUpperCase()) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else if (dna == dna.toLowerCase()) {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        } else {
            return "";
        }
        // Find the index position of the start codon ATG
        int initInd = dna.indexOf(startCodon);
        // If there is no ATG, return the empty string
        if (initInd == -1) {
            return "";
        }
        // Find the index position of the end codon TAA
        int endInd = dna.indexOf(stopCodon, initInd + 3);
        // If there is no TAA, return the empty string
        if (endInd == -1) {
            return "";
        }
        // If the length of the substring between ATG and TAA is a multiple of 3,
        // return that substring
        if ((endInd + 3 - initInd) % 3 != 0) {
            return "";
        }
        String simpleGene = dna.substring(initInd, endInd + 3);
        return simpleGene;
    }
    
    public void testSimpleGene() {
        // DNA str with no ATG
        String dna = "gatgctataat";
        String startCodon = "atg";
        String stopCodon = "taa";
        String simpleGene = findSimpleGene(dna, startCodon, stopCodon);
        //System.out.println("Simple gene from " + dna + ": " + simpleGene);
        // DNA with no TAA
        dna = "ATGGGTTAAGTC";
        simpleGene = findSimpleGene(dna, startCodon, stopCodon);
        //System.out.println("Simple gene from " + dna + ": " + simpleGene);
        // DNA with no ATG or TAA
        dna = "ATATATTCTAC";
        simpleGene = findSimpleGene(dna, startCodon, stopCodon);
        //System.out.println("Simple gene from " + dna + ": " + simpleGene);
        // DNA with ATG and TAA and the substring between them is a multiple of 3
        dna = "ATGtgaatcTAACTAC";
        simpleGene = findSimpleGene(dna, startCodon, stopCodon);
        //System.out.println("Simple gene from " + dna + ": " + simpleGene);
        // DNA with ATG and TAA and the substring between them is not a multiple of 3
        dna = "ATGATAATGTCTAAAC";
        simpleGene = findSimpleGene(dna, startCodon, stopCodon);
        //System.out.println("Simple gene from " + dna + ": " + simpleGene);
        dna = "AAATGCCCTAACTAGATTAAGAAACCTAAAC";
        simpleGene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Simple gene from " + dna + ": " + simpleGene);
    }
    
    public static void main (String[] args) {
        Part2 p1 = new Part2();
        p1.testSimpleGene();
    }    
}
