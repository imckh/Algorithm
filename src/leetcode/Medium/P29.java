/*
29. 两数相除
https://leetcode-cn.com/problems/divide-two-integers/
给定两个整数, 被除数 dividend 和除数 divisor. 将两数相除, 要求不使用乘法、除法和 mod 运算符. 

返回被除数 dividend 除以除数 divisor 得到的商. 

示例 1:

输入: dividend = 10, divisor = 3
输出: 3
示例 2:

输入: dividend = 7, divisor = -3
输出: -2
说明:

被除数和除数均为 32 位有符号整数. 
除数不为 0. 
假设我们的环境只能存储 32 位有符号整数, 其数值范围是 [−231,  231 − 1]. 本题中, 如果除法结果溢出, 则返回 231 − 1. 

*/

public class P29 {
    public static void main(String[] args) {
        int dividend = 22;
        int divisor = 3;
        System.out.println(new Solution().divide(dividend, divisor));
    }

    static class Solution {
        /*
        每个整数都能转化成若干个2的n次方之和, 这种数则一共是log2(N)个, 若是从最低位找起, 则平均每次运行log2(N)/2次, 就可以啦. 
        根据 divisor = dividend * result = dividend * (这些数之和)

        以 22 和 3 相除为例子, 因为 22 >= 3, 我们对 3 进行左移一位, 也就是乘 2, 结果为 6, 比 22 小, 
        我们继续对 6 左移一位结果为 12, 还是比 22 小, 
        我们继续对 12 左移一位为 24, 比 22 大, 
        这时我们可以分析出, 22 肯定比 3 的 4 倍要大, 4 是怎么来的? 
        因为我们左移了两次, 也就是 1 << 2 = 4, 
        此时我们记下这个 4, 然后让 22 - 3 * 4 = 10, 因为 10 >= 3, 对 10 和 3 进行同样的操作, 我们可以得到 2, 
        此时加上上次的 4, 和为 6, 也就是 22 比 3 的 6 倍要大, 
        此时还剩余 10 - 6 = 4, 因为 4 >= 3, 
        所以对 4 和 3 进行同样的操作, 我们发现并不能对 3 进行左移了, 因为 4 >= 3, 所以 1 倍还是有的, 所以加上最后的 1, 
        结果为 6 + 1 = 7, 也就是 22 整除 3 结果为 7. 
        */
        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            long dividendabs = Math.abs((long) dividend);
            long divisorabs = Math.abs((long) divisor);
            int res = 0;
            while (dividendabs >= divisorabs) {
                long temp = divisorabs, multiple = 1;
                while (dividendabs >= temp << 1) {
                    temp <<= 1; // 相当于 * 2
                    multiple <<= 1;
                    //System.out.printf("     temp = %d, multiple = %d \n", temp, multiple);
                }
                dividendabs -= temp; // 剩余需要计算的除数
                res += multiple; // 总倍数
                //System.out.printf("dividendabs = %d, res = %d \n", dividendabs, res);
            }

            // 两个运算成员符号的异或结果
            return (dividend < 0) ^ (divisor < 0) ? -res : res;
        }

        /*
        我们只需要沿用整数除法的定义, 逐次累加除数直至其刚好大于或者等于被除数 - 除数, 结果就一目了然了.

        当然这里说的是除数和被除数都是正整数的情况, 那么当有一方或者两方为负数的时候, 
        问题就不能简单这么解决了, 不过也只需要存储一个两个运算成员符号的异或结果sign, 
        最后输出的时候 return sign ? result : -result 这样也就解决了

        时间复杂度O(N)


        divide(2147483647, 1) 超出时间限制
        */
        public int divide2(int dividend, int divisor) {
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