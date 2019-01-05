import java.util.Arrays;

/*
198. 打家劫舍

https://leetcode-cn.com/problems/house-robber/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/57/



你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 2:

输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
*/
public class P198 {
    public static void main(String[] args) {
        int[] arrs = {1,2,3,1};
        System.out.println(new Solution().rob(arrs));
        int[] arrs2 = {9,2,3,9};
        System.out.println(new Solution().rob(arrs2));
    }

    static class Solution {
        /*
        动态规划
        1. 如果选择了抢劫上一个屋子，那么就不能抢劫当前的屋子，
            所以最大收益就是抢劫上一个屋子的收益

        2. 如果选择抢劫当前屋子，就不能抢劫上一个屋子，
            所以最大收益是到上一个屋子的上一个屋子为止的最大收益，加上当前屋子里有的钱

        所以，我们只要判断一下两个里面哪个大就行了，同时也是我们的递推式。
        另外我们可以做一点优化，本来我们是要用一个dp数组来保存之前的结果的。
        但实际上我们只需要上一次和上上次的结果，所以可以用两个变量就行了。
        */
        public int rob(int[] nums) {
            if (nums == null || nums.length == 1) return 0;
            if (nums.length == 1) return nums[0];
            if (nums.length == 2) return Math.max(nums[0], nums[1]);
            
            /* 使用dp数组保存之前的结果
            int[] res = new int[nums.length + 1]; // 到第i家, 偷或者不偷第i家的最大值
            res[0] = 0;
            res[1] = nums[0];
            for (int i = 2; i <= nums.length; i++) {
                res[i] = Math.max(res[i - 1], res[i - 2] + nums[i - 1]);
                System.out.println("   " + Arrays.toString(nums));
                System.out.println(Arrays.toString(res));
            }
            return res[nums.length];
            */

            // 我们只需要上一次和上上次的结果，所以可以用两个变量就行了
            int last = nums[0]; // 上次最大收益
            int max = Math.max(nums[0], nums[1]); // 当前最大收益
            for (int i = 2; i < nums.length; i++) {
                int tmp = max;
                // 当前的收益是两种选择最大的那个
                max = Math.max(last + nums[i], max);
                last = tmp;
            }
            return max;
        }
    }
}