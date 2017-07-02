import edu.duke.*; 

/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet; 
    private String shiftedAlphabet1; 
    private String shiftedAlphabet2;
    private int mainKey1; 
    private int mainKey2; 
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        mainKey1 = key1; 
        mainKey2 = key2; 
        shiftedAlphabet1 = alphabet.substring(mainKey1) + alphabet.substring(0, mainKey1);
        shiftedAlphabet2 = alphabet.substring(mainKey2) + alphabet.substring(0, mainKey2);
    }
    
    public String encryptTwoKeys(String input) {
        StringBuilder newEncrypted = new StringBuilder(input); 
        for(int i=0; i <input.length(); i++) {
            char currChar = newEncrypted.charAt(i);
            String currCharString = Character.toString(currChar); 
            if(i%2 == 0){
                CaesarCipherOo cc = new CaesarCipherOo (mainKey1);
                String newCharString = cc.encrypt(currCharString); 
                char newChar = newCharString.charAt(0); 
                newEncrypted.setCharAt(i, newChar);
            }
            if(i%2 != 0) {
                CaesarCipherOo cc2 = new CaesarCipherOo(mainKey2); 
                String newCharStringTwo = cc2.encrypt(currCharString); 
                char newCharTwo = newCharStringTwo.charAt(0); 
                newEncrypted.setCharAt(i, newCharTwo);
            }
        }
        return newEncrypted.toString(); 
    }
    
    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder(); 
        for (int i = start; i < message.length(); i+=2){
            char ch = message.charAt(i); 
            sb.append(ch);
        }
        return sb.toString();
    }
    
    public String decrypt(String encrypted){
        String even = halfOfString(encrypted, 0); 
        String odd = halfOfString(encrypted, 1); 
        
        CaesarCipherOo cc = new CaesarCipherOo(mainKey1); 
        String evenDecrypted = cc.decrypt(even); 
        CaesarCipherOo cc2 = new CaesarCipherOo(mainKey2); 
        String oddDecrypted = cc2.decrypt(odd); 
        
        StringBuilder wholeDecrypted = new StringBuilder(); 
        int evenLength = evenDecrypted.length(); 
        int oddLength = oddDecrypted.length(); 
        for(int i = 0; i < oddLength; i++) {
            char chEven = evenDecrypted.charAt(i); 
            char chOdd = oddDecrypted.charAt(i); 
            //first we want to get the first char out of evenDecrypted and append it to a new string
            wholeDecrypted.append(chEven);
            wholeDecrypted.append(chOdd);
            //then we want to get the first char out of oddDecrypted and append it to the new string
            //we want to continue alternating between even/odd until the end of each string is reached 
        }
        if (evenLength > oddLength) {
            int lastPos = evenDecrypted.length() - 1;
            char lastCh = evenDecrypted.charAt(lastPos);
            wholeDecrypted.append(lastCh);
        }
        return wholeDecrypted.toString(); 
    }

}
