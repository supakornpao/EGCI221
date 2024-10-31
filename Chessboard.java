package Project1;



public class Chessboard {
    private int size, columnOccupied = -1, rowOccupied = -1;
    private Element[][] element;
    public Chessboard(int size){
        this.size = size;
        element = new Element[this.size][this.size];
        for(int i = 0;i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                element[i][j] = new Element(i,j);
            }
        }
    }

    public void print(){
        for(int i = -1; i < size ; i++){
            if(i==-1) {
                for (int j = 0; j < size; j++) {
                    if (j == 0) System.out.printf("%13d", j+1 );
                    else System.out.printf("%3d", j+1 );
                }
            }
            else{
                System.out.printf("\nrow %4d",i+1);
                System.out.print("  | ");
                for(int j = 0; j<size;j++){
                    element[i][j].print();
                }
            }
        }
        System.out.println();
        System.out.println();
        String eq = "=";
        System.out.println(eq.repeat(50));
        System.out.println();
    }

    public void placeObject(int i,int j, String n){
        element[i][j].setObject(n);
    }


    boolean isSafe(Element [][] board, int row, int col)
    {
        int i, j;


        // Check this row on left side
        for (i = 0; i < col; i++)
            if (!board[row][i].getStatus())
                return false;

        //Check this row on right side
        for(i = col; i < size; i++){
            if(!board[row][i].getStatus())
                return false;
        }


        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (!board[i][j].getStatus())
                return false;

        // Check upper diagonal on right side
        for (i = row, j = col; i >= 0 && j <size; i--, j++)
            if (!board[i][j].getStatus())
                return false;


        // Check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < size; i++, j--)
            if (!board[i][j].getStatus())
                return false;

        // Check lower diagonal on right side
        for (i = row, j = col; j < size && i < size; i++, j++)
            if (!board[i][j].getStatus())
                return false;


        return true;
    }


    boolean solveNQUtil(Element[][] board, int col)
    {
        if (col >= size)
            return true;

        if (col == columnOccupied) {
            return solveNQUtil(board, col + 1);
        }

        // Consider this column and try placing
        // this queen in all rows one by one
        for (int i = 0; i < size; i++) {

            // Check if the queen can be placed on
            // board[i][col]

            if (isSafe(board, i, col)) {

                // Place this queen in board[i][col]

                board[i][col].setObject("Q");
                System.out.printf("Place object at from row %d column %d \n",i+1,col+1);

                // Recur to place rest of the queens

                if (solveNQUtil(board, col+1))
                    return true;

                // If placing queen in board[i][col]
                // doesn't lead to a solution then
                // remove queen from board[i][col]

                board[i][col].removeObject(); // BACKTRACK
                System.out.printf("Backtracking from row %d column %d \n",i+1,col+1);
            }

        }

        // If the queen can not be placed in any row in
        // this column col, then return false
        return false;
    }

    public void solveNQ(int row, int column){
        if (column > 0) {
            rowOccupied = row;
            columnOccupied = column;
        }

        if(!solveNQUtil(element, 0)){
            System.out.println("No solution");
        }
        print();

    }


}

class Element{
    int row, column;
    private String object = ".";
    private boolean isEmpty;

    boolean input;

    public Element(int row, int column){
        this.row = row;
        this.column = column;
        isEmpty = true;
        input = false;
    }

    public void print(){
        System.out.print(object);
        System.out.print("  ");
    }

    public void setObject(String n){
        object = n;
        isEmpty = false;
    }


    public boolean getStatus(){
        return isEmpty;
    }

    public void removeObject(){
        object = ".";
        isEmpty = true;
    }
}