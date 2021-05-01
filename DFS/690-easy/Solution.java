import java.util.*;

public class Solution {
    public class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    private Map<Integer, Employee> map;
    private int ans;

    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        //遍历employees,来存储所有员工的信息
        for(Employee e : employees) {
            int i = e.id;
            map.put(i, e);
        }
        dfs(id);
        return ans;
    }

    //给一个id，找到对应的员工，然后加上他的重要性和下属的重要性
    private void dfs(int id) {
        Employee e = map.get(id);
        ans += e.importance;
        if(e.subordinates == null) return;

        for(int i : e.subordinates) {
            dfs(i);
        }
    }
}
