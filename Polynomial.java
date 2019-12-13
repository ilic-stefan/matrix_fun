/**
 * Class: Polynomial
 * Represents a Polynomial in the ring Z[x]
 * i.e. polynomial with integer coefficients
 */
public class Polynomial {
    private int[] coeffs; // in order of increasing (term) degree
    private int deg; // last non-zero coefficient in coeffs
    // the zero polynomial has no degree (deg = -1)

    // allocate more space (private)
    /**
     * Makes a copy of the current coefficients array
     * with twice the size and sets that to be the new
     * coeff field
     * pre:
     *   polynomial object must be initialized
     * post:
     *   make_more_space doubles coeffs.length
     *   the values (and order of values) of coeff array
     *   is preserved
     */
    private void make_more_space(){
        int[] new_coeffs = new int[2 * coeffs.length];

        for (int i = 0; i < coeffs.length; i++) {
            new_coeffs[i] = coeffs[i];
        }

        coeffs = new_coeffs;
    }
    
    // Constructor
    /**
     * Constructor
     * 
     * Args:
     *  @param description - A string representing a single variable polynomial 
     *    in standard form
     * Pre:
     *   polynomial must be written in the following form:
     *    a_{n}x^n + a_{n-1}x^{n-1} + ... + a_{1}x^1 + a_{0}x^0
     *   where each a_{i} is a positive integer and 
     *    power is a non negative integer
     *   a_{i} may be ommited (meaning a coefficient of 1)
     *   
     *   Note that *no power is repeated* (e.g. "x^2 + x^2" is not allowed)
     *   Note that every term must include "x^" as a substring
     *   
     *
     *   e.g. "2x^2 + 3x + 5"
     *
     * Post:
     *   creates a Polynomial object
     *   with coefficients array = {a_{0}, a_{1}, ... , a_{n-1}, a_{n}}
     *   and degree = n
     *   coefficients array length >= degree
     */
    Polynomial(String description) {
        coeffs = new int[5]; // initial size 5

        // case of zero polynomial
        if (description == "0x^0") {
            deg = -1;
            return;
        }
         
        String[] terms_lst = description.split("\\+"); //note escape spec. char.
        
        // process each term
        String[] indiv_term;
        for (int i = 0; i < terms_lst.length; i++) {
            // string split based on "x^"

            if (!terms_lst[i].contains("x^")) {
                System.out.println("improperly formed input: missing x^");
            }
                    
            indiv_term = terms_lst[i].trim().split("x\\^");
            
            // find the power of the term (and convert to int)
            int power = Integer.parseInt(indiv_term[1]);
            
            // find the coefficient (and convert to int)
            int c;
            if (indiv_term[0].equals("")) {
                c = 1;
            } else {
                c = Integer.parseInt(indiv_term[0]);
            }
            
            int insert_pos = power;
            // extend coeff if necessary
            while (insert_pos >= coeffs.length) {
                make_more_space();
            }
            
            // put the coefficient into coeffs
            coeffs[insert_pos] = c;

            // update total degree if necessary
            if ((power > deg) && (c != 0)) {
                deg = power;
            }
        }
    }

    public int getDegree() {
        return deg;
    }
    
    // toString
    public String toString() {
        String str = "(";

        for (int i = 0; i < coeffs.length; i++) {
            str += coeffs[i];
            if (i == coeffs.length - 1) {
                str += ")";
            }
            else {
                str += ", ";
            }
        }

        return str;
    }

    // get polynomial string
    /**
     * getPolyStr()
     * @returns a string representing the polynomial in human readable format
     * Pre:
     *  initialized object
     * Post:
     *  the polynomial is represented in terms of x as a string
     *  string in form:
     *   a_{n}x^n + a_{n-1}x^{n-1} + ... + a_{1}x^1 + a_{0}x^0
     *  where a_i is the i-th element of the coefficients array
     *   and n is the degree of the polynomial
     */
    public String getPolyStr() {
        if (deg == -1) {
            return "0x^0";
        } else {
            String str = "";

            int first_non_zero_ind = 0; // we already handled the zero poly case
            for (int j = 0; j <= deg; j++) {
                if (coeffs[j] != 0) {
                    first_non_zero_ind = j;
                    break;
                }
            }
            
            for (int i = deg; i >= 0; i--) {
                if (coeffs[i] != 0) {
                    str += coeffs[i] + "x^" + i;

                    if (i > first_non_zero_ind) {
                        str += " + ";
                    }
                }
            }
            return str;
        }
    }

    // Future Methods to Implement:
    // evaluate polynomial
    // add
    // multiply

    // main: for testing
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial("202x^3 + 50x^0 + 03x^1 + 7x^7");
        
        if (!p1.getPolyStr().equals("7x^7 + 202x^3 + 3x^1 + 50x^0")) {
            System.out.println("string error: p1");
        }
        if (p1.getDegree() != 7) {
            System.out.println("degree error: p1");
        }

        Polynomial p2 = new Polynomial("x^2");
        if (!p2.getPolyStr().equals("1x^2")) {
            System.out.println("string error: p2");
        }
        if (p2.getDegree() != 2) {
            System.out.println("degree error: p2");
        }

        Polynomial p3 = new Polynomial("0x^0");

        if (!p3.getPolyStr().equals("0x^0")) {
            System.out.println("string error: p3");
        }
        if (p3.getDegree() != -1) {
            System.out.println("degree error: p3");
        }
    }
}
