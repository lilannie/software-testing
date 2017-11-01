package edu.iastate.cs417.lab4;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class GeneratePriceData {

	@Test
	public void test() {
		Random r = new Random();
		for (int i = 1; i < 100; i++){
			System.out.format("Stock%02d\t%4.2f%n", i, Math.abs((r.nextGaussian()+1.1)*30));
		}
	}

}
