import java.util.*;

public class Solution_prefixSum {
    //这是一个用来记录前缀和的map，键为前缀和，值为有几个节点拥有这样的前缀和
    private Map<Integer, Integer> map;
    private int targetSum;

    public int pathSum(TreeNode root, int targetSum) {
        //基本思路：使用一个map来记录前缀和以及对应的个数，然后遍历map存储节点对应的前缀和
        //然后使用map来对前缀和进行查找，值即为满足条件的路径的条数
        //注意：当前节点需要额外的进行判断，判断其拥有多少条路径，这也是递归算法的核心代码所在
        //重点：将当前节点的值存储进去之后，进行左右子树递归，递归完毕之后，需要对这一节点对应的值 -1
        //这样是为了避免未在同一条路径的节点相互影响

        //特殊处理，map.put(0,1),这表示的是，一个满足条件的节点就是一条路径
        map = new HashMap<>();
        map.put(0,1);

        this.targetSum = targetSum;
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int curSum) {
        if(root == null) {
            return 0;
        }

        int ret = 0;
        curSum += root.val;

        ret += map.getOrDefault(curSum - targetSum, 0);

        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        int leftRet =  dfs(root.left, curSum);
        int rightRet = dfs(root.right, curSum);

        ret = ret + leftRet + rightRet;
        map.put(curSum, map.get(curSum) - 1);

        return ret;
    }
}