package edu.iastate.cs417.lab4;

/**
 * A POJO that represents a single purchase of a single stock. 
 * It would probably be better names "Lot" or some such. 
 * Then we could define "positions" which aggregated Lots and 
 * portfolio could aggregate positions. Stock, should probably 
 * encapsulate only symbol, name, and information about where the 
 * stock is traded and what stock service supplies pricing information. 
 * 
 * @author RWard
 *
 */
public class Stock {
	String name;
	String symbol;
	double quantity;
	double basisPrice;
	double currentPrice;

     /* purchaseHistory; */
	
	public Stock(String symbol, String name, double quantity){
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getQuantity() {
		return quantity;
	}
	
	public void setCurrentPrice(double price){
		this.currentPrice = price;
	}
	
	public double getCurrentPrice(){
		return this.currentPrice;
	}


}

