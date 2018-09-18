// https://leetcode-cn.com/problems/count-and-say/description/

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