package com.ad.dp;

public class DivideArray2Equal {
    
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for(int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if(sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        boolean [] dp = new boolean[20];
        for(int i = 0; i <= sum; i++) {
            dp[i] = false;
        }
        dp[0] = true;
        for(int i = 0; i < len; i++) {
            for(int j = sum; j >= nums[i]; j--) {
                dp[j] |= dp[j - nums[i]];
            }
        }
        return dp[sum];
    }

    public static void main(String[] args) {
        DivideArray2Equal test = new DivideArray2Equal();
        int [] array = {1, 5, 11, 5};
        boolean rst = test.canPartition(array);
        System.out.println(rst);
    }

}
