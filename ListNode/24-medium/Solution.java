public class Solution {
    public ListNode swapPairs(ListNode head) {
        //使用三个指针来进行记录，为了方便使用一个空的头指针nullHead来连接head指针
        //三个指针为pre，p1，p2，分别指向的是从左到右的指针
        //进行遍历，并不断交换节点，当p1或者p2为null的时候就退出循环
        if(head == null || head.next == null) {
            return head;
        }
        ListNode nullHead = new ListNode();
        nullHead.next = head;
        ListNode pre = nullHead;
        ListNode p1 = head;
        ListNode p2 = head.next;
        while(true) {
            //交换节点
            ListNode p = p2.next;
            pre.next = p2;
            p2.next = p1;
            p1.next = p;
            pre = p1;
            if(pre.next == null || pre.next.next == null) {
                return nullHead.next;
            }
            p1 = pre.next;
            p2 = p1.next;
        }
    }
}
