import java.util.Arrays;
/*
66. 加一

// https://leetcode-cn.com/problems/plus-one/description/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/27/

给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1:

输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
示例 2:

输入: [4,3,2,1]
输出: [4,3,2,2]
解释: 输入数组表示数字 4321。
*/
public class P66 {
    public static void main(String[] args) {
        System.out.println("------P66------");
        
        Solution solution = new Solution();
        int[] s = {9, 9, 9};
        int[] s1 = {1, 2, 3};
        int[] s2 = {1, 9, 9};
        System.out.println(Arrays.toString(solution.plusOne(s)));
        System.out.println(Arrays.toString(solution.plusOne(s1)));
        System.out.println(Arrays.toString(solution.plusOne(s2)));

        System.out.println("------P66------");
    }
}
class Solution {
    public int[] plusOne(int[] digits) {
        boolean flag = false;   // [1, 9, 9]
        boolean needExpand = true;  // [9, 9, 9]
        for (int i = digits.length - 1; i >= 0; i--) {
            // carry
            flag = (digits[i] == 9) ? true : false;
            if (flag) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                needExpand = false;
                break;
            }
        }
        int[] results;
        if (needExpand) {
            results = new int[digits.length + 1];
            results[0] = 1;
            System.arraycopy(digits, 0, results, 1, digits.length);
        } else {
            results = new int[digits.length];
            System.arraycopy(digits, 0, results, 0, digits.length);
        }
        return results;
    }
}