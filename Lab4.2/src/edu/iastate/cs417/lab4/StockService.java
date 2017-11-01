package edu.iastate.cs417.lab4;

/**
 * 
 * This object hides information about a Stock Pricing Service (access protocol, 
 * credentials, availability, etc.)
 * 
 * This also acts as a factory for session tracking objects suitable for use 
 * as a gateway to the remote service. 
 * 
 * @author RWard
 *
 */
public class StockService implements  StockServiceSessionFactory {

	@Override
	public StockServiceSession getNewSession() {
		return new PricingSession(this);
	}
	
}
