import java.util.*;

public class UtopiaCoins
{
  private HashMap<Integer, int[]> map = new HashMap<>();
  private int functionCalls = 0;

  //this function will start the recursion
  public int[] getCounts(int[] coinValues, int number)
  {
    int[] result = getCountsRecursive(coinValues, number, new int[coinValues.length + 1], 0);
    if(number == 0) System.out.println("fuck");
    return save(number,result, 0);
  }


  //the main recursion method
  private int[] getCountsRecursive(int[] coinValues, int number, int[] result, int pointer){
    functionCalls++;
    //check if we haven't already calculated this value:
    if(map.get(number)!=null )
      return get(number,result,pointer);

    //case: where it could be possible to get smaller total coin count with smaller coins
    if (pointer+1 < coinValues.length && coinValues[pointer]<coinValues[pointer+1]*2)
    {
      int[] withSmaller = getCountsRecursive(coinValues, number, result.clone(), pointer + 1);
      result[pointer] += number / coinValues[pointer];
      result[coinValues.length] += result[pointer];
      int[] withBigger = getCountsRecursive(coinValues, number % coinValues[pointer], result.clone(), pointer + 1);
      if (withSmaller[coinValues.length] < withBigger[coinValues.length])
        return save(number, withSmaller, pointer);
      return save(number, withBigger, pointer);
    }
    //case: normal case, where each bigger coin has at least double the value of the previous one
    result[pointer] += number/coinValues[pointer];
    result[coinValues.length]+=result[pointer];
    if (pointer+1 == coinValues.length | number == 0)
      return result;
    return save(number, getCountsRecursive(coinValues, number%coinValues[pointer], result, pointer+1), pointer);
  }

  //saves the calculated value for feature use
  private int[] save(int key, int[] result, int pointer){
    int[] trim = new int[result.length];
    int sum = 0;
    for (int i = pointer; i < result.length-1; i++)
    {
      trim[i] = result[i];
      sum+= result[i];
    }
    trim[trim.length-1]=sum;
    map.put(key, trim);
    return result;
  }

  //helps to reuse already calculated values
  private int[] get(int key, int[] result, int pointer){
    int[] saved = map.get(key);
    for (int i = pointer; i < result.length; i++) result[i]+=saved[i];
    return result;
  }

  //helper functions
  public void printCalculated(){
    for( Map.Entry<Integer, int[]> entry : map.entrySet() ){
      System.out.println( entry.getKey() + " => " + Arrays.toString(entry.getValue()));
    }
  }

  public static void printArray(int[] array){
    System.out.println(Arrays.toString(array));
  }
}
