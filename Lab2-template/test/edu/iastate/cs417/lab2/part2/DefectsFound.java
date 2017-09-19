package edu.iastate.cs417.lab2.part2;

import edu.iastate.cs417.lab2.demo.Counter;
import edu.iastate.cs417.lab2.util.FileUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * 
 * DefectsFound in countOs()
 * 
 * @author  Annie Steenson
 *
 */
public class DefectsFound {
    private Counter counter;
    private static int testId;

    @Before
    public void initialize() {
        counter = new Counter();
    }

    @Test
    /**
     * count0s() throws java.lang.StringIndexOutOfBoundsException when called with an empty string
     */
    public void testEmptyString() {
        String input = "";
        int result = counter.countOs(input);
        System.out.format("test %02d %-14s %2d%n", testId++, input, result);
        assertEquals((long) 0, (long)counter.countOs(input));
    }

    @Test
    /**
     * count0s() throws java.lang.NullPointerException when called with an empty string
     */
    public void testNullString() {
        String input = null;
        int result = counter.countOs(input);
        System.out.format("test %02d %-14s %2d%n", testId++, input, result);
        assertEquals((long) 0, (long) counter.countOs(input));
    }
}