import java.util.Scanner;
public class numberRearrangement {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // get input from user
        System.out.print("Enter number: ");
        String input = scanner.nextLine();

        // call methods to get results
        String largest = getLargestNum(input);
        String smallest = getSmallestNum(input);

        // print the output
        System.out.println("Largest number: " + largest);
        System.out.println("Smallest number: " + smallest);

        scanner.close();
    }

    // first method to find the largest possible number
    public static String getLargestNum(String num) {
        // step 1: Filter out non-digits
        String digitsOnly = "";
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (c >= '0' && c <= '9') {
                digitsOnly += c;
            }
        }

        // step 2: convert the string digitsonly to a character type array to be able sort
        char[] chars = digitsOnly.toCharArray();

        // step 3: sort array in DESCENDING order (9 to 0) using Bubble Sort
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length - 1; j++) {
                if (chars[j] < chars[j + 1]) {
                    // swap
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }

        // Step 4: Convert back to String
        String result = new String(chars);
        return result;
    }

    // second method to find the smallest possible number
    public static String getSmallestNum(String num) {
        // step 1: filter out non-digits
        String digitsOnly = "";
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (c >= '0' && c <= '9') {
                digitsOnly += c;
            }
        }

        // step 2: convert to character array to sort
        char[] chars = digitsOnly.toCharArray();

        // Step 3: Sort array in ASCENDING order (0 to 9) using Bubble Sort
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    // Swap
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }

        // step 4: handle Leading Zeros
        // example: if sorted is "00234", we want "234"
        //convert back to string first
        String sortedStr = new String(chars);
        String finalResult = "";
        boolean foundNonZero = false;

        for (int i = 0; i < sortedStr.length(); i++) {
            char c = sortedStr.charAt(i);
            
            // if haven't found a non-zero number yet, skip '0' ncs we dont want it. only want nonzero number like 1-9
            if (!foundNonZero && c == '0') {
                continue; 
            }
            
            // when we find a non-zero (1-9), we set foundnonzero boolean to true and append the current character at current index to finalresult empty string
            foundNonZero = true;
            finalResult += c;
        }

        // if the finalresult is empty (input was "000"), skip all got empty finalresult,finalResult.length() == 0,we  return "0"
        if (finalResult.length() == 0 && sortedStr.length() > 0) {
            return "0";
        }

        return finalResult;
    }

}
