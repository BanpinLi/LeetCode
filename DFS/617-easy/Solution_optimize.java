public class Solution_optimize {
    public TreeNode mergeTrees(TreeNode root, TreeNode node) {
        //前面采用的是新建树的方式，由于会建立很多节点浪费时间，所以这次采用重叠树的方法
        //也就是以一个树为根基，在某一个节点处如果两个节点都存在就更新值，一旦有一个不存在就直接返回
        //并在返回的时候和根基树做判断，执行相应的操作
                //当某一个节点为null，那么就返回另一个节点，表示在这一条之路上只有这么一条路径，所以不用选了
        if(root == null) {
            return node;
        }
        if(node == null) {
            return root;
        }

        root.val += node.val;
        //root一定不为null
        root.left = mergeTrees(root.left, node.left);
        root.right = mergeTrees(root.right, node.right);
        return root;
    }
}
