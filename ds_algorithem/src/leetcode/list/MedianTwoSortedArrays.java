package leetcode.list;

public class MedianTwoSortedArrays {
    
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        int[] nums = new int[len1 + len2];
        int i1 = 0, i2 =0, i = 0;
        int i1v, i2v;
        while(i < nums.length) {
            if(i1 < len1) {
                i1v = nums1[i1];
                if(i2 < len2) {
                    i2v = nums2[i2];
                    if(i1v < i2v) {
                        nums[i] = i1v;
                        i1++;
                    } else if(i1v > i2v) {
                        nums[i] = i2v;
                        i2++;
                    } else {
                        nums[i] = i1v;
                        i1++;
                        i++;
                        nums[i] = i2v;
                        i2++;
                    }
                    i++;
                } else {
                    nums[i] = i1v;
                    i1++;
                    i++;
                }
            } else {
                if(i2 < len2) {
                    nums[i] = nums2[i2];
                    i2++;
                    i++;
                }
            }
        }
        //binary search
        double rst = 0.0;
        if(nums.length % 2 == 1) {
            rst = nums[nums.length/2];
        } else {
            rst = (nums[nums.length/2 -1] + nums[nums.length/2])/2.0;
        }
        return rst;
    }
    
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int tlen = len1 + len2;
        double rst = 0;
        if(tlen%2 == 1) {//odd
            int median = tlen/2;
            int i = 0, i1 = 0, i2 =0;
            while(i <= median) {
                if(i1 < len1) {
                    if(i2 < len2) {
                        if(nums1[i1] < nums2[i2]) {
                            rst = nums1[i1];
                            i1++;
                        } else if (nums1[i1] > nums2[i2]) {
                            rst = nums2[i2];
                            i2++;
                        } else {
                            rst = nums2[i2];
                            i1++;
                            i2++;
                            i++;
                        }
                        i++;
                    } else {//遍历nums2结束
                        rst = nums1[i1];
                        i1++;
                        i++;
                    }
                } else {//遍历nums1结束
                    rst = nums2[i2];
                    i2++;
                    i++;
                }
            }
        } else {//even
            int rst1 = 0;
            int rst2 = 0;
            int median = tlen/2 -1;
            int i = 0, i1 = 0, i2 = 0;
            while(i <= median) {
                if(i1 < len1) {
                    if(i2 < len2) {
                        if(nums1[i1] < nums2[i2]) {
                            rst1 = nums1[i1];
                            i1++;
                        } else if (nums1[i1] > nums2[i2]) {
                            rst1 = nums2[i2];
                            i2++;
                        } else {
                            if(i % 2 == 0) {
                                rst1 = nums1[i1];
                                i1++;
                            } else {
                                rst1 = nums2[i2];
                                i2++;
                            }
                        }
                        i++;
                    } else {//遍历nums2结束
                        rst1 = nums1[i1];
                        i1++;
                        i++;
                    }
                } else {//遍历nums1结束
                    rst1 = nums2[i2];
                    i2++;
                    i++;
                }
            }
            
            if(i1 < len1) {
                if(i2 < len2) {
                    if(nums1[i1] < nums2[i2]) {
                        rst2 = nums1[i1];
                    } else {
                        rst2 = nums2[i2];
                    }
                } else {
                    rst2 = nums1[i1];
                }
            } else {
                rst2 = nums2[i2];
            }
            rst = (rst1 + rst2)/2.0;
        }
        return rst;
    }
    
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        return 0.0;
    }

    public static void main(String[] args) {
        int [] nums1 = {2};
        int [] nums2 = {2};
        System.out.println(MedianTwoSortedArrays.findMedianSortedArrays1(nums1, nums2));
        
        int [] nums3 = {1, 2};
        int [] nums4 = {3, 4};
        System.out.println(MedianTwoSortedArrays.findMedianSortedArrays1(nums3, nums4));
    }

}
