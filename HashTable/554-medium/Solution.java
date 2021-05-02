import java.util.*;

public class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        //使用一个list来存储每一行的前缀和，前缀和使用set来进行存储，方便进行查找
        //从1开始遍历至sum - 1，然后在set里面查找是否存在这样的前缀和，不存在的话就temp++，最后更新ans

        int ans = wall.size();

        //创建list，并存储前缀和
        List<Set<Integer>> list = new ArrayList<>();
        int sum = 0;
        for(int i = 0;i < wall.size();i++) {
            int prefix = 0;
            Set<Integer> set = new HashSet<>();
            for(int num : wall.get(i)) {
                prefix += num;
                set.add(prefix);
            }
            sum = prefix;
            list.add(set);
        }

        //从1开始遍历至sum - 1
        for(int i = 1;i < sum;i++) {
            int temp = 0;
            for(int j = 0;j < wall.size();j++) {
                if(!list.get(j).contains(i)) {
                    temp++;
                }
            }
            ans = Math.min(ans, temp);
        }

        return ans;
    }
}
