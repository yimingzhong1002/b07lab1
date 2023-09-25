public class Polynomial{
    double[] coefficients;

    public Polynomial(){
    coefficients = new double[1];
    coefficients[0] = 0.0;
    }

    public Polynomial(double[] arr){
        coefficients = new double[arr.length];
        for(int i=0; i<arr.length; i++){
            coefficients[i]=arr[i];
        }
    }

    public Polynomial add(Polynomial function) {
        int max_len = Math.max(this.coefficients.length, function.coefficients.length);
        int min_len = Math.min(this.coefficients.length, function.coefficients.length);
        Polynomial result = new Polynomial(new double[max_len]);
        
        for(int i=0;i<max_len;i++){
            if(i<min_len){
                result.coefficients[i] = this.coefficients[i] + function.coefficients[i];
            }
            else
                if(max_len == this.coefficients.length)
                    result.coefficients[i] = coefficients[i];
                else
                    result.coefficients[i] = function.coefficients[i];
        }
        return result;
    }    

    public double evaluate(double x) {
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] * Math.pow(x, i);
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
