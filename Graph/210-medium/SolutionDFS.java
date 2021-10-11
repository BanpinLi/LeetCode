package src;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SolutionDFS {
    // 和前面不一样，这里使用的是[a, b]中，b指向a来建图，而不是a指向b
    // 考虑使用dfs来进行求解，并使用栈来存储节点
    // 当某一个节点的所有的节点都被访问过的时候，就将该节点放入到栈中
    private Deque<Integer> stack;
    private Map<Integer, ArrayList<Integer>> graph;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 先建图
        graph = new HashMap<>();
        stack = new LinkedList<>();
        for (int[] nums : prerequisites) {
            ArrayList<Integer> nodes = graph.getOrDefault(nums[1], new ArrayList<>());
            nodes.add(nums[0]);
            graph.put(nums[1], nodes);
        }

        boolean[] notLoop = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, new boolean[numCourses], notLoop)) {
                return new int[0];
            }
        }

        int[] ret = new int[numCourses];
        int off = 0;
        while (!stack.isEmpty()) {
            ret[off++] = stack.pop();
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
            stack.push(nodeIndex);
            notLoop[nodeIndex] = true;
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
        stack.push(nodeIndex);

        return true;
    }
}
