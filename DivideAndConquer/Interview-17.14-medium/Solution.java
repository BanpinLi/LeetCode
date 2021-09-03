import java.util.*;

public class Solution {
    public int[] smallestK(int[] arr, int k) {
        // 考虑特殊情况1
        if (k == 0) {
            return new int[0];
        }

        // 运用快速排序的思想，运用快速选择算法
        // 快速排序的思想就是，选定一个下标index之后，对应的值是m，左边就是 <= m 的数，右边则是大于
        // 在这里由于只需要选定前k个数，所以进行了第一次排序之后，分为三种情况：
        // index == k - 1，保存数字，结束递归
        // index < k - 1，将前index + 1个保存在数组中，对后面的数组寻找k - index - 1个数字
        // index > k - 1，对长度为index + 1的数组来寻找前k个数字
        // 整个过程是一个递归的过程，递归函数是QuickSelect(arr, left, right, k, ans, start)
        // 函数表示的是，在left到right范围内，寻找前面k个数字，并将数字存在ans数组以start开头的位置
        int[] ans = new int[k];
        QuickSelect(arr, 0, arr.length - 1, k, ans, 0);
        return ans;
    }

    private void QuickSelect(int[] arr, int left, int right, int k, int[] ans, int start) {
        // 长度为1，直接放到ans里面然后返回，这个时候已经到达了递归终点
        if (left == right) {
            ans[start] = arr[left];
            return;
        }

        // 首先产生一个规定范围内的随机下标
        int index = new Random().nextInt(right - left + 1) + left;
        // 获得这个下标对应的值，将其移动到right位置
        int target = arr[index];
        swap(arr, index, right);
        index = right;

        // 当长度大于2的时候，以index为指标，将其余位置的数字进行位置变更
        // 变更的算法是，将index放到最后，使用两个指针来进行移动，front和back，front指向比index大的位置，同时back指向小于等于index的位置，就将front和back位置进行交换
        int front = left;
        int back = right - 1;
        while (true) {
            // 两个循环做完之后，两个指针必定停在满足条件的位置，或者相遇了
            while (arr[front] <= target && front < back)
                front++;
            while (arr[back] > target && front < back)
                back--;
            if (front == back)
                break;

            swap(arr, front, back);
        }

        // 排除一种特殊的极端情况，选到的数据刚好是最大值，导致，前面自动排好序了
        if (arr[front] > target) {
            index = front;
            swap(arr, right, front);
        }

        // 排完序之后，就应该进行三种情况的判断来进行递归操作
        if (index - left + 1 <= k) {
            for (int i = left; i <= index; i++) {
                ans[start++] = arr[i];
            }
            // 到达递归终点
            if (index - left + 1 == k)
                return;

            // 未到达终点持续递归
            QuickSelect(arr, index + 1, right, k - index + left - 1, ans, start);
        } else {
            // 这里应该缩短一下查找范围
            QuickSelect(arr, left, index - 1, k, ans, start);
        }

    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
