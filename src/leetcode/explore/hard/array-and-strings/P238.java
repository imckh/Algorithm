import java.util.Arrays;

/*
238. 除自身以外数组的乘积

https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/123/
https://leetcode-cn.com/problems/product-of-array-except-self/

给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

示例:

输入: [1,2,3,4]
输出: [24,12,8,6]
说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
*/
public class P238 {
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 4 };
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(new Solution().productExceptSelf(nums)));
        System.out.println(Arrays.toString(new Solution().productExceptSelf_simple(nums)));
    }

    static class Solution {
        /*
         * 
         * 1, 2, 3, 4, 5 除3以外数组的乘积 = 3前边的数的乘积 * 3后边的数组的乘积
         * 
         * 可以依次算出从左到右的数组的乘积和从右到左的乘积 在分别计算i位置的结果
         * 
         */
        public int[] productExceptSelf(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }
            if (nums.length == 1) {
                return new int[] { 1 };
            }

            // L数组可以简化, 因为最终res数组的结算方向和L数组计算的方向是一致的
            int[] L = new int[nums.length];
            L[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                L[i] = nums[i] * L[i - 1];
            }

            int[] R = new int[nums.length];
            R[R.length - 1] = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= 0; i--) {
                R[i] = nums[i] * R[i + 1];
            }

            int[] res = new int[nums.length];
            res[0] = R[1];
            res[nums.length - 1] = L[nums.length - 2];
            for (int i = 1; i < nums.length - 1; i++) {
                res[i] = L[i - 1] * R[i + 1];
            }

            return res;
        }

        // 简化数组 只用res一个数组
        public int[] productExceptSelf_simple(int[] nums) {
            if (nums == null || nums.length == 0) {
                return nums;
            }
            if (nums.length == 1) {
                return new int[] { 1 };
            }

            int n = nums.length;
            int[] res = new int[n];
            Arrays.fill(res, 1);
            int left = 1;
            for (int i = 1; i < n; i++) {
                left *= nums[i - 1];
                res[i] *= left;
            }
            int right = 1;
            for (int i = n - 2; i >= 0; i--) {
                right *= nums[i + 1];
                res[i] *= right;
            }
            return res;
        }
    }
}