import java.util.Arrays;

/*
55. 跳跃游戏

https://leetcode-cn.com/problems/jump-game/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/51/dynamic-programming/104/

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

示例 1:

输入: [2,3,1,1,4]
输出: true
解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
示例 2:

输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
*/
public class P55 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        //nums = new int[]{3,2,1,0,4};
        System.out.println(new Solution().canJump(nums));
        System.out.println(new Solution_dp().canJump(nums));
        System.out.println(new Solution_dp().canJump_2(nums));
    }

    static class Solution {
        /**
         * 想要知道能否到达index，那么只需要知道index之前的元素是不是有nums[i]+i >= index for i in range(index)
         * 
         * 局部的最优贪心策略，在全局看来也是最优的，因为 局部能够到达的最大范围也是全局能够到达的最大范围
         * @param nums
         * @return
         */
        public boolean canJump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return false;
            }

            int len = nums.length;
            int reach = 0; // 记录能够到达的最远的下标
            int i = 0;

            while (i < len && i <= reach) {
                reach = Math.max(reach, i + nums[i]); // 上界指针的位置（也就是当前位置+当前可以跳到的位置）
                System.out.printf("nums[%d]=%d, reach=%d\n", i, nums[i], reach);
                i++;
            }

            // 每走一步比较更新该值，到达最终位置前，如果当前下标大于reach，说明失败。如果能到达最后，说明成功。 
            return reach >= nums.length - 1;
        }
    }

    // 动态规划
    static class Solution_dp {

        public boolean canJump(int[] nums) {
            int[] dp = new int[nums.length]; // 表示达到i位置时剩余的步数
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(dp[i-1], nums[i - 1]) - 1; // 状态转移方程
                /*
                当前位置的剩余步数（dp值）和当前位置的跳力中的较大那个数决定了当前能到的最远距离
                而下一个位置的剩余步数（dp值）就等于当前的这个较大值减去1，因为需要花一个跳力到达下一个位置
                */
                System.out.println(Arrays.toString(dp));
                if (dp[i] < 0) {
                    return false;
                } 
            }
            return dp[nums.length - 1] >= 0;
        }

        public boolean canJump_2(int [] A) {
            if (A.length == 1) return true;
        
            int [] dp = new int[A.length]; // 假设位置i(0≤i≤A.length)能够跳跃的最大长度为dp[i]
            dp[0] = A[0];
            /*
            对于数组A = [2,3,1,1,4]， 则有： 
                i = 0, dp[0] = A[0] + 0 = 2 
                i = 1, if dp[i-1] = dp[0]≥≥ i then dp[1] = max{A[1]+1,dp[0]}=4max{A[1]+1,dp[0]}=4 else dp[1] = 0 
                i = 2, if dp[i-1] = dp[1] ≥≥ i then dp[2] = max{A[2]+2,dp[1]}max{A[2]+2,dp[1]} = 4 else dp[2] = 0
            */
            for (int i = 1; i < dp.length; ++i) {
                if (dp[i-1] >= i)
                    dp[i] = Math.max(A[i]+i, dp[i-1]);
                else
                    dp[i] = 0;
                System.out.println(Arrays.toString(dp));
            }
            return dp[dp.length-1] >= A.length-1;
        }
    }
}