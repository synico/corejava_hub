
public class SwapVariableValue {
    
    public static void swapVar(int a, int b) {
        System.out.printf("before swapVar::a = %d, b= %d", a, b);
        System.out.println();
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.printf(" after swapVar::a = %d, b= %d", a, b);
        System.out.println();
    }
    
    public static void swapInt(int a, int b) {
        System.out.printf("before swapInt::a = %d, b= %d", a, b);
        System.out.println();
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.printf(" after swapInt::a = %d, b= %d", a, b);
        System.out.println();
    }

    public static void main(String[] args) {
        SwapVariableValue.swapVar(12, 4);
        SwapVariableValue.swapInt(12,  4);
    }

}
