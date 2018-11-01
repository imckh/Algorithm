/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba"也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"
hint 1:
How can we reuse a previously computer palindrome to compute a larger palindrome?
hint 2:
If “aba” is a palindrome, is “xabax” and palindrome? Similarly is “xabay” a palindrome?
Complexity based hint:
If we use brute-force and check whether for every start and end position a substring 
is a palindrome we have O(n^2) start - end pairs and O(n) palindromic checks. 
Can we reduce the time for palindromic checks to O(1) by reusing some previous computation.
 */

public class P5{
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("babad"));
    }
}
class Solution {
    public String longestPalindrome(String s) {
        return longestPalindrome_ON2(s);
    }

    int start, end;
    public String longestPalindrome_ON2(String s) {
        int len = s.length();
        if (len <= 1) return s;
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            // O(n^2)
            // 依次去求得每个字符的最长回文
            // 奇数长度的回文串和偶数长度的回文串都要计算
            helper(chars, i, i);
            helper(chars, i, i + 1);
        }
        return s.substring(start, end + 1);
    }

    private void helper(char[] chars, int l, int r) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            // 由该字符向两侧扩展回文字符
            // 直到边界
            --l;
            ++r;
        }
        if (end - start < r - l - 2) {
            start = l + 1;
            end = r - 1;
        }
    }
}