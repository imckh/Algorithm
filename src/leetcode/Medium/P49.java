
/*
49. 字母异位词分组

https://leetcode-cn.com/problems/group-anagrams/

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:

输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
说明：

所有输入均为小写字母。
不考虑答案输出的顺序。
*/
import java.util.*;

public class P49 {
    public static void main(String[] args) {
        String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println(new Solution().groupAnagrams(strs));
    }

    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            
            if (strs == null || strs.length == 0)
                return Collections.emptyList();
            List<List<String>> list = new ArrayList<>();
            Map<String, Integer> hash = new HashMap<>();
            int i = 0;

            for (String str : strs) {
                char[] c = str.toCharArray();
                Arrays.sort(c);
                String sortStr = String.valueOf(c); // 两个字符串含有相同的字符, 排序后是一样的 // 将字符串排序后放入map中
                if (!hash.containsKey(sortStr)) {
                    // 字符串首次添加 
                    hash.put(sortStr, i++);
                    List<String> sub = new ArrayList<>();
                    sub.add(str);
                    list.add(sub);
                } else {
                    // 已存在相同字符的字符串 
                    list.get(hash.get(sortStr)).add(str);
                }
            }
            return list;
            /*
            // 使用更简洁的写法, 原理相同
            Map<String, List<String>> map = new HashMap<>();
            for (String s : strs) {
                char[] sc = s.toCharArray();
                Arrays.sort(sc);
                String key = String.valueOf(sc);
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(s);
            }
            return new ArrayList<>(map.values());
            */
        }
    }
}