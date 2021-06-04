public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //遍历两个头节点，获得分别的长度，然后让长的链表移动差值的长度，最后进行同时移动
        int lenA = 0;
        int lenB = 0;
        ListNode tempA = headA;
        ListNode tempB = headB;
        while(tempA != null) {
            lenA++;
            tempA = tempA.next;
        }
        while(tempB != null) {
            lenB++;
            tempB = tempB.next;
        }
        int count = lenA - lenB;
        tempA = headA;
        tempB = headB;
        if(count < 0) {
            while(count != 0) {
                tempB = tempB.next;
                count++;
            }
        } else if(count > 0) {
            while(count != 0) {
                tempA = tempA.next;
                count--;
            }
        }
        while(tempA != null) {
            if(tempA == tempB) {
                return tempA;
            } else {
                tempA = tempA.next;
                tempB = tempB.next;
            }
        }
        return false;
    }
}
