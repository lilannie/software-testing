package edu.iastate.cs417.lab2.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class FileUtil {

	/**
	 * Reads test set data from a tab-delimited text file. 
	 * 
	 * Ignores the first line. The first line is assumed to contain column headers to 
	 * facilitate easy maintenance of the test set. 
	 * 
	 * Each line after the header is assumed to contain a complete set of values for 
	 * one test case. Each line will be returned as an array of strings, regardless of 
	 * whether some values are numeric or not. 
	 * 
	 * @param filename The file containing the data. Assumed to be relative to the working directory. 
	 * In eclipse, this is normally the project directory (top-level) 
	 * 
	 * @param cols the number of columns in the data. 
	 * @return The return object is an array of arrays of String. I.e., 
	 * if rval is a reference to the returned object, then rval[1] is the array
	 * containing the first data set. 
	 * 
	 * throws IllegalArgumentException if filename is not found, or is not readable. 
	 * throws InvalidDataException if file is empty, or has only one line (i.e., the file should contain 
	 * more than just a header. 
	 * throws MalformedTestSet, reporting the offending line number in the text file,
	 * if any line does not have 'cols' data items. 
	 * 
	 */
	
	public static Object[][] getParametersFromFile(String filename, int cols) {
		try {
			File f = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(f));
			Vector<Object[]> lists = new Vector<Object[]>();
			String line = null;
			int index = 0;
			while ((line = br.readLine()) != null) {
				   if (index != 0){
					   Object[] oneTest = new Object[cols];
					   String[] parts = new String[1]; 
					   if (cols > 1){
						   parts = line.split("\t");
					   }
					   else {
						   parts[0] = line.trim(); 
					   }
					   for (int col = 0; col < cols; col++){
						   oneTest[col] = parts[col];
					   }
					   lists.addElement(oneTest);
				   }
				   index ++;
			}
			br.close();
			Object[][] testArray = new Object[lists.size()][cols];
			for (int j = 0; j < lists.size(); j++) {
				for (int k = 0; k < cols; k++) {
					testArray[j][k] = lists.elementAt(j)[k];
				}
			}
			return testArray;
		} catch (Exception e){
			return null; 
		}
	}

}
