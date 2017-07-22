import java.util.*;
import edu.duke.*;
import java.io.File; 

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        //start by changing the mssage to a stringbuilder 
        StringBuilder messageBuilder = new StringBuilder(message);
        StringBuilder slice = new StringBuilder();
        //then find the character at the given indx, whichSlice
        for(int i=whichSlice; i< message.length(); i+=totalSlices){
            slice.append(messageBuilder.charAt(i));
        }
        //then increment by # total slices adding to a new stringbuilder
        //return the new stringbuilder as a string 
        String sliceString = slice.toString(); 
        return sliceString;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        //use sliceString and CaesarCracker
        CaesarCracker cc = new CaesarCracker(mostCommon); 
        //for each of the slices
        for(int i=0; i<key.length; i++){
            String shift = sliceString(encrypted, i, klength);
            int currKey = cc.getKey(shift);
            key[i] = currKey;
        }
        return key;
    }
    
    public HashSet readDictionary(FileResource fr){
        HashSet<String> hash = new HashSet<String>();
        for(String s : fr.lines()){
            s = s.toLowerCase();
            hash.add(s);
        }
        return hash;
    }
    
    public HashMap<String, HashSet<String>> mapDictionaries(){
        HashMap<String, HashSet<String>> mapDicts = new HashMap<String, HashSet<String>>(); 
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            HashSet<String> currentDict = readDictionary(fr);
            String name = f.getName(); 
            mapDicts.put(name, currentDict); 
        }
        return mapDicts;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int realWords = 0; 
        String finalLang = null; 
        String finalDecrypted = null;
        //for each key in the hash map 
        for(String language : languages.keySet()){
            //call breakForLanguage and pass it the value as the dictionary 
            HashSet<String> dictionary = languages.get(language);
            //call breakForLanguage and pass it the value as the dictionary 
            String tryDecrypted = breakForLanguage(encrypted, dictionary);
            //see how many "real" words it has 
            int currRealWords = countWords(tryDecrypted, dictionary);
            //if it has the most real words
            if(currRealWords>realWords){
                //save the number of real words it has 
                realWords = currRealWords; 
                //save the language name 
                finalLang = language;
                //save the decrypted message
                finalDecrypted = tryDecrypted; 
            }
        }
        //print out the actual best results
        System.out.println("The best language to use is " + finalLang); 
        System.out.println("This has " + realWords + " real words."); 
        System.out.println(finalDecrypted); 
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        //split the message into words which returns a string array 
        int count = 0;
        message = message.toLowerCase();
        String[] split = message.split("\\W+");
        //iterate over the words in string array to see how many are in dictionary
        for(int k=0; k<split.length; k++){
            if(dictionary.contains(split[k])){
                count++;
            }
        }
        //increment count whenever it finds a real word 
        //System.out.println("There are " + count + "valid words");
        return count; 
        //return count 
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String decrypted = null;
        int realWords = 0; 
        int[] realKeys = null;
        Character mostCommon = mostCommonCharIn(dictionary);
        //try all key lengths from 1 to 100 using tryKeyLength
        //for each key length
        for(int i = 1; i<101; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            //decrypt the message using the key length using decrypt method
            String tryDecrypt = vc.decrypt(encrypted);
            //use countWords method to count how many real words
            int tryWords = countWords(tryDecrypt, dictionary);
            //save the countWords method with the most words
            if(tryWords>realWords){
                realWords = tryWords;
                decrypted = tryDecrypt;
                realKeys = keys;
            }
        }
        //return the string decryption
        System.out.println("There are " + realWords + "valid words"); 
        System.out.println("The key length is " + realKeys.length); 
        return decrypted; 
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> counts = new HashMap<Character,Integer>();
        for(String word : dictionary){
            //get each character out of word
            char[] chars = word.toCharArray();
            for (char letter : chars) {
                //if it's already in the map, increment value
                if(counts.containsKey(letter)){
                    int value = counts.get(letter);
                    value = value + 1; 
                    counts.put(letter, value); 
                }
                else{ //if it's not in the map, add it with a value of one
                    counts.put(letter, 1); 
                }
            }
        }
        int total = 0;
        Character max = null;
        for(Character c : counts.keySet()){
            int count = counts.get(c);
            if(count>total){
                total = count; 
                max = c;
            }
        }
        return max; 
    }
    
    public int trySpecificLength(){
        //ignore this method and don't use it anywhere except in quiz question 4
        String decrypted = null;
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource dict = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dict);
        int realWords = 0; 
        int[] keys = tryKeyLength(encrypted, 38, 'e');
        VigenereCipher vc = new VigenereCipher(keys);
            //decrypt the message using the key length using decrypt method
        String tryDecrypt = vc.decrypt(encrypted);
            //use countWords method to count how many real words
        int tryWords = countWords(tryDecrypt, dictionary);
        System.out.println("There are " + tryWords + " valid words with 38 as key length"); 
        return tryWords; 
    }

    public void breakVigenere() {
        //Create a new FileResource using its default constructor (which displays a dialog for you to select a file to decrypt).
        FileResource fr = new FileResource(); 
        //Use that FileResource’s asString method to read the entire contents of the file into a String.
        String fileString = fr.asString(); 
        //You should make a new FileResource to read from the English dictionary file that we have provided. 
        FileResource dict = new FileResource("dictionaries/English");
        //You should use your readDictionary method to read the contents of that file into a HashSet of Strings.
        HashSet<String> dictionary = readDictionary(dict);
        //You should use the breakForLanguage method that you just wrote to decrypt the encrypted message.
        String decrypted = breakForLanguage(fileString, dictionary);
        //int [] keys = tryKeyLength(fileString, 4, 'e');
        //VigenereCipher vc = new VigenereCipher(keys);
        //String decrypted = vc.decrypt(fileString);
        System.out.println(decrypted);
    }
    
    public void breakVigenereTwo(){
        HashMap<String, HashSet<String>> dictionaries = mapDictionaries(); 
        FileResource fr = new FileResource();
        String fileString = fr.asString(); 
        breakForAllLangs(fileString, dictionaries); 
    }
    
}
