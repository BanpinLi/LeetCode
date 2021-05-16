public class Problem2 {
    public int minSwaps(String s) {
        int ans = Math.min(minSwaps('1', s), minSwaps('0', s));
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    //这个方法是获得以ch开头的字符串，所需要的交换次数
    private int minSwaps(char ch, String s) {
        int countOne = 0;
        int countTwo = 0;
        for(int i = 0;i < s.length();i++) {
            if(i % 2 == 0) {
                if(s.charAt(i) != ch) {
                    countTwo++;
                }
            } else {
                if(s.charAt(i) == ch) {
                    countOne++;
                }
            }
        }
        return countOne == countTwo ? countOne : Integer.MAX_VALUE;
    }
}
