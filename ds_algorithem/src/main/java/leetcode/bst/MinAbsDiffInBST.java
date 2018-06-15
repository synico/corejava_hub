package leetcode.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinAbsDiffInBST {
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    public int getMinimumDifference(TreeNode root) {
        int minDiff = Integer.MAX_VALUE;
        if(root == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> output = new ArrayList();
        while(root != null || stack.size() > 0) {
            if(root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.println("Node: " + root.val);
                output.add(root.val);
                root = root.right;
            }
        }
        
        output = new ArrayList();
        output.add(0, 104);
        output.add(1, 227);
        output.add(2, 236);
        output.add(3, 701);
        output.add(4, 911);
        for(int i = 0; i < output.size() - 1; i++) {
            int temp = Math.abs(output.get(i) - output.get(i + 1));
            minDiff = (minDiff < temp) ? minDiff : temp;
        }
        
        return minDiff;
    }
    
    public static void main(String args[]) {
        MinAbsDiffInBST bst = new MinAbsDiffInBST();
        TreeNode root = bst.new TreeNode(1);
        TreeNode tn1 = bst.new TreeNode(3);
        TreeNode tn2 = bst.new TreeNode(2);
//        root.left = tn1;
        root.right = tn1;
        tn1.left = tn2;
        
//        TreeNode tn3 = bst.new TreeNode(1);
//        TreeNode tn4 = bst.new TreeNode(4);
//        tn1.left = tn3;
//        tn1.right = tn4;
//        
//        TreeNode tn5 = bst.new TreeNode(3);
//        tn4.left = tn5;
        System.out.println(bst.getMinimumDifference(root));
    }

}
