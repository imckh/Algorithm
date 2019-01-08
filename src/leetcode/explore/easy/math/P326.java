/*
326. 3的幂

https://leetcode-cn.com/problems/power-of-three/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/25/math/62/

给定一个整数，写一个函数来判断它是否是 3 的幂次方。

示例 1:

输入: 27
输出: true
示例 2:

输入: 0
输出: false
示例 3:

输入: 9
输出: true
示例 4:

输入: 45
输出: false
进阶：
你能不使用循环或者递归来完成本题吗？
*/
import java.util.*;
public class P326 {
    public static void main(String[] args) {
        System.out.println(new Solution().isPowerOfThree(27));
        System.out.println(new Solution().isPowerOfThree(9));
        System.out.println(new Solution().isPowerOfThree(45));
    }

    static class Solution {
        public boolean isPowerOfThree_loop(int n) {
            if (n <= 0)
                return false;
            while (n != 1) {
                if (n % 3 != 0)
                    return false;
                n /= 3;
            }
            return true;
        }

        /*
        通过查看相关解析，发现了这个解法，用到了数论的知识，
        3的幂次的质因子只有3，而所给出的n如果也是3的幂次，
        故而题目中所给整数范围内最大的3的幂次的因子只能是3的幂次，
        1162261467是3的19次幂，是整数范围内最大的3的幂次

        3^19是int范围内最大的3的幂，那么3^19可以被任何3的幂整除
        */
        public boolean isPowerOfThree(int n) {
            return n > 0 && 1162261467%n == 0;
        }
    }
}