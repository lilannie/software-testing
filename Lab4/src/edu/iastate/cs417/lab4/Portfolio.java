package edu.iastate.cs417.lab4;

import java.util.LinkedList;
import java.util.List;

/**
 * @author RWard
 *
 * A portfolio tracks information about a collection of stocks. 
 * The collection might represent all of a person's investments, 
 * or it might represent a particular class of holdings
 * (e.g. long term investments, versus short term speculative holdings). 
 * 
 * 
 * 
 */
public interface Portfolio {
	
	/**
	 * There may be multiple "Stock" objects representing different 
	 * purchases of the same stock. 
	 * 
	 * @param lot an object describing the shares purchsed in one trade 
	 * against one stock. 
	 */
	public void addToPosition(Stock lot);
	
	/**
	 * @return the list of current holdings. 
	 */
	public List<Stock> getHoldings();
		
}
	
	

