/*
125. 验证回文串
https://leetcode-cn.com/problems/valid-palindrome/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/36/
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"
输出: true
示例 2:

输入: "race a car"
输出: false

进阶: 5 最长回文子串
*/
public class P125 {
    public static void main(String[] args) {
        
        String s = "A man, a plan, a canal: Panama";
        System.out.println(new Solution().isPalindrome(s));

    }

    static class Solution {
        public boolean isPalindrome(String s) {
            if (s == null || s.length() <= 1) {
                return true;
            }
            // 忽略字母的大小写
            char[] sc = s.toCharArray();
            int i = 0, j = s.length() - 1;
            while (i <= j) {
                System.out.println("=======================");
                System.out.println(sc[i] + " " + sc[j]);
                while (i <= j && !isCD(sc[i])) i++;
                while (i <= j && !isCD(sc[j])) j--;

                System.out.println(sc[i] + " " + sc[j]);
                if (Character.toUpperCase(sc[i]) != Character.toUpperCase(sc[j])) {
                    return false;
                }
                i++;
                j--;
            }

            return true;
        }

        public boolean isCD(char c) {
            return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
        }
    }
}