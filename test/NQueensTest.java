import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NQueensTest
{
     private NQueens nQueens;
     /*
          1 0 0 0
          0 0 0 0
          0 1 0 0
          0 0 0 0
      */
     private int[][] testBoard = new int[][] {{1,0,0,0}, {0,0,0,0}, {0,1,0,0}, {0,0,0,0}};

     @BeforeEach
     public void setup()
     {
          nQueens = new NQueens();
     }

     //
     @Test
     public void testIsSafeFirst() {
          var isSafe = nQueens.isSafe(testBoard, 0, 2, 4);
          assertFalse(isSafe);
     }
     //
     @Test
     public void testIsSafeSecond() {
          var isSafe = nQueens.isSafe(testBoard, 1, 3, 4);
          assertTrue(isSafe);
     }
     //
     @Test
     public void testIsSafeThird() {
          var isSafe = nQueens.isSafe(testBoard, 3, 2, 4);
          assertFalse(isSafe);
     }

     @Test
     public void doesTestHaveSolution() {
          var result = nQueens.solveNQueensForCol(testBoard, 2, 4);
          assertFalse(result);
     }
}