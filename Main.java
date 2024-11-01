//Pattadon Pimai 6581007
//Weerin Tiwiruch 6581017
//Supakorn Panyadee 6581117
//Thanaphat Mongkollak 6581161

package Project1;

import java.util.Scanner;

public class Main {
    public static void main(String []arg){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter N for N*N board (N must be at least 4)");
        boolean correctsize = false;
        Chessboard chessboard = null;
        int boardSize = scan.nextInt();
        while(!correctsize) {
            try {
                if (boardSize < 4) throw new InvalidInputException("N must be at least the size of 4");
                chessboard = new Chessboard(boardSize);
                chessboard.print();
                correctsize = true;

            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Enter New size: ");
                boardSize = scan.nextInt();
            }
        }

        System.out.println("\nManually place the first Queen? (y for Yes, others for no )");
        String userInput = scan.next();
        if(userInput.equalsIgnoreCase("y")) {
            int row,column;
            boolean correctPos = false;
            System.out.println("yes");
            while(!correctPos) {
                try {
                    System.out.println("Enter row: ");
                    row = scan.nextInt()-1;
                    System.out.println("Enter column: ");
                    column = scan.nextInt()-1;

                    chessboard.placeObject(row, column, "Q");

                    chessboard.print();

                    chessboard.solveNQ(row, column);
                    correctPos = true;
                }
                catch (Exception e){
                    System.out.println(e);
                }

            }
        }
        else chessboard.solveNQ(0,0);


    }
}


class InvalidInputException extends Exception{
    public InvalidInputException(String n){
        super(n);
    }
}