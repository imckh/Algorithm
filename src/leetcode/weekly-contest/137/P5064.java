/**
5064. 删除字符串中的所有相邻重复项
给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。

在 S 上反复执行重复项删除操作，直到无法继续删除。

在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。

 

示例：

输入："abbaca"
输出："ca"
解释：
例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 

提示：

1 <= S.length <= 20000
S 仅由小写英文字母组成。
 */
import java.util.*;
public class P5064 {
    public static void main(String[] args) {
        String s = "abbaca";

        System.out.println(new Solution().removeDuplicates(s));
    }

    static class Solution {
        public String removeDuplicates(String S) {
            StringBuilder sb = new StringBuilder(S);
            while (true) {
                List<Integer> list = testDuplicates(sb);
                if (list == null) {
                    break;
                }
                //System.out.println(list);
                deleteIndexs(list, sb);
            }
            return sb.toString();
        }

        public void deleteIndexs(List<Integer> indexs, StringBuilder sb) {
            if (indexs == null) return;
            int deletedNum = 0;
            for (int i : indexs) {
                System.out.println("delete[" + (i - deletedNum) + "]: " + sb);
                sb.deleteCharAt(i - (deletedNum++));
                System.out.println("delete[" + (i - deletedNum) + "]: " + sb);
                sb.deleteCharAt(i + 1 - (deletedNum++));
                System.out.println(sb);
            }
        }

        public List<Integer> testDuplicates(StringBuilder sb) {
            if (sb == null || sb.length() < 2) {
                return null;
            }
            List<Integer> duplicates = new ArrayList<>();

            for (int i = 1; i < sb.length(); i++) {
                char a = sb.charAt(i - 1);
                char b = sb.charAt(i);

                if (a == b) {
                    duplicates.add(i - 1);
                    i++;
                }
            }
            return duplicates.size() == 0 ? null : duplicates;
        }
    }
}