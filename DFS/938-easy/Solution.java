class Solution {
    private int ans;

    public int rangeSumBST(TreeNode root, int low, int high) {
        //DFS,到达某一个节点进行判断其大小，在范围内就加入ans，小于等于范围就递归右子树，大于等于就递归左子树
        //所以说这个题所说的无重复元素是很重要的
        dfs(root, low, high);
        return ans;
    }
    
    private void dfs(TreeNode root, int low, int high) {
        if(root == null) {
            return;
        }    

        if(root.val >= low && root.val <= high) {
            ans += root.val;
        }
        if(root.val > low && root.val < high) {
            rangeSumBST(root.left, low, high);
            rangeSumBST(root.right, low, high);
        } else if(root.val <= low) {
            rangeSumBST(root.right, low, high);
        } else {
            rangeSumBST(root.left, low, high);
        }
    }
}
