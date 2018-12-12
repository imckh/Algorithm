import java.util.Arrays;

/*
189. 旋转数组

https://leetcode-cn.com/problems/rotate-array/

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释: 
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
说明:

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
要求使用空间复杂度为 O(1) 的原地算法。
*/

public class P189 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        new Solution().rotate(nums, 10);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {1,2,3,4,5,6,7};
        new Solution2().rotate(nums2, 10);
        System.out.println(Arrays.toString(nums2));
    }

    static class Solution {
        public void rotate(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k % nums.length == 0) return;
            int len = nums.length;
            k %= len;
            int[] nums1 = new int[k];

            System.arraycopy(nums, len - k, nums1, 0, k);
            System.arraycopy(nums, 0, nums, k, len - k);
            System.arraycopy(nums1, 0, nums, 0, k);

        }
    }

    static public class Solution2 {
        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++; end--;
            }
        }
        // 反转的方法
        /*
        1,2,3,4,5,6,7 | k=3
        reverse[0, 7-3-1=3]     4,3,2,1,| 5,6,7
        reverse[7-3=4, 7-1=6]   4,3,2,1,| 7,6,5
        reverse[0, 7-1=6]       5,6,7,1,2,3,4
        */
        public void rotate(int[] nums, int k) {
            if (nums.length == 0) {
                return;
            }
            
            k = k % nums.length;
            reverse(nums, 0, nums.length - k - 1);
            reverse(nums, nums.length - k, nums.length - 1);
            reverse(nums, 0, nums.length - 1);
        }
    }
}