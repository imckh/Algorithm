/*
3. 无重复字符的最长子串

https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/29/array-and-strings/78/

给定一个字符串，找出不含有重复字符的最长子串的长度。
示例 1:

输入: "abcabcbb"
输出: 3 
解释: 无重复字符的最长子串是 "abc"，其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 无重复字符的最长子串是 "b"，其长度为 1。
示例 3:

输入: "pwkwew"
输出: 3
解释: 无重复字符的最长子串是 "wke"，其长度为 3。
     请注意，答案必须是一个子串，"pwke" 是一个子序列 而不是子串。

https://leetcode-cn.com/articles/longest-substring-without-repeating-characters/
*/
import java.util.*;

public class P3{
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("dvdfq"));
        System.out.println(new Solution().lengthOfLongestSubstringOptimizedSlidingWindow("dvdfq"));
    }
}
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        // 开辟一个 hash 数组来存储该字符上次出现的位置, 
        // 比如 hash[a] = 3 就是代表 a 字符前一次出现的索引在 3, 
        // 遍历该字符串, 获取到上次出现的最大索引(只能向前, 不能退后), 
        // 与当前的索引做差获取的就是本次所需长度, 从中迭代出最大值就是最终答案.

        int len = s.length();
        int result = 0, preP = 0;//当前遍历到的以c为结尾的最长子序列的起始位置
        int[] hash = new int[128]; // 存放char字符, 存储该字符上次出现的位置, 初始时都是0
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            System.out.println("hash[c] > preP: hash[" + c + "]=" + hash[c] + " > " + preP);
            if (hash[c] > preP) {
                // 该字符c在hash[]的位置所存放的上次出现的索引, 
                // 也就是当前遍历到的以c为结尾的最长子序列的起始位置
                preP = hash[c];
            }
            System.out.println(preP + "");
            int l = i - preP + 1; // 与当前的索引做差获取的就是本次所需长度, 从中迭代出最大值就是最终答案
            hash[c] = i + 1; // 将该字符c的位置放进hash
            if (l > result) result = l;
        }
        return result;
    }

    public int lengthOfLongestSubstringOptimizedSlidingWindow(String s) {
        // 想象一个窗口区从字符串划过
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        // i 窗口的起点
        // j 窗口的终点
        // 找最大的窗口
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
            /*===============也可以用数组的方式====================
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
            */
        }
        return ans;
    }

    public <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey().toString() + ", Value = " + entry.getValue().toString());
        }
    }
}