package leetcode.list;

public class ReverseLinkedList {
    
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
    
    public ListNode reverseList(ListNode node) {
        System.out.println("Enter node: " + node.val);
        if(node.next == null) {
            return node;
        } else {
            ListNode c = node.next;
            ListNode temp = reverseList(c);
            c.next = node;
            node.next = null;
            System.out.println("Return node: " + node.val);
            return temp;
        }
    }

    public static void main(String[] args) {
        //build a singly-linked list
        ReverseLinkedList rll = new ReverseLinkedList();
        ListNode head = rll.new ListNode(4);
        
        ListNode n1 = rll.new ListNode(3);
        head.next = n1;
        
        ListNode n2 = rll.new ListNode(6);
        n1.next = n2;
        
        ListNode n3 = rll.new ListNode(2);
        n2.next = n3;
        
        ListNode n4 = rll.new ListNode(1);
        n3.next = n4;
        n4.next = null;
        
        ListNode node = rll.reverseList(head);
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}
