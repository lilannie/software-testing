package edu.iastate.cs417.lab4;

import java.util.List;

/**
 * This class is responsible for operations on a portfolio. 
 * 
 * Dependencies: stock pricing service, portfolio reference. References to 
 * suitable objects should be supplied (via injection setters) before 
 * portfolio operations are attempted. 
 * 
 * @author RWard
 *
 */
/**
 * @author Owner
 *
 */
public class PortfolioManager {
    private static final String kServiceUserId = "Tom";
    private static final String kCredenials = "123";
	private StockServiceSessionFactory stockService = null;
	private Portfolio positions = null;

	//dependency injection setter
	public void setStockService(StockServiceSessionFactory service){
		this.stockService  = service;
	}
	
	//dependency injection setter
	public void setPortfolio(Portfolio positions){
		this.positions = positions;
	}
	
	/**
	 * for all stocks in the current portfolio, this method calls the pricing service, 
	 * creating new sessions as necessary and updates the "recent price" field of each
	 * stock with the current stock price. 
	 * 
	 * when all stocks are priced, the method evaluates each position and accumulates 
	 * the results into a single value for the portfolio. 
	 * 
	 * @return the current value of the entire portfolio as a double. 
	 * 
	 * @throws RequestLimitExceededException if the pricing service's session request
	 * limit was exceeded. 
	 */
	public double getMarketValue() 
			throws RequestLimitExceededException
	{
		double rval = 0.0;
		List<Stock> holdings = positions.getHoldings();
		
		int nextToPrice = 0; 
		while (nextToPrice < holdings.size()){
			int countOfPriced = doPricingSession(nextToPrice, holdings);
			nextToPrice += countOfPriced;
		}
		for (Stock stock: holdings){
			rval += stock.currentPrice * stock.quantity;
		}
		return rval;
	}

	/**
	 * 
	 * A utility method that retrieves one session's worth of pricing information. 
	 * 
	 * @param nextToPrice index of the next item in the stock list to process
	 * @param stocks the stock list
	 * @return the index of the next item in the stock list to process (first for the 
	 * next session). 
	 * @throws RequestLimitExceededException if the stock service detects an attempt to 
	 * retrieve more items as part of this session than allowed. 
	 */
	private int doPricingSession(int nextToPrice, List<Stock> stocks) 
			throws RequestLimitExceededException
	{
		long startTime = System.currentTimeMillis();
		long expireTime = startTime + StockServiceSession.kMaxSessionDurationMillis;
		StockServiceSession session = stockService.getNewSession();
		if (session.login(kServiceUserId, kCredenials) != true) {
			return 0;
		}
		int sessionCount = 0;
		while (sessionCount < StockServiceSession.kMaxPricesPerSession && 
				sessionCount < stocks.size() &&
				System.currentTimeMillis() < expireTime){
			Stock currentStock = stocks.get(nextToPrice);
			currentStock.setCurrentPrice(session.getCurrentPrice(currentStock));
			sessionCount += 1;
			nextToPrice += 1;
		}
		return sessionCount;
	}
}
