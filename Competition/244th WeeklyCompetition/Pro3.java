public class Pro3 {
    public int minFlips(String s) {
        //字符串分为奇数长度和偶数长度，只有奇数长度中的连续个数为偶数，才能进行删除第一个
        if(s.length() % 2 == 0) {
            return Math.min(flipCount(s, '0'), flipCount(s, '1'));
        } else {
            int sub = 0;
            for(sub = 1;sub < s.length();sub++) {
                if(s.charAt(sub) == s.charAt(sub - 1)) {
                    break;
                }
            }
            int count = 1;
            for(int i = sub;i < s.length();i++) {
                if(s.charAt(i) == s.charAt(sub)) {
                    count++;
                } else {
                    break;
                }
            }
            int ret = s.length() + 1;
            if(count % 2 == 0) {
                ret = Math.min(flipCount(s.substring(1), '0'), flipCount(s.substring(1), '1'));
            }
            ret = Math.min(Math.min(flipCount(s, '0'), flipCount(s, '1')), ret);
            return ret;
        }
    }

    private int flipCount(String s, char ch) {
        //以第一个字符为ch来进行翻转，返回次数
        int ans = 0;
        for(int i = 0;i < s.length();i++) {
            if(i % 2 == 0) {
                if(s.charAt(i) != ch) {
                    ans++;
                }
            } else {
                if(s.charAt(i) == ch) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
