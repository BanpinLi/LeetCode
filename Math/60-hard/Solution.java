import java.util.*;

//  * 算法解释：
//  * 对于一个输入n 和 k，返回的结果是一个长度为n的字符串，对于这个字符串我们对每一位进行确定
//  * 将n！的数据看成是 n 组数据，每一组数据为(n - 1)!，然后判断k属于这n组数据中的哪一组
//  * 从而确定这一位的值
//  * n = 3, k = 3，ans = ""
//  * n = 3 -> n * (n - 1)! -> 3 * 2
//  * 判断出k = 3属于第2组，那么ans = "2"，将数据调整n = 2, k = 1
//  * n = 2 -> n * (n - 1)! -> 2 * 1
//  * 判断出k = 1属于第1组，那么ans = "21"，将数据调整为 n = 1, k = 1
//  * n = 1 -> n * (n - 1)! -> 1 * 1
//  * 判断出k = 1属于第1组，那么ans = "213"，结束程序返回ans

class Solution {
    public String getPermutation(int n, int k) {
        // 将数字1 - n放入一个可变长度的list中，采用递推的方式从左向右确定
        // 利用k / (n - 1)!来确定第n个位置所属的组数

        // 先建立一个阶乘数组
        int[] f = new int[n];
        f[0] = 1;
        for (int i = 1; i < n; i++) {
            f[i] = i * f[i - 1];
        }

        // 建立一个可变长度的数组
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            // 代表这一位是属于第几组
            int group = k / f[i - 1];
            k %= f[i - 1];

            // 恰好整除的时候，调整组数和k的值，便于下一次计算
            if (k == 0) {
                k = f[i - 1];
                group--;
            }

            sb.append(arr.remove(group));
        }

        return sb.toString();
    }
}