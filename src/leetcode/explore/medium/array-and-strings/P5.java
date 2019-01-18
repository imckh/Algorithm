/*
5. 最长回文子串

https://leetcode-cn.com/problems/longest-palindromic-substring/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/29/array-and-strings/79/

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

https://leetcode-cn.com/problems/longest-palindromic-substring/
 */

import java.util.*;
public class P5{
    public static void main(String[] args) {
        /*
        String s = "";

        int cap = 10000;
        StringBuffer sb = new StringBuffer(cap);
        for (int i = 0; i < cap; i++) {
            sb.append("a");
        }
        s = sb.toString();

        long startime = System.currentTimeMillis();
        //System.out.println(new Solution().longestPalindrome_ON2(s));
        new Solution().longestPalindrome_ON2(s);
        long endtime = System.currentTimeMillis();
        System.out.println(endtime - startime);

        startime = System.currentTimeMillis();
        //System.out.println(new Solution().longestPalindrome_Dynamic_ON2(s));
        new Solution().longestPalindrome_Dynamic_ON2(s);
        endtime = System.currentTimeMillis();
        System.out.println(endtime - startime);
        */
        String s = "abc1234321ab";
        System.out.println(new Solution().longestPalindrome_Dynamic_ON2(s));
        // System.out.println(new Solution().manacher(s));

    }
}
class Solution {
    public String longestPalindrome(String s) {
        return longestPalindrome_ON2(s);
    }

    // 中心扩展算法
    int start, end;
    public String longestPalindrome_ON2(String s) {
        int len = s.length();
        if (len <= 1) return s;
        char[] chars = s.toCharArray();
        for (int i = 0; i < len; i++) {
            // O(n^2)
            // 依次去求得每个字符的最长回文
            // 奇数长度的回文串和偶数长度的回文串都要计算
            expandAroundCenter(chars, i, i);
            expandAroundCenter(chars, i, i + 1);
        }
        return s.substring(start, end + 1);
    }

    private void expandAroundCenter(char[] chars, int l, int r) {
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

    // 动态规划
    public String longestPalindrome_Dynamic_ON2(String s) {
        int len = s.length();
        if (len <= 1) return s;
        int start = 0, end = 0;
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[len][len];

        // i, j 的意思是从j到i的子串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            for (int j = 0; j < i; j++) {
                if (j + 1 == i) {
                    // 二字母的回文
                    dp[j][i] = chars[j] == chars[i];
                } else {
                    // 动态规划解法，首先初始化一字母和二字母的回文，然后找到所有三字母回文, 依此类推
                    // 如果之前就是回文只需要找新加的这两个字符相不相等即可
                    // start到end是回文的前提是(start+1)到(end-1)是回文而且char[start] == char[end]
                    // dp[j][i]是不是回文取决于dp[j+1][i-1]是回文而且char[i] == char[j]
                    dp[j][i] = dp[j + 1][i - 1] && chars[i] == chars[j];
                }
                if (dp[j][i] && i - j > end - start) {
                    start = j;
                    end = i;
                }
                System.out.printf("i: %d, j: %d \n", i, j);
                printArray(dp, len, len);
            }
        }
        return s.substring(start, end + 1);
    }

    private void printArray(boolean[][] arr, int len1, int len2) {
        for (int i = 0; i < len1; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }

    // 视频讲解https://www.nowcoder.com/courses/6/4/1
    // https://segmentfault.com/a/1190000008484167#articleHeader3
    public String manacher(String s) {
        String s_new = String.join("#", s.split(""));
        s_new = "$#" + s_new + "#^";

        System.out.println("[" + String.join(", ", s_new.split("")) + "]");

        char[] chars = s_new.toCharArray();
        int[] p = new int[s_new.length()]; // p[i]表示以 i 为中心的最长回文的半径
        int id = 0;
        int mx = 0; // mx 代表以 id 为中心的最长回文的右边界, mx = id + p[id]
        int maxid = -1;
        int maxcircle = -1;
        int maxlen = -1;

        for (int i = 1; i < chars.length - 1; i++) {
            System.out.printf("i = %d, id = %d, mx = %d\n", i, id, mx);
            // 2 * id - i为 i 关于 id 的对称点
            // p[j]表示以 j 为中心的最长回文半径
            p[i] = i < mx ? Math.min(p[2 * id - i], mx - i) : 1;
            System.out.println(Arrays.toString(p));
            while (chars[i - p[i]] == chars[i + p[i]]) { // 不需边界判断，因为左有'$',右有'^'
                p[i]++;
                System.out.println(Arrays.toString(p));
            }

            // 我们每走一步 i，都要和 mx 比较，我们希望 mx 尽可能的远，这样才能更有机会执行 if (i < mx)这句代码，从而提高效率
            if (mx < i + p[i]) {
                id = i;
                mx = i + p[i]; // 最右侧
            }

            if (maxlen < p[i] - 1) {
                maxlen = p[i] - 1;
                maxid = id;
                maxcircle = mx;
            }

            System.out.println();
        }

        System.out.printf("middle:%d, len:%d, circle:%d\n", maxid, maxlen, maxcircle);
        System.out.printf("maxcircle / 2 - maxlen - 1 = %d, maxcircle / 2 - 1 = %d\n", maxcircle / 2 - maxlen - 1, maxcircle / 2 - 1);
        return s.substring(maxcircle / 2 - maxlen - 1, maxcircle / 2 - 1);
    }
}