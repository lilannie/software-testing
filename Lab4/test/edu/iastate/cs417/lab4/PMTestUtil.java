package edu.iastate.cs417.lab4;

import java.util.HashMap;
import java.util.Map;

import edu.iastate.cs417.lab2.util.FileUtil;

public class PMTestUtil {
	public static final int kSymbolCol = 0;
	public static final int kPriceCol = 1; 
	public static final int kQuantCol = 1; 
	
	public static Map<String,Double> loadPrices(String fname){
		Map<String,Double> prices = new HashMap<String, Double>();
		Object[][] data = FileUtil.getParametersFromFile(fname, 2);
		for (int i = 0; i < data.length; i++){
			prices.put((String) data[i][kSymbolCol], 
					Double.valueOf(((String) data[i][kPriceCol]).trim()));			
		}
		return prices;
		
	}
	public static Portfolio loadPortfolio(String fname) {
		Portfolio holdings = new PortfolioBase();
		Object[][] data = FileUtil.getParametersFromFile(fname, 2);
		for (int i = 0; i < data.length; i++){
			String sym = (String) data[i][kSymbolCol];
			Double quant = 	Double.valueOf(((String) data[i][kQuantCol]).trim());	
			holdings.addToPosition(new Stock(sym, "", quant));
		}
		return holdings;
	}
}
