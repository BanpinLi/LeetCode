package src;

public class Solution {

    // public Node flatten(Node head) {
    // if (head == null) {
    // return null;
    // }
    // // dfs，扫描Node，将child拼接到next上，然后返回child末尾节点，在拼接到上一个next上
    // Node copyNode = head;
    // dfs(copyNode);
    // return head;
    // }

    // private Node dfs(Node head) {
    // while (true) {
    // // 找到最后一个节点或者节点的child不为null的节点
    // while (head.child == null && head.next != null) {
    // head = head.next;
    // }
    // // 如果为最后一个节点并且这个节点的child为null
    // if (head.child == null && head.next == null) {
    // break;
    // }
    // Node temp = head.next; // 保存head的下一个节点
    // head.next = head.child; // 将head的next指针指向child
    // head.child.prev = head; // 将child的prev指针指向head
    // Node ret = dfs(head.child); // 回溯得到child的最后一个节点
    // head.child = null; // 将head的child置为null
    // ret.next = temp; // 让回溯得到的child的最后一个节点和head的下一个节点建立联系
    // if (temp != null) {
    // temp.prev = ret;
    // }
    // }
    // return head;
    // }

    public Node flatten(Node head) {
        // 脖子向左倒45°，形成一个二叉树，前序遍历
        if (head == null) {
            return head;
        }
        Node copyNode = head;
        dfs(copyNode);
        return head;
    }

    private Node dfs(Node head) {
        Node left = head.child;
        Node right = head.next;
        // 左节点不为null，处理左
        if (left != null) {
            head.child = null;
            Node retNode = dfs(left);
            head.next = left;
            left.prev = head;
            retNode.next = right;
            head = retNode;
        }
        // 右节点不为null，处理右
        if (right != null) {
            head.next = right;
            right.prev = head;
            head = dfs(right);
        }
        return head;
    }
}
