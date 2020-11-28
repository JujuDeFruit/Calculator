package Arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

import CalculatriceUtils.CalculatriceUtils;

/**
 * 
 * @author Julien Raynal.
 * 
 * Class to perform calculation.
 * I chose to stack operations in String list to simplify the concatenation of numeric and the adding digit.
 * Moreover, there are not type problems, I can store numerics and operators in the same list.
 * Each value of the list is either a numeric or an operator.
 */

public class ArithmeticClass {

	/**
	 * @serialField instructions : Each value of the list is either digit or operator and stacked in this list.
	 * @serialField idxBegin : list contains indexes of all opening parenthesis or operators act like parenthesis (like 'tan(' ...).
	 * @serialField idxEnd : list contains indexes of all closing parenthesis or operators act like parenthesis (like 'tan(' ...).
	 */
	private List<String> instructions;
	private List<Integer> idxBegin;
	private List<Integer> idxEnd;
	private String ans;
	
	/**
	 * Constructor
	 * Initialize list of instructions parameter, and indexes lists. 
	 */
	public ArithmeticClass() {
		this.instructions = new ArrayList<String>();
		this.idxBegin = new ArrayList<Integer>();
		this.idxEnd = new ArrayList<Integer>();
		this.ans = "";
		
	}

	/** 
	 * Method to perform new instruction on instructions. 
	 * Evaluate the created instruction and perform action following instruction.
	 * @param action : action to do (add digit, point or set operator).
	 * @return instructions list to CalculatriceActionListerner to print text in text field : print instructions.
	 */
	public List<String> addInstruction(String action) {
		
		// Initialize new Instruction
		Instruction instruction = 	this.instructions.size() == 0 ? new Instruction(null, null, action) : 
									this.instructions.size() == 1 ? new Instruction(null, this.instructions.get(0), action) : 
									this.instructions.size() > 1 ? new Instruction(this.instructions.get(this.instructions.size() - 2), this.instructions.get(this.instructions.size() - 1), action) :
									null;
		
		// Perform on instructions list following instruction attribute.
		if (instruction != null) {
			switch (instruction.getAuthorization()) {
			case ALLOWED:
				// If action is a parenthesis, then add its index in the corresponding list.
				if (action.equals("(") || action.equals("tan(") || action.equals("sin(") || action.equals("cos(")) this.idxBegin.add(this.instructions.size());
				if (action.equals(")")) this.idxEnd.add(this.instructions.size());
				this.instructions.add(action);
				break;
			case STICK:
				String last = this.instructions.get(this.instructions.size() - 1);
				this.instructions.set(this.instructions.size() - 1, last + action);
				break;
			case FORBIDDEN:
				break;
			}
		}
			
		return this.instructions;
	}

	/**
	 * Set last result in the current operation.
	 * @return list of instructions to be printed in JTextField.
	 */
	public List<String> ans(){
		// If memory is not empty, then try to add ans to instructions list.
		if (!ans.equals("")) {
			double ansValue = Double.parseDouble(ans);  	
			ans = ansValue - (int)ansValue == 0.0 ? String.valueOf((int)ansValue) : String.valueOf(ansValue);
			return addInstruction(ans);
		} else {
			return this.instructions;
		}	
	}
	
