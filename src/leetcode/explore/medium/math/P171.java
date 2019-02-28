/*
171. Excel表列序号

https://leetcode-cn.com/problems/excel-sheet-column-number/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/53/math/114/

给定一个Excel表格中的列名称，返回其相应的列序号。

例如，

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
示例 1:

输入: "A"
输出: 1
示例 2:

输入: "AB"
输出: 28
示例 3:

输入: "ZY"
输出: 701
致谢：
特别感谢 @ts 添加此问题并创建所有测试用例。
*/
import java.util.*;
public class P171 {
    public static void main(String[] args) {
        System.out.println(new Solution().titleToNumber("A"));
        System.out.println(new Solution().titleToNumber("AB"));
        System.out.println(new Solution().titleToNumber("ZY"));
    }

    static class Solution {
        // 26进制转10进制
        public int titleToNumber(String s) {
            int res = 0;
            for (int i = 0; i < s.length() ; i++) {
                res += Math.pow (26, s.length() - 1 - i) * (s.charAt (i) - 64); // 'A'-1
            }
            return res;
        }
    }
}