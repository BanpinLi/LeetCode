package src;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    // 一种复杂的解法，考虑一个图
    // 1
    // |
    // 2
    // | \
    // 4 -> 3
    // | ___|
    // 6 <- 5
    // 将所有元素放入到一个队列中
    // 然后将队列中的元素全部弹出来，放入arr中，并得到元素对应的下一个元素
    // 直到队列为空的时候就结束循环
    // 此时从arr中取出元素放入ret中，如果该元素已经存在于ret中，则跳过
    // 1 2 3 4 5 6
    // 2 3 4 5 6
    // 3 4 5 6
    // 3 5 6
    // 5 6
    // 6
    // 6 -> 5 -> 3 -> 4 -> 2 -> 1 即为答案
    Map<Integer, ArrayList<Integer>> graph;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 先建图
        graph = new HashMap<>();
        for (int[] nums : prerequisites) {
            ArrayList<Integer> nodes = graph.getOrDefault(nums[0], new ArrayList<>());
            nodes.add(nums[1]);
            graph.put(nums[0], nodes);
        }

        boolean[] notLoop = new boolean[numCourses];
        for (int[] nodePair : prerequisites) {
            if (!dfs(nodePair[0], new boolean[numCourses], notLoop)) {
                return new int[0];
            }
        }

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            queue.offer(i);
        }

        List<Integer> arr = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int nodeIndex = queue.poll();
                arr.add(nodeIndex);
                if (graph.get(nodeIndex) != null) {
                    for (int node : graph.get(nodeIndex)) {
                        queue.offer(node);
                    }
                }
            }
        }

        boolean[] added = new boolean[numCourses];
        int[] ret = new int[numCourses];
        int off = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            int course = arr.get(i);
            if (!added[course]) {
                ret[off++] = course;
                added[course] = true;
            }
        }
        return ret;
    }

    private boolean dfs(int nodeIndex, boolean[] visited, boolean[] notLoop) {
        if (visited[nodeIndex]) {
            return false;
        }
        if (notLoop[nodeIndex]) {
            return true;
        }

        ArrayList<Integer> nextNodes = graph.getOrDefault(nodeIndex, null);
        if (nextNodes == null) {
            return true;
        }

        visited[nodeIndex] = true;
        for (int node : nextNodes) {
            if (!dfs(node, visited, notLoop)) {
                return false;
            }
        }
        visited[nodeIndex] = false;
        notLoop[nodeIndex] = true;

        return true;
    }
}
