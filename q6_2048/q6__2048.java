package q6_2048;

import java.util.*;
public class q6__2048 {
    static Random rand = new Random();  //create a random object to generate random numbers for new tiles; use static random wo that all static method in the class can access same random object

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] grid = new int[4][4];

        //enter 4x4
        for (int i = 0; i < 4; i++) 
            for (int j = 0; j < 4; j++)
                grid[i][j] = sc.nextInt();

        String dir = sc.next().toUpperCase();   //read user's inpur direction, change to uppercase
        boolean moved = false;  //initialize false, since havent move

        //handle movement based on user input
        if (dir.equals("LEFT")) moved = moveLeft(grid);
        else if (dir.equals("RIGHT")) moved = moveRight(grid);
        else if (dir.equals("UP")) moved = moveUp(grid);
        else if (dir.equals("DOWN")) moved = moveDown(grid);    

        if (moved) addNewTile(grid);   //2 of 4

        //output- print num in grid
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                System.out.print(grid[i][j] + " "); //to separate num
            System.out.println();
        }
        sc.close();     
    }

    //LEFT
    static boolean moveLeft(int[][] g) {    //collect 4x4 array
        boolean moved = false;  //start wf false

        for(int i = 0; i < 4; i++) {
            int[] temp = new int[4];    //create an arry with length 4 to temporarily store num that not 0(which 2,4,8...)
            int k = 0;  //temp = k

            /* eg. g[i] = [0, 2, 0, 4]
             * initialize temp = [0, 0, 0, 0] (k=0)
             *0,skip; 2,become (k=1); 0,skip; 4 become k = 2
             * final temp = [2, 4, 0, 0]
             */

            //slide
            for (int j = 0; j < 4; j++)
                if (g[i][j] != 0)   //if != 0, then slide to left
                    temp[k++] = g[i][j];

            //merge adjacent [j, j+1, ...]
            for (int j = 0; j < 3; j++) {
                if (temp[j] == temp[j + 1] && temp[j] != 0) {   //if left and right are same && !=0, then *2 it
                    temp[j] *= 2;
                    temp[j + 1] = 0;    //right side(j+1) become 0  after merge
                    moved = true;   //something moved
                }
            }

            //slide again
            int[] finalRow = new int[4];    //hold the final version of the current row after sliding and merging
            k = 0;  //reset k
            for (int j = 0; j < 4; j++) 
                if (temp[j] != 0)   
                    finalRow[k++] = temp[j];    //put the not zero num at left(final row)
                    //k++ put num from left to right

            if (!Arrays.equals(g[i], finalRow)) moved = true;   //check if this row is equal to ori row, if !=, means there's move/merge, then = true
            g[i] = finalRow;    //update grid
        }
        return moved;
    }

    //RIGHT
    static boolean moveRight(int[][] g) {
        boolean moved = false;

        for (int i = 0; i < 4; i++) {
            int[] temp = new int[4];
            int k = 3;  //to right so start from 3[0, 1, 2, 3]

            for (int j = 3; j >= 0; j--) {  //3-->2-->1-->0
                if (g[i][j] != 0)   //if not 0, then put in temp(at right side)
                    temp[k--] = g[i][j];
            }

            for (int j = 3; j > 0; j--) {
                if (temp[j] == temp[j - 1] && temp[j] != 0) {   //if same with adjacent, merge(*2)
                    temp[j] *= 2;   
                    temp[j -1] = 0; //become 0 after merge
                    moved = true;
                }
            }

            //slide again
            int[] finalRow = new int[4];
            k = 3;  //If we put numbers starting from the rightmost position, that means we start at k = 3.
            for (int j = 3; j >= 0; j--) {
                if (temp[j] != 0) {
                    finalRow[k--] = temp[j];  //put the not zero num at right(from[3] to [0])
                    //k-- put num from right to left eg. g[i] = [2,0,2,4] temp = [0,2,2,4]
                }
            }
            if (!Arrays.equals(g[i], finalRow)) moved = true;
            g[i] = finalRow;
        }
        return moved;
    }

    //UP(same wf left)
    static boolean moveUp(int[][] g) {
        boolean moved = false;

        for (int j = 0; j < 4; j++) {
            int [] temp = new int[4];
            int k = 0;
                
            //slide
            for (int i = 0; i < 4; i++)
                if (g[i][j] != 0)   //if not 0 then move up
                    temp[k++] = g[i][j];

            //merge
            for (int i = 0; i < 3; i++) {
                if (temp[i] == temp[i + 1] && temp[i] != 0) {   
                    temp[i] *= 2;
                    temp[i + 1] = 0;
                     moved = true;
                }
            } 

            //slide again
            int[] finalCol = new int[4];
            k = 0;
            for (int i = 0; i < 4; i++) {
                if (temp[i] != 0) {
                    finalCol[k++] = temp[i];
                }
            }
            int[] originalCol = new int[4];
            for (int i = 0; i < 4; i++) {
                originalCol[i] = g[i][j];
            }

            if (!Arrays.equals(originalCol, finalCol)) moved = true;

            for (int i = 0; i < 4; i++) {
                g[i][j] = finalCol[i];
            }   
        }
        return moved; 
    }

    //DOWN(same right)
    static boolean moveDown(int[][] g) {
        boolean moved = false;

        for (int j = 0; j < 4; j++) {
            int[] temp = new int[4];
            int k = 3;  //from down to up

            for (int i = 3; i >= 0; i--){
                if (g[i][j] != 0)
                    temp[k--] = g[i][j];    //move num that != to down
            }

            for (int i = 3; i > 0; i--) {
                if (temp[i] == temp[i - 1] && temp[i] != 0) {
                    temp[i] *= 2;
                    temp[i - 1] = 0;    //up become 0 ;down merge
                    moved = true;
                }
            }

            //slide again
            int[] finalCol = new int[4];
            k = 3;
            for (int i = 3; i >= 0; i--){
                if (temp[i] != 0) 
                    finalCol[k--] = temp[i];      
            }

            int[] originalCol = new int[4];
            for (int i = 0; i < 4; i++) {
                originalCol[i] = g[i][j];
            }

            if (!Arrays.equals(originalCol, finalCol)) moved = true;

            for (int i = 0; i < 4; i++) {
                g[i][j] = finalCol[i];
            }
        }
        return moved;
    }
    
    //add new tile 
    static void addNewTile(int[][] g) { ArrayList<int[]> list = new ArrayList<>(); //a dynamic list that stores integer array, can make change evrtime

        for (int i = 0; i < 4; i++) 
            for(int j = 0; j < 4; j++) 
                if (g[i][j] == 0) 
                    list.add(new int[]{i, j}); //list not store num, its store position of space

            /* eg. list = [[0,1], [1,2]. [3,0]] (means position [0,1] still empty can store num) 
            *list.isEmpty -- false ; !list.isEmpty -- true 
            *eg. list =[] (no position are empty-fullhouse) 
            *list.isEmpty -- true ; !list.isEmpty -- false 
            */
        if (!list.isEmpty()) {   //list.isEmpty-- to determine inside the list got elment or not. if!, means have atleast one empty to place 2/4
            int[] pos = list.get(rand.nextInt(list.size()));
            g[pos[0]][pos[1]] = rand.nextBoolean() ? 2 : 4; 
        } 
    } 
}
