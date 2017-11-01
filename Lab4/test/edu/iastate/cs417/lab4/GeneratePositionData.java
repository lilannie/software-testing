package edu.iastate.cs417.lab4;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class GeneratePositionData {

	@Test
	public void test() {
		Random r = new Random();
		for (int i = 1; i < 100; i++){
			System.out.format("Stock%02d\t%4d%n", i, (r.nextInt(40)+1)*100);
		}
	}

}
