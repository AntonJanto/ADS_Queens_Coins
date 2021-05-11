public class NQueens
{
     public static void main(String[] args)
     {
          int n = 10;
          NQueens nQueens = new NQueens();
          var result = nQueens.findNQueensBoard(n);
          printBoard(result, n);
     }

     public static void printBoard(int[][] result, int n)
     {
          for (int i = 0; i < n; i++) {
               for (int j = 0; j < n; j++) {
                    System.out.print(" {" + result[i][j] + "}");
               }
               System.out.println();
          }
     }

     public int[][] findNQueensBoard(int n)
     {
          int[][] board = generateEmptyBoard(n);
          boolean isSolution = solveNQueensForCol(board, 0, n);
          if (isSolution)
               return board;
          else
               return generateEmptyBoard(n);
     }

     public boolean solveNQueensForCol(int[][] board, int col, int n)
     {
          //algorithm went through all columns (left to right)
          //and found solution for each column - complete solution done
          if (col >= n)
               return true;

          //check each row in column
          for (int i = 0; i < n; i++) {
               if (isSafe(board, i, col, n)) {
                    board[i][col] = 1;
                    if (solveNQueensForCol(board, col + 1, n) == true)
                         return true;

                    //cannot put anywehere else in other columns - BACKTRACK
                    board[i][col] = 0;
               }
          }
          //if loop does not lead to solution - no solution in this column
          return false;
     }

     public boolean isSafe(int[][] board, int row, int col, int n)
     {
          //row check
          for (int i = 0; i < col; i++) {
               if (board[row][i] == 1)
                    return false;
          }

          int i, j;

          //check diagonal top left (row decreasing, column decreasing)
          for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
               if (board[i][j] == 1)
                    return false;
          }

          //check diagonal bottom left (row increasing, column decreasing)
          for (i = row, j = col; i < n && j >= 0; i++, j--) {
               if (board[i][j] == 1)
                    return false;
          }
          return true;
     }

     public int[][] generateEmptyBoard(int n)
     {
          int[][] board = new int[n][n];
          for (int i = 0; i < n; i++) {
               for (int j = 0; j < n; j++) {
                    board[i][j] = 0;
               }
          }
          return board;
     }
}
