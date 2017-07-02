import edu.duke.*;

/**
 * Write a description of DecryptCaesar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DecryptCaesar {
    
    public int[] countOccurrences(String input){
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
    
    public int getKey(String s) {
        int[] freqs = countOccurrences(s); 
        int maxDex = maxIndex(freqs); 
        int dkey = maxDex - 4; 
        if(maxDex < 4){
            dkey = 26 - (4 - maxDex); 
        }
        return dkey;
    }
    
    public String decrypt(String encrypted, int key){
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(encrypted, 26 - key); 
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int dkey = getKey(encrypted);
        System.out.println("key: " + dkey);
        return cc.encrypt(encrypted, 26 - dkey); 
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder(); 
        for (int i = start; i < message.length(); i+=2){
            char ch = message.charAt(i); 
            sb.append(ch);
        }
        return sb.toString();
    }
    
    public String decrypt(String encrypted, int keyOne, int keyTwo){
        String even = halfOfString(encrypted, 0); 
        String odd = halfOfString(encrypted, 1); 
        
        String evenDecrypted = decrypt(even, keyOne); 
        String oddDecrypted = decrypt(odd, keyTwo); 
        
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
    
    public String decryptTwoKeys(String encrypted){
        String even = halfOfString(encrypted, 0); 
        String odd = halfOfString(encrypted, 1); 
        
        int evenKey = getKey(even);
        int oddKey = getKey(odd);
        
        String evenDecrypted = decrypt(even, evenKey); 
        String oddDecrypted = decrypt(odd, oddKey); 
        
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
    
    public int maxIndex(int[] freqs){
        int maxIndex = 0;
        for(int k = 1; k < freqs.length; k++){
            if(freqs[k] > freqs[maxIndex]) {
                maxIndex = k;
            }
        } 
        return maxIndex; 
    }
    
    public void testDecryptWithTwoKeys() {
        String encrypted = "Top ncmy qkff vi vguv vbg ycpx";
        String message = decrypt(encrypted, 2, 20);
        System.out.println(message);
    }
    
    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        //String message = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeee";
        //String encrypted = cc.encryptTwoKeys(message, 16, 18);
        //System.out.println(message);
        //System.out.println(encrypted);
        FileResource fr = new FileResource(); 
        String encrypted = fr.asString(); 
        String result = decryptTwoKeys(encrypted);
        //String result = decrypt("Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!");
        System.out.println(result);
    }
}
