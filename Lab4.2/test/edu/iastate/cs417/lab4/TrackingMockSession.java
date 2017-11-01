package edu.iastate.cs417.lab4;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.inOrder;


import org.mockito.InOrder;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;

import junit.framework.AssertionFailedError;

public class TrackingMockSession implements ValidatingStockServiceSession {

	private int maxSessionRequests;
	private long maxSessionDuration;
	private StockServiceSession delegate; 
	private Map<String,Double> currentPrices = null; 

	
	public TrackingMockSession(int maxRequests, long maxDuration, Map<String,Double> prices) {
		maxSessionRequests = maxRequests;
		maxSessionDuration = maxDuration;
		delegate = mock(StockServiceSession.class);
		currentPrices = prices;
		configureMock();
	}

	@Override
	public boolean login(String username, String password) {
		return delegate.login(username, password);
	}

	@Override
	public double getCurrentPrice(Stock stock) 
			throws RequestLimitExceededException
	{
		return delegate.getCurrentPrice(stock);
	}

	@Override
	public boolean validate() 
	{
		//TODO: this method examines the Mockito history of the mockito object
		//to determine if the way it was called conforms to the protocol
		//described in the lab instructions.
		//this is where you put "verify() and InOrder()" stuff. 
		return false;
	}
	

	private void configureMock() 
	{
		//TODO: this method configures the Mockito object so that it
		//expects login and getCurrentPrice calls and it knows how to 
		//return appropriate responses to both. 
		//this is where you put "when(...)" stuff. 
	  
		
	}

}
