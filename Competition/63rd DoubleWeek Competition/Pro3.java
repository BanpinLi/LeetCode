package src;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Pro3 {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        // 建图，无向图
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < patience.length; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] pair : edges) {
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }

        // 统计节点到达0的路径长度
        int[] pathLen = new int[patience.length];

        boolean[] arrival = new boolean[patience.length];
        arrival[0] = true;

        // bfs得到节点到达的路径长度
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                int nodeIndex = queue.poll();
                for (int nextNodeIndex : map.get(nodeIndex)) {
                    if (!arrival[nextNodeIndex]) {
                        pathLen[nextNodeIndex] = depth;
                        queue.offer(nextNodeIndex);
                        arrival[nextNodeIndex] = true;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < patience.length; i++) {
            // nearestDis
            if (patience[i] >= 2 * pathLen[i]) {
                ans = Math.max(ans, 2 * pathLen[i]);
            } else {
                int nearestDis = (2 * pathLen[i]) % patience[i];
                if (nearestDis == 0) {
                    nearestDis = patience[i];
                }
                int temp = 4 * pathLen[i] - nearestDis;
                ans = Math.max(ans, temp);
            }
        }
        return ans + 1;
    }
}
