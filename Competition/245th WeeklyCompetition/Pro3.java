import java.util.*;

public class Pro3 {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        //找出triple中的所有小于target的元素，然后对这个遍历，查看能不能进行查找到
        List<int[]> array = new ArrayList<>();
        for(int i = 0;i < triplets.length;i++) {
            if(triplets[i][0] <= target[0] && triplets[i][1] <= target[1] && triplets[i][2] <= target[2]) {
                array.add(triplets[i]);
            }
        }
        //开始对这个进行遍历，看是否包含了target中的所有元素
        boolean[] flag = new boolean[3];
        for(int[] nums : array) {
            for(int j = 0;j < 3;j++) {
                if(nums[j] == target[j]) {
                    flag[j] = true;
                }
            }
        }
        return flag[0] && flag[1] && flag[2];
    }
}
