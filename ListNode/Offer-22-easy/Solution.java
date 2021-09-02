class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        // 非常经典的解法，快慢指针
        // 使用两个指针，之间的距离为k - 1，当快指针到达末尾之后，慢指针指向的位置即为指定位置
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k - 1; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
