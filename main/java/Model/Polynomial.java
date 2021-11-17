package Model;

import java.util.HashMap;

public class Polynomial {

    public HashMap<Integer, Double> polynomial = new HashMap<>();

    public HashMap<Integer, Double> getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(HashMap<Integer, Double> polynom) {
        this.polynomial = polynom;
    }

    public void add(double coefficient, int power) {
        if (polynomial.containsKey(power)) {
            double coeff = polynomial.get(power);
            polynomial.put(power, coeff + coefficient);
        } else {
            polynomial.put(power, coefficient);
        }
    }
}