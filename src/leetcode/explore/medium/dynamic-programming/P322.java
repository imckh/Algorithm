import java.util.Arrays;

/*
322. 零钱兑换

https://leetcode-cn.com/problems/coin-change/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/51/dynamic-programming/106/

给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

示例 1:

输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1
示例 2:

输入: coins = [2], amount = 3
输出: -1
说明:
你可以认为每种硬币的数量是无限的。
*/
public class P322 {
    public static void main(String[] args) {
        int[] coins = new int[] { 1, 2, 6, 5 };
        int amount = 11;
        //System.out.println(new Solution().coinChange(coins, amount));
        System.out.println(new Solution2().coinChange(new int[] { 2 }, 3));
    }

    static class Solution {
        /*
         * 这是一个典型的完全背包问题。 设dp[i][j]表示使用前i个硬币，总金额为j时需要的最少硬币数量。
         * 
         * dp[i][j]=max(dp[i−1][j],dp[i−1][j−k∗coin[i]]+k)(0≤k∗coin[i]≤j)
         */
        public int coinChange(int[] coins, int amount) {
            int[] f = new int[amount + 1];
            int len = coins.length;

            f[0] = 0;
            int i, j;
            for (i = 1; i <= amount; i++) {
                f[i] = -1;
                for (j = 0; j < len; j++) {
                    if (i >= coins[j] && f[i - coins[j]] != -1) {
                        if (f[i] == -1 || f[i - coins[j]] + 1 < f[i]) {
                            f[i] = f[i - coins[j]] + 1;
                            System.out.println(Arrays.toString(f));
                        }
                    }
                }
            }

            return f[amount];
        }
    }
}