class Solution {
    public int balancedStringSplit(String s) {
        // 遍历字符串，并使用两个变量来记录L和R的数量，当数量一致的时候就ans++
        int countL = 0;
        int countR = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                countL++;
            } else {
                countR++;
            }
            if (countL == countR) {
                ans++;
                countL = countR = 0;
            }
        }
        return ans;
    }
}