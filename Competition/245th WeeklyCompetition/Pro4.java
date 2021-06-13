import java.util.Arrays;

public class Pro4 {
    //麻麻的，超时了
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        //使用递归的算法，当两个人两两进行比较的时候，考虑两种情况，所以考虑每一种情况，将这个数组进行递归
        int[] person = new int[n];
        for(int i = 0;i < n;i++) {
            person[i] = i  +1;
        }
        int[] ret = new int[2];
        ret[0] = minOrMaxRound(person, firstPlayer, secondPlayer, 0);
        ret[1] = minOrMaxRound(person, firstPlayer, secondPlayer, 1);
        return ret;
    }

    private int minOrMaxRound(int[] person, int first, int second, int minOrMax) {
        //递归的终点是两个运动员不得不进行比较
        int firstIndex = 0;
        int secondIndex = person.length - 1;
        for(int i = 0;i < person.length;i++) {
            if(person[i] == first) {
                firstIndex = i;
            } else if(person[i] == second) {
                secondIndex = i;
            }
        }
        if(firstIndex + secondIndex == person.length - 1) {
            return 1;
        }

        //递归的中心内容，也就是搭配不同的运动员，然后存入数组里面，最后对数组进行排序，进行递归
        int[] nextPerson = new int[(person.length + 1) / 2];
        int ans;
        if(minOrMax == 0) {
            ans = Integer.MAX_VALUE;
        } else {
            ans = -1;
        }
        
        int subLen = person.length / 2;
        for(int i = 0;i < (int)Math.pow(2, subLen);i++) {
            int[] temp = generateSub(subLen, i);
            //通过得到的下标数组，来分配不同的运动员
            int index = 0;
            for(int j = 0;j < temp.length;j++) {
                if(person[j] == first || person[person.length - j -  1] == first) {
                    nextPerson[index] = first;
                } else if(person[j] == second || person[person.length - j - 1] == second){
                    nextPerson[index] = second;
                } else {
                    if(temp[j] == 1) {
                        nextPerson[index] = person[j];
                    } else {
                        nextPerson[index] = person[person.length - 1 - j];
                    } 
                }
                index++;
            }
            if(person.length % 2 == 1) {
                nextPerson[index] = person[index];
            }
            Arrays.sort(nextPerson);
            if(minOrMax == 0) {
                ans = Math.min(1 + minOrMaxRound(nextPerson, first, second, 0), ans);
            } else {
                ans = Math.max(1 + minOrMaxRound(nextPerson, first, second, 1), ans);
            }
        }
        return ans;
    }

    //给一个数字，返回一个这个数字所代表的二进制数组
    private int[] generateSub(int len, int number) {
        int[] ret = new int[len];
        int index = len - 1;
        while(number > 0) {
            ret[index] = number % 2;
            index--;
            number = number / 2;
        }
        return ret;
    }
}
