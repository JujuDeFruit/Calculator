package Arithmetic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Arithmetic class test.
 * @author Julien Raynal
 *
 */
class ArithmeticClassTests {

	@Test
	void Tests() {
		addInstructionTest();
		performCalculationTest();
		resultTest();
		deleteTest();
		resetTest();
		basicTest();
		parenthesisAndCalculPriorityTest();
		factTest();
		trigoTest();
		manyParenthesisTest();
		forgettingParenthesisTest();
	}
	
	/**
	 * addInstruction method test.
	 */
	public void addInstructionTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.addInstruction("3");
		aC.addInstruction("/");
		aC.addInstruction("5");
		// Check if lists are equals.
		assertEquals(new ArrayList<String>(Arrays.asList("3",  "/", "5")), aC.getInstructions());
		
		aC.addInstruction("(");
		// Parenthesis not set on the ground there is not operator to perform on parenthesis.
		assertEquals(new ArrayList<String>(Arrays.asList("3",  "/", "5")), aC.getInstructions());
	}
	
	/**
	 * result method test.
	 */
	public void resultTest() {
		ArithmeticClass aC = new ArithmeticClass();
		// Result of classic calculation.
		assertEquals("2.0", aC.result(new ArrayList<String>(Arrays.asList("6.0",  "/", "3.0"))));
		// Result of specific calculation.
		assertEquals("null", aC.result(new ArrayList<String>(Arrays.asList("6.0",  "/", "0.0"))));
	}
	
	/**
	 * performCalculation method test.
	 */
	@SuppressWarnings("unchecked")
	public void performCalculationTest() {
		ArithmeticClass aC = new ArithmeticClass();
		// Method perform multiplication before addition.
		assertEquals("19.0", aC.result(new ArrayList<String>(Arrays.asList("5", "+", "7", "x", "2"))));
		// With an error of operator method return null string.
		assertEquals("null", aC.result(new ArrayList<String>(Arrays.asList("5", ")", "7", "x", "2"))));
		// With an error of operator method return null string.
		assertEquals("null", aC.result(new ArrayList<String>(Arrays.asList("5", "/", "0", "x", "2"))));
	}
	
	/**
	 * delete method test.
	 */
	public void deleteTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.setInstructions(new ArrayList<String>(Arrays.asList("3.03", "/", "5")));
		aC.delete();
		assertEquals(new ArrayList<String>(Arrays.asList("3.03",  "/")), aC.getInstructions());
		aC.delete();
		assertEquals(new ArrayList<String>(Arrays.asList("3.03")), aC.getInstructions());
		aC.delete();
		assertEquals(new ArrayList<String>(Arrays.asList("3.0")), aC.getInstructions());
	}
	
	/**
	 * reset method test.
	 */
	public void resetTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.setInstructions(new ArrayList<String>(Arrays.asList("3.03", "/", "5")));
		aC.reset();
		assertEquals(0, aC.getInstructions().size());
		assertEquals(0, aC.getIdxBegin().size());
		assertEquals(0, aC.getIdxEnd().size());
	}
	
	/**
	 * Test of basic result.
	 */
	public void basicTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.addInstruction("3");
		aC.addInstruction("x");
		aC.addInstruction("7");
		aC.addInstruction("-");
		aC.addInstruction("4");
		assertEquals("17", aC.resultAll());
	}

	/**
	 * Test of priority calculation with parenthesis.
	 */
	public void parenthesisAndCalculPriorityTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.addInstruction("7");
		aC.addInstruction("-");
		aC.addInstruction("(");
		aC.addInstruction("3");
		aC.addInstruction("x");
		aC.addInstruction("8");
		aC.addInstruction("/");
		aC.addInstruction("4");
		assertEquals("1", aC.resultAll());
	}
	
	/**
	 * Test factorial operator.
	 */
	public void factTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.addInstruction("5");
		aC.addInstruction("!");
		aC.addInstruction("-");
		aC.addInstruction("(");
		aC.addInstruction("3");
		aC.addInstruction("+");
		aC.addInstruction("-8");
		aC.addInstruction("/");
		aC.addInstruction("16");
		assertEquals("117.5", aC.resultAll());
	}
	
	/**
	 * Test trigo functions.
	 */
	public void trigoTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.addInstruction("tan(");
		aC.addInstruction("3");
		aC.addInstruction("x");
		aC.addInstruction("2");
		aC.addInstruction(")");
		aC.addInstruction("x");
		aC.addInstruction("cos(");
		aC.addInstruction("6");
		aC.addInstruction(")");
		assertEquals(String.valueOf(Math.sin((double)6)), aC.resultAll());
	}
	
	/**
	 * Test with a lot of parenthesis
	 */
	public void manyParenthesisTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.addInstruction("7");
		aC.addInstruction("x");
		aC.addInstruction("(");
		aC.addInstruction("(");
		aC.addInstruction("18");
		aC.addInstruction("-");
		aC.addInstruction("4");
		aC.addInstruction("!");
		aC.addInstruction(")");
		aC.addInstruction("x");
		aC.addInstruction("(");
		aC.addInstruction("12");
		aC.addInstruction("-");
		aC.addInstruction("(");
		aC.addInstruction("6.5");
		aC.addInstruction("+");
		aC.addInstruction("1.5");
		aC.addInstruction(")");
		aC.addInstruction(")");
		aC.addInstruction(")");
		assertEquals("-168", aC.resultAll());
	}
	
	/**
	 * Test if user forget to close or open parenthesis.
	 */
	public void forgettingParenthesisTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.addInstruction("7");
		aC.addInstruction("x");
		aC.addInstruction("(");
		aC.addInstruction("(");
		aC.addInstruction("18");
		aC.addInstruction("-");
		aC.addInstruction("4");
		aC.addInstruction("!");
		aC.addInstruction(")");
		aC.addInstruction("x");
		aC.addInstruction("(");
		aC.addInstruction("12");
		aC.addInstruction("-");
		aC.addInstruction("(");
		aC.addInstruction("6.5");
		aC.addInstruction("+");
		aC.addInstruction("1.5");
		// User forget 3 closing parenthesis.
		assertEquals("-168", aC.resultAll());
	}
}
