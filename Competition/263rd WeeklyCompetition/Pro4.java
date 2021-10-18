package src;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Pro4 {
    // 寻找第二短的路径那里所用到的算法复杂度太大了
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        int secondPathLen = secondPathLen(n, edges);

        return getTime(time, change, secondPathLen);

    }

    private int secondPathLen(int n, int[][] edges) {
        // 建图
        Array[] map = new Array[n + 1];
        for (int i = 0; i < map.length; i++) {
            map[i] = new Array();
        }
        for (int[] pair : edges) {
            map[pair[0]].add(pair[1]);
            map[pair[1]].add(pair[0]);
        }

        Deque<Integer> queue = new LinkedList<>();
        queue.offer(1);
        int count = 0;
        int depth = 0;
        while (count != 2) {
            int size = queue.size();
            depth++;
            boolean[] added = new boolean[map.length];
            for (int i = 0; i < size; i++) {
                int nodeIndex = queue.poll();
                for (int nextNodeIndex : map[nodeIndex]) {
                    if (!added[nextNodeIndex]) {
                        queue.offer(nextNodeIndex);
                        if (nextNodeIndex == n) {
                            count++;
                        }
                        added[nextNodeIndex] = true;
                    }
                }
            }
        }
        return depth;
    }

    // 直接用模拟来做
    private int getTime(int time, int change, int steps) {
        int nowTime = 0;
        for (int i = 0; i < steps - 1; i++) {
            nowTime += time;
            // 判断nowTime是否是在红灯区
            int res = nowTime % (2 * change);
            if (res >= change) {
                nowTime = nowTime - res + 2 * change;
            }
        }
        return nowTime + time;
    }

    private class Array extends ArrayList<Integer> {
    }
}