	/**
	 * Method to perform the final result of operation written by user. This method is called when user click on '=' button.
	 * @return final result.
	 */
	public String resultAll() {
		
		if (this.instructions.size() == 0) return ""; 
		
		// Get last instruction. 
		String last = this.instructions.get(this.instructions.size() - 1);
		
		// Remove operator at the end of the list.
		if (	last.length() == 1
				&& CalculatriceUtils.getPositionOperator(last) != 1
				&& !CalculatriceUtils.isNumeric(last)) {
			this.instructions.remove(this.instructions.size() - 1);
		}
		
		// If there is more closing parenthesis, add the difference to opening parenthesis.
		if (idxBegin.size() < idxEnd.size()) {
			int diff = idxEnd.size() - idxBegin.size();
			// Translate all opening parenthesis indexes.
			this.idxBegin.replaceAll(i -> i + diff);
			for (int i = diff; i > 0 ; i--) {
				idxBegin.add(0, i - 1);
				this.instructions.add(0, "(");
			}
			// Translate all closing parenthesis indexes.
			this.idxEnd.replaceAll(i -> i + diff);
		// If there is more opening parenthesis, add the difference to closing parenthesis.
		} else if (idxBegin.size() > idxEnd.size()) {
			int diff = idxBegin.size() - idxEnd.size();
			for (int i = 0; i < diff; i++) {
				idxEnd.add(this.instructions.size());
				this.instructions.add(")");
			}
		}
		
		// Sub list size : to translate closing parenthesis indexes when sub list will be suppressed from instructions list.
		int subInstructionSize = 0;
		
		while (idxBegin.size() != 0 && idxEnd.size() != 0 && this.instructions.size() != 1) {
			int begin_parenthesis_last = idxBegin.get(idxBegin.size() - 1);
			// Remove opening parenthesis current index from list.
			idxBegin.remove(idxBegin.size() - 1);
			
			int end_parenthesis_first = -1;
			for (int i = 0; i < idxEnd.size(); i++) {
				if (idxEnd.get(i) > begin_parenthesis_last) {
					// Get first closing parenthesis with an index superior of opening parenthesis one.
					end_parenthesis_first = idxEnd.get(i);
					idxEnd.remove(i);	// Remove index from list of closing parenthesis.
					break;
				}
			}
			
			// Get the current action working on. Is it parenthesis or trigonometric function.
			String action = this.instructions.get(begin_parenthesis_last);
			int actionPosition = CalculatriceUtils.getPositionOperator(action);
			// If it is a parenthesis, then do not pass it to the calculation function.
			if (actionPosition == -1 && action.equals("(")) begin_parenthesis_last += 1;
			
			// Get the sub list instructions in the parenthesis.
			List<String> subInstruction = this.instructions.subList(begin_parenthesis_last, end_parenthesis_first);
			
			// Translate closing parenthesis if these ones are after opening one.
			subInstructionSize = CalculatriceUtils.getPositionOperator(subInstruction.get(0)) == -1 && !subInstruction.get(0).equals("(") ? subInstruction.size() + 1 : subInstruction.size() + 2;
			final int end = end_parenthesis_first, sIS = subInstructionSize;
			// Modify each index of closing parenthesis for each parenthesis after the opening one, on the ground all current will be suppressed => size of instructions list decreases.
			this.idxEnd.replaceAll(i -> i > end ? i - sIS + 1 : i);
			
			// Apply and calculate result on operations in parenthesis.
			String subResult = result(subInstruction);
			
			// Return error message in the text entry if an error occurred while calculation.
			if (subResult.equals("null")) { 
				this.reset();
				return "Error while calculating. Please retry !"; 
			}
		
			// Clear all operations in parenthesis and parenthesis in the final instructions list.
			if (actionPosition == -1 && action.equals("(")) {
				this.instructions.remove(begin_parenthesis_last + 1);
				this.instructions.remove(begin_parenthesis_last - 1);			
			} else if (actionPosition == -1 && !action.equals("(")) {
				this.instructions.remove(begin_parenthesis_last + 1);
			}
		}
		
		// Get result when there are not parenthesis anymore. Stack it in ans variable.
		ans = result(this.instructions);
		
		if (ans.equals("null")) { 
			this.reset();
			return "Error while calculating. Please retry !"; 
		}
		
		// Cast double to integer if float value is 0.0.
		this.instructions.set(0, Double.parseDouble(this.instructions.get(0)) - (int)Double.parseDouble(this.instructions.get(0)) == 0.0 ? String.valueOf((int)Double.parseDouble(this.instructions.get(0))) : this.instructions.get(0));
		
		// Print double if needed and print int otherwise.	
		double val = Double.parseDouble(ans);  	
		return val - (int)val == 0.0 ? String.valueOf((int)val) : String.valueOf(val);
	}
	
