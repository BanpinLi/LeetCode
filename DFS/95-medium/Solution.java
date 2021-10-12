package src;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(n, 1);
    }

    // 生成一颗n个节点的树，这颗树最小节点从startVal开始
    private List<TreeNode> generateTrees(int n, int startVal) {
        List<TreeNode> retNodesList = new ArrayList<>();

        if (n == 0) {
            // 应该返回一个非空的list，但是里面的内容是null
            retNodesList.add(null);
            return retNodesList;
        }

        // 当节点数量大于0的时候，一个根节点，左右两边返回的节点之和为n - 1
        for (int i = 0; i <= n - 1; i++) {
            List<TreeNode> leftNodesList = generateTrees(i, startVal);
            List<TreeNode> rightNodesList = generateTrees(n - i - 1, startVal + i + 1);
            for (TreeNode left : leftNodesList) {
                for (TreeNode right : rightNodesList) {
                    TreeNode root = new TreeNode(startVal + i);
                    root.left = left;
                    root.right = right;
                    retNodesList.add(root);
                }
            }
        }

        return retNodesList;
    }
}
