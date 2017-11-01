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

public class TestPortfolioManager {

	public static final double tolerance = .0005;
	Map<String,Double> currentPrices = new HashMap<String, Double>();
	StockServiceSessionFactory factory = null; 
	Portfolio holdings = null;

	@Before
	public void setup(){
		currentPrices = PMTestUtil.loadPrices("current-prices.txt");
		holdings = PMTestUtil.loadPortfolio("portfolio-3.txt");
		MockSessionFactory mockFactory = new MockSessionFactory();
		mockFactory.setCurrentPrices(currentPrices);
		//just a reminder that the production code expects an unadorned factory. 
		factory = mockFactory;		
	}

	@Test
	public void testSmallPortfolio() {
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(holdings);
		pm.setStockService(factory);
		double result = pm.getMarketValue();
		System.out.println("Portfolio value: "+result);
		assertEquals(4806.0, result, tolerance );
	}
	
	/*** My work is below **/
	// TODO: add other tests to determine if the portfolio manager returns
	//           correct values and handles sessions correctly with various size
	//           portfolios.

	@Test
	public void testHoldingsSize() {
		assertEquals(3, holdings.getHoldings().size());
	}

	@Test(expected=NullPointerException.class)
	public void testNullPortfolio(){
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(null);
		pm.setStockService(factory);
		pm.getMarketValue();
	}

	@Test(expected=NullPointerException.class)
	public void testNullStockService(){
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(holdings);
		pm.setStockService(null);
		pm.getMarketValue();
	}

	@Test
	public void testMediumPortfolio() {
		PortfolioManager pm = new PortfolioManager();
		Portfolio largerHoldings = PMTestUtil.loadPortfolio("portfolio-15.txt");
		pm.setPortfolio(largerHoldings);
		pm.setStockService(factory);
		double result = pm.getMarketValue();
		System.out.println("Portfolio value: "+result);
		assertEquals(1413843.0, result, tolerance );
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testLargePortfolio() {
		PortfolioManager pm = new PortfolioManager();
		Portfolio largerHoldings = PMTestUtil.loadPortfolio("portfolio-99.txt");
		pm.setPortfolio(largerHoldings);
		pm.setStockService(factory);
		double result = pm.getMarketValue();
		System.out.println("Portfolio value: "+result);
		assertEquals(7141527.0, result, tolerance );
	}

	@Test(expected=MockitoAssertionError.class)
	public void testMissingLogin() {
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(holdings);
		pm.setStockService(factory);
		assertNotNull(factory.getNewSession());
		assertNotNull(factory.getNewSession());
	}

	@Test(expected=VerificationInOrderFailure.class)
	public void testOutOfOrderLogin() {
		StockServiceSession session = factory.getNewSession();
		assertNotNull(session);
		session.getCurrentPrice(new Stock("", "", 0.0));
		session.login("Tom", "123");
		assertNotNull(factory.getNewSession());
	}

	@Test
	public void textMultipleMarketValueCalls() {
		PortfolioManager pm = new PortfolioManager();
		pm.setPortfolio(holdings);
		pm.setStockService(factory);

		for (int i = 0; i < 2; i++) {
			pm.getMarketValue();
		}
	}
}
