/**
 * "12:01"
"12:44"
"20:00"
"06:00"
"00:00"
"23:59"
"04:54"
"18:51"
"00:01"
"10:01"
"00:01"
"00:00"
 * 
 */
public class Pro2 {
    public int numberOfRounds(String startTime, String finishTime) {
        //进行掐头去尾操作，开始时间beginTime定位在15分钟整数位向上取整，结束时间endTime向下取整
        //判断这个人是否是熬夜打游戏，然后分成两种不同情况
        //计算两个时刻之间时间差totalTime，这个时间差必定是15整数倍，最后返回 totalTime / 15 结果即可

        int[] sTime = new int[2];
        sTime[0] = Integer.parseInt(startTime.substring(0, 2));
        sTime[1] = Integer.parseInt(startTime.substring(3));
        int[] fTime = new int[2];
        fTime[0] = Integer.parseInt(finishTime.substring(0, 2));
        fTime[1] = Integer.parseInt(finishTime.substring(3));

        int beginTime = (sTime[1] / 15) * 15 + (sTime[1] % 15 == 0 ? 0 : 1) * 15;
        int endTime = (fTime[1] / 15) * 15;
        int totalTime = 0;
        if(fTime[0] < sTime[0] || (fTime[0] == sTime[0] && fTime[1] < sTime[1])) {
            totalTime += (23 - sTime[0]) * 60 + 60 - beginTime;
            totalTime += fTime[0] * 60 + endTime;
        } else {
            totalTime += fTime[0] * 60 + endTime;
            totalTime -= (sTime[0] * 60 + beginTime);
        }
        return totalTime / 15;
    }
}
