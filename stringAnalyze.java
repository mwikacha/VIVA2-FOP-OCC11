import java.util.Scanner;
public class stringAnalyze {

    private static final char[][] defaultMirror = {
        {'A', 'A'}, {'H', 'H'}, {'I', 'I'}, {'M', 'M'}, {'O', 'O'},
        {'T', 'T'}, {'U', 'U'}, {'V', 'V'}, {'W', 'W'}, {'X', 'X'},
        {'Y', 'Y'}, {'o', 'o'}, {'u', 'u'}, {'v', 'v'}, {'w', 'w'},
        {'b', 'd'}, {'d', 'b'}, {'p', 'q'}, {'q', 'p'}
    };

    private static char[][] newMP = new char[20][]; //10 rows → 10 arrays

    // I want to use this method to change 1 input String into 2 connected output arrays and store
    private static void addMirror(String enterString){
        //original
        char[] addM1 = new char[2];
        addM1[0] = enterString.charAt(0);
        addM1[1] = enterString.charAt(2);
    
        //reversed
        char[] addM2 = new char[2];
        addM2[1] = enterString.charAt(0);
        addM2[0] = enterString.charAt(2);
        
        //store
        for (int j = 1; j <= 2; j++){
            for (int i = 0; i <= 19; i++){
                if (newMP[i] == null){
                    if (j == 1){newMP[i] = addM1; continue;}
                    else{newMP[i] = addM2; break;}
                }
            }
        }
    }

    // Find all the plaindromes
    private static String[] getPalindromes(String str){
        String[] plaindromes = new String[str.length()*(str.length()-1)/2]; //n*(n-1)/2
        for(int i = 0; i < str.length()-1;i++){
            for(int j = i+1; j < str.length();j++){ // the minimum length for valid plaindrom is 2
                if (Character.toLowerCase(str.charAt(i)) == Character.toLowerCase(str.charAt(j))){
                    int start = i;
                    int end = j;
                    boolean isPD = true;
                    while (start < end){
                        start ++;
                        end --;
                        isPD = Character.toLowerCase(str.charAt(start)) == Character.toLowerCase(str.charAt(end));
                        if (!isPD){break;}
                    }
                    if (isPD){
                        for (int k = 0; k < plaindromes.length; k++){
                            if (plaindromes[k] == null){
                                plaindromes[k] = str.substring(i, j+1);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return plaindromes;
    }

    private static String getLongestPalindrome(String str){
        String[] strs = getPalindromes(str);
        if (strs[0] == null){
            return "-";
        }
        String longest = "";
        for (String a : strs){
            if (a == null) {break;} 
            if (a.length()>longest.length()){
                longest = a;
            }
        }
        return longest;
    }

    // Find if two inputs are mirror words
    private static boolean isMirror(char c1,char c2){
        //default
        for (int i = 0; i <= defaultMirror.length -1; i++){
            for (int j = 0; j <=1; j++){
                if (defaultMirror[i][j] == c1){
                    if(defaultMirror[i][1-j] == c2){
                        return true;
                    }
                }
            }
        }
        // new mirror
        for (int i = 0; i <= newMP.length -1; i++){
            for (int j = 0; j <=1; j++){
                if (newMP[i] == null){
                    return false;
                } 
                else if (newMP[i][j] == c1){
                    if(newMP[i][1-j] == c2){
                        return true;
                    }
                }
            }
        }
        return false;

    }

    // find all the mirror words
    private static String[] getMirrorWord(String str){
        String[] mirrorwords = new String[str.length()*(str.length()-1)/2]; //n*(n-1)/2
        for(int i = 0; i < str.length()-1;i++){
            for(int j = i+1; j < str.length();j++){ // the minimum length for valid plaindrom is 2
                if (isMirror(str.charAt(i),str.charAt(j))){
                    int start = i;
                    int end = j;
                    boolean isMW = true;
                    while (start < end){
                        start ++;
                        end --;
                        isMW = isMirror(str.charAt(start),str.charAt(end));
                        if (!isMW){break;}
                    }
                    if (isMW){
                        for (int k = 0; k < mirrorwords.length; k++){
                            if (mirrorwords[k] == null){
                                mirrorwords[k] = str.substring(i, j+1);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return mirrorwords;
    }

    private static String getLongestMirrorWord(String str){
        String[] strs = getMirrorWord(str);
        if (strs[0] == null){
            return "-";
        }
        String longest = "";
        for (String a : strs){
            if (a == null) {break;} 
            if (a.length()>longest.length()){
                longest = a;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        Scanner analyzer = new Scanner(System.in);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Enter additional mirror pairs:");
        int newMirror = 0; 
        // ※ The user can also add up to 10 new mirror pairs before testing their input word.
        while(newMirror < 10){
            String enter = analyzer.nextLine();
            //an empty line indicates the end of mirror pair input.
            if (enter.length()==0){
                System.out.println(newMirror + " pair(s) entered.");
                break;
            }
            //each containing two characters separated by a space
            else if(enter.length() == 3 && enter.charAt(1) == ' '){
                addMirror(enter);
                newMirror ++;
            }
            else{
                System.out.println("Please enter a mirror pair with the form with 2 characters separated by 1 space,");
            }
        }

        //Read a string from the user.
        System.out.print("Enter word: ");
        String word = analyzer.nextLine();

        System.out.println("Longest Palindrome substring: " + getLongestPalindrome(word));
        System.out.println("Longest mirrorable substring: " + getLongestMirrorWord(word));

        
        
        analyzer.close();
    }

}
