import java.util.*;

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //使用优先队列(堆)，将所有的链表存入到堆里面，时间复杂度是O(kn*log(kn))，k是链表条数，n是数量
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for(ListNode list : lists) {
            while(list != null) {
                ListNode temp = list;
                pq.offer(temp);
                list = list.next;
            }
        }
        ListNode nuNode = new ListNode();
        ListNode p = nuNode;
        while(!pq.isEmpty()) {
            p.next = pq.poll();
            p = p.next;
        }
        p.next = null;      //需要将最后一个节点的next置为null
        return nuNode.next;
    }
}
