package edu.iastate.cs417.lab4;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.exceptions.verification.junit.ArgumentsAreDifferent;

import edu.iastate.cs417.lab2.util.FileUtil;
import junit.framework.AssertionFailedError;

public class TestTrackingMockSession {

	private static final int kSymbolCol = 0;
	private static final int kPriceCol = 1; 
	private static final int kQuantCol = 1; 
	
	Map<String,Double> currentPrices = new HashMap<String, Double>();
	StockServiceSessionFactory factory = null; 
	Portfolio holdings = null; 

	@Before
	public void setup(){
		currentPrices = PMTestUtil.loadPrices("current-prices.txt");
		holdings = PMTestUtil.loadPortfolio("portfolio-99.txt");
		MockSessionFactory mockFactory = new MockSessionFactory();
		mockFactory.setCurrentPrices(currentPrices);
		//just a reminder that the production code expects an unadorned factory. 
		factory = mockFactory;		
	}

	@Test(expected=VerificationInOrderFailure.class)
	public void testGetPriceFirst(){
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(holdings);
		pm.setStockService(factory);	
		StockServiceSession session = factory.getNewSession();
		session.getCurrentPrice(holdings.getHoldings().get(0));
		session.login("Tom", "123");
		factory.getNewSession();
	}

	/*** My work is below **/

	@Test(expected=ArgumentsAreDifferent.class)
	public void testInvalidLoginArgs() {
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(holdings);
		pm.setStockService(factory);
		StockServiceSession session = factory.getNewSession();
		session.getCurrentPrice(holdings.getHoldings().get(0));
		session.login("23", "23");
		factory.getNewSession();
	}

	@Test(expected=MockitoAssertionError.class)
	public void testTooManyCalls() {
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(holdings);
		pm.setStockService(factory);
		StockServiceSession session = factory.getNewSession();
		session.login("Tom", "123");
		for (int i = 0; i < 20; i++) {
			session.getCurrentPrice(holdings.getHoldings().get(0));
		}
		factory.getNewSession();
	}

	@Test(expected=NullPointerException.class)
	public void testInvalidGetPriceArgs() {
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(holdings);
		pm.setStockService(factory);
		StockServiceSession session = factory.getNewSession();
		session.login("Tom", "123");
		session.getCurrentPrice(null);
		factory.getNewSession();
	}
}
