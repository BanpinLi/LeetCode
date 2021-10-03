package src;

public class Pro3 {
    public boolean stoneGameIX(int[] stones) {
        // 取余计数
        int[] count = new int[3];
        for (int num : stones) {
            count[num % 3]++;
        }

        // alice先手选1和2
        int[] copy = new int[3];
        for (int i = 0; i < 3; i++)
            copy[i] = count[i];
        return game(1, count) || game(2, copy);
    }

    private boolean game(int firstChoice, int[] count) {
        if (count[firstChoice] == 0) {
            return false;
        }

        char select = 'a';
        count[firstChoice]--;

        int zero = count[0];
        if (zero % 2 == 0) {
            select = 'b';
        } else {
            select = 'a';
        }

        // 消除所有的配对
        int one = count[1];
        int two = count[2];
        int r;
        if (one > two) {
            r = 1;
        } else if (one < two) {
            r = 2;
        } else {
            return false;
        }
        count[1] -= Math.min(one, two);
        count[2] -= Math.min(one, two);

        if (firstChoice != r) {
            return select == 'a' ? false : true;
        }

        // 判断余下的个数
        if (count[r] == 1) {
            return false;
        }

        return select == 'a' ? true : false;

    }
}
