package src;

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // 递归的去创建，每次创建都从数组的中间取一个数出来作为父节点
        // 剩下两个数组作为左右子树
        // TreeNode root = new TreeNode();
        // if(!createBST(nums, 0, nums.length, root)) {
        // root = null;
        // }
        // return root;

        return createBST(nums, 0, nums.length);
    }

    // 数组是开区间也就是不包含end
    private boolean createBST(int[] nums, int start, int end, TreeNode root) {
        if (start == end) {
            return false;
        }

        int mid = (start + end) / 2;
        root.val = nums[mid];
        root.left = new TreeNode();
        root.right = new TreeNode();
        if (!createBST(nums, start, mid, root.left)) {
            root.left = null;
        }
        if (!createBST(nums, mid + 1, end, root.right)) {
            root.right = null;
        }
        return true;
    }

    // 对某一段数组构造一个BST，然后返回这个BST的根节点，比上一种方法好
    private TreeNode createBST(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }

        int mid = start + (end - start) / 2; // 防止溢出
        TreeNode root = new TreeNode(nums[mid]);
        root.left = createBST(nums, start, mid);
        root.right = createBST(nums, mid + 1, end);

        return root;
    }
}
