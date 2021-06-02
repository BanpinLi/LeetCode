public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //使用分治算法将一个lists拆分成两个，然后调用方法进行合并
        //递归结束的终点是传递进来的lists的长度为 1 就直接返回，为 2 就归并排序

        //边界条件，防止后面的的方法造成数组索引越界
        if(lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    //这个方法才是用来递归的，start和end表示了数组的长度
    private ListNode mergeKLists(ListNode[] lists, int start, int end) {
        //的递归的终点是start和end的差值是 1 或者 2
        if(end - start == 0) {
            return lists[start];
        }
        if(end - start == 1) {
            return mergeTwoLists(lists[start], lists[end]);
        }

        //递归的中间过程，将一个长度大于3的数组分成两组
        ListNode a = mergeKLists(lists, start, (start + end) / 2);
        ListNode b = mergeKLists(lists, (start + end) / 2 + 1, end);
        return mergeTwoLists(a, b);
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        //经典的归并排序进行合并
        ListNode nuNode = new ListNode();
        ListNode p = nuNode;
        while(a != null && b != null) {
            if(a.val < b.val) {
                p.next = a;
                a = a.next;
            } else {
                p.next = b;
                b = b.next;
            }
            p = p.next;
        }
        if(a != null) {
            p.next = a;
        }
        if(b != null) {
            p.next = b;
        }
        return nuNode.next;
    }
}
