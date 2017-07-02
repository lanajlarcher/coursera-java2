import edu.duke.*;

/**
 * Write a description of WordLengths here.
 * 
 * @author Lana 
 * @version (a version number or a date)
 */
public class WordLengths {
    
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            //count word length
            int length = word.length();
            StringBuilder sb = new StringBuilder(word); 
            
            char firstChar = sb.charAt(0);
            char lastChar = sb.charAt(length - 1); 
            //if the first letter of the word is not a letter, subtract one from the length
            if (!Character.isLetter(firstChar)) {
                length = length - 1; 
            }
            //if the last letter of the word is not a letter, subtact one from the length
            if (!Character.isLetter(lastChar)){
                length = length - 1; 
            }
            if (length < 0){
                length = 0; 
            }
            if (length > counts.length){
                length = counts.length - 1;
            }
            //increment count at index length
            counts[length] = counts[length] + 1; 
        }
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource(); 
        int[] counts = new int[31]; 
        countWordLengths(fr, counts);
        for(int k = 0; k < counts.length; k++){
            System.out.println("Number of words that are " + k + " long = " + counts[k]);  
        }
    }

}
