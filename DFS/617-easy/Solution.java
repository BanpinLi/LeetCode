public class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //采用新建树的做法，舍弃原本的输入的树
        TreeNode root = new TreeNode();
        if(dfs(root, root1, root2)) {
            root = null;
        }
        return root;
    }

    private boolean dfs(TreeNode root, TreeNode node1, TreeNode node2) {
        //事实证明还是设立一个递归终点比较好
        //返回值代表这个root节点应该被置为null
        if(node1 == null && node2 == null) {
            return true;
        }

        //node必定有一个是非空的，否则也不会给root分配空间
        int val = 0;
        if(node1 != null) {
            val += node1.val;
        }
        if(node2 != null) {
            val += node2.val;
        }
        root.val = val;

        root.left = new TreeNode();
        root.right = new TreeNode();

        if(node1 == null) {
            if(dfs(root.left, null, node2.left)) {
                root.left = null;
            }
            if(dfs(root.right, null, node2.right)) {
                root.right = null;
            }
        } else if (node2 == null) {
            if(dfs(root.left, node1.left, null)) {
                root.left = null;
            }
            if(dfs(root.right, node1.right, null)) {
                root.right = null;
            }
        } else {
            if(dfs(root.left, node1.left, node2.left)) {
                root.left = null;
            }
            if(dfs(root.right, node1.right, node2.right)) {
                root.right = null;
            }
        }
        return false;
    }
}
