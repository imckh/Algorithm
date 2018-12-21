/*
242. 有效的字母异位词
https://leetcode-cn.com/problems/valid-anagram/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/35/
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

类似的题目: 49 字母异位词分组
*/
import java.util.*;
public class P242 {
    public static void main(String[] args) {
        System.out.println(new Solution().isAnagram("ab", "a"));
    }

    
    static class Solution {
        public boolean isAnagram(String s, String t) {
            if (s == null && t == null) {
                return true;
            }
            if (s == null || t == null) {
                return false;
            }
            // 排序对比
            char[] sc = s.toCharArray();
            Arrays.sort(sc);
            char[] tc = t.toCharArray();
            Arrays.sort(tc);
            if (sc.length != tc.length) return false;
            for (int i = 0; i < sc.length; i++) {
                if (sc[i] != tc[i]) {
                    return false;
                }
            }

            return true;
        }

        public boolean isAnagram2(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            // 使用一个int的数组记录字母出现次数
            int[] tem = new int[26];
            char[] sc = s.toCharArray();
            for (int i = 0; i < sc.length; i++) {
                tem[sc[i] - 'a']++;
            }
            char[] tc = t.toCharArray();
            for (int i = 0; i < tc.length; i++) {
                tem[tc[i] - 'a']--;
            }
            int index = 0;
            for (int i = 0; i < tem.length; i++) {
                index = i;
                if (tem[i] != 0) {
                    break;
                }
            }
            if (index == 25) {
                return true;
            } else {
                return false;
            }
        }
    }
}