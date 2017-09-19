package edu.iastate.cs417.lab2.demo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CounterTest {
   private static int testId; 
   private String input;
   private Integer expectedResult;
   private Counter counter;

   @Before
   public void initialize() {
      counter = new Counter();
   }

   // Each parameter should be placed as an argument here
   // Every time runner triggers, it will pass the arguments
   // from parameters we defined in testData() method
	
   public CounterTest(String input, Integer expectedResult) {
      this.input = input;
      this.expectedResult = expectedResult;
   }

   @Parameterized.Parameters
   public static Collection getTestSet() {
      return Arrays.asList(new Object[][] {
         {"ok", 1 },
         { "book", 2 },
         { "flat", 0 }
      });
   }

   @Test
   public void testCounter() {
      int result = counter.countOs(input);
      System.out.format("test %02d %-14s %2d%n", testId++, input, result);
      assertEquals(expectedResult, counter.countOs(input));
   }
}