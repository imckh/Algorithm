/*
29. 两数相除
https://leetcode-cn.com/problems/divide-two-integers/
给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

示例 1:

输入: dividend = 10, divisor = 3
输出: 3
示例 2:

输入: dividend = 7, divisor = -3
输出: -2
说明:

被除数和除数均为 32 位有符号整数。
除数不为 0。
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。

*/

public class P29 {
    public static void main(String[] args) {
        int dividend = 10;
        int divisor = -3;
        System.out.println(new Solution().divide(dividend, divisor));
    }

    static class Solution {
        /*
        我们只需要沿用整数除法的定义, 逐次累加除数直至其刚好大于或者等于被除数 - 除数, 结果就一目了然了.

        当然这里说的是除数和被除数都是正整数的情况, 那么当有一方或者两方为负数的时候, 
        问题就不能简单这么解决了, 不过也只需要存储一个两个运算成员符号的异或结果sign, 
        最后输出的时候 return sign ? result : -result 这样也就解决了

        时间复杂度O(N)


        divide(2147483647, 1) 超出时间限制
        */
        public int divide(int dividend, int divisor) {
            if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
                return Integer.MAX_VALUE;
            }
            if (dividend == 0) {
                return 0;
            }

            int result = 0, sum = 0;
            boolean sign = dividend < 0 ^ divisor < 0;
            long dividendabs = Math.abs((long) dividend);
            long divisorabs = Math.abs((long) divisor);

            while (true) {
                sum += divisorabs;
                result++;

                if (sum == dividendabs) {
                    break;
                } else if (sum > dividendabs) {
                    result--;
                    break;
                }
            }

            return sign ? -result : result;
        }
    }
}