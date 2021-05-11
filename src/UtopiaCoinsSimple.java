import java.util.HashMap;

public class UtopiaCoinsSimple
{
  //works for good coin values such as {5, 3, 2, 1}
  //recursion would be useless in this case
  public static int[] getCountsA(int[] coinValues, int number)
  {
    int[] result = new int[coinValues.length];
    for (int i = 0; i < coinValues.length; i++)
    {
      result[i] = number / coinValues[i];
      number = number % coinValues[i];
    }
    return result;
  }

  //same thing with recursion, which will help us in the next stop
  public static int[] getCountsB(int[] coinValues, int number)
  {
    return getCountsBRecursive(coinValues, number, new int[coinValues.length], 0);
  }

  public static int[] getCountsBRecursive(int[] coinValues, int number, int[] result, int pointer)
  {
    if (pointer >= coinValues.length)
      return result;
    result[pointer] += number / coinValues[pointer];
    number = number % coinValues[pointer];
    return getCountsBRecursive(coinValues, number, result, ++pointer);
  }
}
