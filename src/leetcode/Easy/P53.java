// https://leetcode-cn.com/problems/maximum-subarray/description/
public class P53 {
    public static void main(String[] args) {
        int[] arrs = {-2,1,-3,4,-1,2,1,-5,4};
        int[] arrs2 = {-2,-1,-3,-4,-1,-2,-1,-5,-4};
        int[] arrs3 = {2,1,3,4,1,2,1,5,4};
        int r = new Solution().maxSubArray3(arrs2);
        System.out.println(r);
    }
}
class Solution {
    // Prefix Sum
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // max is the max array till 'i'.
            max = Math.max(max, sum);
            // if sum < 0, then the next sum start from 0
            // positive is bigger.
            sum = Math.max(sum, 0);
            
            /* faster than this 
            if(sum < 0) {
        		sum = 0;
        	}
             */
        }

        return max;
    }

    // Version 2: Prefix Sum
    public int maxSubArray2(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }
        
        int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }

        return max;
    }

    public int maxSubArray3(int[] A) {
        // Dynamic programming，DP
        if (A == null || A.length == 0){
            return 0;
        }

        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}