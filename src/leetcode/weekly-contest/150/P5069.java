/**

5069. 按字典序排在最后的子串 

给你一个字符串 s，找出它的所有子串并按字典序排列，返回排在最后的那个子串。

 

示例 1：

输入："abab"
输出："bab"
解释：我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是 "bab"。
示例 2：

输入："leetcode"
输出："tcode"
 

提示：

1 <= s.length <= 10^5
s 仅含有小写英文字符。

 */

import java.util.*;

public class P5069 {
    public static void main(String[] args) {
        System.out.println(new Solution().lastSubstring("abab"));
    }

    static class Solution {
        public String lastSubstring(String s) {
            char[] chars = s.toCharArray();

            // 找到最后的字符
            char last = 'a';
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] > last) {
                    last =  chars[i];
                }
            }

            TreeSet<String> set = new TreeSet<>();
            // 找到以最后的字符开头的所有子串
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == last) {
                    for (int j = i + 1; j <= chars.length; j++) {
                        set.add(String.copyValueOf(chars, i, j - i));
                    }
                }
            }

            return set.last();
        }
    }
}