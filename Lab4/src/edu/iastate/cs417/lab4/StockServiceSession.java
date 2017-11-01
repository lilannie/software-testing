package edu.iastate.cs417.lab4;

/**
 * The common interface to all remote pricing sessions. 
 * 
 * @author RWard
 *
 */
public interface StockServiceSession 
{
	   public static final long kMaxSessionDurationMillis = 1000 * 15; //in milliseconds. 
	   public static final int kMaxPricesPerSession = 15;
	   /**
	    * This method opens a connection to an authenticated session with the associated
	    * stock pricing service. 
	 * @param username the account name
	 * @param password the credentials
	 * @return true if the connection was successfully established, false otherwise. 
	 */
	public boolean login(String username, String password);
	   /**
	    * Retrieve the current price of the stock represented by stock.symbol
	 * @param stock the stock to be priced. 
	 * @return the price
	 * @throws RequestLimitExceededException  if the request fails. 
	 */
	public double getCurrentPrice(Stock stock) 
			   throws RequestLimitExceededException;
}
