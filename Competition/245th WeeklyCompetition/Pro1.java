public class Pro1 {
    public boolean makeEqual(String[] words) {
        //统计每一个字符串的字符的出现次数，然后统计每一个出现的次数是否是长度的整数倍，不是的话直接返回false
        int[] table = new int[26];
        for(String s : words) {
            for(int i = 0;i < s.length();i++) {
                table[s.charAt(i) - 'a']++;
            }
        }
        int len = words.length;
        for(int i = 0;i < 26;i++) {
            if(table[i] % len != 0) {
                return false;
            }
        }
        return true;
    }
}
