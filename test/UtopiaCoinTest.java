import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
          System.out.println("function calls: " + utopiaCoins.getFunctionCalls() + "; saved values:");
          utopiaCoins.printCalculated();
          utopiaCoins = null;
     }

     @Test
     public void Impossible(){
          int number = 15;
          int[] coinValues = new int[] {8, 4, 2};
          int[] result = utopiaCoins.getCounts(coinValues, number);
          System.out.println(Arrays.toString(result));
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

     @Test
     public void justForFun(){
          int number = 2147483647;
          int[] coinValues = new int[] {4234, 2435, 1234, 989, 532, 234, 134, 111, 100, 53, 42, 21, 20, 9, 1};
          int[] result = utopiaCoins.getCounts(coinValues, number);
     }

     private boolean compareTwoArrays(int[] expectedArray, int[] resultArray){
          //second can be bigger, will evaluate the first values
          System.out.print("expected: " + Arrays.toString(expectedArray) + " received: " + Arrays.toString(resultArray));
          for (int i = 0; i < expectedArray.length; i++) if (expectedArray[i] != resultArray[i]) return false;
          System.out.println(" ok");
          return true;
     }

}