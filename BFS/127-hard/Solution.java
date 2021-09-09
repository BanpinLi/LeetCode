import java.util.*;

class Solution {
    private List<ArrayList<Integer>> graph;
    private int minPathLen;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 构建开始节点startNode和图graph
        graph = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            graph.add(new ArrayList<>());
        }
        // List<Integer> startNode = new ArrayList<>();
        // //构建startNode
        // for(int i = 0;i < wordList.size();i++) {
        // if(isMatch(beginWord, wordList.get(i))) {
        // startNode.add(i);
        // }
        // }
        // 构建图graph
        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (isMatch(wordList.get(i), wordList.get(j))) {
                    ArrayList<Integer> temp1 = graph.get(i);
                    temp1.add(j);
                    graph.set(i, temp1);
                    ArrayList<Integer> temp2 = graph.get(j);
                    temp2.add(i);
                    graph.set(j, temp2);
                }
            }
        }

        // 从开始节点进行检查
        Queue<Integer> queue = new LinkedList<>();
        boolean[] used = new boolean[wordList.size()];
        // 省略构建startNode，而是直接将数据放到queue里面
        for (int i = 0; i < wordList.size(); i++) {
            if (isMatch(beginWord, wordList.get(i))) {
                // 因为少判断了一层，那就是第一层，所以进行额外判断
                if (wordList.get(i).equals(endWord)) {
                    return 2;
                }

                queue.offer(i);
                used[i] = true;
            }
        }

        bfs(3, queue, beginWord, endWord, wordList, used);
        return minPathLen;
    }

    // depth代表的是深度，也就是当前广搜正在搜索第几层，初始化为3，为什么depth初始化为2？
    // 因为queue进来的时候就已经存储了第一层的所有节点，所以判断都是从第3层开始
    private void bfs(int depth, Queue<Integer> queue, String beginWord, String endWord, List<String> wordList,
            boolean[] used) {
        // 广搜的结束点，表示搜索失败
        if (depth > wordList.size() + 1) {
            minPathLen = 0;
            return;
        }

        int queueLen = queue.size();
        for (int i = 0; i < queueLen; i++) {
            int nodeIndex = queue.poll();
            for (int index : graph.get(nodeIndex)) {
                // 判断这个节点是否被使用过
                if (used[index]) {
                    continue;
                }
                // 加入的节点包含了endWord，递归结束
                if (wordList.get(index).equals(endWord)) {
                    minPathLen = depth;
                    return;
                }

                queue.offer(index);
                used[index] = true;
            }
        }

        bfs(depth + 1, queue, beginWord, endWord, wordList, used);
    }

    // 判断s1和s2是否是只相差一位
    private boolean isMatch(String s1, String s2) {
        boolean flag = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (flag) {
                    return false;
                } else {
                    flag = true;
                }
            }
        }
        return flag;
    }
}