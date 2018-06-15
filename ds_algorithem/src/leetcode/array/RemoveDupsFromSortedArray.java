package leetcode.array;

/**
 * Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * @author 907729
 */

public class RemoveDupsFromSortedArray {
    
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int dup = nums[0];
        int ri = 1;
        
        for(int cp = 1; cp < n; cp++) {
            if(dup == nums[cp]) {
                
            } else {
                dup = nums[cp];
                swap(nums, cp, ri);
                ri++;
            }
        }
        System.out.println(ri);
        return ri;
    }
    
    private void swap(int[] a, int from, int to) {
        int temp = a[from];
        a[from] = a[to];
        a[to] = temp;
    }

    public static void main(String[] args) {
        RemoveDupsFromSortedArray demo = new RemoveDupsFromSortedArray();
        int[] case1 = {1, 1, 2, 2, 2, 3};
        demo.removeDuplicates(case1);
    }

}
