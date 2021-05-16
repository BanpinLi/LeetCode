public class Problem3 {
    private char[][] ans;

    public char[][] rotateTheBox(char[][] box) {
        ans = new char[box[0].length][box.length];
        for(int i = 0;i < box.length;i++) {
            rotateOneRow(box[i], box.length - i - 1);
        }
        return ans;
    }

    //设定一个方法，用于解决某一行的问题，因为每一行是不一样的·
    private void rotateOneRow(char[] chs, int sub) {
        //从左至右开始记录 # .的个数，当遇见了 * 的时候，就更新数组
        int space = 0;
        int start = 0; //这时开始记录的时候的下标，也是回溯的下标

        for(int i = 0;i < chs.length;i++) {
            if(chs[i] == '*') {

                for(int j = start;j < space + start;j++) {
                    ans[j][sub] = '.';
                }
                for(int j = space + start;j < i;j++) {
                    ans[j][sub] = '#';
                }
                ans[i][sub] = '*';
                space = 0;
                start = i + 1;
            } else {
                if(chs[i] == '.') {
                    space++;
                }
            }
        }

        for(int j = start;j < space + start;j++) {
            ans[j][sub] = '.';
        }
        for(int j = space + start;j < chs.length;j++) {
            ans[j][sub] = '#';
        }
    }
}
