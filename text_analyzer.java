import java.util.Scanner;

public class text_analyzer {
    public static void main(String[] args) {
        Scanner scan= new Scanner(System.in);
        String fullLetter="";

        //testing purpose only
        //System.out.println("Please write your letter (Press Enter on an empty line to finish):");

        //e.g. Hello\nWorld, Hello is the first loop while World is the second loop
        while (true) {
            String line = scan.nextLine();
            if (line.isEmpty()) {
                break;
            }

            if (fullLetter.isEmpty()) {
                fullLetter += line;
            } else {
                fullLetter += "\n" + line ; //spacing before each lines but not the first line, prevent extra new line at the end
            }
        }

        System.out.println("\n--- Letter Statistics ---");
        System.out.println("Word Count: " + wordCount(fullLetter));
        System.out.println("Character Count: " + characterCount(fullLetter));
        System.out.println("Character Count without Space: " + characterCountWithoutSpaces(fullLetter));
        System.out.println("Sentence Count: " + sentenceCount(fullLetter));
        System.out.println("Most Frequent Word: " + mostFrequentWord(fullLetter));
        System.out.println("Longest Word: " + longestWord(fullLetter));
        
        scan.close();
    }

    public static int wordCount(String str) {
        if (str == null || str.isEmpty()) return 0; 

        /*split the string wherever there is one or more consecutive whitespace characters.
        \\s means any whitespace character(\t,\n etc.).
        Why need two  blackslashes? one backslash used to escape char within string literal.Single backslash in regex must be escaped with another one
        + means one or more 
        summary: split at one or more whitespace characters
        */
        String[] words = str.split("\\s+");
        return words.length;
    }

    public static int characterCount(String str){
        if (str == null || str.isEmpty()) return 0;
        return str.length();
    }

    public static int characterCountWithoutSpaces(String str){
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            /*means not a space(tab, newline, etc.) then count
            Note: str.charAt(i) != ' ' only checks for space character,
            not other whitespace characters like tabs or newlines(they are count as characters too)
            alternative manual check: if (c == ' ' || c == '\t' || c == '\n')
            */
            if (!Character.isWhitespace(str.charAt(i))){
                count++;
            }
        }
        return count;
    }

    /*[...] defines a char class, wchih matches any char contained within it */
    public static int sentenceCount(String str){
        //later explain why need trim() even though we have isEmpty()
        if (str == null || str.trim().isEmpty()) return 0;
        /*or can use if (c == '.' || c == '!' || c == '?') more simpler
        .trim() is used to remove trailing whitespace brefore splitting since trailing newline
        is often treated as extra sentence
        */ 
        String[] sentences = str.trim().split("[.!?]+");
        return sentences.length;
    }

    public static String mostFrequentWord(String str){
        String[] words = getCleanWords(str);

        String mostFrequent = "";
        int maxCount = 0;

        //if the first word(words[0]) is "The", then inner loop help to compare other words 
        for (int i = 0; i < words.length; i++) {
            String currentword = words[i];
            int currentCount = 0;


            //count the occurence of the current word， then reset to zero for next word
            for (int j = 0; j < words.length; j++) {
                if (words[j].equalsIgnoreCase(currentword)) {
                    currentCount++;
                    
                }
            }

            if (currentCount > maxCount) {
                maxCount = currentCount;
                mostFrequent = currentword;
            }
        }
        return mostFrequent;
    }

    public static String longestWord(String str){
        String[] words =getCleanWords(str);
        String longest = "";

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }
        return longest;
    }

    /*  
    Helper method to clean words ("":remove punctuation)
    Any character that is NOT(^ : negative sign) a letter (a-z, A-Z) or a whitespace such as numbers, punctuation and symbols
    */
    public static String[] getCleanWords(String str) {
        String cleanStr = str.replaceAll("[^a-zA-Z\\s ]", ""); 
        return cleanStr.trim().split("\\s+");
    }
}
