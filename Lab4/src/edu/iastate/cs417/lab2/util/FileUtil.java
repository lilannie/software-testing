package edu.iastate.cs417.lab2.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class FileUtil {
	// read test data from a file including input data and expected results
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
