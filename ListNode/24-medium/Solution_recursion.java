public class Solution_recursion {
    public ListNode swapPairs(ListNode head) {
        //使用递归来进行求解，主要解决的问题是，递归要干什么？
        //递归就是传递一个链表，然后返回交换好的链表的头节点
        //交换链表节点：p0 -> p1 -> p2 -> p3 -----> p0 -> p2 -> p1 -> p3，这里交换了 p1 和 p2
        
        //考虑递归的终点，那就是一条链表只有一个节点或者是空的
        if(head == null || head.next == null) {
            return head;
        }

        //交换前面两个节点，然后让交换了的第二个节点去连接后面的链表，这里使用递归
        ListNode p1 = head;
        ListNode p2 = p1.next;
        ListNode temp = swapPairs(p2.next);
        p2.next = p1;
        p1.next = temp;
        return p2;
    }
}
