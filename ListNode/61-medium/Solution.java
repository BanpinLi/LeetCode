package src;

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        k %= len;
        if (k == 0) {
            return head;
        }
        k = len - k;

        ListNode retNode = head;
        for (int i = 0; i < k - 1; i++) {
            retNode = retNode.next;
        }

        ListNode temp = retNode;
        retNode = retNode.next;
        temp.next = null;
        tail.next = head;
        return retNode;
    }
}
