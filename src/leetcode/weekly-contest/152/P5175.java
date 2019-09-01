/*

5175. 构建回文串检测

给你一个字符串 s，请你对 s 的子串进行检测。

每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。我们可以 重新排列 子串 s[left], ..., s[right]，并从中选择 最多 k 项替换成任何小写英文字母。 

如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。

返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。

注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，也就是说，如果 s[left..right] = "aaa" 且 k = 2，我们只能替换其中的两个字母。（另外，任何检测都不会修改原始字符串 s，可以认为每次检测都是独立的）

 

示例：

输入：s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
输出：[true,false,false,true,true]
解释：
queries[0] : 子串 = "d"，回文。
queries[1] : 子串 = "bc"，不是回文。
queries[2] : 子串 = "abcd"，只替换 1 个字符是变不成回文串的。
queries[3] : 子串 = "abcd"，可以变成回文的 "abba"。 也可以变成 "baab"，先重新排序变成 "bacd"，然后把 "cd" 替换为 "ab"。
queries[4] : 子串 = "abcda"，可以变成回文的 "abcba"。
 

提示：

1 <= s.length, queries.length <= 10^5
0 <= queries[i][0] <= queries[i][1] < s.length
0 <= queries[i][2] <= s.length
s 中只有小写英文字母

*/
import java.util.*;

public class P5175 {
    public static void main(String[] args) {

        // String s = "abcda";
        // int[][] queries = {{3,3,0},{1,2,0},{0,3,1},{0,3,2},{0,4,1}};
        String s = "hunu";
        int[][] queries = //{{0,3,1}};
         {{1,1,1},{2,3,0},{3,3,1},{0,3,2},{1,3,3},{2,3,1},{3,3,1},{0,3,0},{1,1,1},{2,3,0},{3,3,1},{0,3,1},{1,1,1}};


        System.out.println(new Solution().canMakePaliQueries(s, queries));
    }

    static class Solution {
        int[] charsn = new int[26];
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
            // for (int i = 0; i < s.length(); i++) {
            //     charsn[s.charAt(i) - 'a']++;
            // }
            List<Boolean> result = new ArrayList<>();
            for (int i = 0; i < queries.length; i++) {
                int left = queries[i][0];
                int right = queries[i][1];
                int k = queries[i][2];
                
                result.add(maybePalindrome(s.toCharArray(), left, right, k));
            }

            return result;
        }

        public boolean maybePalindrome(char[] chars, int left, int right, int k) {
            if (right - left < 1) {
                return true;
            }
            if (!map.containsKey(left)) {
                map.put(left, new HashMap<>());
            }
            if (!map.get(left).containsKey(right)) {
                int[] charn = new int[26];
                for (int i = left; i <= right; i++) {
                    charn[chars[i] - 'a']++;
                    if (charn[chars[i] - 'a'] >= 2) {
                        charn[chars[i] - 'a'] -= 2;
                    }
                }
                // 只剩下 字符数为1的非回文了
                // "abddaa" --> "b"
    
                int sum = 0;
                for (int i = 0; i < charn.length; i++) {
                    sum += charn[i];
                }
                map.get(left).put(right, sum);
            }
            
            int sum = map.get(left).get(right);

            System.out.println(sum);
            if (sum <= 1) {
                // 说明只剩1个字符  或者一个都没有  肯定是回文
                return true;
            }
            // 看k能替换几个
            // 比如剩下2个ab 替换1个即可 aa
            // 比如剩下3个abc 替换1个即可 aba
            // 比如剩下4个abcd 替换2个即可 abba
            // 比如剩下5个abcde 替换2个即可 abcba
            // 比如剩下6个abcdef 替换3个即可 abccba
            return k >= sum / 2;
        }
    }
}