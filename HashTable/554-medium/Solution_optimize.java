import java.util.*;

public class Solution_optimize {
    public int leastBricks(List<List<Integer>> wall) {
        //使用一个list来存储每一行的前缀和，前缀和使用set来进行存储，方便进行查找
        //从1开始遍历至sum - 1，然后在set里面查找是否存在这样的前缀和，不存在的话就temp++，最后更新ans
        //这种方法会从1一直遍历至sum，时间花费可能会在一些毫无意义的组合上面
        //所以使用一个set来记录所有的砖缝，然后遍历这个set

        int ans = wall.size();

        //创建list，并存储前缀和
        List<Set<Integer>> list = new ArrayList<>();
        Set<Integer> opsition = new HashSet<>();
        for(int i = 0;i < wall.size();i++) {
            int prefix = 0;
            Set<Integer> set = new HashSet<>();
            for(int num : wall.get(i)) {
                //把opsition的位置放在上面是为了不添加sum这个元素
                opsition.add(prefix);
                prefix += num;
                set.add(prefix);
                //将set和opsition的位置互换其实也有同样的效果
            }
            list.add(set);
        }

        //遍历opsition，其中的元素即为可能的答案
        for(int num : opsition) {
            int temp = 0;
            for(int i = 0;i < wall.size();i++) {
                if(!list.get(i).contains(num)) {
                    temp++;
                }
            }
            ans = Math.min(ans, temp);
        }

        return ans;
    }
}
