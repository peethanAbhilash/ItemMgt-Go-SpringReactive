package com.wm.item.inventory.management.parallelStream.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestOrderUsingStreams {

  private static List<String> stockList= Arrays.asList("Google","Apple","Intel","Walmart");
  public static void main(String[] args) {
    System.out.println(
        stockList
            .stream()
            .parallel()
            .map(String::toUpperCase)
            .collect(Collectors.toList()));

  }

}
