import java.util.*;

/**
387. 字符串中的第一个唯一字符

https://leetcode-cn.com/problems/first-unique-character-in-a-string/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/34/

给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

案例:

s = "leetcode"
返回 0.

s = "loveleetcode",
返回 2.
 

注意事项：您可以假定该字符串只包含小写字母。
 */

public class P387 {
    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(new Solution().firstUniqChar(s));
        System.out.println(new Solution().firstUniqChar2(s));

    }

    static class Solution {
        public int firstUniqChar(String s) {
            if (s == null) return -1;
            if (s.length() == 1) return 0;
            char[] c = s.toCharArray();
            Map<Character, Integer> map = new LinkedHashMap<>();

            for (int i = 0; i < c.length; i++) {
                map.put(c[i], map.containsKey(c[i]) ? map.get(c[i]) + 1 : 1);
            }

            for (Character cr : map.keySet()) {
                if(map.get(cr) == 1) {
                    for (int i = 0; i < c.length; i++) {
                        if (cr == c[i]) {
                            return i;
                        }
                    }
                }
            }
            return -1;
        }

        public int firstUniqChar2(String s) {
            int temp;
            int minIndex = -1;
            // 遍历所有字母在s中最后出现的位置,
            // 找到最小的位置
            for(char c = 'a'; c<='z' ; c++){
                temp = s.indexOf(c);
                if( temp != -1 && temp == s.lastIndexOf(c)) {
                    minIndex = (minIndex == -1)? temp : Math.min(temp, minIndex);
                }
                //System.out.printf("c = \'%c\' temp = %d, minIndex = %d\n", c, temp, minIndex);
            }
            return minIndex;
        }
    }
}