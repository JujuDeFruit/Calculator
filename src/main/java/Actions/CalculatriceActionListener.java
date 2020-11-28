package Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import Arithmetic.ArithmeticClass;

/**
 * 
 * @author Julien Raynal
 *
 * ActionListener of the application. Each button has its own ActionLister and perform action depending 
 * on its name. Look at actionPerformed(...) that implements interface.
 */

public class CalculatriceActionListener implements ActionListener, KeyListener {

	/**
	 * @serialField action : action of the JButton : its name.
	 * @serialField resultField : text entry to print result.
	 * @serialField value : ArithmeticClass to perform calculation.
	 * @serialField button : current button linked to event.
	 */
	private String action;
	private JTextField resultField;
	private ArithmeticClass value;
	private JButton button;
	
	/**
	 * Constructor : collect all parameters to link graphical interface to calculation.
	 * @param value_ : current mathematical item to calculate.
	 * @param rf_ : JTextField of windows to print operations.
	 * @param action_ : button pressed by user.
	 */
	public CalculatriceActionListener(ArithmeticClass value_, JTextField rf_, JButton button_) {
		super();
		this.action = button_ != null ? button_.getText() : "";
		this.resultField = rf_;
		this.value = value_;
		this.button = button_ != null ? button_ : null ;
	}
	
	/**
	 * Define actionPerformed(...) method of interface ActionListener.
	 * This method is called when user press JButton.
	 * This override calls appropriate method about arithmetic following action.
	 * @param e : current event.
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// resultField.setText(..) allow to print result in the JTextField.
		if (action.equals("del")) {
			String text = String.join("", value.delete());
			resultField.setText(text);
		}
		else if (action.equals("=")) {
			String val = value.resultAll();
			resultField.setText(val);
		}
		else if (action.equals("res")) {
			value.reset();
			resultField.setText("");
		}
		else if (action.equals("ans")) {
			String text = String.join("", value.ans());
			resultField.setText(text);
		}
		else  {
			String text = String.join("", value.addInstruction(action));
			resultField.setText(text);
		}
		// If e not equals null, then the action went from mouse, so focus on panel to allow keyboard.
		if (e != null) 
			button.getParent().requestFocus();
	}

	/**
	 * When user use keyboard to set operations, bind actions.
	 * @param arg0 : current event by keyboard
	 * @return void
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		// Get char of the key pressed by user.
		char a = arg0.getKeyChar();	

		if (	Character.isDigit(a) 
				|| a == '.' 
				|| a == '!' 
				|| a == '+'
				|| a == '-'
				|| a == '/'
				|| a == '('
				|| a == ')'
			) {	 
			// Call action method but change the value of the action before.
			this.action = Character.toString(a);
			actionPerformed(null);
		} else if (a == '*') {
			this.action = "x";
			actionPerformed(null);
		} else if (a == '\b') {
			this.action = "del";
			actionPerformed(null);
		} else if (a == '\n') {
			this.action = "=";
			actionPerformed(null);
		}
	}

	/**
	 * Nothing happen in this method. Just declare to implement interface.
	 * @return void
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {}

	/**
	 * Nothing happen in this method. Just declare to implement interface.
	 * @return void
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
