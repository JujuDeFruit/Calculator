package ApplicationForm;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Actions.CalculatriceActionListener;
import Arithmetic.ArithmeticClass;
import net.miginfocom.swing.MigLayout;

/**
 * 
 * @author Julien Raynal
 *
 *         Class to manage calculator design.
 */

public class MainWindow {

	/**
	 * Main contents.
	 */
	private JFrame frame;
	private JPanel multiplePanels;
	private JPanel panelStandard;
	private JPanel panelScientific;
	private JTextField resultField;

	/**
	 * All JButtons.
	 */
	private JButton button_1;
	private JButton button_1_scientific;
	private JButton button_2;
	private JButton button_2_scientific;
	private JButton button_3;
	private JButton button_3_scientific;
	private JButton button_4;
	private JButton button_4_scientific;
	private JButton button_5;
	private JButton button_5_scientific;
	private JButton button_6;
	private JButton button_6_scientific;
	private JButton button_7;
	private JButton button_7_scientific;
	private JButton button_8;
	private JButton button_8_scientific;
	private JButton button_9;
	private JButton button_9_scientific;
	private JButton button_0;
	private JButton button_0_scientific;
	private JButton button_equal;
	private JButton button_equal_scientific;
	private JButton button_point;
	private JButton button_point_scientific;
	private JButton button_mod;
	private JButton button_divide;
	private JButton button_multiply;
	private JButton button_minus;
	private JButton button_plus;
	private JButton button_fact;
	private JButton button_tan;
	private JButton button_sin;
	private JButton button_cos;
	private JButton button_del;
	private JButton button_ans;
	private JButton button_reset;
	private JButton button_switch;
	private JButton button_switch_scientific;
	private JButton button_parenthesis_left;
	private JButton button_parenthesis_right;

	/**
	 * Static main to launch the application.
	 * 
	 * @return void
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor of design application. Create the application.
	 */
	public MainWindow() {
		initialize();
		initializeButtons();
		initializeActions();
		// Set focus on panelStandard to make CalculatriceKeyListener active at the
		// beginning.
		panelStandard.requestFocus();
	}

