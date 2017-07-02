import edu.duke.*;

public class CaesarCipher {
    private String alphabet; 
    private String shiftedAlphabet; 
    
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key); 
    }
    
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
       
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
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
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder newEncrypted = new StringBuilder(input); 
        for(int i=0; i <input.length(); i++) {
            char currChar = newEncrypted.charAt(i);
            String currCharString = Character.toString(currChar); 
            if(i%2 == 0){
                String newCharString = encrypt(currCharString, key1); 
                char newChar = newCharString.charAt(0); 
                newEncrypted.setCharAt(i, newChar);
            }
            if(i%2 != 0) {
                String newCharStringTwo = encrypt(currCharString, key2); 
                char newCharTwo = newCharStringTwo.charAt(0); 
                newEncrypted.setCharAt(i, newCharTwo);
            }
        }
        return newEncrypted.toString(); 
    }
    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is "+ key + "\n" + encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
    }
    
    public void testCaesarTwo() {
        String result = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!"
        , 15); 
        System.out.println(result); 
    }
    
    public void testEncryptTwoKeys(){
        String result = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21); 
        System.out.println(result); 
    }
}

