public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        //反转链表的进阶版
        //使用一个tail来对head进行遍历，得到一个k长度的链表，剩下部分进行递归
        //写一个方法，传递一个链表头和尾，将这个链表进行反转，返回反转后的链表的尾，因为反转链表的头就是tail
        ListNode tail = head;
        int count = 0;
        while(tail != null) {
            count++;
            if(count == k) {
                break;
            }
            tail = tail.next;
        }
        //这里是递归的终点，也就是tail为null了，表示链表的长度不够了，直接返回
        if(tail == null) {
            return head;
        }
        //这里的计算顺序是先从左至右计算函数，最后在进行赋值，所以先计算了reverse函数之后tail位置改变
        //此时再计算右边函数就会出现无限递归的情况
        //reverse(head, tail).next = reverseKGroup(tail.next, k);
        ListNode temp = reverseKGroup(tail.next, k);
        reverse(head, tail).next = temp;
        return tail;
    }

    private ListNode reverse(ListNode head, ListNode tail) {
        //递归的作用是什么？将一条链表进行反转，返回尾节点，为什么返回尾节点，返回尾可以更好的进行链表连接
        //递归结束的位置是什么？链表只剩下一个节点了
        if(head == tail) {
            return tail;
        }

        reverse(head.next, tail).next = head;
        return head;
    }
}
