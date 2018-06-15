package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int diff = 0;
        int [] rst = new int[2];
        boolean bingo = false;
        
        for(int i = 0; i < n -1; i++) {
            rst[0] =i;
            diff = target - rst[0];
            for(int j = i + 1; j < n; j++) {
                if(nums[j] == diff) {
                    rst[1] = j;
                    bingo = true;
                    break;
                }
            }
            if(bingo) {
                break;
            }
        }
        return rst;
    }
    
    public int[] twoSumHash(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] case1 = {2, 7, 11, 15};
        int target1 = 9;
        TwoSum ts = new TwoSum();
        ts.twoSum(case1, target1);
    }

}
