class Solution {
    public int compareVersion(String version1, String version2) {
        // // 使用api来进行求解
        // // 将版本号使用split拆分成数组，创建一个转换后的数组，数组长度取两者最大值
        // String[] vers1 = version1.split("\\.");
        // String[] vers2 = version2.split("\\.");
        // int len = Math.max(vers1.length, vers2.length);
        // int[] verNum1 = new int[len];
        // int[] verNum2 = new int[len];
        // for (int i = 0; i < vers1.length; i++) {
        // verNum1[i] = Integer.parseInt(vers1[i]);
        // }
        // for (int i = 0; i < vers2.length; i++) {
        // verNum2[i] = Integer.parseInt(vers2[i]);
        // }
        // for (int i = 0; i < len; i++) {
        // if (verNum1[i] > verNum2[i]) {
        // return 1;
        // } else if (verNum1[i] < verNum2[i]) {
        // return -1;
        // }
        // }
        // return 0;

        // 将上面的方法进行简单的改进，以节省空间
        String[] vers1 = version1.split("\\.");
        String[] vers2 = version2.split("\\.");

        int maxLen = Math.max(vers1.length, vers2.length);
        for (int i = 0; i < maxLen; i++) {
            int a = (i < vers1.length ? Integer.parseInt(vers1[i]) : 0);
            int b = (i < vers2.length ? Integer.parseInt(vers2[i]) : 0);
            if (a > b)
                return 1;
            else if (a < b)
                return -1;
        }
        return 0;
    }
}
