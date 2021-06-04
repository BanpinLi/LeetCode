public class Solution_optimize {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //考虑几种情况：
        /**
         * 链表相交：
         *      长度一致，那么同时从头节点移动就会同时到达交点，返回tempA
         *      长度不一致，同时移动头节点，并在到达尾节点的时候指向另一条链表，此时也同时到达交点
         * 链表不相交：
         *      长度一致，同时到达尾，返回tempA，即null
         *      长度不一致，到达尾的时候，连接到另条链表，之后同时到达尾返回tempA，即null
         */
        ListNode tempA = headA;
        ListNode tempB = headB;
        while(tempA != tempB) {
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }
        return tempA;
    }
}
