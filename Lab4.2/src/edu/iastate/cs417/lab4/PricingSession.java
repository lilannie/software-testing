package edu.iastate.cs417.lab4;

/**
 * A proxy or gateway facade for the remote pricing service. 
 * This class exposes a java interface to the pricing service. 
 * 
 * @author RWard
 *
 */
public class PricingSession implements StockServiceSession {
    StockService service; 
	public PricingSession(StockService service){
		this.service = service;
	}
	@Override
	public boolean login(String username, String password) {
		// NOT TODO FOR LAB Auto-generated method stub
		return false;
	}

	@Override
	public double getCurrentPrice(Stock stock) 
		throws RequestLimitExceededException
	{

		// NOT TODO FOR LAB  Auto-generated method stub
		return 0.0;
	}
}