
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        // This function checks if stringa appears more than once in stringb
        // Create the result variable
        boolean boolTwoOcc = true;
        // Check the first occurrence of stringa in stringb
        int occ1 = stringb.indexOf(stringa);
        // Check the second occurrence of stringa in stringb
        int occ2 = stringb.indexOf(stringa, occ1 + 1);
        // If any of them is -1 (no occurrence, return False). Otherwise, True
        if ((occ1 == -1) || (occ2 == -1)) {
            boolTwoOcc = false;
        }
        return boolTwoOcc;
    }
    
    public String lastPart(String stringa, String stringb) {
        // Find the first occurrence of stringa in stringb
        int occ1 = stringb.indexOf(stringa);
        // If there is no occurrence, return stringb
        if (occ1 == -1) {
            return stringb;
        } else {
            // Take the part of stringb that follows stringa
            String finalPart = stringb.substring(occ1 + stringa.length());
            return finalPart;
        }
    }
    
    public void testing() {
        String stringa = "zoo";
        String stringb = "forest";
        System.out.println(lastPart(stringa, stringb));
    }
   

}
