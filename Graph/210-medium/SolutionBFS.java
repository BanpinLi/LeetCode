package src;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class SolutionBFS {
    // 同样对于这样一个图，我们的理想建图方式是[a, b]， b -> a，a的入度++
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 前面使用的DFS其实是一种逆向思维，先将后修课程入栈，然后才入栈先修课程
        // BFS就是一种顺序的思维，直接将那些没有先修课程或者先修课程已经修完的课程入队
        // 然后移出队首元素，并更新相应的课程的入度，然后加入入度为0的课程
        // 最后判断ret是否是满的，否则直接返回空数组

        // 建图并创建入度表
        // Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        Array[] gragh = new Array[numCourses];
        for (int i = 0; i < numCourses; i++)
            gragh[i] = new Array();
        int[] incoming = new int[numCourses];
        for (int[] nodePair : prerequisites) {
            incoming[nodePair[0]]++;
            gragh[nodePair[1]].add(nodePair[0]);
        }

        // 将入度为0的节点加入进来
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (incoming[i] == 0) {
                queue.offer(i);
            }
        }

        int[] ret = new int[numCourses];
        int off = 0;
        while (!queue.isEmpty()) {
            int nodeIndex = queue.poll();
            ret[off++] = nodeIndex;

            // 更新入度表，并同时入队入度为0的节点
            Array nodes = gragh[nodeIndex];
            for (int i = 0; i < nodes.size(); i++) {
                if (--incoming[nodes.get(i)] == 0) {
                    queue.offer(nodes.get(i));
                }
            }
        }

        return off == numCourses ? ret : new int[0];
    }

    private class Array extends ArrayList<Integer> {
    }
}
