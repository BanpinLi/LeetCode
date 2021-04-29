public class Solution {
    private int ans;

    //引用：将问题转换为，求某一个节点的左右子树的最深深度之和最大值
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return ans - 1;
    }

    //给定一个根节点，返回由该节点对应的最深深度
    private int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        //更新一次ans，由于递归会访问到每一个节点，所以每一个节点都计算一次左右子树之和
        //由于最大的路径的顶点一定是某一个节点，所以这种方法一定会计算出最大深度
        ans = Math.max(ans, 1 + left + right);

        return Math.max(left, right) + 1;
    }
}
