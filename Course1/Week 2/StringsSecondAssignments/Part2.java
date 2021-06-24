
/**
 * This assignment will write a method to determine how many occurrences of a string appear in 
 * another string.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    public int howMany(String stringa, String stringb) {
        // This method returns an integer indicating how many times stringa appears in stringb,
        // where each occurrence of stringa must not overlap with another occurrence of it
        //
        // Init the count variable as 0
        int count = 0;
        int startIndex = 0;
        int currIndex = 0;
        // Loop until there are no more occurrences
        while (true) {
            // Compute currIndex, where stringa is in stringb
            currIndex = stringb.indexOf(stringa, startIndex);
            // If there are no occurrences, get out of the loop
            if (currIndex == -1) {
                break;
            // If there are, add +1 to the count and update startIndex
            } else {
                count += 1;
                startIndex = currIndex + stringa.length();
            }
        }
        return count;
    }
    
    public void testHowMany() {
        String stringa = "AA";
        String stringb = "ATAAAA";
        System.out.println(howMany(stringa, stringb));
    }
}
