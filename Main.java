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
            while(!correctPos) {
                System.out.println("yes");
                try {
                    System.out.println("Enter row: ");
                    row = scan.nextInt();
                    System.out.println("Enter column: ");
                    column = scan.nextInt();

                    chessboard.placeObject(row - 1, column - 1, "Q");

                    chessboard.print();
                    correctPos = true;
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        }

        chessboard.solveNQ();



    }
}


class InvalidInputException extends Exception{
    public InvalidInputException(String n){
        super(n);
    }
}