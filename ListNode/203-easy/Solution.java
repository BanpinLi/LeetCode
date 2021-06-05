public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        //简单的链表罢了，使用一个dummy头节点，方便进行转换
        ListNode dummy = new ListNode(0, head);
        ListNode p0 = dummy;
        ListNode p = dummy.next;
        while(p != null) {
            if(p.val == val) {
                //满足条件进行删除
                p0.next = p.next;
            } else {
               p0 = p; 
            }
            p = p.next;
        }
        return dummy.next;
    }
}
