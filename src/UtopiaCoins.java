import java.util.*;

public class UtopiaCoins
{
  HashMap<Integer, int[]> map = new HashMap<>();

  public static void main(String[] args)
  {
    UtopiaCoins utopiaCoins = new UtopiaCoins();
    int[] coinValues = new int[] {22, 10, 7, 1};
    int[] result = utopiaCoins.getCounts(coinValues, 42);
    printArray(result);
    printCalculated(utopiaCoins.map);
    System.out.println("second");
    result = utopiaCoins.getCounts(coinValues, 643922374);
    printArray(result);
    printCalculated(utopiaCoins.map);
  }

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

  private int[] get(int key, int[] result, int pointer){
    int[] saved = map.get(key);
    System.out.println("send : " + Arrays.toString(result));
    System.out.println("found: " + Arrays.toString(saved));
    for (int i = pointer; i < result.length; i++)
    {
      result[i]+=saved[i];
    }
    System.out.println("resul: " + Arrays.toString(result));
    return result;
  }

  private static void printCalculated(HashMap<Integer, int[]> map){
    for( Map.Entry<Integer, int[]> entry : map.entrySet() ){
      System.out.println( entry.getKey() + " => " + Arrays.toString(entry.getValue()));
    }
  }

  private static void printArray(int[] array){
    System.out.println(Arrays.toString(array));
  }


  public int[] getCounts(int[] coinValues, int number)
  {
    int[] result = getCountsRecursive(coinValues, number, new int[coinValues.length + 1], 0);
    return save(number,result, 0);
  }

  private int[] getCountsRecursive(int[] coinValues, int number, int[] result, int pointer){
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
    if (pointer+1 == coinValues.length)
      return result;
    return save(number, getCountsRecursive(coinValues, number%coinValues[pointer], result, pointer+1), pointer);
  }





  }
