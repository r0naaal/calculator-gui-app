import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class CalculatorApp {

    private JFrame frame;
    private JTextField textField;
    private JPanel panel;
    private RoundedButton[] numberButtons;
    private RoundedButton[] functionButtons;
    private Font myFont = new Font(null, Font.PLAIN, 35);

    private String currentInput = ""; // holds the current number input by the user
    private String operator = ""; // Stores the selected operator like sum, sub, div, mul
    private double firstOperand = 0; // holds the first number for the operation

    public CalculatorApp()
    {
        // create frame
        frame = new JFrame("Calculator");
        frame.setSize(380, 620);
        frame.setResizable(false); // dont let resize 
        frame.setLayout(null); // set layout to null
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // stop running when closed
        
        // create text field
        textField = new JTextField();
        textField.setBounds(-1, 10, 369, 150); // size and y - x position
        textField.setEditable(false); // dont let edit the textfield
        textField.setForeground(Color.WHITE);
        textField.setFont(myFont); 
        textField.setBorder(new EmptyBorder(-1,-1,-1,-1)); // take the border of the textfield off
        textField.setBackground(new Color(49,49,49)); // change colorbackground
        textField.setHorizontalAlignment(JTextField.CENTER);
        frame.add(textField); // add textfield to the frame
        setupKeyBindings();

        // Request focus for key events
        textField.requestFocusInWindow();

        // create panel to hold numberButtons
        panel = new JPanel(null); // no absoule layout manager or no layout manager 
        panel.setBounds(-1, 160, 380, 470);
        panel.setBorder(new EmptyBorder(-1,-1,-1,-1)); // take the border of the panel off
        panel.setBackground(new Color(0,0,0));
        
        // create number buttons
        numberButtons = new RoundedButton[10];
        numberButtons[0] = new RoundedButton("0", "zero");
        numberButtons[0].setBounds(30, 340, 150, 70);
        panel.add(numberButtons[0]);

        numberButtons[1] = new RoundedButton("1", "number");
        numberButtons[1].setBounds(30, 260, 70, 70);
        panel.add(numberButtons[1]);

        numberButtons[2] = new RoundedButton("2", "number");
        numberButtons[2].setBounds(110, 260, 70, 70);
        panel.add(numberButtons[2]);

        numberButtons[3] = new RoundedButton("3", "number");
        numberButtons[3].setBounds(190, 260, 70, 70);
        panel.add(numberButtons[3]);

        numberButtons[4] = new RoundedButton("4", "number");
        numberButtons[4].setBounds(30, 180, 70, 70);
        panel.add(numberButtons[4]);

        numberButtons[5] = new RoundedButton("5", "number");
        numberButtons[5].setBounds(110, 180, 70, 70);
        panel.add(numberButtons[5]);

        numberButtons[6] = new RoundedButton("6", "number");
        numberButtons[6].setBounds(190, 180, 70, 70);
        panel.add(numberButtons[6]);

        numberButtons[7] = new RoundedButton("7", "number");
        numberButtons[7].setBounds(30, 100, 70, 70);
        panel.add(numberButtons[7]);

        numberButtons[8] = new RoundedButton("8", "number");
        numberButtons[8].setBounds(110, 100, 70, 70);
        panel.add(numberButtons[8]);

        numberButtons[9] = new RoundedButton("9", "number");
        numberButtons[9].setBounds(190, 100, 70, 70);
        panel.add(numberButtons[9]);

        // create function buttons
        functionButtons = new RoundedButton[7];

        functionButtons[0]  = new RoundedButton("+","function");
        functionButtons[0].setBounds(190, 20, 70, 70);
        functionButtons[0].setBackground(new Color(159,159,159));
        functionButtons[0].setForeground(Color.BLACK);
        functionButtons[0].setFont(new Font("Helvetica", Font.PLAIN, 30));
        panel.add(functionButtons[0]);

        functionButtons[1] = new RoundedButton("-","function");
        functionButtons[1].setBounds(270, 20, 70, 70);
        functionButtons[1].setBackground(new Color(159,159,159));
        functionButtons[1].setForeground(Color.BLACK);
        functionButtons[1].setFont(new Font("Helvetica", Font.PLAIN, 30));
        panel.add(functionButtons[1]);

        functionButtons[2] = new RoundedButton("✖","function");
        functionButtons[2].setBounds(110, 20, 70, 70);
        functionButtons[2].setBackground(new Color(159,159,159));
        functionButtons[2].setForeground(Color.BLACK);
        functionButtons[2].setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(functionButtons[2]);

        functionButtons[3] = new RoundedButton("➗", "function");
        functionButtons[3].setBounds(30, 20, 70, 70);
        functionButtons[3].setBackground(new Color(159,159,159));
        functionButtons[3].setForeground(Color.BLACK);
        functionButtons[3].setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(functionButtons[3]);

        functionButtons[4] = new RoundedButton(".", "number");
        functionButtons[4].setBounds(190, 340, 70, 70);
        panel.add(functionButtons[4]);

        functionButtons[5] = new RoundedButton("=", "equals");
        functionButtons[5].setBounds(270, 260, 70, 150);
        functionButtons[5].setBackground(new Color(203, 169, 0));
        panel.add(functionButtons[5]);

        functionButtons[6] = new RoundedButton("AC", "ac");
        functionButtons[6].setBounds(270, 100, 70, 150);
        panel.add(functionButtons[6]);

        // make sure the buttons are colored and drawn correctly
        panel.revalidate();
        panel.repaint(); 

        frame.add(panel); // add panel with all the buttons in it
        frame.setLocationRelativeTo(null); // center window for better user experience
        frame.setVisible(true); // make frame visible

        /* BUTTON LOGIC */

        // number buttons - for loop
        for (int i = 0; i < numberButtons.length; i++) {
            final int index = i; // create a final variable to hold the index
            numberButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentInput += String.valueOf(index); // append the number
                    System.out.println("current input: " + currentInput);
                    textField.setText(currentInput); // update display
                }
            });
        }

        // Sum operator button logic
        functionButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentInput.isEmpty()) {
                    operator = "+"; // Store the operator
                    firstOperand = Double.parseDouble(currentInput); // Save first number
                    currentInput = ""; // Clear input for next number
                    textField.setText(operator);
                } else {
                    textField.setText("Enter a number first!"); // Inform user to enter a number
                }
            }
        });

        // Subtraction operator button logic
        functionButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentInput.isEmpty()) {
                    operator = "-"; // Store the operator
                    firstOperand = Double.parseDouble(currentInput); // Save first number
                    currentInput = ""; // Clear input for next number
                    textField.setText(operator);
                } else {
                    textField.setText("Enter a number first!"); // Inform user to enter a number
                }
            }
        });

        // Multiplication operator button logic
        functionButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentInput.isEmpty()) {
                    operator = "✖"; // Store the operator
                    firstOperand = Double.parseDouble(currentInput); // Save first number
                    currentInput = ""; // Clear input for next number
                    textField.setText(operator);
                } else {
                    textField.setText("Enter a number first!"); // Inform user to enter a number
                }
            }
        });

        // Division operator button logic
        functionButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentInput.isEmpty()) {
                    operator = "➗"; // Store the operator
                    firstOperand = Double.parseDouble(currentInput); // Save first number
                    currentInput = ""; // Clear input for next number
                    textField.setText(operator);
                } else {
                    textField.setText("Enter a number first!"); // Inform user to enter a number
                }
            }
        });

        // equal button logic - if statement
        functionButtons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });

        // AC button logic
        functionButtons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCalculator();
                System.out.println("Calculator reset."); // Optional debug line
            }
        });

        // point button logic
        functionButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!currentInput.contains(".")) {
                    currentInput += "."; // Append decimal point
                    textField.setText(currentInput); // Update display
                }
            }
        });


    } // end of Calculator constructor 
 

    private void setupKeyBindings() {
        // Define arrays for the number keys and operation keys
        String[] numberKeys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] operationKeys = {"+", "-", "✖", "➗", "="};
    
        // Bind number keys to their respective actions
        for (String key : numberKeys) {
            textField.getInputMap().put(KeyStroke.getKeyStroke(key), "number" + key);
            textField.getActionMap().put("number" + key, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    appendToCurrentInput(key); // Append the number to current input
                }
            });
        }
    
        // Bind operation keys to their respective actions
        for (String key : operationKeys) {
            textField.getInputMap().put(KeyStroke.getKeyStroke(key), key);
            textField.getActionMap().put(key, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    performOperation(key); // Perform the selected operation
                }
            });
        }
    
        // Bind ENTER and BACK_SPACE
        textField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "equals");
        textField.getActionMap().put("equals", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult(); // Calculate result on ENTER
            }
        });
    
        textField.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "clear");
        textField.getActionMap().put("clear", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCalculator(); // Clear calculator on BACK_SPACE
            }
        });
    }

    private void appendToCurrentInput(String number) {
        currentInput += number; // append the number
        textField.setText(currentInput); // update display
    }
    
    private void performOperation(String op) {
        if (!currentInput.isEmpty()) {
            operator = op; // Store the operator
            firstOperand = Double.parseDouble(currentInput); // Save first number
            currentInput = ""; // Clear input for next number
            textField.setText(""); // Optionally clear display
        } else {
            textField.setText("Enter a number first!"); // Inform user
        }
    }

    // calculate result method
    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;
            // Perform calculation based on the operator
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "✖":
                    result = firstOperand * secondOperand;
                    break;
                case "➗":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        textField.setText("ERROR");
                        return;
                    }
                    break;
            }
            // Update the text field with the result
            final double finalResult = result;
            SwingUtilities.invokeLater(() -> { // updates the text field with the calculated result
                textField.setText(String.valueOf(finalResult)); // Display the result
                System.out.println("Text Field Updated: " + finalResult); // Debugging line
                textField.requestFocusInWindow(); // ensure that the text field is ready for the next input by requesting focus
                panel.revalidate(); // Revalidate the panel
                panel.repaint(); // Repaint the panel
            });
            currentInput = ""; // Reset
            operator = ""; // Reset
            firstOperand = result; // Store result for further calculations
        } else {
            textField.setText("Enter a number first!");
        }
    }

    // clear calculator method
    private void clearCalculator() {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        textField.setText("");
    }
    
    public static void main(String[] args) 
    {
        new CalculatorApp();
    }

} // end of class