	/**
	 * Initialize the main contents of the frame. Initialize : - JFrame : Wider
	 * content in the window - JTextField : to print result - JPanel : one contains
	 * two other printed panels. This panel has a CardLayout to switch z-index of
	 * two panels contained in this one. - JPanel : panel to print standard
	 * calculator. It contains a GridBagLayout to print buttons following rows and
	 * colums. - JPanel : panel to print scientific calculator. It contains a
	 * GridBagLayout to print buttons following rows and colums.
	 * 
	 * @return void
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 282, 253);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[45.00][0:n,grow,top][]"));

		resultField = new JTextField();
		resultField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resultField.setHorizontalAlignment(SwingConstants.RIGHT);
		resultField.setText("");
		resultField.setEnabled(false);
		resultField.setToolTipText("Result");
		frame.getContentPane().add(resultField, "cell 0 0,grow");
		resultField.setColumns(10);

		multiplePanels = new JPanel();
		frame.getContentPane().add(multiplePanels, "cell 0 1,grow");
		multiplePanels.setLayout(new CardLayout(0, 0));

		panelStandard = new JPanel();
		multiplePanels.add(panelStandard, "name_255466827195100");
		GridBagLayout gbl_panelStandard = new GridBagLayout();
		gbl_panelStandard.columnWidths = new int[] { 51, 59, 45, 39, 0 };
		gbl_panelStandard.rowHeights = new int[] { 21, 21, 21, 21, 21, 0 };
		gbl_panelStandard.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelStandard.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelStandard.setLayout(gbl_panelStandard);
		panelStandard.setFocusable(true);

		panelScientific = new JPanel();
		multiplePanels.add(panelScientific, "name_255532920892400");
		GridBagLayout gbl_panelScientific = new GridBagLayout();
		gbl_panelScientific.columnWidths = new int[] { 51, 59, 45, 39, 0 };
		gbl_panelScientific.rowHeights = new int[] { 21, 21, 21, 21, 21, 0 };
		gbl_panelScientific.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelScientific.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelScientific.setLayout(gbl_panelScientific);
		panelScientific.setFocusable(true);
	}

	/**
	 * Initialize settings of all buttons needed for standard calculation.
	 * Initialize all JButtons on the calculator. Each button of standard panel has
	 * its GridBagContraints disposition cloned to dispose JButton on calculator.
	 * This technique avoid to have many same GridBagConstraints. Unfortunately it
	 * is not applicable for JButton (impossible to clone with basic methods).
	 * Except for 'switch' JButtons all JButtons binded events are created in
	 * initializeActions().
	 * 
	 * @return void
	 */
	private void initializeButtons() {

		button_1 = new JButton("1");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.BOTH;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 1;
		panelStandard.add(button_1, gbc_button_1);

		button_1_scientific = new JButton("1");
		panelScientific.add(button_1_scientific, (GridBagConstraints) gbc_button_1.clone());

		button_2 = new JButton("2");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.fill = GridBagConstraints.BOTH;
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 1;
		gbc_button_2.gridy = 1;
		panelStandard.add(button_2, gbc_button_2);

		button_2_scientific = new JButton("2");
		panelScientific.add(button_2_scientific, (GridBagConstraints) gbc_button_2.clone());

		button_3 = new JButton("3");
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.fill = GridBagConstraints.BOTH;
		gbc_button_3.insets = new Insets(0, 0, 5, 5);
		gbc_button_3.gridx = 2;
		gbc_button_3.gridy = 1;
		panelStandard.add(button_3, gbc_button_3);

		button_3_scientific = new JButton("3");
		panelScientific.add(button_3_scientific, (GridBagConstraints) gbc_button_3.clone());

		button_4 = new JButton("4");
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.fill = GridBagConstraints.BOTH;
		gbc_button_4.insets = new Insets(0, 0, 5, 5);
		gbc_button_4.gridx = 0;
		gbc_button_4.gridy = 2;
		panelStandard.add(button_4, gbc_button_4);

		button_4_scientific = new JButton("4");
		panelScientific.add(button_4_scientific, (GridBagConstraints) gbc_button_4.clone());

		button_5 = new JButton("5");
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.fill = GridBagConstraints.BOTH;
		gbc_button_5.insets = new Insets(0, 0, 5, 5);
		gbc_button_5.gridx = 1;
		gbc_button_5.gridy = 2;
		panelStandard.add(button_5, gbc_button_5);

		button_5_scientific = new JButton("5");
		panelScientific.add(button_5_scientific, (GridBagConstraints) gbc_button_5.clone());

		button_6 = new JButton("6");
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.fill = GridBagConstraints.BOTH;
		gbc_button_6.insets = new Insets(0, 0, 5, 5);
		gbc_button_6.gridx = 2;
		gbc_button_6.gridy = 2;
		panelStandard.add(button_6, gbc_button_6);

		button_6_scientific = new JButton("6");
		panelScientific.add(button_6_scientific, (GridBagConstraints) gbc_button_6.clone());

		button_7 = new JButton("7");
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.fill = GridBagConstraints.BOTH;
		gbc_button_7.insets = new Insets(0, 0, 5, 5);
		gbc_button_7.gridx = 0;
		gbc_button_7.gridy = 3;
		panelStandard.add(button_7, gbc_button_7);

		button_7_scientific = new JButton("7");
		panelScientific.add(button_7_scientific, (GridBagConstraints) gbc_button_7.clone());

		button_8 = new JButton("8");
		GridBagConstraints gbc_button_8 = new GridBagConstraints();
		gbc_button_8.fill = GridBagConstraints.BOTH;
		gbc_button_8.insets = new Insets(0, 0, 5, 5);
		gbc_button_8.gridx = 1;
		gbc_button_8.gridy = 3;
		panelStandard.add(button_8, gbc_button_8);

		button_8_scientific = new JButton("8");
		panelScientific.add(button_8_scientific, (GridBagConstraints) gbc_button_8.clone());

		button_9 = new JButton("9");
		GridBagConstraints gbc_button_9 = new GridBagConstraints();
		gbc_button_9.fill = GridBagConstraints.BOTH;
		gbc_button_9.insets = new Insets(0, 0, 5, 5);
		gbc_button_9.gridx = 2;
		gbc_button_9.gridy = 3;
		panelStandard.add(button_9, gbc_button_9);

		button_9_scientific = new JButton("9");
		panelScientific.add(button_9_scientific, (GridBagConstraints) gbc_button_9.clone());

		button_0 = new JButton("0");
		GridBagConstraints gbc_button_0 = new GridBagConstraints();
		gbc_button_0.fill = GridBagConstraints.BOTH;
		gbc_button_0.insets = new Insets(0, 0, 0, 5);
		gbc_button_0.gridx = 1;
		gbc_button_0.gridy = 4;
		panelStandard.add(button_0, gbc_button_0);

		button_0_scientific = new JButton("0");
		panelScientific.add(button_0_scientific, (GridBagConstraints) gbc_button_0.clone());

		button_point = new JButton(".");
		GridBagConstraints gbc_button_point = new GridBagConstraints();
		gbc_button_point.fill = GridBagConstraints.BOTH;
		gbc_button_point.insets = new Insets(0, 0, 0, 5);
		gbc_button_point.gridx = 2;
		gbc_button_point.gridy = 4;
		panelStandard.add(button_point, gbc_button_point);

		button_point_scientific = new JButton(".");
		panelScientific.add(button_point_scientific, (GridBagConstraints) gbc_button_point.clone());

		button_mod = new JButton("mod");
		GridBagConstraints gbc_button_mod = new GridBagConstraints();
		gbc_button_mod.fill = GridBagConstraints.BOTH;
		gbc_button_mod.insets = new Insets(0, 0, 5, 5);
		gbc_button_mod.gridx = 2;
		gbc_button_mod.gridy = 0;
		panelStandard.add(button_mod, gbc_button_mod);

		button_sin = new JButton("sin(");
		panelScientific.add(button_sin, (GridBagConstraints) gbc_button_mod.clone());

		button_multiply = new JButton("x");
		GridBagConstraints gbc_button_multiply = new GridBagConstraints();
		gbc_button_multiply.fill = GridBagConstraints.BOTH;
		gbc_button_multiply.insets = new Insets(0, 0, 5, 0);
		gbc_button_multiply.gridx = 3;
		gbc_button_multiply.gridy = 1;
		panelStandard.add(button_multiply, gbc_button_multiply);

		button_parenthesis_right = new JButton(")");
		panelScientific.add(button_parenthesis_right, (GridBagConstraints) gbc_button_multiply.clone());

		button_divide = new JButton("/");
		GridBagConstraints gbc_button_divide = new GridBagConstraints();
		gbc_button_divide.fill = GridBagConstraints.BOTH;
		gbc_button_divide.insets = new Insets(0, 0, 5, 0);
		gbc_button_divide.gridx = 3;
		gbc_button_divide.gridy = 0;
		panelStandard.add(button_divide, gbc_button_divide);

		button_cos = new JButton("cos(");
		panelScientific.add(button_cos, (GridBagConstraints) gbc_button_divide.clone());

		button_plus = new JButton("+");
		GridBagConstraints gbc_button_plus = new GridBagConstraints();
		gbc_button_plus.fill = GridBagConstraints.BOTH;
		gbc_button_plus.insets = new Insets(0, 0, 5, 0);
		gbc_button_plus.gridx = 3;
		gbc_button_plus.gridy = 3;
		panelStandard.add(button_plus, gbc_button_plus);

		button_minus = new JButton("-");
		GridBagConstraints gbc_button_minus = new GridBagConstraints();
		gbc_button_minus.fill = GridBagConstraints.BOTH;
		gbc_button_minus.insets = new Insets(0, 0, 5, 0);
		gbc_button_minus.gridx = 3;
		gbc_button_minus.gridy = 2;
		panelStandard.add(button_minus, gbc_button_minus);

		button_parenthesis_left = new JButton("(");
		panelScientific.add(button_parenthesis_left, (GridBagConstraints) gbc_button_minus.clone());

		button_fact = new JButton("!");
		GridBagConstraints gbc_button_fact = new GridBagConstraints();
		gbc_button_fact.fill = GridBagConstraints.BOTH;
		gbc_button_fact.insets = new Insets(0, 0, 5, 0);
		gbc_button_fact.gridx = 3;
		gbc_button_fact.gridy = 3;
		panelScientific.add(button_fact, gbc_button_fact);

		button_equal = new JButton("=");
		GridBagConstraints gbc_button_equal = new GridBagConstraints();
		gbc_button_equal.fill = GridBagConstraints.BOTH;
		gbc_button_equal.gridx = 3;
		gbc_button_equal.gridy = 4;
		panelStandard.add(button_equal, gbc_button_equal);

		button_equal_scientific = new JButton("=");
		panelScientific.add(button_equal_scientific, (GridBagConstraints) gbc_button_equal.clone());

		button_del = new JButton("del");
		GridBagConstraints gbc_button_del = new GridBagConstraints();
		gbc_button_del.fill = GridBagConstraints.BOTH;
		gbc_button_del.insets = new Insets(0, 0, 0, 5);
		gbc_button_del.gridx = 0;
		gbc_button_del.gridy = 4;
		panelStandard.add(button_del, gbc_button_del);

		button_ans = new JButton("ans");
		panelScientific.add(button_ans, (GridBagConstraints) gbc_button_del.clone());

		button_reset = new JButton("res");
		GridBagConstraints gbc_button_reset = new GridBagConstraints();
		gbc_button_reset.fill = GridBagConstraints.BOTH;
		gbc_button_reset.insets = new Insets(0, 0, 5, 5);
		gbc_button_reset.gridx = 1;
		gbc_button_reset.gridy = 0;
		panelStandard.add(button_reset, gbc_button_reset);

		button_tan = new JButton("tan(");
		panelScientific.add(button_tan, (GridBagConstraints) gbc_button_reset.clone());

		button_switch = new JButton("switch");
		button_switch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) multiplePanels.getLayout(); // Get current layout.
				cardLayout.next(multiplePanels); // Change the panel.
				panelScientific.requestFocus();
			}
		});
		GridBagConstraints gbc_button_switch = new GridBagConstraints();
		gbc_button_switch.fill = GridBagConstraints.BOTH;
		gbc_button_switch.insets = new Insets(0, 0, 5, 5);
		gbc_button_switch.gridx = 0;
		gbc_button_switch.gridy = 0;
		panelStandard.add(button_switch, gbc_button_switch);

		button_switch_scientific = new JButton("switch");
		button_switch_scientific.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) multiplePanels.getLayout();
				cardLayout.previous(multiplePanels); // Change the panel.
				panelStandard.requestFocus();
			}
		});
		panelScientific.add(button_switch_scientific, (GridBagConstraints) gbc_button_switch.clone());
	}

	/**
	 * Initialize all actions on JButtons.
	 * 
	 * @return void
	 */
	private void initializeActions() {
		// Initialize new ArithmeticClass object will used to calculate results.
		ArithmeticClass value = new ArithmeticClass();

		// Get all fields of the current window.
		Field[] fields = this.getClass().getDeclaredFields();

		// Browse all field.
		for (Field field : fields) {
			// If the current field is a button.
			if (field.getType() == JButton.class) {
				// Convert the current field into a JButton instance and bind a
				// CalculatriceActionListener on it.
				// Do not forget to pass all needed arguments to the action listener.
				try {
					JButton button = (JButton) field.get(this);
					// Do not add ActionListener on switch button
					if (!button.getText().equals("switch")) {
						// Action is the name of the current button (Example : /, 9, ., del ...)
						button.addActionListener(new CalculatriceActionListener(value, resultField, button));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (field.getType() == JPanel.class) {
				try {
					JPanel panel = (JPanel) field.get(this);
					panel.addKeyListener(new CalculatriceActionListener(value, resultField, null));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
