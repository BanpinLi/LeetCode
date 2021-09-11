import java.util.*;

class Solution {
    private List<String> ans;

    public List<String> findWords(char[][] board, String[] words) {
        // 回溯 + 字典树

        ans = new ArrayList<>();
        int m = board.length;
        int n = board[0].length;

        // 将words存入字典树中
        DictTree dictTreeRoot = new DictTree();
        for (String word : words) {
            DictTree[] dictTreeNodes = dictTreeRoot.dictTrees;
            for (int i = 0; i < word.length(); i++) {
                int position = word.charAt(i) - 'a';
                if (dictTreeNodes[position] == null) {
                    dictTreeNodes[position] = new DictTree();
                }

                // 在这个节点添加结束标志
                if (i == word.length() - 1) {
                    dictTreeNodes[position].isEnd = true;
                }

                dictTreeNodes = dictTreeNodes[position].dictTrees;
            }
        }

        // 对每一个位置都要进行回溯
        boolean[][] used = new boolean[m][n];
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(dictTreeRoot.dictTrees, board, i, j, used, path);
            }
        }

        return ans;
    }

    private void dfs(DictTree[] DictTreeNodes, char[][] chs, int row, int col, boolean[][] used, StringBuilder path) {
        // 走出范围和走了走过的地方，直接返回
        if (row < 0 || row >= chs.length || col < 0 || col >= chs[0].length || used[row][col]) {
            return;
        }

        // 这个位置有一个字符，将这个字符加入path中，更新used，和普通的回溯还有点区别
        path.append(chs[row][col]);
        used[row][col] = true;

        // 得到当前的字符对应的位置
        int position = chs[row][col] - 'a';
        DictTree dictTreeNode = DictTreeNodes[position];
        if (dictTreeNode == null) {
            // 这表示匹配到这里就匹配失败了，将造成的影响去掉
            path.deleteCharAt(path.length() - 1);
            used[row][col] = false;
            return;
        } else {
            if (dictTreeNode.isEnd) {
                // 考虑到会有重复的答案出现，所以一个词匹配完之后就丢掉
                dictTreeNode.isEnd = false;
                ans.add(path.toString());
            }
        }

        // 继续进行下一步的探索
        dfs(dictTreeNode.dictTrees, chs, row + 1, col, used, path);
        dfs(dictTreeNode.dictTrees, chs, row, col + 1, used, path);
        dfs(dictTreeNode.dictTrees, chs, row - 1, col, used, path);
        dfs(dictTreeNode.dictTrees, chs, row, col - 1, used, path);

        // 回溯掉加入的字符
        path.deleteCharAt(path.length() - 1);
        used[row][col] = false;
    }

    // 字典树
    class DictTree {
        private DictTree[] dictTrees = new DictTree[26];
        // 表示一个单词是不是已经在这里结束了
        private boolean isEnd;

    }
}
