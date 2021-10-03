package src;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // 递归，返回一个已经经过处理的链表的头节点
        if (head == null || head.next == null) {
            return head;
        }

        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
        } else {
            int hVal = head.val;
            while (head != null && head.val == hVal) {
                head = head.next;
            }
            head = deleteDuplicates(head);
        }
        return head;
    }
}
