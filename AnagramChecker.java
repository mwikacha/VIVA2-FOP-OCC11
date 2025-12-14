import java.util.Scanner;
import java.util.Arrays;

public class AnagramChecker{

    private static String getCanonicalForm(String str) {
        // Convert to character array
        char[] chars = str.toCharArray();
        
        // Sort the array (This is the key to the canonical form)
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
        // Perform defensive trimming/lowercasing
        String strA = a.trim().toLowerCase();
        String strB = b.trim().toLowerCase();
        
        //Check length (must be the same)
        if (a.length() != b.length()) {
            return false;
        }

        //Compare the canonical forms
        String canonicalA = getCanonicalForm(strA);
        String canonicalB = getCanonicalForm(strB);
        return canonicalA.equals(canonicalB);
    }
    
    //Main Program Logic
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Input: Read N
        System.out.print("Enter the number of words (N): ");
        int n = scanner.nextInt(); 
        scanner.nextLine();

        //FILTERING BLOCK

        String[] allInputWords = new String[n];
        // 1. Read input into allInputWords
        for (int i = 0; i < n; i++) {
            System.out.print("Enter word " + (i + 1) + ": ");
            allInputWords[i] = scanner.nextLine().trim().toLowerCase();
        }

            String[] uniqueWords = new String[n];
            int uniqueCount = 0;
            for (int i = 0; i < n; i++) {
            
            // 2. Filter from allInputWords to uniqueWords
            String currentWord = allInputWords[i];
            boolean isDuplicate = false;

            // Check against only the words already confirmed unique
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

        //END FILTERING BLOCK

        // 2. Tracking: Use a boolean array to track which words have been grouped
        boolean[] grouped = new boolean[uniqueCount];
        int groupNumber = 1;

        // Output strings for organized printing
        String anagramGroupsOutput = "";
        String ungroupedWordsOutput = "";


        // --- ANAGRAM GROUPING BLOCK (The final corrected logic) ---
        for (int i = 0; i < uniqueCount; i++) {
            if (grouped[i]) {
                continue; // Skip words already assigned to a group
            }

            // Initialize string to hold anagrams found *for this group*
            String foundAnagrams = ""; 
            int groupSize = 1; 
            grouped[i] = true; 

            // Inner loop: Check word[i] against all SUBSEQUENT words (j = i + 1)
            for (int j = i + 1; j < uniqueCount; j++) { 
                
                // Check if word 'j' hasn't been grouped yet AND is an anagram of word 'i'
                if (!grouped[j] && isAnagram(uniqueWords[i], uniqueWords[j])) {
                    
                    // Add the found anagram (word j)
                    foundAnagrams += uniqueWords[j] + " "; 
                    
                    // Mark the found anagram as grouped
                    grouped[j] = true; 
                    groupSize++;
                }
            }

            // Output Logic
            if (groupSize > 1) {
                // True Anagram Group (size > 1).
                String finalGroup = uniqueWords[i] + " " + foundAnagrams;
                anagramGroupsOutput += "Anagram group " + groupNumber + ": " + finalGroup.trim() + "\n";
                groupNumber++;

            } else {
                // Ungrouped Word (size 1). 
                ungroupedWordsOutput += uniqueWords[i] + " ";
            }
        }
        // --- END ANAGRAM GROUPING BLOCK ---
        
        // 4. Final Output
        System.out.println("\nAnagram Group Results");
        System.out.print(anagramGroupsOutput);
        System.out.println("\nRemaining Words");
        System.out.println("Without anagram group: " + ungroupedWordsOutput.trim());
        
        scanner.close();
    }
}