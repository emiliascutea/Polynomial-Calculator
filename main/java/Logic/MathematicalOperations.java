package Logic;

import Model.Polynomial;

import java.util.ArrayList;
import java.util.Collections;

public class MathematicalOperations {

    public Polynomial addition(Polynomial a, Polynomial b) {
        Polynomial sum = new Polynomial();
        a.getPolynomial().forEach((power, coefficient) -> {
            if (b.getPolynomial().containsKey(power)) {
                double coefficient1 = a.getPolynomial().get(power); // get coefficient of 1st polynomial
                double coefficient2 = b.getPolynomial().get(power); // get coefficient of 2nd polynomial
                coefficient = coefficient1 + coefficient2;
                if(coefficient!=0){
                    sum.add(coefficient, power);
                }
            } else {
                if(coefficient!=0){
                    sum.add(coefficient, power);
                }
            }
        });
        b.getPolynomial().forEach((power, coefficient) -> {
                    if (!a.getPolynomial().containsKey(power)) {
                        int p = power;
                        double c = b.getPolynomial().get(p);
                        sum.add(c, p);
                    }
                }
        );
        return sum;
    }

    public Polynomial subtraction(Polynomial a, Polynomial b) {
        Polynomial difference = new Polynomial();
        //iterate through the polynoms
        a.getPolynomial().forEach((power, coefficient) -> {
            if (b.getPolynomial().containsKey(power)) {
                double coefficient1 = a.getPolynomial().get(power); // get coefficient of 1st polynomial
                double coefficient2 = b.getPolynomial().get(power); // get coefficient of 2nd polynomial
                coefficient = coefficient1 - coefficient2;
                if (coefficient != 0) {
                    difference.add(coefficient, power);
                }
            } else {
                difference.add(coefficient, power);
            }
        });
        b.getPolynomial().forEach((power, coefficient) -> {
            if (!a.getPolynomial().containsKey(power)) {
                coefficient *= (-1);
                difference.add(coefficient, power);
            }
        });
        return difference;
    }

    public Polynomial multiplication(Polynomial a, Polynomial b) {
        Polynomial product = new Polynomial();

        a.getPolynomial().forEach((power1, coefficient1) -> {
            b.getPolynomial().forEach((power2, coefficient2) -> {
                if(coefficient1!=0 && coefficient2!=0){
                    int power = power1+power2;
                    double coefficient = coefficient1 * coefficient2;
                    product.add(coefficient, power);
                }
            });
        });
        return product;
    }

    public Polynomial division(Polynomial b, Polynomial quotient, Polynomial remainder) {
        ArrayList<Integer> powers1 = new ArrayList<>(remainder.getPolynomial().keySet());
        ArrayList<Integer> powers2 = new ArrayList<>(b.getPolynomial().keySet());
        Collections.sort(powers1);
        Collections.reverse(powers1);
        Collections.sort(powers2);
        Collections.reverse(powers2);
        int maxDegree1 = powers1.get(0); // get max power of first polynomial
        int maxDegree2 = powers2.get(0); // get max power of the second polynomial
        while (!remainder.getPolynomial().isEmpty() && maxDegree1 >= maxDegree2 && (maxDegree2>0 || (maxDegree2==0 && b.getPolynomial().get(maxDegree2)!=0))) {
            int power = maxDegree1 - maxDegree2; // subtract powers
            double coefficient1 = remainder.getPolynomial().get(maxDegree1); // get 1st coeff
            double coefficient2 = b.getPolynomial().get(maxDegree2); // get 2nd coeff
            double coefficient = coefficient1 / coefficient2; // divide coefficients
            Polynomial currentQuotient = new Polynomial(); // create current quotient
            currentQuotient.add(coefficient, power); // add in the current quotient
            quotient.add(coefficient, power); // add the current quotient in the quotient
            Polynomial difference = multiplication(currentQuotient,b); // multiply the current quotient with b
            remainder=subtraction(remainder,difference); // subtract from remainder the multiplication of curr quotient and b
            if (!remainder.getPolynomial().isEmpty()) { // if remainder still contains entries
                if (!remainder.getPolynomial().containsKey(maxDegree1)) { // if remainder does not contain maxdegree anymore
                    powers1.remove(0); // remove maxdegree from powers1
                    if (!powers1.isEmpty()) { // if powers1 is not empty maxdegree gets the next 1st elem in powers1
                        maxDegree1 = powers1.get(0);
                    }
                    else{
                        powers1.clear();
                        maxDegree1 = -1;
                    }
                }
            } else { // else remainder is empty
                powers1.clear();
                maxDegree1 = -1;
            }
        }
        return remainder;
    }

    public Polynomial derivation(Polynomial a) {
        Polynomial derivative = new Polynomial();
        a.getPolynomial().forEach((power, coeffcient) -> {
            if(power!=0){
                if(coeffcient!=0){
                    coeffcient= power * coeffcient;
                    power-=1;
                    derivative.add(coeffcient,power);
                }
            }
        });
        return derivative;
    }

    public Polynomial integration(Polynomial a) {
        Polynomial integrate = new Polynomial();

        a.getPolynomial().forEach((power, coefficient) -> {
            if(coefficient!=0){
                power += 1;
                coefficient = coefficient/ power;
                integrate.add(coefficient, power);
            }
        });
        return integrate;
    }
}
