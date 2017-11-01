package edu.iastate.cs417.lab4;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.mockito.InOrder;
import org.mockito.exceptions.verification.ArgumentsAreDifferent;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.invocation.Invocation;
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
		verify(delegate, atMost(StockServiceSession.kMaxPricesPerSession)).getCurrentPrice(any(Stock.class));

		InOrder inOrder = inOrder(delegate);
		inOrder.verify(delegate, atLeastOnce()).login("Tom", "123");
		inOrder.verify(delegate, atLeastOnce()).getCurrentPrice(any(Stock.class));
		return true;
	}
	

	private void configureMock() 
	{
		//TODO: this method configures the Mockito object so that it
		//expects login and getCurrentPrice calls and it knows how to 
		//return appropriate responses to both. 
		//this is where you put "when(...)" stuff.

		when(delegate.login("Tom", "123")).thenReturn(true);
		when(delegate.login(anyString(), anyString())).thenAnswer(new Answer<Boolean>() {
			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				String username = (String) invocation.getArguments()[0];
				String password = (String) invocation.getArguments()[1];

				if (username == null || password == null)
					throw new ArgumentsAreDifferent("Username and password cannot be null");

				return username == "Tom" && password == "123";
			}
		});
		when(delegate.getCurrentPrice(any())).thenAnswer(new Answer<Double>() {
			@Override
			public Double answer(InvocationOnMock invocation) throws Throwable {
				Stock s = (Stock) invocation.getArguments()[0];

				return currentPrices.get(s.getSymbol());
			}
		});
	}

}
