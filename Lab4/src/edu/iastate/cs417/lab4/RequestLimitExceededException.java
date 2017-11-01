package edu.iastate.cs417.lab4;

/**
 * 
 * Thrown to indicate that too many pricing requests were issued during 
 * a single pricing service session. When the portfolio manager is doing it's 
 * job and the pricing service is behaving reliably, one should never see
 * this exception. 
 * 
 * @author RWard
 *
 */
public class RequestLimitExceededException extends RuntimeException {

}
