import java.util.*;

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //首先对数组进行一个排序
        //固定某一个数，再固定另一个数，然后对剩下的数组进行查找，可以单独写一个查找函数，返回一个List
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0;i < nums.length - 3;i++) {
            for(int j = i + 1;j < nums.length - 2;j++) {
                List<int[]> tempList = twoSum(nums, j + 1, target - nums[i] - nums[j]);
                if(tempList.size() > 0) {
                    for(int[] tempArray : tempList) {
                        List<Integer> tempAns = new ArrayList<>();
                        tempAns.add(nums[i]);
                        tempAns.add(nums[j]);
                        tempAns.add(tempArray[0]);
                        tempAns.add(tempArray[1]);
                        ans.add(tempAns);
                    }                    
                }
                while(j < nums.length - 2 && nums[j + 1] == nums[j]) {
                    j++;
                }
            }
            while(i < nums.length - 3 && nums[i + 1] == nums[i]) {
                i++;
            }
        }
        return ans;
    }

    private List<int[]> twoSum(int[] nums, int start, int target) {
        //进行去重操作是不行的！！
        int left = start;
        int right = nums.length - 1;
        List<int[]> ret = new ArrayList<>();
        while(left < right) {
            if(nums[left] + nums[right] > target) {
                right--;
            } else if(nums[left] + nums[right] < target) {
                left++;
            } else {
                int[] temp = {nums[left], nums[right]};
                ret.add(temp);
                while(left < right && nums[left + 1] == nums[left]) {
                    left++;
                }
                while(left < right && nums[right - 1] == nums[right]) {
                    right--;
                }
                left++;
                right--;
            }
        }
        return ret;
    }
}
