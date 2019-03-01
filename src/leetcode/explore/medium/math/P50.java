/*
50. Pow(x, n)

https://leetcode-cn.com/problems/powx-n/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/53/math/115/

实现 pow(x, n) ，即计算 x 的 n 次幂函数。

示例 1:

输入: 2.00000, 10
输出: 1024.00000
示例 2:

输入: 2.10000, 3
输出: 9.26100
示例 3:

输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
说明:

-100.0 < x < 100.0
n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
*/
public class P50 {
    public static void main(String[] args) {
        System.out.println(new Solution().myPow(2.00000, 10));
    }

    static class Solution {
        public double myPow(double x, int n) {
            if (n < 0) return binaryPow(1 / x, -n);
            return binaryPow(x, n);
        }

        /*
        要求pow(2,10), 可以分为pow(2,5)*pow(2,5), 其中pow(2,5)可以分为pow(2,2)*pow(2,2)*pow(2,1)
               -> n == 10,n>>>1 = 5 -> (n = 5, n>>>1 = 2, x*x*d)*(n = 5, n>>>1 = 2, x*x*d)
        2 ^ 10 == (2 ^ 5) * (2 ^ 5) == ((2 ^ 2) * (2 ^ 2) * 2)*((2 ^ 2) * (2 ^ 2) * 2)
        */
        private double binaryPow(double x, int n) {
            if (n == 0) return 1;
            if (n == 1) return x;
            // 其实还可以用一个全局的数组保存已经计算过的值来加速计算
            double d = binaryPow(x, n >>> 1); // 二分
            if (n % 2 == 0) return d * d;
            return d * d * x;
        }
    }
}