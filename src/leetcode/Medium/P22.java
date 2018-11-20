import java.util.ArrayList;
import java.util.List;

/*
给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
public class P22 {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(new Solution().generateParenthesis(n));
    }

    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            if (n < 1) {
                return result;
            }
            backtracking(result, "", n, 0);
            return result;
        }

        /**
         * @param result
         * @param s 上一状态传递过来需要增加括号的字符串
         * @param leftRest 还有几个左括号可以用
         * @param rightNeed 代表还需要几个右括号才能匹配
         */
        public void backtracking(List<String> result, String s, int leftRest, int rightNeed) {
            // 取左括号和右括号的个数,
            if (leftRest == 0 && rightNeed == 0) {
                result.add(s);
                return;
            }

            // 只能左括号在前
            if (rightNeed > 0) {
                backtracking(result, s + ")", leftRest, rightNeed - 1);
            }
            if (leftRest > 0) {
                // 需要右括号补齐
                backtracking(result, s + "(", leftRest - 1, 1 + rightNeed);
            }
        }

        /**
         * @param n 将要左右括号的总数
         * @param left 左括号的个数
         * @param right 右括号的个数
         * @param s
         * @param results
         */
        public void backtracking2(int n, int left, int right, String s, List<String> results) {
            //递归结束的条件
            if(left == n && right == n) {
                results.add(s);
                return;
            }
            //可以添加左括号的条件
            if(left < n)
                backtracking2(n, left+1, right, s+"(", results);
            //可以添加有括号的条件
            if(right < left)
                backtracking2(n, left, right+1, s+")", results);
        }
    }
}
