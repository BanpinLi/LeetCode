public class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        //由于题目中说了是一个山脉数组，所以不存在山顶的左右元素中还存在相等的值，遵循严格单调
        //使用比较普通简单的二分查找
        int left  = 1;
        int right = arr.length - 2;
        int ans = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                ans = mid;
                break;
            } else if(arr[mid + 1] > arr[mid] && arr[mid] > arr[mid - 1]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }
}
