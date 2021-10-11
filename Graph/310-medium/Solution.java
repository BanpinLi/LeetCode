package src;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> ret = new ArrayList<>();
            ret.add(0);
            return ret;
        }
        // 从度为1的节点开始扫描，然后更新相邻节点的度，把度为1的节点再次加入到队列中

        // 建图并更新度
        Array[] graph = new Array[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Array();
        }
        int[] degrees = new int[n];
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                queue.offer(i);
            }
        }
        List<Integer> ret = new ArrayList<>();
        while (!queue.isEmpty()) {
            ret = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                ret.add(node);
                List<Integer> nodes = graph[node];
                for (int j = 0; j < nodes.size(); j++) {
                    if (--degrees[nodes.get(j)] == 1) {
                        queue.offer(nodes.get(j));
                    }
                }
            }
        }
        return ret;
    }

    private class Array extends ArrayList<Integer> {
    }
}