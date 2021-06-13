public class Pro1 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        //数据量比较小，所以可以采用逐个判断的方法来进行排除
        for(int i = left; i <= right; i++) {
            boolean flag = false;
            for(int j = 0;j < ranges.length;j++) {
                if(ranges[j][0] <= i && ranges[j][1] >= i) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                return false;
            }
        }
        return true;
    }
}
