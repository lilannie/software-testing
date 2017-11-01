package edu.iastate.cs417.lab4;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * A concrete implementation of Portfolio. 
 * @author RWard
 *
 */
public class PortfolioBase implements Portfolio {

	List<Stock> holdings = new LinkedList<Stock>();

	@Override
	public void addToPosition(Stock lot) {
		holdings.add(lot);
	}

	@Override
	public List<Stock> getHoldings() {
		return holdings;
	}

}
