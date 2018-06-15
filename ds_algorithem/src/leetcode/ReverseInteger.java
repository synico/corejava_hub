package leetcode;

public class ReverseInteger {
    
    public static int reverse(int x) {
        if(x >= Integer.MAX_VALUE || x <= Integer.MIN_VALUE) {
            return 0;
        } else {
            String s = null;
            if(x > 0) {
                s = Integer.toString(x);
            } else {
                s = Integer.toString(Math.abs(x));
            }
            
            char[] sa = s.toCharArray();
            char [] newarr = new char[sa.length];
            int j = 0;
            for(int i = sa.length - 1; i >= 0; i--) {
                newarr[j++] = sa[i];
            }
            String reversedInt = new String(newarr);
            if(x < 0) {
                reversedInt = "-" + reversedInt;
            }
            long rst = Long.parseLong(reversedInt);
            if(rst >= Integer.MAX_VALUE || rst <= Integer.MIN_VALUE) {
                rst = 0;
            }
            return (int)rst;
        }
    }

    public static void main(String[] args) {
        ReverseInteger.reverse(-2147483648);
    }

}
