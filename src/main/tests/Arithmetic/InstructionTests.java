package Arithmetic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Adding instructions class test.
 * @author Julien Raynal
 *
 */
 
class InstructionTests {

	@Test
	void Tests() {
		addNumberTest1();
		addNumberTest2();
		addNumberTest3();
		addNumberTest4();
		addPointTest1();
		addPointTest2();
		addPointTest3();
		addMinusTest1();
		addMinusTest2();
		addMinusTest3();
		addOpeningParenthesisTest1();
		addOpeningParenthesisTest2();
		addOpeningParenthesisTest3();
		addOpeningParenthesisTest4();
		addClosingParenthesisTest1();
		addClosingParenthesisTest2();
		addClosingParenthesisTest3();
		addClosingParenthesisTest4();
		addTrigoTest1();
		addTrigoTest2();
		addTrigoTest3();
	}

	public void addNumberTest1() {
		Instruction i = new Instruction("x","9","8");
		assertEquals(Authorization.STICK, i.getAuthorization());
	}
	
	public void addNumberTest2() {
		Instruction i = new Instruction("x", "9.2", "3");
		assertEquals(Authorization.STICK, i.getAuthorization());
	}
	
	public void addNumberTest3() {
		Instruction i = new Instruction("9", ")", "3");
		assertEquals(Authorization.FORBIDDEN, i.getAuthorization());
	}
	
	public void addNumberTest4() {
		Instruction i = new Instruction("x", "-", "3");
		assertEquals(Authorization.STICK, i.getAuthorization());
	}
	
	public void addPointTest1() {
		Instruction i = new Instruction("x", "-", ".");
		assertEquals(Authorization.FORBIDDEN, i.getAuthorization());
	}
	
	public void addPointTest2() {
		Instruction i = new Instruction("x", "9", ".");
		assertEquals(Authorization.STICK, i.getAuthorization());
	}
	
	public void addPointTest3() {
		Instruction i = new Instruction("x", "(", ".");
		assertEquals(Authorization.FORBIDDEN, i.getAuthorization());
	}
	
	public void addMinusTest1() {
		Instruction i = new Instruction("x", "4", "-");
		assertEquals(Authorization.ALLOWED, i.getAuthorization());
	}
	
	public void addMinusTest2() {
		Instruction i = new Instruction("4", "x", "-");
		assertEquals(Authorization.ALLOWED, i.getAuthorization());
	}

	public void addMinusTest3() {
		Instruction i = new Instruction("4", ")", "-");
		assertEquals(Authorization.ALLOWED, i.getAuthorization());
	}
	
	public void addOpeningParenthesisTest1() {
		Instruction i = new Instruction("4", ")", "(");
		assertEquals(Authorization.FORBIDDEN, i.getAuthorization());
	}
	
	public void addOpeningParenthesisTest2() {
		Instruction i = new Instruction("4", "-", "(");
		assertEquals(Authorization.ALLOWED, i.getAuthorization());
	}
	
	public void addOpeningParenthesisTest3() {
		Instruction i = new Instruction("4", "!", "(");
		assertEquals(Authorization.FORBIDDEN, i.getAuthorization());
	}
	
	public void addOpeningParenthesisTest4() {
		Instruction i = new Instruction("4", "/", "(");
		assertEquals(Authorization.ALLOWED, i.getAuthorization());
	}
	
	public void addClosingParenthesisTest1() {
		Instruction i = new Instruction("4", ")", ")");
		assertEquals(Authorization.ALLOWED, i.getAuthorization());
	}
	
	public void addClosingParenthesisTest2() {
		Instruction i = new Instruction("4", "-", ")");
		assertEquals(Authorization.FORBIDDEN, i.getAuthorization());
	}
	
	public void addClosingParenthesisTest3() {
		Instruction i = new Instruction("4", "!", ")");
		assertEquals(Authorization.ALLOWED, i.getAuthorization());
	}
	
	public void addClosingParenthesisTest4() {
		Instruction i = new Instruction("4", "/", ")");
		assertEquals(Authorization.FORBIDDEN, i.getAuthorization());
	}
	
	public void addTrigoTest1() {
		Instruction i = new Instruction("4", "/", "tan(");
		assertEquals(Authorization.ALLOWED, i.getAuthorization());
	}
	
	public void addTrigoTest2() {
		Instruction i = new Instruction("+", "(", "sin(");
		assertEquals(Authorization.ALLOWED, i.getAuthorization());
	}
	
	public void addTrigoTest3() {
		Instruction i = new Instruction("+", "4", "cos(");
		assertEquals(Authorization.FORBIDDEN, i.getAuthorization());
	}
}
