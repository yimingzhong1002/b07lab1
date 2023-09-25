public class Polynomial{
    double[] coefficients = new double[100];
    // initialize a class Polynomial, which contains solely primitive, numeric type, double

    public Polynomial(){
    // create this no-argument constructor to return 0.0 when passing down n/a
    }

    public Polynomial(double arr[]){
        for(int i=0; i<arr.length; i++){
            coefficients[i]=arr[i];
        }
    }

    public Polynomial add(Polynomial function){
    // 'add' method
        Polynomial result = new Polynomial(); 
    // Create a new Polynomial to store the result
        for (int i = 0; i < 100; i++) {
            result.coefficients[i] = this.coefficients[i] + function.coefficients[i];
        }
        return result; // Return the result Polynomial
    }

    public double evaluate(double x){
        double sum = 0;
        for(int i=0; i<100; i++){
            sum = sum + coefficients[i]*Math.pow(x,i);
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
}