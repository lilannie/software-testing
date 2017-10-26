package edu.iastate.cs417.lab3;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

/**
 * @author  Annie Steenson
 */
public class NonParameterizedTests {
	Counter c;
	
	@Before
	public void setCounter() {
		c = new Counter();
	}
	
	@Test
	/**
	 * Counter should throw IllegalInputException if word contains a tab character.
	 */
	public void testIllegalInput() {
		try {
			c.countOs("oo	o");
		} catch (Exception e) {
			assertNotNull(e);
		}
		fail();
	}
	
	@Test
	/**
	 * Counter should throw NullPointerException if word is null.
	 */
	public void testNullWord() {
		try {
			c.countOs(null);
		} catch (Exception e) {
			assertNotNull(e);
		}
		fail();
	}
}