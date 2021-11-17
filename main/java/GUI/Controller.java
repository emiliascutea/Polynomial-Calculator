package GUI;

import Logic.*;
import Model.Polynomial;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


import java.util.Map;

public class Controller {

    @FXML
    private RadioButton buttonP1;
    @FXML
    private RadioButton buttonP2;
    @FXML
    private TextField polynom1txt;
    @FXML
    private TextField polynom2txt;
    @FXML
    private TextField result;
    public String text1;
    public String text2;
    public Polynomial polynomial1 = new Polynomial(); // create new polynom
    public Polynomial polynomial2 = new Polynomial(); // create new polynom
    public Alert alert = new Alert();

    public boolean validateInput(TextField polynomtxt, Polynomial polynomial, String text) {
        boolean ok = true;
        if (polynomtxt != null) {

            polynomial.getPolynomial().clear();
            text = polynomtxt.getText(); // get text from text field
            String[] s = text.split("[+-]"); // split the text around + and -
            for (int index = 0; index < s.length; index++) { // iterate through each splitted part

                if (s[index].equals("") && index == 0) { // if first char was a sign, then at split it got an " "
                    continue;
                }
                char sign = 0;
                int coefficient = 0;
                int power = -1;


                if (text.contains(s[index])) { // if splitted part is part of the initial text
                    // get sign of the coefficient
                    int startsAt = text.indexOf(s[index]); // get split's start position in the text string
                    if (startsAt - 1 >= 0) { // if there was another in initial text before the split
                        if (text.charAt(startsAt - 1) == '-') { // if the sign is -
                            sign = '-';
                        } else {
                            sign = '+';
                        }
                    } else sign = '+';
                    // get the coefficient
                    if (s[index].contains("x")) { // if the split contains x

                        int indexOfX = s[index].indexOf("x"); // get index of x;

                        if (s[index].substring(0, indexOfX).matches("\\d+")) { // if the coefficient contains only integers

                            String coeff = sign + s[index].substring(0, indexOfX); // form the coefficient: sign + value
                            coefficient = Integer.parseInt(coeff); // assign the value to the coefficient
                        } else {
                            if (indexOfX == 0) { // if x is the 1st elem in the split
                                coefficient = 1; // coefficient is 1
                            } else {
                                ok = false;
                                alert.alertValueContainsDigits();
                            }
                        }

                        // get power
                        if (indexOfX != (s[index].length() - 1)) { // if x is not the last character in the split
                            if (s[index].charAt(indexOfX + 1) == '^') { // if the next char after x is the power sign power sign
                                int indexOfPower = indexOfX + 1; // get the index of the power sign
                                String pow = s[index].substring(indexOfPower + 1); // get the value of the power
                                if (pow.matches("\\d+")) { // if the power contains only integers
                                    power = Integer.parseInt(pow);
                                    polynomial.add(coefficient, power);
                                } else {
                                    ok = false;
                                    alert.alertValueContainsDigits();
                                }
                            } else {
                                ok = false;
                                alert.alertWrongPowerChar();
                            }
                        } else {
                            power = 1; // if x is last element => it power is 1
                            polynomial.add(coefficient, power);
                        }

                    } else { // if the part does not contain x
                        if (s[index].matches("\\d+")) { // check if it contains only numbers
                            coefficient = Integer.parseInt(sign + s[index]);
                            polynomial.add(coefficient, 0); // if it contains only integers => power of x is 0
                        } else {
                            ok = false;
                            alert.alertInvalidInput();
                        }
                    }
                }
            }
        }
        return ok;
    }

    public boolean validateInput1() {
        return validateInput(polynom1txt, polynomial1, text1);
    }

    public boolean validateInput2() {
        return validateInput(polynom2txt, polynomial2, text2);
    }

    public String displayResult(double coefficient, char sign, int power, String resultString) {
        if (coefficient > 0) sign = '+';
        else {
            sign = '-';
            coefficient = (-1) * coefficient;
        }
        if (coefficient != 0) {
            if (power != 0) {
                if (power == 1)
                    resultString = resultString.concat(sign + String.valueOf(coefficient) + "x");
                else
                    resultString = resultString.concat(sign + String.valueOf(coefficient) + "x^" + String.valueOf(power));
            } else
                resultString = resultString.concat(sign + String.valueOf(coefficient));
        }
        return resultString;
    }

    public String displayResult(int coefficient, char sign, int power, String resultString) {
        if (coefficient > 0) sign = '+';
        else {
            sign = '-';
            coefficient = (-1) * coefficient;
        }
        if (coefficient != 0) {
            if (power != 0) {
                if (power == 1)
                    resultString = resultString.concat(sign + String.valueOf(coefficient) + "x");
                else
                    resultString = resultString.concat(sign + String.valueOf(coefficient) + "x^" + String.valueOf(power));
            } else
                resultString = resultString.concat(sign + String.valueOf(coefficient));
            if (resultString.charAt(0) == '+') {
                resultString = resultString.substring(1, resultString.length());
            }
        }
        return resultString;
    }

