
/**
 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.StorageResource;
public class Class1 {
    
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
            // Set startIndex to just past the end of the gene
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            // Consider only the dna string after startIndex
            auxDNA = dna.substring(startIndex);
        }
        // Answer is geneList
        return geneList;
    }
    
    public void testOn(String dna) {
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

}
