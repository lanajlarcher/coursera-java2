import edu.duke.*; 

/**
 * Write a description of CaesarCipherOo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherOo {
    private String alphabet; 
    private String shiftedAlphabet;
    private int mainKey; 
    
    public CaesarCipherOo(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key); 
        mainKey = key; 
    }
    
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            char upperChar = Character.toUpperCase(currChar);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(upperChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                if (Character.isLowerCase(currChar)) {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else { // upper
                    encrypted.setCharAt(i, newChar);
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String decrypt(String encrypted){
        CaesarCipherOo cc = new CaesarCipherOo(26 - mainKey);
        //int dkey = getKey(encrypted);
        //System.out.println("key: " + dkey);
        System.out.println("Decrypt key: "+ (26 - mainKey)); 
        return cc.encrypt(encrypted); 

    }
    
    private int[] countOccurrences(String input){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k = 0; k < input.length(); k++){
            char ch = Character.toLowerCase(input.charAt(k));
            int dex = alpha.indexOf(ch); 
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    private int getKey(String s) {
        int[] freqs = countOccurrences(s); 
        int maxDex = maxIndex(freqs); 
        int dkey = maxDex - 4; 
        if(maxDex < 4){
            dkey = 26 - (4 - maxDex); 
        }
        return dkey;
    }
    
    private int maxIndex(int[] freqs){
        int maxIndex = 0;
        for(int k = 1; k < freqs.length; k++){
            if(freqs[k] > freqs[maxIndex]) {
                maxIndex = k;
            }
        } 
        return maxIndex; 
    }
}
    

