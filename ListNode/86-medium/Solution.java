package src;

class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null) {
            return head;
        }
        
        // 一个最普通的想法是，重新生成两条链表，然后重组
        ListNode smallList = new ListNode();
        ListNode bigList = new ListNode();
        ListNode p1 = smallList;
        ListNode p2 = bigList;
        while(head != null) {
            if(head.val < x) {
                p1.next = head;
                p1 = p1.next;
            } else {
                p2.next = head;
                p2 = p2.next;
            }
            head = head.next;
        }
        p1.next = bigList.next;
        p2.next = null;
        return smallList.next;
    }
}
