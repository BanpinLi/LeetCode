import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        //使用一个map来对words作词频统计
        //做一个滑动窗口，一共有n = words[0].length个起始位置，每一次滑动n个位置，然后更新相应的map
        //更新map之后，和词频map进行比较，如果完全相同，那么将结果加入到ans里面

        List<Integer> ans = new ArrayList<>();
        //对于特殊情况的处理，那就是s的长度本身就不符合要求
        if(s.length() < words.length * words[0].length()) {
            return ans;
        }
        
        //先做词频统计
        Map<String, Integer> wordMap = new HashMap<>();
        for(String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        int n = words[0].length();
        for(int i = 0;i < n;i++) {
            //每次改变起始位置的时候map都应该是处于清空的状态
            Map<String, Integer> map = new HashMap<>();

            //选定一个初始位置，从这个位置开始进行存入数据，n各一组一共放入words.length - 1个，然后使用滑动窗口移动
            for(int j = i;j < i + n * (words.length - 1);j += n) {
                String temp = s.substring(j, j + n);
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }

            //将初始数据放进去之后，进行map的对比，然后移动窗口直至超出范围
            int start = i;
            //滑动窗口稍微有点不一样，每一次进入while循环都是先添加一个，离开的时候，在删除一个，而不是同步进行
            while(start + n * words.length <= s.length()) {
                //添加新进入的滑动窗口的字符串
                String temp = s.substring(start + n * (words.length - 1), start + n * words.length);
                map.put(temp, map.getOrDefault(temp, 0) + 1);

                boolean flag = true;
                for(String word : words) {
                    //不懂为什么当测试的数据过大的时候，直接比较会出现错误，所以只能够提前准备比较的值
                    int a = map.getOrDefault(word, -1);
                    int b = wordMap.get(word);
                    if(a != b) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    ans.add(start);
                }

                //从map里面删掉离开滑动窗口的字符串
                temp = s.substring(start, start + n);
                map.put(temp, map.get(temp) - 1);
                start += n;
            }
        }

        return ans;
    }
}