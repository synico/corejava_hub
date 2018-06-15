package leetcode.list;

public class ReverseNodesInKGroup {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
        public String toString() {
            return Integer.toString(val);
        }
    }
    
    public ListNode reverseNodes(ListNode node, int k) {
        return null;
    }
   
    public static void main(String[] args) {
        ReverseNodesInKGroup rn = new ReverseNodesInKGroup();
        ListNode head = rn.new ListNode(1);
        ListNode n2 = rn.new ListNode(2);
        head.next = n2;
        ListNode n3 = rn.new ListNode(3);
        n2.next = n3;
        ListNode n4 = rn.new ListNode(4);
        n3.next = n4;
        ListNode n5 = rn.new ListNode(5);
        n4.next = n5;
        ListNode newHead = rn.reverseNodes(head, 2);
        while(newHead != null) {
            System.out.println("Node: " + newHead.val);
            newHead = newHead.next;
        }
    }
}
