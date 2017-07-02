import edu.duke.*; 

/**
 * Write a description of TestCaesarCipherOo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherOo {
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
    
    private int maxIndex(int[] freqs){
        int maxIndex = 0;
        for(int k = 1; k < freqs.length; k++){
            if(freqs[k] > freqs[maxIndex]) {
                maxIndex = k;
            }
        } 
        return maxIndex; 
    }
    
    public void simpleTests(){
        //FileResource fr = new FileResource(); 
        //String file = fr.asString(); 
        String file = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println("The original message is: " + file); 
        
        CaesarCipherOo cc = new CaesarCipherOo(15); 
        String encrypted = cc.encrypt(file); 
        System.out.println("The encrypted message is: " + encrypted); 
        
        String decrypted = cc.decrypt(encrypted); 
        System.out.println("The decrypted message is: " + decrypted); 
        
        String redecrypted = breakCaesarCipher(encrypted); 
        System.out.println("The decrypted message with generated key is: " + redecrypted); 
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
    
    private String breakCaesarCipher(String input){
        int dkey = getKey(input);
        CaesarCipherOo cc = new CaesarCipherOo(dkey);
        
        System.out.println("key: " + dkey);
        String decrypted = cc.decrypt(input);
        return decrypted; 
    }

}
