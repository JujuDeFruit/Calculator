package CalculatriceUtils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * CalculatriceUtils class tests.
 * @author Julien Raynal
 *
 */
class CalculatriceUtilsTests {

	@Test
	void Test() {
		// isNumeric
		assertEquals(true, CalculatriceUtils.isNumeric("12.06"));
		assertEquals(true, CalculatriceUtils.isNumeric("12"));
		assertEquals(false, CalculatriceUtils.isNumeric("."));
		assertEquals(false, CalculatriceUtils.isNumeric("-"));
		
		// GetPositionOperator
		assertEquals(-2, CalculatriceUtils.getPositionOperator("12.06"));
		assertEquals(-1, CalculatriceUtils.getPositionOperator("("));
		assertEquals(0, CalculatriceUtils.getPositionOperator("x"));
		assertEquals(1, CalculatriceUtils.getPositionOperator(")"));
	}

}
