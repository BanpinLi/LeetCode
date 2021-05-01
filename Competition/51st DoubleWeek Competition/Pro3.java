import java.util.Arrays;

public class Pro3 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        //先对数组进行排序，从前往后遍历，不满足条件的就置为前一个元素 + 1
        Arrays.sort(arr);
        arr[0] = 1;
        for(int i = 1;i < arr.length;i++) {
            if(arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[arr.length - 1];
    }
}
