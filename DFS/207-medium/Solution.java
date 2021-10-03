package src;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 核心思想是找到一个有向图中是否存在环路，使用深度优先搜索

        // 建图
        List<Integer>[] arrList = new Array[numCourses];
        for (int[] nums : prerequisites) {
            int start = nums[0];
            int end = nums[1];
            if (arrList[start] == null) {
                arrList[start] = new Array();
            }
            arrList[start].add(end);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] notLoop = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (arrList[i] == null)
                continue;

            for (int index : arrList[i]) {
                boolean ret = dfs(index, arrList, visited, notLoop);
                if (!ret) {
                    return false;
                }
            }
        }
        return true;
    }

    // 深度优先搜索，每次开始搜索一个结点的时候，将visited标记为true，表示正在访问中
    // 同时在结束搜索这个结点之后，如果没有返回false，就将notloop置为true
    // 表示这个节点开始一定可以到达出口
    private boolean dfs(int nodeIndex, List<Integer>[] arrList, boolean[] visited, boolean[] notLoop) {
        if (notLoop[nodeIndex] || arrList[nodeIndex] == null) {
            return true;
        }
        if (visited[nodeIndex]) {
            return false;
        }

        visited[nodeIndex] = true;
        for (int index : arrList[nodeIndex]) {
            boolean ret = dfs(index, arrList, visited, notLoop);
            if (!ret) {
                return false;
            }
        }
        notLoop[nodeIndex] = true;
        visited[nodeIndex] = false;

        return true;
    }

    class Array extends ArrayList<Integer> {
    }
}
