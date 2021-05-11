import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtopiaCoinTest
{
     //this one is hard to test since there is a possibility that there can be multiple good results with the same optimal number of coins
     //example value: 30
     //coins      : 22, 10,  7,  1
     //this       :  1,  0,  1,  1
     //but also   :  0,  3,  0,  0
     //both use 3 coins but are different solutions

     private UtopiaCoins utopiaCoins;


     @BeforeEach
     public void setup()
     {
          utopiaCoins = new UtopiaCoins();
     }

     @AfterEach
     public void destroy(){
          utopiaCoins.printCalculated();
          utopiaCoins = null;
     }

     @Test
     public void testValues1(){
          int number = 30;
          int[] coinValues = new int[] {22, 10, 7, 1};
          int[] expected = new int[] {1, 0, 1, 1};
          int[] result = utopiaCoins.getCounts(coinValues, number);

          assertTrue(compareTwoArrays(expected,result));
     }

     @Test
     public void testValues2(){
          int number = 15;
          int[] coinValues = new int[] {22, 10, 7, 1};
          int[] expected = new int[] {0, 0, 2, 1};
          int[] result = utopiaCoins.getCounts(coinValues, number);

          assertTrue(compareTwoArrays(expected,result));
     }

     @Test
     public void testValues3(){
          int number = 32;
          int[] coinValues = new int[] {22, 10, 7, 1};
          int[] expected = new int[] {1, 1, 0, 0};
          int[] result = utopiaCoins.getCounts(coinValues, number);

          assertTrue(compareTwoArrays(expected,result));
     }

     @Test
     public void testValues4(){
          int number = 27;
          int[] coinValues = new int[] {100, 20, 9, 1};
          int[] expected = new int[] {0, 0, 3, 0};
          int[] result = utopiaCoins.getCounts(coinValues, number);

          assertTrue(compareTwoArrays(expected,result));
     }

     private boolean compareTwoArrays(int[] arrA, int[] arrB){
          //second can be bigger, will evaluate the first values
          for (int i = 0; i < arrA.length; i++) if (arrA[i] != arrB[i]) return false;
          return true;
     }

}