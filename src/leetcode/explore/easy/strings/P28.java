/*
28. 实现strStr()

https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/38/
https://leetcode-cn.com/problems/implement-strstr/

实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:

输入: haystack = "hello", needle = "ll"
输出: 2
示例 2:

输入: haystack = "aaaaa", needle = "bba"
输出: -1
说明:

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
*/
public class P28 {
    public static void main(String args[]){
            
        String str = "abcxabcdabcdabcy";
        String subString = "abcdabcy";
        Solution ss = new Solution();
        int result = ss.strStr(str, subString);
        System.out.print(result);
        
    }


    static class Solution {
        /**
         * kmp算法 O(m+n)
         * 
         * @param source:
         * @param target:
         * @return: return the index
         */
        public int strStr(String source, String target) {
            // Write your code here
            if (target.length() == 0) {
                return 0;
            }
            int c = 0, d = 0;
            char[] sourceC = source.toCharArray();
            char[] targetC = target.toCharArray();
            int[] next = getNext(targetC);
            while (c < sourceC.length && d < targetC.length) {
                if (d == -1 || sourceC[c] == targetC[d]) {
                    c++;
                    d++;
                } else {
                    d = next[d];
                }
            }
            if (d == targetC.length) {
                return c - d;
            }
            return -1;
        }

        // KMP算法的简明介绍：
        // https://www.zhihu.com/question/21923021/answer/281346746
        // https://www.jianshu.com/p/e2bd1ee482c3
        // 得到KMP算法的next数组
        // 字符串的前缀和后缀
        protected int[] getNext(char[] p) {
            int[] next = new int[p.length];
            int a = -1, b = 0;
            next[0] = -1;
            // 一步步计算每一个next。
            while (b < p.length - 1) {
                if (a == -1 || p[a] == p[b]) {
                    a++;
                    b++;
                    next[b] = a;
                } else {
                    a = next[a];
                }
            }
            return next;
        }
    }
}