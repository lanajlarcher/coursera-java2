import edu.duke.*;

/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch); 
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
            return true; 
        }
        else {
            return false; 
        }
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder rv = new StringBuilder(phrase); 
        for (int i=0; i < phrase.length(); i++){
            char currChar = phrase.charAt(i);
            int idx = phrase.indexOf(currChar); 
            boolean vowel = isVowel(currChar);
            if (vowel == true) {
                rv.setCharAt(i, ch); 
            }
        }
        return rv.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        ch = Character.toLowerCase(ch); 
        StringBuilder newPhrase = new StringBuilder(phrase); 
        for (int i=0; i < phrase.length(); i++){
            char currChar = phrase.charAt(i);
            currChar = Character.toLowerCase(currChar); 
            int idx = phrase.indexOf(currChar); 
            if (currChar == ch && i%2 == 0){
                //do stuff * 
                newPhrase.setCharAt(i, '*'); 
            }
            if (currChar == ch && i%2 != 0){
                //do other stuff + 
                newPhrase.setCharAt(i, '+'); 
            }
        }
        return newPhrase.toString(); 
    }
    
    public void testIsVowel(){
        boolean result = isVowel('g'); 
        System.out.println(result); 
    }
    
    public void testReplaceVowels() {
        String result = replaceVowels("Hello World", '*'); 
        System.out.println(result); 
    }
    
    public void testEmphasize() {
        String result = emphasize("Mary Bella Abracadabra", 'a'); 
        System.out.println(result); 
    }
}
