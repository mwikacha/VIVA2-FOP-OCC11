import java.util.Scanner;
import java.util.Arrays;

public class AnagramChecker {

    //Core Helper Function: Creates a unique, sorted character representation
    //Converts a string into its canonical form (sorted character array turned back into a string).
     
    private static String getCanonicalForm(String str) {
        
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        
        // Return the sorted string
        return new String(chars);
    }

    public static int countChar(String str){
        return str.length();
    }

    //Method 1: isAnagram
    //Checks if string 'a' and string 'b' are anagrams by comparing their canonical forms.
    
    public static boolean isAnagram(String a, String b) {
        // Step 1: Check length (must be the same)
        if (a.length() != b.length()) {
            return false;
        }

        // Step 2: Compare the canonical forms
        String canonicalA = getCanonicalForm(a);
        String canonicalB = getCanonicalForm(b);
        return canonicalA.equals(canonicalB);
    }
    
    //Main Program Logic
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Input: Read N
        System.out.print("Enter the number of words (N): ");
        int n = scanner.nextInt(); 
        scanner.nextLine();

        String[] originalWords = new String[n];
        for (int i = 0; i < n; i++) {
        System.out.print("Enter word " + (i + 1) + ": ");
        // All words are read and immediately converted to lowercase
        originalWords[i] = scanner.nextLine().toLowerCase();
        }

        String[] uniqueWords = new String[n];
        int uniqueCount = 0;

        for (int i = 0; i < n; i++) {
        String currentWord = originalWords[i];
        boolean isDuplicate = false;

        // Check if the current word is already in the uniqueWords array
        for (int j = 0; j < uniqueCount; j++) {
             if (currentWord.equals(uniqueWords[j])) { 
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            uniqueWords[uniqueCount] = currentWord;
            uniqueCount++;
        }
    }

        //2. Tracking: Use a boolean array to track which words have been grouped
        //prevent duplicate grouping
        boolean[] grouped = new boolean[uniqueCount];
        int groupNumber = 1;

        // Output strings for organized printing
        String anagramGroupsOutput = "";
        String ungroupedWordsOutput = "";

        //Anagram Grouping
        
        for (int i = 0; i < n; i++) {
            // Skip if the word has already been used in a group
            if (grouped[i]) {
                continue; 
            }

            String currentGroupWords = "";
            int groupSize = 0;

            // Check word[i] against all words (starting from i)
            for (int j = i; j < uniqueCount; j++) {
                // Check if they are anagrams AND word 'j' hasn't been grouped yet
                if (!grouped[j] && isAnagram(originalWords[i], originalWords[j])) {
                    currentGroupWords += originalWords[j] + " ";
                    grouped[j] = true; // Mark as used
                    groupSize++;
                }
            }

            if (groupSize > 1) {
                // If no anagrams found, continue to next word
                anagramGroupsOutput += "Anagram group " + groupNumber + ": " + currentGroupWords.trim() + "\n";
                groupNumber++;
            } else {
                // If no anagrams found, add to ungrouped words
                ungroupedWordsOutput += uniqueWords[i] + " ";
            }
        
                
            //3. Output Logic
            if (groupSize > 1) {
                // True Anagram Group (size > 1)
                anagramGroupsOutput += "Anagram group " + groupNumber + ": " + currentGroupWords.trim() + "\n";
                groupNumber++;
            } else {
                // Ungrouped Word (size 1) - If it wasn't an anagram of anything else
                // Note: The word[i] itself is the only one in the group of size 1
                ungroupedWordsOutput += uniqueWords[i] + " ";
            }
        }
        
        //4. Final Display
        System.out.println("\n Anagram Group Results");
        System.out.print(anagramGroupsOutput);
        System.out.println("\n Remaining Words");
        System.out.println("Without anagram group: " + ungroupedWordsOutput.trim());
        
        scanner.close();
    }
}
