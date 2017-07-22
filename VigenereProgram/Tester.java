import java.util.*;
import edu.duke.*;

/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void testCaesarCipher(){
        FileResource fr = new FileResource(); 
        String fileString = fr.asString(); 
        CaesarCipher caesar = new CaesarCipher(6); 
        String encrypted = caesar.encrypt(fileString);
        System.out.println("The encrypted message is: "+ encrypted); 
        String decrypted = caesar.decrypt(encrypted);
        System.out.println("The decrypted message is: " + decrypted); 
    }
    
    public void testCaesarCharacter(){
        CaesarCipher caesar = new CaesarCipher(6); 
        Character a = caesar.encryptLetter('a'); 
        System.out.println(a); 
        Character g = caesar.decryptLetter('g');
        System.out.println(g);
    }
    
    public void testCaesarCracker(){
        FileResource fr = new FileResource();
        String fileString = fr.asString(); 
        CaesarCracker crack = new CaesarCracker(); 
        int key = crack.getKey(fileString); 
        System.out.println("The key is " + key); 
        String decrypted = crack.decrypt(fileString); 
        System.out.println("The decrypted message is: " + decrypted); 
    }
    
    public void testVigenereCipher(){
        FileResource fr = new FileResource();
        String fileString = fr.asString(); 
        int [] key = {17, 14, 12, 4};
        VigenereCipher vi = new VigenereCipher(key); 
        String encrypted = vi.encrypt(fileString); 
        System.out.println("The encryption is: " + encrypted); 
        String decrypted = vi.decrypt(encrypted); 
        System.out.println("The decryption is: " + decrypted); 
    }
    
    public void testVigenereSlicer(){
        String message = "abcdefghijklm"; 
        int whichSlice = 4; 
        int totalSlices = 5; 
        VigenereBreaker vb = new VigenereBreaker();
        String slice = vb.sliceString(message, whichSlice, totalSlices); 
        System.out.println(slice);
    }
    
    public void testVigenereKeyLength(){
        FileResource fr = new FileResource();
        String fileString = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] key = vb.tryKeyLength(fileString, 4, 'e');
        for(int i=0; i<key.length; i++){
            System.out.println(key[i]);
        }
    }
    
    public void testVigenereBreaker(){
        VigenereBreaker vb = new VigenereBreaker(); 
        vb.breakVigenere();
    }
    
    public void testVigenereDictionary(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource(); 
        HashSet<String> hash = vb.readDictionary(fr); 
        System.out.println(hash);
    }
}
