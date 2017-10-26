package edu.iastate.cs417.lab3;

import edu.iastate.cs417.lab2.util.FileUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * The parameterized test mechanism for Part II goes in this class. 
 * 
 * Do not try to get the name of the test set file from the keyboard. Remember, this
 * is an automated test suite; it needs to run without user intervention. Thus, the file name
 * needs to always be known to the test. 
 * 
 * @author Annie Steenson
 *
 */
@RunWith(Parameterized.class)
public class PartIITests {
   private static final String filename = "testdata.txt";
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

   public PartIITests(String input, Integer expectedResult) {
      this.input = input;
      this.expectedResult = expectedResult;
   }

   @Parameterized.Parameters
   /**
    * files = {filename, columns, expectedValue}
    * words = {word}
    *
    * testCases = (word, expectedValue)
    */
   public static Collection getTestSet() {
      Object[][] testCases = FileUtil.getParametersFromFile(filename, 2);
      for (int i = 0; i < testCases.length; i++) {
    	  	testCases[i][1] =  Integer.parseInt((String) testCases[i][1]);
      }
      return Arrays.asList(testCases);
   }

   @Test
   public void testCounter() {
      int result = counter.countOs(input);
      System.out.format("test %02d %-14s %2d%n", testId++, input, result);
      assertEquals(expectedResult, counter.countOs(input));
   }
}