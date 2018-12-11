/*
68. 文本左右对齐

https://leetcode-cn.com/problems/text-justification/

给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

文本的最后一行应为左对齐，且单词之间不插入额外的空格。

说明:

单词是指由非空格字符组成的字符序列。
每个单词的长度大于 0，小于等于 maxWidth。
输入单词数组 words 至少包含一个单词。
示例:

输入:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
示例 2:

输入:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。       
     第二行同样为左对齐，这是因为这行只包含一个单词。
示例 3:

输入:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
*/
import java.util.*;
public class P68 {
    public static void main(String[] args) {
        for (String s : new Solution().fullJustify(new String[]{
                "This", "is", "an", "example", "of", "text", "justification."
            }, 16)) {
            System.out.println("[" + s + "]");
        }
        System.out.println();
    }

    static class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            int len = words.length;
            List<String> ans = new ArrayList<>();
            StringBuilder spaces = new StringBuilder(); // 从spaces里切空格
            for (int i = 0; i < maxWidth; i++) {
                spaces.append(" ");
            }
            int curLen = -1 // 当前行长度
                , start = 0;// words
            for (int i = 0; i < len; i++) {
                if (curLen + words[i].length() + 1 <= maxWidth) {// 这行放不开这个单词了
                    curLen += words[i].length() + 1;
                } else {
                    StringBuilder sub = new StringBuilder(words[start]);
                    int rest = maxWidth - curLen;
                    int l = i - start - 1;
                    // 如果最后有多余的空格，那就把空格均匀地插入到单词之间
                    if (l <= 0) {
                        sub.append(spaces.substring(0, rest));
                    } else {
                        int m = rest / l + 1;
                        int mod = rest % l;
                        // 如果不能平分的话，那就从左开始依次多插一个空格，最后一行单词之间就正常地一个空格即可，如果凑不到最大行宽，那就在末尾补充空格即可
                        for (int j = start + 1; j < i; j++) {
                            if (mod-- > 0) {
                                sub.append(spaces.substring(0, m + 1)).append(words[j]);
                            } else {
                                sub.append(spaces.substring(0, m)).append(words[j]);
                            }
                        }
                    }
                    ans.add(sub.toString());
                    start = i;
                    curLen = words[i].length();
                }
            }
            //最后一行的话单独处理
            StringBuilder sub = new StringBuilder(words[start]);
            for (int i = start + 1; i < len; i++) {
                sub.append(" ").append(words[i]);
            }

            ans.add(sub + spaces.substring(0, maxWidth - sub.length()));

            return ans;
        }
    }
}