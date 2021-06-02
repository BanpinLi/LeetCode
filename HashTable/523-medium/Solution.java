import java.util.*;

public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        //同余定理：如果(a - b) / m是整数，那么称为 a 和 b 同余，此时 a 和 b 对 m 的余数相同
        //利用同余定理和前缀和，每次将前缀和的余数存进Map里面，然后遇到一个数就进行查找
        Map<Integer, Boolean> map = new HashMap<>();
        int[] prefixMod = new int[nums.length];
        prefixMod[0] = nums[0] % k;
        for(int i = 1;i < nums.length;i++) {
            prefixMod[i] = (prefixMod[i - 1] + nums[i]) % k;
        }
        //初始化map，存入一个0和第一个元素，然后从第二个元素开始进行检查
        map.put(0, true);
        for(int i = 1;i < nums.length;i++) {
            if(map.containsKey(prefixMod[i])) {
                return true;
            } else {
                //由于对数量没有限制，所以可以直接存元素，而不用getordefault方法
                map.put(prefixMod[i - 1], true);
            }
        }
        return false;
    }
}
