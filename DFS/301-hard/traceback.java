class Solution {
    private int leftDel;
    private int rightDel;
    private int leftAll;
    private int rightAll;
    private StringBuilder path;
    private Set<String> set;
    private List<String> ret;

    /*public static void main(String[] args) {
        String s = "()()))()";
        System.out.println(removeInvalidParentheses(s));
    }*/

    public List<String> removeInvalidParentheses(String s) {
        //先得到需要删除的左右括号数目
        //使用回溯算法来选择括号，选择的依据是左括号数目小于某一特定值
        //右括号选择依据是选择的左括号的数目大于右括号，等于不能选
        //遇到了字母直接添加进去，然后continue

        //这一部分来对需要的参数进行初始化
        int length = s.length();
        for(int i = 0; i < length; i++) {
            if(s.charAt(i) == '(') {
                leftAll++;
            } else if(s.charAt(i) == ')') {
                if(rightAll == leftAll) {
                    rightDel++;
                } else {
                    rightAll++;
                }
            }
        }
        if(rightAll < leftAll) {
            leftDel = leftAll - rightAll;
            leftAll = rightAll;
        }
        System.out.println(leftAll);
        System.out.println(leftDel);
        System.out.println(rightAll);
        System.out.println(rightDel);

        set = new HashSet<>();
        path = new StringBuilder();
        dfs(s,0,0,0,0,0);
        ret = new ArrayList<>();
        for(String ss : set) {
            ret.add(ss);
        }
        return ret;
    }
    
    private void dfs(String s,int depth,int ld,int rd,int la,int ra) {
        if(depth == s.length()) {
            if(rd != rightDel || ld != leftDel) return;

            set.add(new String(path.toString()));
            return;
        }

        //判断当前深度对应的字符是否是字母
        if(s.charAt(depth) != '(' && s.charAt(depth) != ')') {
            path.append(s.charAt(depth));
            dfs(s,depth + 1,ld,rd,la,ra);
            path.deleteCharAt(path.length() - 1);
        } else {
            //当前深度对应字符是括号，进入正题
            //对于当前字符一共有两个选择，加入path和不加入path

            //左括号的操作
            if(s.charAt(depth) == '(') {
                //必须添加
                if(ld > leftDel) return;

                path.append('(');
                dfs(s,depth + 1,ld,rd,la + 1,ra);
                path.deleteCharAt(path.length() - 1);
                if(ld != leftDel) {
                    dfs(s,depth + 1,ld + 1,rd,la,ra);
                }

            } else {
                //右括号的操作
                if(rd > rightDel) return;

                if(la != ra) {
                    path.append(')');
                    dfs(s,depth + 1,ld,rd,la,ra + 1);
                    path.deleteCharAt(path.length() - 1);
                }
                if(rd != rightDel){
                    dfs(s,depth + 1,ld,rd + 1,la,ra);
                }
            }
            /* 没有精简代码的版本
            if(s.charAt(depth) == '(') {
                //必须添加
                if(ld > leftDel) return;
                if(ld == leftDel) {
                    path.append('(');
                    dfs(s,depth + 1,ld,rd,la + 1,ra);
                    path.deleteCharAt(path.length() - 1);
                } else {
                    //可以添加也可以不用添加
                    path.append('(');
                    dfs(s,depth + 1,ld,rd,la + 1,ra);
                    path.deleteCharAt(path.length() - 1);

                    dfs(s,depth + 1,ld + 1,rd,la,ra);
                }
            } else {
                //右括号的操作
                if(rd > rightDel) return;
                if(rd == rightDel) {
                    if(la != ra) {
                        path.append(')');
                        dfs(s,depth + 1,ld,rd,la,ra + 1);
                        path.deleteCharAt(path.length() - 1);
                    }
                } else {
                    if(la != ra) {
                        path.append(')');
                        dfs(s,depth + 1,ld,rd,la,ra + 1);
                        path.deleteCharAt(path.length() - 1);
                    }

                    dfs(s,depth + 1,ld,rd + 1,la,ra);
                }
            }
            */
        }
    }
}