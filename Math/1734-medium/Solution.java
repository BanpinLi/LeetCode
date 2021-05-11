public class Solution {
    public int[] decode(int[] encoded) {
        //perm数组有n个元素，后面n-1个元素的n_1Xor结果可以通过encoded数组的偶数项来计算得到
        //perm数组所有n个元素也可以得到 nXor，第一个元素 perm[0] = n_1Xor ^ nXor
        int[] perm = new int[encoded.length + 1];
        for(int i = 1;i < encoded.length;i = i + 2) {
            perm[0] ^= encoded[i] ^ i ^ (i + 1);
        }
        perm[0] ^= perm.length;
        
        for(int i = 0;i < encoded.length;i++) {
            perm[i + 1] = perm[i] ^ encoded[i];
        }

        return perm;
    }
}
