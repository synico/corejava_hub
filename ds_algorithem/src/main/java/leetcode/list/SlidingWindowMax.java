package leetcode.list;

/**
* We scan the array from 0 to n-1, keep ¡°promising¡± elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.
* At each i, we keep ¡°promising¡± elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means
* 1. If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array
* 2. Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the ¡°max¡± in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.
* 3. As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]
*/
import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMax {
    
    /**
     * @param nums
     * @param 1 =< k =< nums.length
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || k <= 0) {
            return new int[0];
        }
        int[] rst = new int[nums.length - k + 1];
        int ri = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {
            //remove element which is out of scope
            while(!q.isEmpty() && q.peekFirst() < i - k + 1) {
                q.pollFirst();
//                q.poll();
            }
            //remove smaller element
            while(!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.offerLast(i);
//            q.offer(i);
            if(i >= k - 1) {
                rst[ri++] = nums[q.peekFirst()];
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        SlidingWindowMax swm = new SlidingWindowMax();
        //case 1
        int[] nums1 = {1,3,-1,-3,5,3,6,7};
        int k1 = 3;
        int[] rst1 = swm.maxSlidingWindow(nums1, k1);
        for(int v : rst1) {
            System.out.print(v + "|");
        }
        System.out.println();
        System.out.println("--------------------------");
        //case 2
        int[] nums2 = {7, 4};
        int k2 = 1;
        int[] rst2 = swm.maxSlidingWindow(nums2, k2);
        for(int v : rst2) {
            System.out.print(v + "|");
        }
    }

}
