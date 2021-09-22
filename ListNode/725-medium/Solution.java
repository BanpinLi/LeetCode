package src;

class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        // 获得链表的长度为len，k / len = n + pre
        // 表示前pre个分割长度为n + 1，后面的分割均为n
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        int n = len / k;
        int res = len % k;
        ListNode[] ret = new ListNode[k];
        for (int i = 0; i < k; i++) {
            int subLen = (i < res ? 1 : 0) + n;
            if (subLen > 0) {
                ret[i] = head;
                for (int j = 0; j < subLen - 1; j++) {
                    head = head.next;
                }
                temp = head;
                head = head.next;
                temp.next = null;
            } else {
                break;
            }
        }
        return ret;
    }
}