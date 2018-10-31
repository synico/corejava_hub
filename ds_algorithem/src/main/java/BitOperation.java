public class BitOperation {

    private static int op1(int target, int pos) {
        target |= target >>> pos;
        System.out.println("afeter n | (n >>> " + pos + "): " + Integer.toBinaryString(target));
        return target;
    }

    public static void main(String args[]) {
        int n = 9;
        n = n-1;
        n = op1(n, 1);
        n = op1(n, 2);
        n = op1(n, 4);
        n = op1(n, 8);
        n = op1(n, 16);
    }
}
