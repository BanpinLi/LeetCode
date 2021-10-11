package src;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // 广度优先搜索
        // 通过原Node来得到当前克隆Node
        Map<Node, Node> map = new HashMap<>();
        Deque<Node> queue = new LinkedList<>();

        queue.offer(node);
        Node root = new Node(node.val);
        map.put(node, root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node preNode = queue.poll();
                List<Node> neighbors = new ArrayList<>();
                for (Node n : preNode.neighbors) {
                    Node cloneNode = map.getOrDefault(n, new Node(n.val));
                    if (!map.containsKey(n)) {
                        map.put(n, cloneNode);
                        queue.offer(n);
                    }
                    neighbors.add(cloneNode);
                }
                map.get(preNode).neighbors = neighbors;
            }
        }
        return root;
    }
}
