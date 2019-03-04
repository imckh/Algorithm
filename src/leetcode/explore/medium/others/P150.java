/*
150. 逆波兰表达式求值

https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/54/others/120/

根据逆波兰表示法，求表达式的值。

有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：

整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
示例 1：

输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9
示例 2：

输入: ["4", "13", "5", "/", "+"]
输出: 6
解释: (4 + (13 / 5)) = 6
示例 3：

输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
输出: 22
解释: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
*/

import java.util.*;
public class P150 {
    public static void main(String[] args) {
        System.out.println(new Solution().evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
        System.out.println(new Solution().evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(new Solution().evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

    static class Solution {
        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length == 0) {
                return 0;
            }
            // 使用栈保存结果, 将每个数字入栈
            // 需要运算符需要出栈两个数, 并将结果入栈
            // 最后只剩一个数字
            Deque<Integer> stack = new ArrayDeque<>();
            int first, second;
            for (String var : tokens) {
                switch (var) {
                    case "+":
                        first = stack.pop();
                        second = stack.pop();
                        stack.push(second + first);
                        break;
                    case "-":
                        first = stack.pop();
                        second = stack.pop();
                        stack.push(second - first);
                        break;
                    case "*":
                        first = stack.pop();
                        second = stack.pop();
                        stack.push(second * first);
                        break;
                    case "/":
                        first = stack.pop();
                        second = stack.pop();
                        stack.push(second / first);
                        break;
                
                    default:
                        stack.push(Integer.valueOf(var));
                        break;
                }
            }

            return stack.pop();
        }
    }

    // 递归实现
    static class Solution_r {
        private int N = -1;

        public int evalRPN(String[] tokens) {

            if (N == -1)
                N = tokens.length - 1;
            String s = tokens[N--];
            char c = s.charAt(0);
            if (s.length() == 1 && "+-*/".indexOf(c) != -1) {
                int a = evalRPN(tokens);
                int b = evalRPN(tokens);
                switch (c) {
                case '+':
                    return a + b;
                case '-':
                    return b - a;
                case '*':
                    return a * b;
                case '/':
                    return b / a;
                default:
                    break;
                }
            }
            return Integer.parseInt(s);
        }
    }
}