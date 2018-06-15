package leetcode.list;

public class AddTwoNumbers {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
        }
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode c = null;
        
        while(l1 != null || l2 != null) {
            int l1v = l1 != null ? l1.val : 0;
            int l2v = l2 != null ? l2.val : 0;
            int sum = l1v + l2v;
            if(c != null && c.next != null) {
                sum = sum + c.next.val;
            }
            int newVal = sum % 10;
            int delta = sum / 10;
            
            if(head == null) {
                head = new ListNode(newVal);
                c = head;
            } else {
                if(c.next != null) {
                    c.next = new ListNode(newVal);
                    c = c.next;
                }
            }
            
            boolean move = false;
            if(l1 != null) {
                l1 = l1.next;
                if(l1 != null) {
                    move = true;
                }
            }
            if(l2 != null) {
                l2 = l2.next;
                if(l2 != null) {
                    move = true;
                }
            }
            
            if(move || sum != newVal) {
                c.next = new ListNode(delta);
            }
        }
        
        return head;
    }
    
    public void printListNodes(ListNode head) {
        while(head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        AddTwoNumbers atn = new AddTwoNumbers();
        ListNode ln1 = atn.new ListNode(1);
//        ln1.next = atn.new ListNode(8);
//        ln1.next.next = atn.new ListNode(3);
        atn.printListNodes(ln1);
                
        ListNode ln2 = atn.new ListNode(9);
        ln2.next = atn.new ListNode(9);
//        ln2.next.next = atn.new ListNode(4);
        atn.printListNodes(ln2);
        
        ListNode output = atn.addTwoNumbers(ln1, ln2);
        atn.printListNodes(output);
    }

}
