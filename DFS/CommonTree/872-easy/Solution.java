import java.util.*;

public class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        //一个最简单的遍历树，将所有是叶子结点的值存储进去，然后比较两个集合的元素
        ArrayList<Integer> root1List = new ArrayList<>();
        ArrayList<Integer> root2List = new ArrayList<>();
        search(root1, root1List);
        search(root2, root2List);
        if(root1List.size() != root2List.size()) {s
            return false;
        }

        for(int i = 0;i < root1List.size();i++) {
            if(root1List.get(i) != root2List.get(i)) {
                return false;
            }
        }

        return true;
    }

    private void search(TreeNode root, ArrayList<Integer> list) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }

        search(root.left, list);
        search(root.right, list);
    }
}
