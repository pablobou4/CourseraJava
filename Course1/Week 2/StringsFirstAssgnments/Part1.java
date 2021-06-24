
/**
 * Part 1 of the exercise of Week 2 of Coursera's Java Programming course
 * 
 * @author Pablo Boullosa
 * @version April 2021
 */
public class Part1 {
    
    public String findSimpleGene(String g) {
        // Find the index position of the start codon ATG
        int initInd = g.indexOf("ATG");
        // If there is no ATG, return the empty string
        if (initInd == -1) {
            return "";
        }
        // Find the index position of the end codon TAA
        int endInd = g.indexOf("TAA", initInd + 3);
        // If there is no TAA, return the empty string
        if (endInd == -1) {
            return "";
        }
        // If the length of the substring between ATG and TAA is a multiple of 3,
        // return that substring
        if ((endInd + 3 - initInd) % 3 != 0) {
            return "";
        }
        String simpleGene = g.substring(initInd, endInd + 3);
        return simpleGene;
    }
    
    public void testSimpleGene() {
        // DNA str with no ATG
        String gene = "ATATATCTACTAA";
        String simpleGene = findSimpleGene(gene);
        System.out.println("Simple gene from " + gene + ": " + simpleGene);
        // DNA with no TAA
        gene = "ATATATGTCTAC";
        simpleGene = findSimpleGene(gene);
        System.out.println("Simple gene from " + gene + ": " + simpleGene);
        // DNA with no ATG or TAA
        gene = "ATATATTCTAC";
        simpleGene = findSimpleGene(gene);
        System.out.println("Simple gene from " + gene + ": " + simpleGene);
        // DNA with ATG and TAA and the substring between them is a multiple of 3
        gene = "ATGATGTAACTAC";
        simpleGene = findSimpleGene(gene);
        System.out.println("Simple gene from " + gene + ": " + simpleGene);
        // DNA with ATG and TAA and the substring between them is not a multiple of 3
        gene = "ATGATAATGTCTAAAC";
        simpleGene = findSimpleGene(gene);
        System.out.println("Simple gene from " + gene + ": " + simpleGene);
    }
    
    public static void main (String[] args) {
        Part1 p1 = new Part1();
        p1.testSimpleGene();
    }    
}
