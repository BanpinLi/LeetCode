class Solution {
    public int candy(int[] ratings) {
        // 左右分别扫描一次，左扫描途中，我们把相等处理为更小；右扫描相等时单独处理
        // 左 -> 右的时候：ratings[i + 1] > ratings[i] ---> candys[i + 1] = candys[i] + 1
        // 右 -> 左的时候：ratings[i] > ratings[i + 1] candys[i] <= candys[i + 1]
        // ---> candys[i] = candys[i + 1] + 1
        int[] candys = new int[ratings.length];
        // left -> right
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candys[i] = candys[i - 1] + 1;
            }
        }
        // right -> left
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candys[i] <= candys[i + 1]) {
                candys[i] = candys[i + 1] + 1;
            }
        }

        int sumCandys = ratings.length;
        for (int num : candys) {
            sumCandys += num;
        }
        return sumCandys;
    }
}