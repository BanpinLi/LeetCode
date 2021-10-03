package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pro4 {
    public int waysToPartition(int[] nums, int k) {
        // 获得前缀和，并将其存在map中，map对应的为一个list，可以做二分查找
        Map<Integer, Array> map = new HashMap<>();
        int prefix = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            prefix += nums[i];
            Array temp;
            if (map.containsKey(prefix)) {
                temp = map.get(prefix);
            } else {
                temp = new Array();
            }
            temp.add(i);
            map.put(prefix, temp);
        }
        int sum = prefix + nums[nums.length - 1];

        int ans = 0;
        if (sum % 2 == 0) {
            // 考虑没有改变元素的情况
            if (map.containsKey(sum / 2)) {
                ans = map.get(sum / 2).size();
            }
        }

        // 考虑改变元素
        for (int i = 0; i < nums.length; i++) {
            int tempSum = sum + k - nums[i];
            if (tempSum % 2 == 0) {
                int leftAns, rightAns;
                leftAns = rightAns = 0;

                if (map.containsKey(tempSum / 2)) {
                    ArrayList<Integer> tempArr1 = map.get(tempSum / 2);
                    leftAns = find(tempArr1, i);
                }
                if (map.containsKey(tempSum / 2 - k + nums[i])) {
                    ArrayList<Integer> tempArr2 = map.get(tempSum / 2 - k + nums[i]);
                    rightAns = tempArr2.size() - find(tempArr2, i);
                }

                ans = Math.max(ans, leftAns + rightAns);
            }
        }
        return ans;
    }

    // 找到在nums中小于n的个数
    private int find(ArrayList<Integer> nums, int n) {
        if (nums == null) {
            return 0;
        }

        if (nums.get(nums.size() - 1) < n) {
            nums.size();
        }

        // 二分查找
        int left = 0;
        int right = nums.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums.get(mid) > n) {
                right--;
            } else if (nums.get(mid) < n) {
                left++;
            } else {
                return mid;
            }
        }

        if (left == nums.size() || nums.get(left) > n) {
            return left;
        } else {
            return left + 1;
        }
    }

    private class Array extends ArrayList<Integer> {
    }
}
