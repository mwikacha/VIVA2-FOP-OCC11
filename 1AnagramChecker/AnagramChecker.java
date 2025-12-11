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
        scanner.nextLine(); // Consume newline after reading int

        // 2. Input: Read N words and preprocess
        String[] originalWords = new String[n];
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter word " + (i + 1) + ": ");
            // Convert to lowercase immediately to handle case-insensitivity
            originalWords[i] = scanner.nextLine().toLowerCase(); 
        }

        // 3. Tracking: Use a boolean array to track which words have been grouped
        //prevent duplicate grouping
        boolean[] grouped = new boolean[n];
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
            for (int j = i; j < n; j++) {
                // Check if they are anagrams AND word 'j' hasn't been grouped yet
                if (!grouped[j] && isAnagram(originalWords[i], originalWords[j])) {
                    currentGroupWords += originalWords[j] + " ";
                    grouped[j] = true; // Mark as used
                    groupSize++;
                }
            }
            
            // 4. Output Logic
            if (groupSize > 1) {
                // True Anagram Group (size > 1)
                anagramGroupsOutput += "Anagram group " + groupNumber + ": " + currentGroupWords.trim() + "\n";
                groupNumber++;
            } else {
                // Ungrouped Word (size 1) - If it wasn't an anagram of anything else
                // Note: The word[i] itself is the only one in the group of size 1
                ungroupedWordsOutput += originalWords[i] + " ";
            }
        }
        
        // 5. Final Display
        System.out.println("\n Anagram Group Results");
        System.out.print(anagramGroupsOutput);
        
        System.out.println("\n Remaining Words");
        System.out.println("Without anagram group: " + ungroupedWordsOutput.trim());
        
        scanner.close();
    }
}
