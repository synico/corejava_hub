
public class Fibonacci {
    
    private static int calcFibRecursive(int fib) {
        if(fib == 0) {
            return 0;
        }
        if(fib == 1) {
            return 1;
        }
        return calcFibRecursive(fib -2) +  calcFibRecursive(fib - 1);
    }
    
    private static int calcFibNonRecursive(int fib) {
        int fibMinusTwo = 0;
        int fibMinusOne = 1;
        int fibRst = 0;
        if(fib == 1) {
            return fibMinusTwo;
        }
        if(fib == 2) {
            return fibMinusOne;
        }
        for(int i = 2; i <= fib; i++) {
            fibRst = fibMinusTwo + fibMinusOne;
            fibMinusTwo = fibMinusOne;
            fibMinusOne = fibRst;
        }
        return fibRst;
    }

    public static void main(String[] args) {
        int fib = 5;
        int fibRecursive = Fibonacci.calcFibRecursive(fib);
        System.out.println("Calculate Fib in recursive way: " + fibRecursive);
        int fibNonRecursive = Fibonacci.calcFibNonRecursive(fib);
        System.out.println("Calculate Fib in recursive way: " + fibNonRecursive);
    }

}
