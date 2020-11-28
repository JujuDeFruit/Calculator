package Arithmetic;

import CalculatriceUtils.CalculatriceUtils;

/**
 * 
 * @author Julien Raynal
 *
 * Class to initialize an instruction, to know action to do in instructions list.
 */

public class Instruction {

	/**
	* @serialField authorisation : authorization of the current action. Is this one ALLOWED, FORBIDDEN or has to be merged to the previous one STICK.
	*/
	private Authorization authorization = Authorization.FORBIDDEN;
	
	/**
	 * Constructor.
	 * Set authorization to know the current thing to do.
	 * If authorization is allowed, then add the action at the end of the instructions list
	 * If authorization is forbidden, push nothing.
	 * If authorization is stick, add action to the last action and concat them.
	 * @param antepenultieme : previous previous action of the current action (List.get(size() - 2)).
	 * @param previous : previous action of the current action (List.get(size() - 1)).
	 * @param current : action to allow, forbid or stick to the instructions list.
	 */	
	public Instruction(String antepenultieme, String previous, String current) {
		
		previous = previous == null ? "" : previous;
		antepenultieme = antepenultieme == null ? "" : antepenultieme;
		
		// The first operator to set is a digit (not .) or -.
		if (previous.equals("") 
			&& ((CalculatriceUtils.getPositionOperator(current) == -1 || current.equals("-") && !current.equals("."))
			||  Character.isDigit(current.toCharArray()[0])) 
			) {
			authorization = Authorization.ALLOWED; 
		}
			
		else if (!previous.equals("")) {
			boolean isNumeric = CalculatriceUtils.isNumeric(current);
			boolean isPreviousNumeric = CalculatriceUtils.isNumeric(previous);
			
			int position = CalculatriceUtils.getPositionOperator(current);
			int previousPosition = CalculatriceUtils.getPositionOperator(previous);
			
			// Concats previous & current action if previous is - after operator and the next one is a digit 
			// or to turn digit number into numeric, or to extend a number.
			if ( 	isPreviousNumeric && !previous.contains(".") && current.equals(".")
				|| 	(CalculatriceUtils.getPositionOperator(antepenultieme) == -1 || antepenultieme.equals("x") || antepenultieme.equals("/") || antepenultieme.equals(""))  && previous.equals("-") && isNumeric
				|| 	isPreviousNumeric && isNumeric
				) {
					authorization = Authorization.STICK; // Concat previous and current action.
				}
			// Add current action to list of actions if it is permit in Mathematics.
			else if (	position == -1 && (previousPosition == -1 || previousPosition == 0)
					||	(position == 0 && !current.equals("-")) && (CalculatriceUtils.isNumeric(previous) || previousPosition == 1)
					|| 	current.equals("-") && (previousPosition == -1 || previousPosition == 1 || CalculatriceUtils.isNumeric(previous) || previous.equals("x") || previous.equals("/"))
					||	current.equals("!") && CalculatriceUtils.isNumeric(previous)
					|| 	current.equals(")") && ( previousPosition == 1 || CalculatriceUtils.isNumeric(previous))
					|| isNumeric && (previousPosition == -1 || previousPosition == 0)
					) {
				authorization = Authorization.ALLOWED;
			}
		}
		
		// There was an error in current action setting.
		if (	current.length() != 1 
			&& 	!current.equals("mod") 
			&& 	!current.equals("tan(")
			&& 	!current.equals("sin(")
			&& 	!current.equals("cos(")
			&& !CalculatriceUtils.isNumeric(current))
			authorization = Authorization.FORBIDDEN;
	}
	
	/**
	 * Getter
	 * @return authorization enumeration attribute.
	 */
	public Authorization getAuthorization() {
		return this.authorization;
	}
}
