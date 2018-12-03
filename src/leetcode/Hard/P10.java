/*
10. 正则表达式匹配

https://leetcode-cn.com/problems/regular-expression-matching/

给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符。
'*' 匹配零个或多个前面的元素。
匹配应该覆盖整个字符串 (s) ，而不是部分字符串。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
示例 4:

输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
示例 5:

输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
*/

import java.util.*;
public class P10 {
    public static void main(String[] args) {
        //String s = "adceb", p = "c*a*b";
        String s = "aaaaaaaaaaaaa", p = "aa*";
        System.out.println(new Solution().isMatch(s, p));
        System.out.println(new Solution().isMatch2(s, p));
    }
    static class Solution {
        public boolean isMatch(String s, String p) {
            if (p.isEmpty()) return s.isEmpty(); // 如果 s 和 p 都为空, 那么返回 true
            System.out.printf("p.charAt(0) = %s, p.charAt(0) = %s , s.charAt(0) = %s\n", p.charAt(0), p.charAt(1), s.charAt(0));
            if (p.length() == 1) { // 如果 p 的长度为 1, 当 s 的长度也为 1, 并且他们首位匹配则返回 true, 否则返回 false
                return s.length() == 1 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
            }

            if (p.charAt(1) != '*') {
                if (s.isEmpty()) return false; // 如果 p 的第二个字符不为 '*', 如果 s 为空, 那就返回 false
                return (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')
                        && isMatch(s.substring(1), p.substring(1)); // 首位匹配则返回递归调用他们去掉首位的子字符串
            }

            // 如果 p 的第二个字符为 '*', 循环当 s 不为空, 且首位匹配, 
            // 如果递归调用是否匹配 s 字符串和 p 去掉前两位的子字符串, 则返回 true, 否则 s 去掉首字母继续循环
            while (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s, p.substring(2))) return true;
                s = s.substring(1);
            }

            //返回递归调用 s 字符串和 p 去掉前两位的子字符串是否匹配
            return isMatch(s, p.substring(2));
        }

        public boolean isMatch2(String s, String p) {
            boolean[] match = new boolean[s.length() + 1];
            match[s.length()] = true;
            for (int i = p.length() - 1; i >= 0; i--) {
                if (p.charAt(i) == '*') {
                    for (int j = s.length() - 1; j >= 0; j--) {
                        match[j] = match[j]
                                || (match[j + 1] 
                                    && (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j)));
                        System.out.println("* " + Arrays.toString(match));
                    }
                    i--;
                } else {
                    for (int j = 0; j < s.length(); j++) {
                        match[j] = match[j + 1] && (p.charAt(i) == '.' || p.charAt(i) == s.charAt(j));
                        System.out.println("  " + Arrays.toString(match));
                    }
                    match[s.length()] = false;
                    System.out.println(Arrays.toString(match));
                }
            }
            return match[0];
        }
    }
}