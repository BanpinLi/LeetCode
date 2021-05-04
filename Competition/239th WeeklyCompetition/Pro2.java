public class Pro2 {
    public boolean splitString(String s) {
        for(int i = 0;i < s.length();i++) {
            //int target = Integer.parseInt(s.substring(0, i + 1));
            long target = toNumber(s.substring(0, i + 1));
            if(dfs(s, i + 1, target, 0)) return true;
        }
        return false;
    }

    private boolean dfs(String s, int index, long target, int count) {
        if(index == s.length() && count > 0) return true;

        for(int i = index;i < s.length();i++) {
            if(target - 1 == toNumber(s.substring(index, i + 1))) {
                if(dfs(s, i + 1, target - 1, count + 1)) return true;
            }
        }
        return false;
    }

    private long toNumber(String s) {
        long ans = 0;
        for(int i = 0;i < s.length();i++) {
            ans = ans * 10 + s.charAt(i) - '0';
        }
        return ans;
    }
}
