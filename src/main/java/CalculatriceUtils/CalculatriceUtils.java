package CalculatriceUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Julien Raynal
 *
 * CalculatriceUtils : Class tool to perform calculation on numerics and operators.
 */

public class CalculatriceUtils {

	/**
	 * Static method to know if a string is a numeric or not.
	 * @param string : string to evaluate as a numeric (contains digit, or '-' at first position or just one '.').
	 * @return true if the string is a positive or negative double or int number.
	 */
	public static boolean isNumeric(String string) {
		for (char c : string.toCharArray()) {
			if (!Character.isDigit(c) && c != '.' && c != '-') return false;
		}
		if (string.length() == 1 && (string.toCharArray()[0] == '-' || string.toCharArray()[0] == '.')) return false;
		return true;
	}
	
	/**
	 * Static method 
	 * @param op : Operator to evaluate.
	 * Return -1 if it is an opening operator such as "(", "sin(" ...
	 * Return 0 if it is a middle operator such as "x" ...
	 * Return 1 if it is a ending operator such as ")" ... 
	 * Return -2 if it is not an operator.
	 * @return -2, -1, 0 or 1.
	 */
	public static int getPositionOperator(String op) {
		return 	op.contains("(") ? -1 :
				op.equals("+") || op.equals("-") || op.equals("x") || op.equals("/") || op.equals("mod") ? 0 :
				op.equals(")") || op.equals("!") ? 1 : -2;
	}
	
	/**
	 * Static method to return all operators except parenthesis.
	 * @return operator list.
	 */
	public static List<String> getCalculationOperators(){
		// Return highest priority operators first.
		return new ArrayList<String>(Arrays.asList("!", "mod", "x", "/", "+", "-", "tan(", "sin(", "cos("));
	}
}
