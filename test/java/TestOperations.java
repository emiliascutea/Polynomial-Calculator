import GUI.Controller;
import Logic.MathematicalOperations;
import Model.Polynomial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestOperations {
    @Test
    public void addTest() {
        MathematicalOperations op = new MathematicalOperations();
        Polynomial polynomial1 = new Polynomial();
        Polynomial polynomial2 = new Polynomial();
        polynomial1.add(2, 1);
        polynomial1.add(3, 2);
        polynomial1.add(7, 4);
        polynomial2.add(8, 2);
        polynomial2.add(9, 3);
        Polynomial sum = op.addition(polynomial1, polynomial2);
        Controller controller = new Controller();
        String result = "";
        assertEquals("2x+11x^2+9x^3+7x^4", controller.computeResult(sum, result));
    }

    @Test
    public void subtractTest() {
        MathematicalOperations op = new MathematicalOperations();
        Polynomial polynomial1 = new Polynomial();
        Polynomial polynomial2 = new Polynomial();
        polynomial1.add(2, 1);
        polynomial1.add(3, 2);
        polynomial1.add(7, 4);
        polynomial2.add(8, 2);
        polynomial2.add(9, 3);
        Polynomial difference = op.subtraction(polynomial1, polynomial2);
        Controller controller = new Controller();
        String result = "";
        assertEquals("2x-5x^2-9x^3+7x^4", controller.computeResult(difference, result));
    }

    @Test
    public void multiplyTest() {
        MathematicalOperations op = new MathematicalOperations();
        Polynomial polynomial1 = new Polynomial();
        Polynomial polynomial2 = new Polynomial();
        polynomial1.add(2, 1);
        polynomial1.add(3, 2);
        polynomial1.add(7, 4);
        polynomial2.add(8, 2);
        polynomial2.add(9, 3);
        Polynomial product = op.multiplication(polynomial1, polynomial2);
        Controller controller = new Controller();
        String result = "";
        assertEquals("16x^3+42x^4+27x^5+56x^6+63x^7", controller.computeResult(product, result));
    }

    @Test
    public void divideTest() {
        MathematicalOperations op = new MathematicalOperations();
        Polynomial polynomial1 = new Polynomial();
        Polynomial polynomial2 = new Polynomial();
        Polynomial quotient = new Polynomial();
        polynomial1.add(8, 2);
        polynomial1.add(16, 3);
        polynomial2.add(4, 2);
        Polynomial division = op.division(polynomial2, quotient, polynomial1);
        Controller controller = new Controller();
        String result = "";
        assertEquals("2+4x", controller.computeResult(quotient, result));
    }

    @Test
    public void integrateTest() {
        MathematicalOperations op = new MathematicalOperations();
        Polynomial polynomial = new Polynomial();
        polynomial.add(9, 2);
        polynomial.add(16, 3);
        Polynomial integral = op.integration(polynomial);
        Controller controller = new Controller();
        String result = "";
        assertEquals("3x^3+4x^4", controller.computeResult(integral, result));
    }

    @Test
    public void deriveTest() {
        MathematicalOperations op = new MathematicalOperations();
        Polynomial polynomial = new Polynomial();
        polynomial.add(9, 2);
        polynomial.add(16, 3);
        Polynomial derivative = op.derivation(polynomial);
        Controller controller = new Controller();
        String result = "";
        assertEquals("18x+48x^2", controller.computeResult(derivative, result));
    }

}
