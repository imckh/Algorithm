/*
136. 只出现一次的数字

https://leetcode-cn.com/problems/single-number/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/25/

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4
*/

public class P136 {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 3 };
        System.out.println(new Solution().singleNumber(arr));
    }

    static class Solution {
        /**
         * 1. 使用map记录出现次数, 参考P217 
         * 
         * 2. 使用异或
         * 
         * 因为A XOR A = 0, 且XOR运算是可交换的, 于是, 对于实例{2,1,4,5,2,4,1}就会有这样的结果:
         * (2^1^4^5^2^4^1) => ((2^2)^(1^1)^(4^4)^(5)) => (0^0^0^5) => 5
         * 就把只出现了一次的元素(其余元素均出现两次)给找出来了
         * 
         * 相应的可以扩展为奇数偶数这样的题目
         * 
         * @param nums
         * @return
         */
        public int singleNumber(int[] nums) {
            // 一个非空整数数组
            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                result ^= nums[i];
            }
            return result;
        }
    }
}