class Solution_Optimize {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 一个全新的知识点——差分，也就是前缀和的逆算法
        // * 差分数组的定义：有一个差分数组deff和一个原本数组nums，对应的关系是：
        // * deff[i] = nums[i] - nums[i - 1];
        // * deff[0] = nums[0]
        // * 如何由deff还原到nums呢？
        // * 对deff求前缀和
        // * 根据差分数组的定义式可以得到，当nums某一个区间[l, r]整体增加w的时候，deff在deff[l]增加w，
        // * 而在deff[r + 1]减少w，r为端点时就省略后半步

        // 创建一个原始的差分数组
        int[] diff = new int[n];

        // 先遍历一遍bookings，得到改变后的差分数组
        for (int i = 0; i < bookings.length; i++) {
            int left = bookings[i][0] - 1;
            int right = bookings[i][1] - 1;
            diff[left] += bookings[i][2];
            if (right != n - 1) {
                diff[right + 1] -= bookings[i][2];
            }
        }

        // 对差分数组求前缀和
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }
        return diff;
    }
}