    public String computeResult(Polynomial polynomial, String resultString) {
        for (Map.Entry<Integer, Double> entry : polynomial.getPolynomial().entrySet()) {
            Integer power = entry.getKey();
            double coefficient = entry.getValue();
            int integerCoefficient;
            char sign = 0;
            if (coefficient == Math.floor(coefficient)) { // if coefficient has no decimal points
                integerCoefficient = (int) coefficient;
                resultString = displayResult(integerCoefficient, sign, power, resultString);
            } else { // coefficient will be displayed with 2 decimal points
                coefficient = Math.floor(coefficient * 100) / 100;
                resultString = displayResult(coefficient, sign, power, resultString);
            }
        }
        return resultString;
    }

    public void addInputs() {
        if (validateInput1() && validateInput2()) {

            MathematicalOperations op = new MathematicalOperations();
            Polynomial sum = op.addition(polynomial1, polynomial2);
            String resultString = "";
            if (sum.getPolynomial().isEmpty()) {
                resultString = "0";
            } else {
                resultString = computeResult(sum, resultString);
            }
            result.setText(resultString);
        }
    }

    public void subtractInputs() {
        if (validateInput1() && validateInput2()) {

            MathematicalOperations op = new MathematicalOperations();
            Polynomial difference = op.subtraction(polynomial1, polynomial2);
            String resultString = "";
            if (difference.getPolynomial().isEmpty())
                resultString = "0";
            else {
                resultString = computeResult(difference, resultString);
            }
            result.setText(resultString);
        }
    }

    public void multiplyInputs() {
        if (validateInput1() && validateInput2()) {

            MathematicalOperations op = new MathematicalOperations();
            Polynomial product = op.multiplication(polynomial1, polynomial2);
            String resultString = "";
            if (product.getPolynomial().isEmpty()) {
                resultString = "0";
            } else {
                resultString = computeResult(product, resultString);
            }
            result.setText(resultString);
        }
    }

    public void divideInputs() {
        if (validateInput1() && validateInput2()) {

            MathematicalOperations op = new MathematicalOperations();
            Polynomial quotient = new Polynomial();
            Polynomial remainder = new Polynomial();
            remainder.setPolynomial(polynomial1.getPolynomial());

            remainder = op.division(polynomial2, quotient, remainder);
            String resultString = "";
            if (quotient.getPolynomial().isEmpty()) {
                resultString = "Could not perform division.";
            } else {
                resultString = computeResult(quotient, resultString);
                if (!remainder.getPolynomial().isEmpty()) {
                    resultString = resultString.concat(", remainder ");
                    resultString = computeResult(remainder, resultString);
                }
            }

            result.setText(resultString);
        }
    }

    public void deriveInput(Polynomial polynomial) {
        if (!polynomial.getPolynomial().isEmpty()) {
            MathematicalOperations op = new MathematicalOperations();
            Polynomial derivative = op.derivation(polynomial);
            String resultString = "";
            if (derivative.getPolynomial().isEmpty())
                resultString = "0";
            else {
                resultString = computeResult(derivative, resultString);
            }
            result.setText(resultString);
        }
    }

    public void derive() {
        if (buttonP1.isSelected() && buttonP2.isSelected()) {
            alert.alertInvalidSelection();
        } else if (!buttonP1.isSelected() && !buttonP2.isSelected()) {
            alert.alertInvalidSelection();
        } else if (buttonP1.isSelected()) {
            if (validateInput1()) {
                deriveInput(polynomial1);
            }
        } else if (buttonP2.isSelected()) {
            if (validateInput2()) {
                deriveInput(polynomial2);
            }
        }
    }

    public void integrateInput(Polynomial polynomial) {
        if (!polynomial.getPolynomial().isEmpty()) {
            MathematicalOperations op = new MathematicalOperations();
            Polynomial integral = op.integration(polynomial);
            String resultString = "";
            if (integral.getPolynomial().isEmpty())
                resultString = "0";
            else {
                resultString = computeResult(integral, resultString);
            }
            result.setText(resultString);
        }
    }

    public void integrate() {
        if (buttonP1.isSelected() && buttonP2.isSelected()) {
            alert.alertInvalidSelection();
        } else if (!buttonP1.isSelected() && !buttonP2.isSelected()) {
            alert.alertInvalidSelection();
        } else if (buttonP1.isSelected()) {
            if (validateInput1()) {
                integrateInput(polynomial1);
            }
        } else if (buttonP2.isSelected()) {
            if (validateInput2()) {
                integrateInput(polynomial2);
            }
        }
    }

    public void clear() {
        result.setText("");
        polynom1txt.setText("");
        polynom2txt.setText("");
        polynomial1.getPolynomial().clear();
        polynomial2.getPolynomial().clear();
    }
}