	/**
	 * Method called when user click on '=' button, to calculate a sub-result in parenthesis.. Analyze each char of instructions list to calculate all in the list correspond what is in parenthesis.
	 * @return String result of the sub-list defined by parenthesis.
	 */
	public String result(List<String> list) {
	
		String last = list.get(list.size() - 1);
		
		// Remove operator at the beginning and the end of the list.
		if (	last.length() == 1
				&& CalculatriceUtils.getPositionOperator(last) != 1
				&& !CalculatriceUtils.isNumeric(last)) {
			list.remove(list.size() - 1);
		}
	
		// Perform all calculations in beginning by those with highest priority.
		for (String op : CalculatriceUtils.getCalculationOperators()) {
			list = performCalculation(list, op);
			if (list.get(0).equals("null")) { return "null"; }
		}
		
		// Return error if calculation failed.
		if (list.size() != 1) { return "null"; }
	
		return list.get(0);
	}
	
	/**
	 * Method performs operations on sub-instructions list.
	 * @param list : sub-instructions list to perform calculation on. 
	 * @param operator : operator to perform calculation such as "x", "+" ...
	 * @return New list of instructions without operators in parameter.
	 */
	@SuppressWarnings("unchecked")
	public List<String> performCalculation(List<String> list, String operator) {
		
		// List contains just 'null' value to return if an error occured while calculating.
		@SuppressWarnings("rawtypes")
		List<String> nullList = new ArrayList(Arrays.asList("null"));
		
		int index = list.indexOf(operator);
		double res;
		// While list contains the current operator to perform on.
		while (index != -1) {
			
			int pos = CalculatriceUtils.getPositionOperator(operator);
			
			// Create variable to store values previous/next operator values depending on operator position.
			double previous = pos == 0 || pos == 1 ? Double.parseDouble(list.get(index - 1)) : 0.0;
			double next = pos == -1 || pos == 0 ? Double.parseDouble(list.get(index + 1)) : 0.0;
			
			// Perform calculation depending on operator.
			if(pos == -1) {
				res = 	operator.equals("tan(") ? Math.tan(next) : 
						operator.equals("sin(") ? Math.sin(next) :
						operator.equals("cos(") ? Math.cos(next) :
						0.0;
			} else if (pos == 0) {
				res = 	operator.equals("/") && next != 0.0 ? previous / next : 
						operator.equals("x") ? previous * next : 
						operator.equals("+") ? previous + next : 
						operator.equals("-") ? previous - next : 
						operator.equals("mod") ? (int) previous / (int) next : 
						0.0;
				if (operator.equals("/") && next == 0.0) return nullList;
			} else if (pos == 1 && operator.equals("!")) {
				res = 1.0;
				for (int i = 1; i <= previous; i++) res *= (double)i;
			}
			else return nullList; 
			
			// Set the new result at the current index place.
			list.set(index, String.valueOf(res));
			
			// Delete old previous/next values.
			// Remove the next value first to not translate the res in the list.
			if (pos == -1 || pos == 0) list.remove(index + 1);
			if (pos == 0 || pos == 1) list.remove(index - 1);
				
			index = list.indexOf(operator);
		}
		
		return list;
	}
	
	/**
	 * Delete method to delete last action (digit or operator).
	 * @return instructions list to print new instructions.
	 */
	public List<String> delete() {
		
		/* If the last argument is a number, delete last digit of this one. */
		if (this.instructions.size() > 0) {
			String last = this.instructions.get(this.instructions.size() - 1);
			if (last.length() > 1) {
				// Remove last digit of the last number
				last = last.substring(0, last.length() - 1);
				// Update list of instructions
				this.instructions.set(this.instructions.size() - 1, last);			
			}
			/* Else, delete last operator. */
			else {
				int lastIndex = this.instructions.size() - 1;
				// Remove parenthesis indexes if the removed operator is a parenthesis or a trigonometric function.
				if (this.instructions.get(lastIndex).equals(")")) this.idxEnd.remove(this.idxEnd.size() - 1);
				else if (CalculatriceUtils.getPositionOperator(this.instructions.get(lastIndex)) == -1) this.idxBegin.remove(this.idxBegin.size() - 1);
				// Remove last operator.
				this.instructions.remove(lastIndex);
			}
		}
		
		return this.instructions;
	}
	
	/**
	 * Reset memory of the calculation tool. 
	 * @return void
	 */
	public void reset() {
		this.instructions.clear();
		this.idxBegin.clear();
		this.idxEnd.clear();
	}
}
