/*
38. 报数

https://leetcode-cn.com/problems/count-and-say/description/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/39/

报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 被读作  "one 1"  ("一个一") , 即 11。
11 被读作 "two 1s" ("两个一"）, 即 21。
21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。

给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。

注意：整数顺序将表示为一个字符串
*/
public class P38 {
    public static void main(String[] args) {
        Solution s = new Solution();
        for (int i = 0; i < 10; i++) {
            
            String str = s.countAndSay(i);
            System.out.println(str);
        }
        //String cn = s.readStr("11223334555");
        //System.out.println(cn);
    }

    static class Solution {
        public String countAndSay(int n) {
            String s = "1";
            for (int i = 0; i < n; i++) {
                s = readStr(s);
            }
            return s;
        }

        public String readStr(String s) {
            StringBuilder sb = new StringBuilder();

            int cnt = 1;
            char c = s.charAt(0);
            int i;
            for (i = 1; i < s.length(); i++) {
                if (s.charAt(i) == c) {
                    cnt++;
                } else {
                   sb.append(cnt);
                   sb.append(c);
                   cnt = 1;
                   c = s.charAt(i);
                }
            }
            sb.append(cnt);
            sb.append(c);

            return sb.toString();
        }
    }
}