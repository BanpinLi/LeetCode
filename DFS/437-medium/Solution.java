public class Solution {
        public int pathSum(TreeNode root, int targetSum) {
        //自上而下搜索，在某一个节点给定一个搜索目标，进行递归的返回
        //定义一个ret来记录targe和节点值是否一致；
        //在该节点的返回值为左右子节点的共四种返回值，递归结束位置是null
        //对递归子函数进行限制，给定一个参数表示在这个节点之前的节点是否加入了path中
        //如果没有加入，则子函数无限制，否则该节点必须添加
        return dfs(root, targetSum, false);
    }

    private int dfs(TreeNode root, int target, boolean isAdd) {
        if(root == null) {
            return 0;
        }
        
        int ret = 0;
        if(root.val == target) {
            ret = 1;
        }

        int temp = dfs(root.left, target - root.val, true) + dfs(root.right, target - root.val, true);

        return ret + temp + (isAdd ? 0 : dfs(root.left, target, false) + dfs(root.right, target, false)); 
    }
}
