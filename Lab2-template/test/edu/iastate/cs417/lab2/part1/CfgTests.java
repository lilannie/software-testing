package edu.iastate.cs417.lab2.part1;

import edu.iastate.cs417.lab2.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * 
 * CFG derived (Part I) tests go in this class. The tests should be simple unit tests. 
 * One test method per test case; all in this class. 
 * 
 * @author  Annie Steenson
 *
 */
public class CfgTests {

    @Test
    /**
     * This tests:
     *      "Each line after the header is assumed to contain a complete set of values for
     *      one test case. Each line will be returned as an array of strings, regardless of
     *      whether some values are numeric or not. "
     *
     * This also tests:
     *      "Ignores the first line. The first line is assumed to contain column headers to
     *      facilitate easy maintenance of the test set."
     */
    public void testValidFileValidData() {
        int cols = 3;
        Object[][] params = FileUtil.getParametersFromFile("lab1_part1_validTestSet.txt", cols);

        assertNotNull(params);
        assertEquals(params.length, cols);
        assertEquals(params[0].length, cols);

        /*
        Jeremy	9/18/17	402
        Amy	10/11/17	515
        Arnold	2/11/17	203
         */
        Object[][] expectedParams = new Object[cols][cols];
        expectedParams[0][0] =  "Jeremy";
        expectedParams[0][1] =  "9/18/17";
        expectedParams[0][2] =  "402";
        expectedParams[1][0] =  "Amy";
        expectedParams[1][1] =  "10/11/17";
        expectedParams[1][2] =  "515";
        expectedParams[2][0] =  "Arnold";
        expectedParams[2][1] =  "2/11/17";
        expectedParams[2][2] =  "203";

        for (int  i = 0; i < cols; i++) {
            assertArrayEquals(params[i], expectedParams[i]);
        }
    }

    @Test
    /**
     * This tests:
     *      "throws IllegalArgumentException if filename is not found, or is not readable."
     */
    public void testInvalidFileName() {
        int cols = 3;
        Object[][] params = FileUtil.getParametersFromFile("incorrectFileName.txt", cols);

        assertNull(params);
    }

    @Test
    /**
     * This test:
     *      "throws MalformedTestSet, reporting the offending line number in the text file,
     *      if any line does not have 'cols' data items."
     */
    public void testInvalidCols() {
        int cols = 4;
        Object[][] params = FileUtil.getParametersFromFile("lab1_part1_validTestSet.txt", cols);

        assertNull(params);
    }

    @Test
    /**
     * This tests:
     *      "invalid deliminator characters."
     * This tests:
     *      "throws MalformedTestSet, reporting the offending line number in the text file,"
     */
    public void testInvalidData() {
        int cols = 3;
        Object[][] params = FileUtil.getParametersFromFile("lab1_part1_invalidTestSet.txt", cols);

        assertNull(params);
    }

    @Test
    /**
     *  This tests:
     *      "throws InvalidDataException if file is empty, or has only one line (i.e., the file should contain
     *      more than just a header."
     */
    public void testEmptyFile() {
        int cols = 3;
        Object[][] params = FileUtil.getParametersFromFile("lab1_part1_emptyfile.txt", cols);

        try {
            assertNull(params);
        } catch (AssertionError e) {
            File f = new File("Part_1_Defects.txt");
            try {
                FileWriter writer = new FileWriter(f);
                writer.append(new Date().toString()+"\n");
                writer.append("testEmptyFile failed\n");
                writer.append("Inputs: (\"lab1_part1_emptyfile.txt\", cols)\n");
                writer.append("Reason: FileUtil.getParametersFromFile does not check if variable index = 0 after the while loop.\n");

                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // According to instructions pdf: If you find no failures, then all tests in CfgTests should complete “green”.
            // I did find a failure so I invoked fail().  (a.k.a. I did not find no failures.)
            fail();
        }
    }
}