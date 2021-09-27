package src;

import java.util.*;

public class Codec {
    public String serialize(TreeNode root) {
        // 广度优先遍历得到二叉树的序列化
        StringBuilder seriaString = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        // 存放根节点，并且判断根节点是否是一个空节点
        if (root == null) {
            return "null";
        }

        queue.offer(root);
        seriaString.append(root.val + ",");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null && node.right != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                    seriaString.append(node.left.val + ",").append(node.right.val + ",");
                } else if (node.left != null && node.right == null) {
                    queue.offer(node.left);
                    seriaString.append(node.left.val + ",null,");
                } else if (node.left == null && node.right != null) {
                    queue.offer(node.right);
                    seriaString.append("null," + node.right.val + ",");
                } else {
                    seriaString.append("null,null,");
                }
            }
        }
        return seriaString.deleteCharAt(seriaString.length() - 1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("null")) {
            return null;
        }

        // 按照广度优先遍历的顺序来还原
        String[] vals = data.split(",");
        // 先创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        // 创建队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (index < vals.length) {
            int size = queue.size();
            for (int i = index; i < size * 2 + index && i < vals.length; i += 2) {
                TreeNode temp = queue.poll();
                String lVal = vals[i];
                String rVal = vals[i + 1];
                TreeNode left;
                TreeNode right;
                if (!lVal.equals("null") && !rVal.equals("null")) {
                    left = new TreeNode(Integer.parseInt(lVal));
                    right = new TreeNode(Integer.parseInt(rVal));
                    queue.offer(left);
                    queue.offer(right);
                } else if (!lVal.equals("null") && rVal.equals("null")) {
                    left = new TreeNode(Integer.parseInt(lVal));
                    right = null;
                    queue.offer(left);
                } else if (lVal.equals("null") && !rVal.equals("null")) {
                    left = null;
                    right = new TreeNode(Integer.parseInt(rVal));
                    queue.offer(right);
                } else {
                    left = null;
                    right = null;
                }
                temp.left = left;
                temp.right = right;
            }
            index += size * 2;
        }

        return root;
    }
}