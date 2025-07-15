
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
int boardWidth = 360;
int boardHeight = 540;

Color customLightGrey = new Color(212,212,210);
Color customeDarkGrey = new Color(80,80,80);
Color customBlack = new Color(28,28,28);
Color customOrange = new Color(255,149,0);
Color customcraxy = new Color(22,5,88);

String[] buttonValues = {
    "AC", "+/-", "%", "÷", 
    "7", "8", "9", "×", 
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "0", ".", "√", "="
};
String[] rightSymbols = {"÷", "×", "-", "+", "="};
String[] topSymbols = {"AC", "+/-", "%"};



JFrame frame = new JFrame("Calculator");
JLabel displayLabel = new JLabel();
JPanel displayPanel = new JPanel();
JPanel buttonsPanel = new JPanel();

//these are the values that a user going to enter
// A operator B ----- A + B , A - B , A %  B
String A= "0";
String Operator = null;
String B = "0";


Calculator(){
    //calculator() is an constructor
    // frame.setVisible(true);
    frame.setSize(boardWidth, boardHeight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    displayLabel.setBackground(customBlack);
    displayLabel.setForeground(Color.white);
    displayLabel.setFont(new Font("Arial", Font.PLAIN,89 ));
    displayLabel.setHorizontalAlignment(JLabel.RIGHT);
    displayLabel.setText("0");
    displayLabel.setOpaque(true);

    displayPanel.setLayout(new BorderLayout());
    displayPanel.add(displayLabel);
    frame.add(displayPanel,BorderLayout.NORTH);

    buttonsPanel.setLayout(new GridLayout(5, 4));
    buttonsPanel.setBackground(customBlack);
    frame.add(buttonsPanel);

    for (int i=0;i<buttonValues.length;i++){
        JButton button = new JButton();
        String buttonValue = buttonValues[i];
        button.setFont(new Font("Arial", Font.PLAIN,30));
        button.setText(buttonValue);
        // focusable is a square type select efect to the 
        // buttons on clicking 
        // and doint it false makes it desable
        button.setFocusable(false);
        //changing border of buttons
        button.setBorder(new LineBorder(customBlack));
        //styling button
        if(Arrays.asList(topSymbols).contains(buttonValue)){
            //top buttons "AC", "+/-", "%"
            button.setBackground(customLightGrey);
            button.setForeground(customBlack);
        }else if(Arrays.asList(rightSymbols).contains(buttonValue)){
           //right buttons "÷" x - + =
            button.setBackground(customOrange);
            button.setForeground(Color.black);
        }else{
            // other buttons which are numericals
            button.setBackground(customeDarkGrey);
            button.setForeground(Color.white);
        }
        buttonsPanel.add(button);
        
                //adding functionality of the buttons
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        JButton button = (JButton) e.getSource();
                        String buttonValue = button.getText();
                        //the working logic of the code
                        if(Arrays.asList(rightSymbols).contains(buttonValue)){
                            if(buttonValue == "="){
                                if(A!=null){
                                    B = displayLabel.getText();
                                    double numA = Double.parseDouble(A);
                                    double numB = Double.parseDouble(B);

                                    if(Operator== "+"){
                                        displayLabel.setText(removeZeroDecimal(numA+numB));
                                    }else if(Operator=="-"){
                                        displayLabel.setText(removeZeroDecimal(numA-numB));
                                    }else if(Operator=="×"){
                                        displayLabel.setText(removeZeroDecimal(numA*numB));
                                    }else if(Operator=="÷"){
                                        displayLabel.setText(removeZeroDecimal(numA/numB));
                                    }
                        }
                    }else if("+-×÷".contains(buttonValue)){
                        if(Operator == null){
                            A = displayLabel.getText();
                            displayLabel.setText("0");
                            B = "0";

                        }
                        Operator = buttonValue;
                    }

                }else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue=="AC"){
                            clearAll();
                            displayLabel.setText("0");
                        }else if(buttonValue=="+/-"){
                            //parsedouble convert string entered in the calculator into the double for arithmetic operations
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            //multiply with -1 ex (- * -) = + ,,,,,, (- + +)= -
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        }else if(buttonValue=="%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /=100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        }

                }else{//digit or "."
                    if(buttonValue=="."){
                        //if buttonvalue is equal to decimal place
                        if(!displayLabel.getText().contains(buttonValue)){
                            //if user enters "." second time but it is blocked
                            displayLabel.setText(displayLabel.getText()+buttonValue);
                            
                        }
                    }else if("0123456789".contains(buttonValue)){
                            //for digits
                            if(displayLabel.getText()=="0"){
                                //what it does is ...if the current number is 
                                //0 and i press 5 i dont want 05 
                                // instead i want only 5
                                displayLabel.setText(buttonValue);
                            }else{
                                displayLabel.setText(displayLabel.getText()+ buttonValue);
                                //displaylabel.gettext is entered old number + buttonvalue is new entered number
                                //ex 12 + 3 = 123
                            }
                    }

                }
            }
        });

           frame.setVisible(true);
 
    }


}
//function for AC
    void clearAll(){
        A  ="0";
        Operator =null;
        B = "0";

    }
//function for removing zero after decimal number
//ex 245.0 ---> 245

 String removeZeroDecimal(double numDisplay){
if(numDisplay%1 == 0){
//above condition means it is a whole number ex . 245
return Integer.toString((int)numDisplay);
}
//if above condition does not satifies it will return the numDisplay
return Double.toString(numDisplay);
 }
}
