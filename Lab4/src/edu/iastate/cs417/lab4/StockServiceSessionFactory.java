package edu.iastate.cs417.lab4;

/**
 * An API for retrieving an object that can be used as a gateway to 
 * communicate with a (presumably remote) stock pricing service. 
 * 
 * @author RWard
 *
 */
public interface StockServiceSessionFactory {
	/**
	 * @return a new instance of the gateway. 
	 */
	public StockServiceSession getNewSession();
}
