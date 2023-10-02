import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {-2,6,3,-7};
        int [] c2 = {4,1,2,3};
        Polynomial p1 = new Polynomial(c1,c2);
        double [] c3 = {6,5};
        int [] c4 = {2,0};
        Polynomial p2 = new Polynomial(c3,c4);

        // add testing
        Polynomial s = p1.add(p2);
        System.out.println("s(2) = " + s.evaluate(2));
        if(s.hasRoot(2)){
            System.out.println("1 is a root of s");
        }
        
        else{
            System.out.println("1 is not a root of s");
        }

        // multiply testing
        Polynomial m = p1.multiply(p2);
        System.out.println("m(2) = " + m.evaluate(2));
        
        // file constructor testing
        try {
            File polynomialFile = new File("/Users/lynnyimingzhong/Desktop/b07lab1/mypolynomial.txt");
            Polynomial o = new Polynomial(polynomialFile);
            for(int i = 0; i < o.coefficients.length; i++){
                System.out.println("C is " + o.coefficients[i] + " E is " + o.exponents[i]);
            }
            double result = o.evaluate(3);
            System.out.println("Result: " + result);

            // saveToFile testing
            double [] coef_test = {-2,6,3,-3};
            int [] expo_test = {4,1,2,0};
            Polynomial poly_write = new Polynomial(coef_test, expo_test);
            poly_write.saveToFile("output_file.txt");
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception or display an error message
        }
    
    }
}

