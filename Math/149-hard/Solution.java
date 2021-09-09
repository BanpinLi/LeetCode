class Solution {
    public int maxPoints(int[][] points) {
        // 判断特殊情况
        if (points.length <= 2) {
            return points.length;
        }
        // 两个点为一组，遍历剩下的点，来判断是否在同一条直线上，并记录点的个数
        int maxNumber = 2;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                int number = 2;
                for (int k = 0; k < points.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }

                    int x = points[k][0];
                    int y = points[k][1];
                    if ((y2 - y1) * (x - x1) + (x2 - x1) * (y1 - y) == 0) {
                        number++;
                    }
                }
                maxNumber = Math.max(maxNumber, number);
            }
        }
        return maxNumber;
    }
}