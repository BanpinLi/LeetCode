import java.util.*;

public class TreeTools {
    //这是一个工具类，用来对树进行操作的



    //用来创建一颗完全二叉树，限制输入是一个字符串，使用逗号进行分割
    //但是局限性很明显，只要不是完全二叉树，就直接会创建失败，或者创建出一些非目标值
    public static TreeNode createTree(String s) {
        String[] sArray = s.split(",");

        TreeNode root = new TreeNode();
        //可以使用完全二叉树的性质，某一个节点的左节点是 sub × 2 + 1
        createTree(root, sArray, 0);
        return root;
    }

    //返回值表示这个传递进来的节点是否应该被置为null
    private static boolean createTree(TreeNode root, String[] s, int sub) {
        //传递进来的root必定是被分配了内存空间的
        //sub对应的就是这个root应该的值
        if(sub >= s.length || "null".equals(s[sub])) {
            return true;
        }

        root.val = Integer.parseInt(s[sub]);
        root.left = new TreeNode();
        root.right = new TreeNode();

        if(createTree(root.left, s, sub * 2 + 1)) {
            root.left = null;
        }
        if(createTree(root.right, s, sub * 2 + 2)) {
            root.right = null;
        }

        return false;
    }




    //用来做二叉树的层序遍历
    public static void printTreeInLevelOrder(TreeNode root) {
        //先获得二叉树的最大深度,不应该简单的进行左边的遍历，而是在全局内进行寻找
        int depth = findMaxDepth(root, 0, 0);

        int len = -1 + (int)Math.pow(2, depth);
        String[] sArray = new String[len];
        Arrays.fill(sArray,"null");
        printTreeInLevelOrder(root, sArray, 0);

        for(int i = len - 1; i >= 0; i--) {
            if(sArray[i].equals("null")) {
                sArray[i] = null;
            } else {
                break;
            }
        }
        for(String str : sArray) {
            if(str != null) {
                System.out.print(str + " ");
            } else {
                System.out.println();
                return;
            }
        }
    }

    private static void printTreeInLevelOrder(TreeNode root, String[] sArray, int sub) {
        if(sub >= sArray.length) {
            return;
        }

        if(root == null) {
            sArray[sub] = String.valueOf("null");
            return;
        } else {
            sArray[sub] = String.valueOf(root.val);
        }
        printTreeInLevelOrder(root.left, sArray, sub * 2 + 1);
        printTreeInLevelOrder(root.right, sArray, sub * 2 + 2);
    }

    //找到一棵树的最大深度
    private static int findMaxDepth(TreeNode root, int maxDepth, int depth) {
        if(root == null) {
            return Math.max(maxDepth, depth);
        }

        return Math.max(findMaxDepth(root.left, maxDepth, depth + 1), 
                findMaxDepth(root.right, maxDepth, depth + 1));
    }
}
