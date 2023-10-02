
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial{
    double[] coefficients;
    int[] exponents;

    public Polynomial(){
    coefficients = new double[1];
    exponents = new int[1];
    }

    public Polynomial(double[] coef, int[] expo){
        if(coef.length != expo.length){
            System.out.println("Polynomial coefficients and exponents are not corresponded. Please validate.");
        }

        coefficients = new double[coef.length];
        exponents = new int[expo.length];
        
        for(int i = 0; i < coef.length; i++){
            coefficients[i] = coef[i];
            exponents[i] = expo[i];  
        }
    }   
    
    public Polynomial add(Polynomial function){
        HashMap<Integer, Double> termMap = new HashMap<>();
    
        // Add terms from 'this' polynomial
        for (int i = 0; i < this.coefficients.length; i++) {
            termMap.put(this.exponents[i], termMap.getOrDefault(this.exponents[i], 0.0) + this.coefficients[i]);
        }
    
        // Add terms from 'function' polynomial
        for (int i = 0; i < function.coefficients.length; i++) {
            termMap.put(function.exponents[i], termMap.getOrDefault(function.exponents[i], 0.0) + function.coefficients[i]);
        }
    
        // Create the result polynomial
        int resultSize = termMap.size();
        
        // if they have the opposite coef and same exponents, cancel to zero
        for(int i = 0; i < this.exponents.length; i++){
            for(int m = 0; m < function.exponents.length; m++){
                if((this.exponents[i] == function.exponents[m]) 
                && (this.coefficients[i] == -(function.coefficients[m]))){
                    resultSize--;
                }
            }
        }

        // initiate result polynomial
        double[] resultCoefficients = new double[resultSize];
        int[] resultExponents = new int[resultSize];
    
        int index = 0;
        for (Map.Entry<Integer, Double> entry : termMap.entrySet()) {
            resultExponents[index] = entry.getKey();
            resultCoefficients[index] = entry.getValue();
            index++;
        }
    
        return new Polynomial(resultCoefficients, resultExponents);
    }
    

    public double evaluate(double x) {
        double sum = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            sum += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return sum;
    }    

    public boolean hasRoot(double root){
        double result = evaluate(root);
        if (result == 0){
            return true;
        }
        return false;
    }

    public Polynomial multiply(Polynomial y){
        int thisLength = this.coefficients.length;
        int yLength = y.coefficients.length;
        int productLength = thisLength * yLength;
    
        Polynomial product = new Polynomial(new double[productLength], new int[productLength]);
    
        int n = 0;
    
        // Multiply every element
        for (int i = 0; i < thisLength; i++) {
            for (int m = 0; m < yLength; m++) {
                product.coefficients[n] = this.coefficients[i] * y.coefficients[m];
                product.exponents[n] = this.exponents[i] + y.exponents[m];
                n++;
            }
        }
    
        // Combine terms with identical exponents
        for (int i = 0; i < productLength; i++) {
            for (int j = i + 1; j < productLength; j++) {
                if (product.exponents[i] == product.exponents[j]) {
                    product.coefficients[i] += product.coefficients[j];
                    // Set the coefficient at j to 0 to mark it for removal
                    product.coefficients[j] = 0.0;
                }
            }
        }
    
        // Remove terms with coefficient 0
        int newSize = 0;
        for (int i = 0; i < productLength; i++) {
            if (product.coefficients[i] != 0.0) {
                product.coefficients[newSize] = product.coefficients[i];
                product.exponents[newSize] = product.exponents[i];
                newSize++;
            }
        }
    
        // Resize the arrays to the actual size
        product.coefficients = Arrays.copyOf(product.coefficients, newSize);
        product.exponents = Arrays.copyOf(product.exponents, newSize);

        return product;
    }


    // add a new constructor
    public Polynomial(File file)throws IOException{
        BufferedReader br = null;
        String[] terms = null; // Declare terms here

        br = new BufferedReader(new FileReader(file)); // Use the provided file parameter
        String line = br.readLine(); // Read a single line from the file

        if (line != null) {
            // Process the line or split it as needed
            terms = line.split("(?=[+-])");
        }
        else{
            System.out.println("The file is empty.");
        }

        coefficients = new double[terms.length];
        exponents = new int[terms.length];

        for(int i = 0; i < terms.length; i++){
            String[] parts = terms[i].split("x");
            if(parts.length == 1){
                coefficients[i] = Double.parseDouble(parts[0]);
                exponents[i] = 0;
            }
            else{
                coefficients[i] = Double.parseDouble(parts[0]);
                exponents[i] = Integer.parseInt(parts[1]);
            }
        }
        br.close();
    }

    public void saveToFile(String file_name)throws IOException{
        BufferedWriter edit = new BufferedWriter(new FileWriter(file_name));
        String poly_string = "";

        for(int i = 0; i < coefficients.length; i++){
            if((coefficients[i] > 0) && (i != 0)){
                poly_string += "+" + Double.toString(coefficients[i]) + "x";
                if(exponents[i] != 0){
                    poly_string += Integer.toString(exponents[i]);
                }
                else{
                    poly_string += Double.toString(coefficients[i]) + "x";
                }
            }
            else{
                poly_string += Double.toString(coefficients[i]) + "x";
            }
        }
        edit.write(poly_string);
        edit.close();
    }
}
