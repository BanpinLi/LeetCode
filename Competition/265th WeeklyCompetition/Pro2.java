package src;

import java.util.ArrayList;
import java.util.List;

public class Pro2 {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // 链表化为集合
        List<Integer> nodeList = new ArrayList<>();
        while (head != null) {
            nodeList.add(head.val);
            head = head.next;
        }

        List<Integer> position = new ArrayList<>();
        for (int i = 1; i < nodeList.size() - 1; i++) {
            if (nodeList.get(i - 1) < nodeList.get(i) && nodeList.get(i) > nodeList.get(i + 1)) {
                position.add(i);
            }
            if (nodeList.get(i - 1) > nodeList.get(i) && nodeList.get(i) < nodeList.get(i + 1)) {
                position.add(i);
            }
        }

        int[] ret = new int[2];
        if (position.size() <= 1) {
            ret[0] = ret[1] = -1;
            return ret;
        }
        ret[0] = Integer.MAX_VALUE;
        for (int i = 1; i < position.size(); i++) {
            ret[0] = Math.min(ret[0], position.get(i) - position.get(i - 1));
        }
        ret[1] = position.get(position.size() - 1) - position.get(0);
        return ret;
    }
}
