package src;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    private int[] index; // 记录的是nums数组中对应值的下标
    private int[] ans; // 答案数组

    public List<Integer> countSmaller(int[] nums) {
        // 使用归并排序，在归并排序的过程中，实时去更新某一个位置所对应的比它小的值
        index = new int[nums.length];
        ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }

        mergeSort(nums, 0, nums.length);

        List<Integer> ret = new LinkedList<>();
        for (int num : ans) {
            ret.add(num);
        }
        return ret;
    }

    // 这里的right是开区间的，也就是不包含在排序范围里面的
    private void mergeSort(int[] nums, int left, int right) {
        if (left == right || left + 1 == right) {
            return;
        }

        int mid = (left + right) >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid, right);
        merge(nums, left, mid, right);
    }

    // 从start到mid-1都是排好序的，从mid到end也都是排好序的
    private void merge(int[] nums, int start, int mid, int end) {
        // 在进行归并的时候需要实时更新ans

        int left = start;
        int right = mid;
        int[] temp = new int[end - start]; // 暂时存储归并过程的值
        int[] tempIndex = new int[end - start]; // 暂时存储归并过程中index的变化
        int off = 0;
        while (left < mid || right < end) {
            // 传统的归并排序所采用的单点移动指针方法，并不适用这里，应该while循环移动指针
            // if (lVal > rVal) {
            // temp[off] = rVal;
            // tempIndex[off++] = index[right++];
            // ans[index[left]]++;
            // } else {
            // temp[off] = lVal;
            // tempIndex[off++] = index[left++];
            // }

            // 现将right移动到大于left的位置
            while (right < end && (left == mid || nums[right] < nums[left])) {
                temp[off] = nums[right];
                tempIndex[off++] = index[right++];
            }
            // 再将left移动到大于right所指向的位置
            while (left < mid && (right == end || nums[left] <= nums[right])) {
                temp[off] = nums[left];
                tempIndex[off++] = index[left];
                ans[index[left++]] += right - mid;
            }
        }

        // 将改变后的值重新赋给真实数组
        for (int i = 0; i < temp.length; i++) {
            nums[i + start] = temp[i];
            index[i + start] = tempIndex[i];
        }
    }
}
