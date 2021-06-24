
/**
 * This assignment is to write the code from the lesson to make the following improvements to your algorithm:

 * A. Find a gene in a strand of DNA where the stop codon could be any of the three stop codons “TAA”, “TAG”, or “TGA”.
 * B. Find all the genes (where the stop codon could be any of the three stop codons) in a strand of DNA.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
    
    public void testFindStopCodon () {
        String stopCodon = "TAA";
        //                ^   ^   ^   ^   ^
        String dna = "ATATATGATATATTGATAAGAA";
        int startIndex = dna.indexOf("ATG");
        System.out.println(findStopCodon(dna, startIndex, stopCodon));
        dna = "ATATATGATATATTGATAGGAA";
        startIndex = dna.indexOf("ATG");
        System.out.println(findStopCodon(dna, startIndex, stopCodon));
    }
    
    public void testFindGene() {
        // No ATG
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA string 1: " + dna);
        System.out.println("Gene: " + findGene(dna));
        // ATG and TAA
        dna = "ATATATGATATATTGATAAGAA";
        System.out.println("DNA string 2: " + dna);
        System.out.println("Gene: " + findGene(dna));
        // ATG and TAA, TGA, ATG
        dna = "ATATATGATATATTGATAAGAAATG";
        System.out.println("DNA string 3: " + dna);
        System.out.println("Gene: " + findGene(dna));
        // ATG and no stopCodon
        dna = "ATATATGATATATTATAAGAA";
        System.out.println("DNA string 4: " + dna);
        System.out.println("Gene: " + findGene(dna));
        // gene equal to dna
        dna = "ATGATTATTATTTAA";
        System.out.println("DNA string 5: " + dna);
        System.out.println("Gene: " + findGene(dna));
        
    }
    
    public void printAllGenes(String dna) {
        // Print all genes in the dna string
        //String dna = "ATGHHHJJJKKKTAAJJJKKKHHHATGJJJKKKSSSTGACCCEEEATG"
        // Set startIndex to 0
        int startIndex = 0;
        // Repeat the following steps
        while (true) {
            // Find the next gene after startIndex
            String currGene = findGene(dna);
            // If no gene was found,leave this loop
            if (currGene.isEmpty()) {
                break;
            }
            // Print that gene out
            System.out.println(currGene);
            // Set startIndex to just past the end of the gene
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            // Consider only the dna string after startIndex
            dna = dna.substring(startIndex);
        }
        
    }

}
