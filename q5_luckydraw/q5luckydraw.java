package q5_luckydraw;

import java.util.*;
public class q5luckydraw {

    //Genarate random numbers
    public static int[] generateNum(int numOfBall, int length) {
        Random r = new Random();
        int[] arr = new int[numOfBall]; //d&i array(arr), to store ball's rand

        int min = (int)Math.pow(10, length - 1);    //since Math.pow(10^2 = 100) will return dou, to convert --> (int)
        int max = (int)Math.pow(10, length) - 1;    //to determine range of rand, if lenght = 3 (100~999)

        for (int i = 0; i < numOfBall; i++) {   //generate ball's num
            arr[i] = r.nextInt(max - min + 1) + min;    //if max = 999; min = 100, (999-100 + 1 = 900) --> r.nextInt(900)(0-899)
        }
        return arr;
    }

    //display numbers in grid format
    public static void displayNum(int[] num, int numOfBall) {
        int col = (int)Math.ceil(Math.sqrt(numOfBall)); //to determine how many num in 1 col, .ceil means carry(7.07--8) eg.nOB = 50 sqrt(50) = 7.07 ceil(7.07) = 8--> 1 col 8 num

        for (int i = 0; i < numOfBall; i++) {
            System.out.printf("%6d", num[i]);   //format to make outpur more clean 

            if ((i + 1) % col == 0) {   //i+1 to become 1,2,3,..(since i = 0,...); if col = 8 then == 0(means alr 1 col 8 num), then nextln
                System.out.println();
            }
        }
        System.out.println();
    }

    //check exact match
    public static boolean foundTarget(int target, int poolNumber) {
        return target == poolNumber;    //if macth then return true
    }

    //check near miss (only 1 digit different)
    public static boolean nearMiss(int target, int poolNumber, int length) {
        int same = 0;

        for (int i = 0; i < length; i++) { //loop through each digit
            if (target % 10 == poolNumber % 10) //compare the last digit of both numbers
                same++;

            target /= 10;   //remove the last digit and move to next digit
            poolNumber /= 10;
        }

        return same == length - 1;  //if the same = 3; length of num is 4 -1 =3, 3==3 then return true(means only 1 diff)
    }

    //Prize calculation
    public static double calcPrize(int length, int ballValue, int numOfBall) {
        return (length * ballValue * 1000.0) / numOfBall;   //formula given
    }

    //main program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number length (3 or 4): ");
        int length = sc.nextInt();

        System.out.print("Enter number of ball to choose: ");
        int numOfBall = sc.nextInt();

        System.out.print("Enter ball value: ");
        int ballValue = sc.nextInt();

        System.out.print("Target number: ");
        int target = sc.nextInt();

        //generate numbers
        int[] pool = generateNum(numOfBall, length);

        //display numbers
        displayNum(pool, numOfBall);    //display all the rand num(in grid)

        boolean win = false;    
        ArrayList<Integer> nearList = new ArrayList<>();    //store all nearmiss num

        for (int num : pool) {
            if (foundTarget(target, num)) {     //if true then break
                win = true;
                break;
            } else if (nearMiss(target, num, length)) { //else if nearmiss then store the num in nearList
                nearList.add(num);
            }
        }
        sc.close();

        //output result
        if(win) {
            System.out.println("Congratulations!! You Got The Number " + target);
            double prize = calcPrize(length, ballValue, numOfBall);
            System.out.printf("Total Prize: RM%.2f\n",  prize);    //2 decimal num
        } else if (!nearList.isEmpty()) {
            System.out.println("You almost get it");
            for (int n : nearList)
                System.out.print(n + " ");
        } else {
            System.out.println("Try again next time");
        }
    }
}
