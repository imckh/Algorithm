/*
172. 阶乘后的零

https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/53/math/113/
https://leetcode-cn.com/problems/factorial-trailing-zeroes/

给定一个整数 n，返回 n! 结果尾数中零的数量。

示例 1:

输入: 3
输出: 0
解释: 3! = 6, 尾数中没有零。
示例 2:

输入: 5
输出: 1
解释: 5! = 120, 尾数中有 1 个零.
说明: 你算法的时间复杂度应为 O(log n) 。
*/
import java.util.*;
public class P172 {
    public static void main(String[] args) {
        System.out.println(new Solution().trailingZeroes(3));
        System.out.println(new Solution().trailingZeroes(5));
        System.out.println(new Solution().trailingZeroes(10));
        System.out.println(new Solution().trailingZeroes(20));
        System.out.println(new Solution().trailingZeroes(25));
    }

    static class Solution {
        /*
        1 2 3 4 5 6 7 8 9 10
        [2,5->一个0], [10一个0]
        11 12 13 14 15

        最终尾随零的数量之和质因子中2和5的数量有关，
        很容易想到质因子5的数量一定会比2少，
        所以只需要算出n!的质因子5的数量即可
        所以我们考虑1~n！之间5的倍数，25的倍数，125的倍数，625的倍数......的数量，即可算出答案
        */
        public int trailingZeroes(int n) {
            if (n >= 5) {
                return n / 5 + trailingZeroes (n / 5);
            } else {
                return 0;
            }
        }
    }
}