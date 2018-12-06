/*
44. 通配符匹配
https://leetcode-cn.com/problems/wildcard-matching/

给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
示例 3:

输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
示例 4:

输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
示例 5:

输入:
s = "acdcb"
p = "a*c?b"
输入: false
与 10. 正则表达式匹配 类似
https://leetcode-cn.com/problems/regular-expression-matching/

*/
import java.util.*;
public class P44 {
    public static void main(String[] args) {
        //String s = "adceb", p = "c*a*b";
        String s = "aaaaaaaaaaaaa", p = "*b";
        System.out.println(new Solution().isMatch(s, p));
    }
    static class Solution {
        // 贪心算法
        public boolean isMatch(String s, String p) {
            if (p.length() == 0) return s.length() == 0;
            int si = 0, // 当前匹配的s字符串的位置
                pi = 0, // 当前匹配的s字符串的位置
                match = 0, // 当前s所能达到的最后位置
                star = -1; // pi的上一次的*的位置
            int sl = s.length(), pl = p.length();
            char[] sc = s.toCharArray(), pc = p.toCharArray();
            while (si < sl) {
                // 其贪心体现在如果遇到 *，那就尽可能取匹配后方的内容，如果匹配失败，那就回到上一个遇到 * 的位置来继续贪心
                if (pi < pl && (pc[pi] == sc[si] || pc[pi] == '?')) {// 匹配了一个字符
                    si++;
                    pi++;
                } else if (pi < pl && pc[pi] == '*') {
                    star = pi++;
                    match = si;
                } else if (star != -1) {
                    si = ++match;
                    pi = star + 1;
                } else {
                    return false;
                }
            }
            while (pi < pl && pc[pi] == '*') pi++;
            return pi == pl;
        }
        public boolean isMatch_Dp(String s, String p) {
            if (p.length() == 0) return s.length() == 0;
            int sl = s.length(), pl = p.length();
            // dp[i][j] 的真假表示 s[0..i) 是否匹配 p[0..j)
            boolean[][] dp = new boolean[sl + 1][pl + 1];
            char[] sc = s.toCharArray(), pc = p.toCharArray();
            dp[0][0] = true;

            for (int i = 1; i <= pl; i++) {
                if (pc[i - 1] == '*') {
                    // *所能到达的地方
                    dp[0][i] = dp[0][i-1];
                }
            }
            /*
            其状态转移方程如下所示：

                如果 p[j - 1] != '*'，P[i][j] = P[i - 1][j - 1] && (s[i - 1] == p[j - 1] || p[j - 1] == '?');

                如果 p[j - 1] == '*'，P[i][j] = P[i][j - 1] || P[i - 1][j]
            */
            for (int i = 1; i <= sl; i++) {
                for (int j = 1; j <= pl; j++) {
                    if (pc[j - 1] != '*') {
                        dp[i][j] = dp[i - 1][j - 1] // s[i-1] == p[j-1]
                                    && (sc[i - 1] == pc[j - 1] || pc[j - 1] == '?'); // 这一对字符相等或者p是'?'
                    } else {
                        // s[i-1] == p[j] 或者 s[i] == p[j-1]
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                    }
                }
            }
            return dp[sl][pl];
        }
    }
}