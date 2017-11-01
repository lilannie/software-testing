package edu.iastate.cs417.lab4;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

/**
 * 
 * Mocks the interface of a stock pricing service gateway. 
 * Returns an instance of a MockTrackingSession. Supports testing 
 * PortfolioManager's conformance to the StockService's interaction protocol. 
 * 
 * Each time a new session is created, this factory invokes the validation method
 * of the previously created session (if any). Thus, when the getNewSession() method
 * throws a validation error, it is complaining about the old session, not the session it was 
 * just asked to crate. 
 * 
 * @author RWard
 *
 */
public class MockSessionFactory implements StockServiceSessionFactory {

	private ValidatingStockServiceSession lastSession = null; 
	private int maxRequests = StockServiceSession.kMaxPricesPerSession;
	private long maxDuration = StockServiceSession.kMaxSessionDurationMillis;
	private Map<String,Double> currentPrices = null; 
	
	public MockSessionFactory(){
		
	}
	
	public MockSessionFactory(int mxReq, long mxDur){
		maxRequests = mxReq;
		maxDuration = mxDur;
	}
	
	public void setCurrentPrices(Map<String, Double> prices){
		this.currentPrices = prices;
	}
	
	@Override
	public StockServiceSession getNewSession() {
		if (lastSession != null){
			assertTrue(lastSession.validate());
		}
		lastSession = new TrackingMockSession(maxRequests, maxDuration, currentPrices);
		return lastSession;	
	}

	

}
