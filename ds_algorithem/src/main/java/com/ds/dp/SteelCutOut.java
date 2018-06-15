package com.ds.dp;

public class SteelCutOut {
    
    int [] price = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
    
    public int recursiveCutRod(int n) {
        if(n == 0) {
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            q = Math.max(q, price[i] + recursiveCutRod(n - i));
        }
        return q;
    }
    
    /**
     * top-down with memoization
     * array r is the memoization
     */
    public int topDownMethod(int n) {
        int [] r = new int[n + 1];
        for(int i=0;i<=n;i++) {
            r[i] = -1;
        }
        return topDownMethodAux(n, r);
    }
    
    /**
     * auxiliar method of topDownMethod
     */
    private int topDownMethodAux(int n, int[] r) {
        int q = 0;
        if(r[n] >= 0) {
            return r[n];
        }
        if(n == 0) {
            q = 0;
        } else {
            q = -1;
            for(int i=1;i<=n;i++) {
                q = Math.max(q, price[i] + topDownMethodAux(n-i,r));
            }
            r[n] = q;
        }
        return q;
    }
    
    /**
     * bottom up method
     */
    public int bottomUpMethod(int n) {
        int [] r = new int[n + 1];
        r[0] = 0;
        int q = -1;
        for(int j=1;j<=n;j++) {
            for(int i=1;i<=j;i++) {
                q = Math.max(q, price[i] + r[j - i]);
            }
            r[j] = q;
        }
        return r[n];
    }

    public static void main(String[] args) {
        SteelCutOut sco = new SteelCutOut();
        int r = sco.bottomUpMethod(10);
        System.out.println("optical revene: " + r);
    }

}
