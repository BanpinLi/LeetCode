public class Solution {
    public int[] decode(int[] encoded, int first) {
        //a ^ b = c 则 a = b ^ c
        int[] ans = new int[encoded.length + 1];
        ans[0] = first;
        for(int i = 0;i < encoded.length;i++) {
            ans[i + 1] = ans[i] ^ encoded[i];
        }
        return ans;
    }
}
