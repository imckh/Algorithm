import java.util.*;

/*
127. 单词接龙
https://leetcode-cn.com/problems/word-ladder/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/57/trees-and-graphs/137/
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。

示例 1:

    输入:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    输出: 5

    解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
        返回它的长度 5。

示例 2:

    输入:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]

    输出: 0

    解释: endWord "cog" 不在字典中，所以无法进行转换。
*/
public class P127 {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot","dot","dog","lot","log","cog");
        System.out.println(new Solution().ladderLength(beginWord, endWord, wordList));
    }

    // 广度优先
    static class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (beginWord == null || endWord == null || beginWord.equals(endWord)) {
                return 0;
            }

            int count = 0;
            Set<String> list = new HashSet<>(wordList);
            Set<String> set = new HashSet<>();
            set.add(beginWord);
            // 判断wordList中是否存在差了一个字母后的word
            while (!set.contains(endWord)) {
                Set<String> tmp = new HashSet<>();
                //System.out.println(set);
                for (String str : set) {
                    // 将 hit 的第一个字母 h 用a——z替换 ait bit cit dit fit ...xit yit zit
                    // 看一下 wordList中 是否存在 ait bit cit dit fit ...xit yit zit
                    // 分别替换所有字母
                    // 若存在符合只差一个字母的条件, 比如hot, 就将hot加入到tmp中
                    // 使用set来去掉重复
                    for (int i = 0; i < str.length(); i++) {
                        char[] chars = str.toCharArray();
                        for (int j = 97; j < 97+26; j++) { // 将set中的单词字母每个替换一遍
                            chars[i] = (char)j;
                            String word = new String(chars);
                            //System.out.print("  " + word);
                            if (list.contains(word)) {
                                list.remove(word);
                                tmp.add(word);
                            }
                        }
                        //System.out.println();
                    }
                }
                if (tmp.size() == 0) {
                    return 0;
                }
                // 相当于将bfs分层, count用来记录到达的当前层数
                count++;
                set = tmp;
            }
            return count + 1;
        }
    }
}