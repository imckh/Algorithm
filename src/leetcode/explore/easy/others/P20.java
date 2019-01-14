/*
20. 有效的括号

https://leetcode-cn.com/problems/valid-parentheses/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/68/

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true
*/
public class P20 {
    public static void main(String[] args) {
        System.out.println(new Solution().isValid("{}"));
        System.out.println(new Solution().isValid("{}[]()"));
        System.out.println(new Solution().isValid("{[}]"));
        System.out.println(new Solution().isValid("{[()]}"));
    }

    static class Solution {
        public boolean isValid(String s) {
            char[] stack = new char[s.length() + 1];
            int top = 1;
            
            for(char c: s.toCharArray()) {
                if(c == '(' || c == '{' || c == '[') {
                    stack[top++] = c;
                } else if (c == ')' && stack[--top] != '(') {
                    return false;
                } else if (c == '}' && stack[--top] != '{') {
                    return false;
                } else if (c == ']' && stack[--top] != '[') {
                    return false;
                }
            }
            
            return top == 1;
        }
    }
}