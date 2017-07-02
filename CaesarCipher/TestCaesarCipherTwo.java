import edu.duke.*; 

/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    
    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder(); 
        for (int i = start; i < message.length(); i+=2){
            char ch = message.charAt(i); 
            sb.append(ch);
        }
        return sb.toString();
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
        FileResource fr = new FileResource(); 
        String file = fr.asString(); 
        //String file = "Hfs cpwewloj loks cd Hoto kyg Cyy."; 
        System.out.println("The original message is: " + file); 
        
        CaesarCipherTwo cc2 = new CaesarCipherTwo(14, 24); 
        String encrypted = cc2.encryptTwoKeys(file); 
        System.out.println("The encrypted message is: " + encrypted); 
        
        String decrypted = cc2.decrypt(encrypted); 
        System.out.println("The decrypted message is: " + decrypted); 
        
        String redecrypted = breakCaesarCipher(file); 
        System.out.println("The decrypted message using generated keys is " + redecrypted); 
        
        
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
    
    public String breakCaesarCipher(String input){
        String even = halfOfString(input, 0); 
        String odd = halfOfString(input, 1); 
        
        int evenKey = getKey(even);
        System.out.println("The key used for the even part of the message is " + evenKey);
        int oddKey = getKey(odd);
        System.out.println("The key used for the odd part of the message is " + oddKey); 
        
        CaesarCipherTwo cc2 = new CaesarCipherTwo(evenKey, oddKey); 
        String decrypted = cc2.decrypt(input); 
        return decrypted; 
        
    }

}
