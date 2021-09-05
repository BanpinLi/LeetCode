//class Solution extends SolBase
//本来是继承了类SolBase，SolBase中存在方法rand7，表示产生随机数1 - 7
class Solution extends SolBase {
    // public int rand10() {
    // int mod = rand7();
    // while(mod == 7) {
    // mod = rand7();
    // }
    // int ret = rand7();
    // while(ret >= 6) {
    // ret = rand7();
    // }
    // return mod <= 3 ? (ret * 2 - 1) : (ret * 2);
    // }

    public int rand10() {
        // 进制转换，也就是将产生的两个rand7并排，看成是7进制数，那么范围是01 - 67
        // 也就是十进制数的1 - 49，这49个数是等概率出现的，我们取前40个数，表示1 - 10
        int num = 50;
        while (num > 40) {
            num = (rand7() - 1) * 7 + rand7();
        }
        return num % 10 + 1;
    }
}