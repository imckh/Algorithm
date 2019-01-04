/*
70. 爬楼梯
https://leetcode-cn.com/problems/climbing-stairs/description/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/54/

假设你正在爬楼梯。需要 n 步你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：

输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 步 + 1 步
2.  2 步
示例 2：

输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 步 + 1 步 + 1 步
2.  1 步 + 2 步
3.  2 步 + 1 步
*/
public class P70 {
    public static void main(String[] args) {
        System.out.println("------P70------");
        
        Solution solution = new Solution();

        System.out.println(solution.climbStairs(8));
        System.out.println(solution.climbStairs2(8));

        System.out.println("------P70------");
    }
}
class Solution {
    public int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        } // 递归做法
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    public int climbStairs2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int n1 = 1;
        int n2 = 2;
        // 这其实就是斐波那契数列
        for (int i = 3; i <= n; i++) {
            n2 = n1 + n2;
            n1 = n2 - n1;
        }

        return n2;
    }
}