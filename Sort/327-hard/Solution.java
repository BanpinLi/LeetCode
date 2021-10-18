package src;

public class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        // 归并排序
        // 考虑两个已经排序好的数组n1 n2，分别为0 1，3 4
        // 对n2拥有两个指针l和r，同时在最左边移动
        // 开始时考察n1的最左端元素为num1，l停止移动的条件是num2 >= num1 + lower
        // r停止移动的条件是num2 > num1 + upper，这里没有等号
        // 这样一来，再[l, r)之内的元素都满足 num2 - num1 属于[lower, upper]
        // 以这个为思路，对本题考查一个前缀和数组，并在最前端添加一个0，也就是找到这个数组中
        // 后面的减去前面的满足[lower, upper]的下标对的数目

        // 由于数值的范围问题，需要使用long防止int溢出
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return mergeCountPrefixSum(prefix, 0, prefix.length - 1, lower, upper);
    }

    // 头尾都包含
    private int mergeCountPrefixSum(long[] nums, int start, int end, int lower, int upper) {
        // 考虑递归的终点
        if (start >= end) {
            return 0;
        }

        // 对两个排序数组进行求满足条件的下标对
        int mid = (start + end) >> 1;
        int ret = 0;
        ret += mergeCountPrefixSum(nums, start, mid, lower, upper);
        ret += mergeCountPrefixSum(nums, mid + 1, end, lower, upper);
        // 到此，left-mid是升序，mid-1-right也是升序
        int left, right;
        right = left = mid + 1;
        for (int i = start; i <= mid; i++) {
            while (left <= end && nums[left] < nums[i] + lower) {
                left++;
            }
            while (right <= end && nums[right] <= nums[i] + upper) {
                right++;
            }

            ret += (right - left >= 0 ? right - left : 0);
        }

        // 再进行merge
        long[] temp = new long[end - start + 1];
        int off = 0;
        left = start;
        right = mid + 1;
        while (left <= mid || right <= end) {
            long leftNum = left <= mid ? nums[left] : Long.MAX_VALUE;
            long rightNum = right <= end ? nums[right] : Long.MAX_VALUE;
            if (leftNum < rightNum) {
                temp[off++] = nums[left++];
            } else {
                temp[off++] = nums[right++];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            nums[i + start] = temp[i];
        }
        return ret;
    }
}
