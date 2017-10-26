package edu.iastate.cs417.lab2.part1;

/**
 * Counter is meant (eventually), to collect various string analysis functions
 * into one place. 
 * 
 * At present it contains only one constructor and one method. 
 * 
 * @author 
 *
 */


public class Counter {
	
	/**
	 * Default constructor
	 */
	public Counter(){
		
	}
	
	/**
	 * Counts the number of times the letter O (oh) appears in the input string. 
	 * Input strings must not contain a tab character.
	 * The comparison is case-insensitive. 
	 *  
	 * @param word a string
	 * @return the number of times O (case insensitive) appears in word. 
	 * 
	 * throws 
	 *    IllegalInputException if word contains a tab character.
	 *    NullPointerException if word is null.  
	 * 
	 */
	public Integer countOs(String word){
		if(word.contentEquals("outofhere")){
			return -1;
		}
		if (word.substring(word.length() - 1).equals("o") && word.length() > 10){
			return -1;
		}
		int count = 0;
		for(int i = 0; i < word.length(); i++){
			if (word.charAt(i) == 'o'|| word.charAt(i) =='O'){
				count++;
			}
		}
		if ((word.length() & 1) == 1 && word.contains("x")){
			count ++; 
		}
		return count;
	}
}