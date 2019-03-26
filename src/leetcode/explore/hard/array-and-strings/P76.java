/*
76. 最小覆盖子串

https://leetcode-cn.com/problems/minimum-window-substring/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/133/

给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。

示例：

输入: S = "ADOBECODEBANCCCCCCCC", T = "ABC"
输出: "BANC"
说明：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。
*/

import java.util.*;
public class P76 {
    public static void main(String[] args) {
        String S = "ADOBECODEBANC", T = "ABC";
        System.out.println(new Solution_Sliding_Window().minWindow(S, T));
    }

    /*
    采用滑动窗口，窗口有左右边界，先通过扩展右边界找出一个包含T中所有字符的子串，
    然后收缩左边界，直到不能再收缩。
    记录此时的子串。然后收缩左边界，继续扩展右边界，直到再找到满足要求的子串，
    和上次的进行比较，保存更小的子串。
    返回执行，直到右边界到达S串尾，且左边界不能再收缩。
    */
    static class Solution_Sliding_Window {
        public String minWindow(String s, String t) {
            if (s.length() == 0 || t.length() == 0) {
                return "";
            }

            // 计算所有t中包含的不重复字符数
            Map<Character, Integer> dictT = new HashMap<>();
            for (char ti : t.toCharArray()) {
                dictT.put(ti, dictT.getOrDefault(ti, 0) + 1);
            }

            // t中唯一字符的个数, 这些将要出现在期望的窗口中
            int required = dictT.size();

            // 窗口的左右指针
            int l = 0, r = 0;

            // formed用来记录多少个t中的不重复字母的期望频率 出现在当前的窗口中
            // 比如'AABC' 必须有两个A, 一个B, 一个C
            // 那么在所有的字母都遇到的情况下, formed将等于3
            int formed = 0;

            // 记录当前窗口的不重复字母数
            Map<Character, Integer> windowCounts = new HashMap<>();

            // 结果 (窗口大小, 左右指针位置)
            int[] ans = {-1, 0, 0};

            while (r < s.length()) {
                // 向窗口右侧添加一个字符
                char c = s.charAt(r);
                windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

                // 如果当前字符加进去以后其出现次数与t中期望的该字符的出现次数一样
                // formed++
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                    formed++;
                }

                // 尝试把窗口缩小到不是期望的程度
                while (l <= r && formed == required) {
                    c = s.charAt(l);
                    // 保存最小窗口
                    if (ans[0] == -1 || r - l + 1 < ans[0]) {
                        ans[0] = r - l + 1;
                        ans[1] = l;
                        ans[2] = r;
                    }

                    // 窗口左指针指向的字符已经不属于窗口了 
                    windowCounts.put(c, windowCounts.get(c) - 1);
                    if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                        formed--;
                    }

                    // 左指针前移, 用来寻找新的窗口
                    l++;
                }

                // 收缩完之后扩展右边界
                r++;
            }

            return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
        }
    }

    // 使用数组替换map
    static class Solution {
        public String minWindow(String s, String t) {
            char[] tChars = t.toCharArray();
            int[] hash = new int[256];
            char[] sChars = s.toCharArray();
            for (char tChar : tChars) {
                hash[tChar]++;
            }
            int l = 0;
            int r = 0;
            int match = tChars.length;
            String res = "";
            int min = Integer.MAX_VALUE;
            while (r != sChars.length) {
                hash[sChars[r]]--;
                if (hash[sChars[r]] >= 0) {
                    match--;
                }
                while (match == 0) {
                    while (hash[sChars[l]] < 0) {
                        hash[sChars[l++]]++;
                    }
                    int len = r - l + 1;
                    if (min > len) {
                        res = new String(sChars, l, len);
                        min = len;
                    }
                    match++;
                    hash[sChars[l++]]++;
                }
                r++;
            }
            return res;
        }
    }
}