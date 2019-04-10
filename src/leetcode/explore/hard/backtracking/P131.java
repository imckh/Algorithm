/*
131. 分割回文串

https://leetcode-cn.com/problems/palindrome-partitioning/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/58/backtracking/146/

给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。

示例:

输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]
*/
import java.util.*;
public class P131 {
    public static void main(String[] args) {
        String s = "aabaab";
        System.out.println(new Solution().partition(s));
    }

    /*
    这里使用动态规划来判断回文串, 而且可以存储子串的回文串的情况.
    isPalindrome[i][j]:表示i到j的子串是否是回文串.
    状态转移方程:
        isPalindrome[i][j] = isPalindrome[i+1][j-1] && s.charAt(i) == s.charAt(j)
    自然如果一个串是回文串, 那么首尾必须要相等, 并且中间也是子串.
    初始化, 显然当i==j的时候都是回文串
    当串只有两个字符且相等的时候也是回文串.
    知道如何判断子串是否是回文串, 然后只要模式化的深度搜索回溯即可
    */
    static class Solution {
        List<List<String>> results = new ArrayList<>();
        /**
         * 表示i到j的子串是否是回文串
         */
        boolean[][] isPalindrome;

        public List<List<String>> partition(String s) {
            if (s == null || s.length() == 0) {
                return results;
            }

            initIsPalindrome(s);

            helper(s, 0, new ArrayList<Integer>());

            return results;
        }

        private void initIsPalindrome(String s) {
            int l = s.length();
            isPalindrome = new boolean[l][l];
            for (int i = 0; i < l; i++) {
                // 每个字母自己都是回文的
                isPalindrome[i][i] = true;
            }
            for (int i = 0; i < l - 1; i++) {
                // 回文除了aba还有aa的情况
                // 这个是找出aa的情况
                isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            }
            //print(isPalindrome);
            for (int i = l - 3; i >= 0; i--) { // i = l - 3 : 为最右侧留出一个字符的位置
                for (int j = i + 2; j < l; j++) {
                    isPalindrome[i][j] = isPalindrome[i+1][j-1] && s.charAt(i) == s.charAt(j);
                }
            }
            //print(isPalindrome);
        }
        // 深度搜索回溯
        private void helper(String s, int startIndex, List<Integer> partition) {
            if (startIndex == s.length()) {
                addResult(s, partition);
                return;
            }
            for (int i = startIndex; i < s.length(); i++) {
                if (!isPalindrome[startIndex][i]) {
                    continue;
                }
                partition.add(i);
                
                helper(s, i + 1, partition);
                partition.remove(partition.size() - 1);
            }
        }

        private void addResult(String s, List<Integer> partition) {
            System.out.println(partition);
            List<String> result = new ArrayList<>();
            int startIndex = 0;
            for (int i = 0; i < partition.size(); i++) {
                result.add(s.substring(startIndex, partition.get(i) + 1));
                startIndex = partition.get(i) + 1;
            }
            results.add(result);
        }
    }

    private static void print(boolean[][] array) {
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print((array[i][j] == true ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }
}