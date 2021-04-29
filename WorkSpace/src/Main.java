public class Main {
    public static void main(String[] args) {
        String s = "1,2,3,4,5";
        TreeNode root = TreeTools.createTree(s);
        Solution solution = new Solution();
        int ans = solution.diameterOfBinaryTree(root);
        System.out.println(ans);
    }
}
