package src;

public class Pro3 {
    // 莫名其妙就错了，还没找出BUG
    public int countHighestScoreNodes(int[] parents) {
        // 建树
        int[][] tree = createTree(parents);

        int[][] height = new int[parents.length][2];
        treeHeight(tree, 0, height);

        int count = 1;
        int max = -1;
        for (int i = 0; i < tree.length; i++) {
            int left = height[i][0];
            int right = height[i][1];
            int up = (tree.length - left - right - 1) == 0 ? 1 : (tree.length - left - right - 1);
            int temp = up * (left == 0 ? 1 : left) * (right == 0 ? 1 : right);
            if (max < temp) {
                max = temp;
                count = 1;
            } else if (max == temp) {
                count++;
            }
        }

        return count;
    }

    private int[][] createTree(int[] paren) {
        int[][] ret = new int[paren.length][2];
        for (int i = 0; i < paren.length; i++) {
            ret[i][0] = ret[i][1] = -1;
        }

        for (int i = 1; i < paren.length; i++) {
            int pNode = paren[i];
            // if (pNode == -1) {
            // continue;
            // }
            int cNode = i;
            if (ret[pNode][0] == -1) {
                ret[pNode][0] = cNode;
            } else {
                ret[pNode][1] = cNode;
            }
        }

        return ret;
    }

    private int treeHeight(int[][] tree, int root, int[][] height) {
        if (root == -1) {
            return 0;
        }

        int left = tree[root][0];
        int right = tree[root][1];
        height[root][0] = treeHeight(tree, left, height);
        height[root][1] = treeHeight(tree, right, height);

        return height[root][0] + height[root][1] + 1;
    }
}
