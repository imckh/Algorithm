/*
5065. 最长字符串链
给出一个单词列表，其中每个单词都由小写英文字母组成。

如果我们可以在 word1 的任何地方添加一个字母使其变成 word2，那么我们认为 word1 是 word2 的前身。例如，"abc" 是 "abac" 的前身。

词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word_1 是 word_2 的前身，word_2 是 word_3 的前身，依此类推。

从给定单词列表 words 中选择单词组成词链，返回词链的最长可能长度。
 

示例：

输入：["a","b","ba","bca","bda","bdca"]
输出：4
解释：最长单词链之一为 "a","ba","bda","bdca"。
 

提示：

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] 仅由小写英文字母组成。
*/
import java.util.*;
public class P5065 {
    public static void main(String[] args) {
        String[] words = {"bca", "a","b","ba","bda","bdca"};

        System.out.println(new Solution().longestStrChain(words));
    }

    static class Solution {
        public int longestStrChain(String[] words) {
            if (words == null || words.length == 0) return 0;
            if (words.length == 1) return 1;
            Arrays.sort(words, (a, b) -> a.length() - b.length());
            
            int max = 0;
            for (int i = 0; i < words.length; i++) {
                int next = helper(words, words[i]);
                max = Math.max(max, next);
            }

            return max;
        }
        int max = 0;
        public int helper(String[] words, String s) {
            int max = 0;
            for (int i = 0; i < words.length; i++) {
                if (words.length == s.length() + 1) {
                    if (isOneEditDistance(s, words[i])) {
                        int next = helper(words, words[i]);
                        max = Math.max(max, next);
                    }
                }
            }
            return max;
        }

        public boolean isOneEditDistance(String s, String t) {
            if (Math.abs(s.length() - t.length()) != 1) return false;
            // 一处不同
            for (int i = 0; i < Math.min(s.length(), t.length()); i++){
                if (s.charAt(i) != t.charAt(i)){
                    if (s.length() == t.length()){
                        return s.substring(i + 1).equals(t.substring(i + 1));
                    } else if (s.length() == t.length() - 1){
                        return s.substring(i).equals(t.substring(i + 1));
                    } else {
                        return s.substring(i + 1).equals(t.substring(i));
                    }
                }
            }
            return Math.abs(s.length() - t.length()) == 1;
        }
    }
}