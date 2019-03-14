import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/*
227. 基本计算器 II

https://leetcode-cn.com/problems/basic-calculator-ii/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/131/


实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:

输入: "3+2*2"
输出: 7
示例 2:

输入: " 3/2 "
输出: 1
示例 3:

输入: " 3+5 / 2 "
输出: 5
说明：

你可以假设所给定的表达式都是有效的。
请不要使用内置的库函数 eval。

*/
public class P227 {
    public static void main(String[] args) {
        String str = " 3+50 / 22222 ";
        System.out.println(new Solution().calculate(str));
    }

    static class Solution {
        public int calculate(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            s = s.replaceAll(" ", "");

            Deque<Integer> stack = new ArrayDeque<>();
            int temp = 0;
            char simple = '+';
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    temp = temp * 10 + c - '0';
                }
                //System.out.println(temp);
                //System.out.println("c = " + c);
                if (!(c >= '0' && c <= '9') || count == s.length() - 1) {
                    //System.out.println(simple);
                    switch (simple) {
                        case '+':
                            stack.push(temp);
                            break;
                        case '-':
                            stack.push(-temp);
                            break;
                        case '*':
                            stack.push(stack.pop() * temp);
                            break;
                        case '/':
                            stack.push(stack.pop() / temp);
                            break;
                        default:
                            break;
                        }
                    //System.out.println(simple);
                    //System.out.println(stack);
                    temp = 0;
                    simple = c;
                }
                count++;
            }
            int r = 0;
            
            while(!stack.isEmpty()) {
                r += stack.pop();
            }
            return r;
        }
    }
}