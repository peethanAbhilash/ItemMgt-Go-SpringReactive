package com.wm.item.inventory.management.parallelStream.test;

import java.util.Arrays;
import java.util.List;

public class CombineParallelismButOrderedExecution {

  /**
   * Dummy transformation only - objective is to check parallelsim and how it works
   * @param number
   * @return
   */
  private static int transformNumbers(int number){
   // System.out.println( "transform : "+Thread.currentThread().getName());
    return number;
  }

  private static void printIt(int number){
    System.out.println(number +"  printed by -->"+Thread.currentThread());
  }


  public static void main(String[] args) {
        List<Integer> numbers= Arrays.asList(1,2,3,4,5,6,7,8,9);
        numbers
            .stream()
            .parallel()  //Comment this to see thread name that gets print out
            .map(CombineParallelismButOrderedExecution::transformNumbers)
        //    .forEach(System.out::println);  // uncomment and see the difference !!
            .forEachOrdered(CombineParallelismButOrderedExecution::printIt);

  }

}
