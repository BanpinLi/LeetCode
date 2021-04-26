class Solution {
    public static void main(String[] args) {
        String word = "aeiaaioaaaaeiiiiouuuooaauuaeiu";
        System.out.println(longestBeautifulSubstring(word));
    }

    public static int longestBeautifulSubstring(String word) {
        //动态规划
        //先使用普通的常规解法:时间复杂度过高，不考虑
        //考虑使用栈这种数据结构，当栈为空或者大于等于栈里的元素的时候就入栈
        //否则就出栈（采用全部出栈的方式），出栈时检验第一个元素是否是''u',是的话，就更新ans
        //考虑到栈的特殊性，这里采用数组来进行代替，并用len来代表数组的长度
        int length = word.length();
        int[] stack = new int[length];
        int len = 0;
        
        int ans = 0;
        //元素开始入栈、出栈操作
        for(int i = 0;i < length;i++) {
            //先执行出栈操作
            if(len != 0 && !canIntoStack(word.charAt(i), stack[len - 1])) {
                if(stack[len - 1] == 'u') {
                    ans = Math.max(ans, len);
                }
                len = 0;
            }

            //再执行入栈操作，分为栈空和栈不空的情况
            if(len == 0 && word.charAt(i) == 'a') {
                stack[len++] = word.charAt(i);                
            } else if(len != 0 && canIntoStack(word.charAt(i), stack[len - 1])) {
                stack[len++] = word.charAt(i); 
            }
        }

        //最后对栈中元素进行最后一次判断
        if(len != 0 && stack[len - 1] == 'u') {
            ans = Math.max(ans, len);
        }
        return ans;
    }

    //判断此时是否应该执行入栈操作
    private static boolean canIntoStack(char t, int ch) {
        if(ch == t) return true;

        if((ch == 'a' && t == 'e') || (ch == 'e' && t == 'i') || (ch == 'i' && t == 'o')
        || (ch == 'o' && t == 'u')) return true;
        
        return false;
    }
}