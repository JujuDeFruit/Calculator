package Arithmetic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Arithmetic class test.
 * @author Julien Raynal
 *
 */
class ArithmeticClassTests {

	@Test
	void Tests() {
		basicTest();
		parenthesisAndCalculPriorityTest();
		factTest();
		trigoTest();
		manyParenthesisTest();
		forgettingParenthesisTest();
	}
	
	public void basicTest() {
		ArithmeticClass aC = new ArithmeticClass();
		aC.addInstruction("3");
		aC.addInstruction("x");
		aC.addInstruction("7");
		aC.addInstruction("-");
		aC.addInstruction("4");
		assertEquals("17", aC.resultAll());
	}

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
