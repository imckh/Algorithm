/*
14. 最长公共前缀

https://leetcode-cn.com/problems/longest-common-prefix/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/40/

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
说明:

所有输入只包含小写字母 a-z 。
*/
public class P14 {
    public static void main(String[] args) {
        String[] s = {"flower","flow","flight"};
        String[] t = {"dog","racecar","car"};
        Solution so = new Solution();
        System.out.println(so.longestCommonPrefix(s));
        System.out.println(so.longestCommonPrefix(t));

    }
    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (null == strs || 0 == strs.length) {
                return "";
            }
            
            int len = strs.length;
            int minlen = 0x7fffffff; // int.max
            
            // 找出最短的那个字符串的长度
            for(String str: strs) {
                minlen = Math.min(str.length(), minlen);
            }
            
            /*
            0...minLen 的范围比较所有字符串，如果比较到有不同的字符，那么直接返回当前索引长度的字符串即可，否则最后返回最短的字符串即可
            */
            for(int i = 0; i < minlen; ++i) {
                for(int j = 0; j < len; ++j) {
                    if(strs[0].charAt(i) != strs[j].charAt(i))
                        return strs[0].substring(0, i);
                }
            }
            
            return strs[0].substring(0, minlen);
        }
    }
}