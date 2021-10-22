package src;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[][] updateMatrix(int[][] nums) {
        // 广度优先搜索，从所有的0位置开始搜索，将1加入到队列中，没加入一次，距离加1
        Deque<int[]> queue = new LinkedList<>();
        int m = nums.length;
        int n = nums[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == 0) {
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }

        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pair = queue.poll();
                int row = pair[0];
                int col = pair[1];
                // searchAround(row, col, nums, visited, depth);
                offerQueue(row + 1, col, visited, queue, nums, depth);
                offerQueue(row - 1, col, visited, queue, nums, depth);
                offerQueue(row, col + 1, visited, queue, nums, depth);
                offerQueue(row, col - 1, visited, queue, nums, depth);
            }
        }

        return nums;
    }

    private boolean offerQueue(int row, int col, boolean[][] visited, Deque<int[]> queue, int[][] nums, int depth) {
        if (row < 0 || row >= visited.length || col < 0 || col >= visited[0].length || visited[row][col]) {
            return false;
        }

        nums[row][col] = depth;
        visited[row][col] = true;
        queue.offer(new int[] { row, col });

        return true;
    }
}