/*
153. 寻找旋转排序数组中的最小值II
https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

注意数组中可能存在重复的元素。

示例 1：

输入: [1,3,5]
输出: 1
示例 2：

输入: [2,2,2,0,1]
输出: 0
说明：

这道题是 寻找旋转排序数组中的最小值 的延伸题目。
允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
*/
public class P154 {
    public static void main(String[] args) {
        //int[] nums = { 4, 5, 6, 7, 0, 1, 1, 2, 2 };
        //int[] nums = {0,1,3,4, 4, 4,5,6,7};
         int[] nums = {3,4,5,1,2};
         int[] nums1 = {1,3,5};
         int[] nums2 = {10,1,10,10,10};
         int[] nums3 = {10,10,10,1,10};
         System.out.println(new Solution().findMin2(nums));
         System.out.println(new Solution().findMin(nums));
         System.out.println(new Solution().findMin(nums1));
         System.out.println(new Solution().findMin(nums2));
         System.out.println(new Solution().findMin(nums3));
    }

    static class Solution {
        public int findMin2(int[] num) {
            //  这道题目在面试中不会让写完整的程序
            //  只需要知道最坏情况下 [1,1,1....,1] 里有一个0
            //  这种情况使得时间复杂度必须是 O(n)
            //  因此写一个for循环就好了。
            //  如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
            //  反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
            int min = num[0];
            for (int i = 1; i < num.length; i++) {
                if (num[i] < min)
                    min = num[i];
            }
            return min;
        }

        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int l = 0, r = nums.length - 1, mid;

            while (l + 1 < r) {
                // mid = l + (r - l) / 2;
                mid = l + r >>> 1;
                // l r 是相互逼近的
                
                // 通过判断属于rotation的左边或者右边来决定 往左或者往右搜索
                if (nums[mid] == nums[r]) {
                    // 如果mid==r, 说明可以移动右边界, 而最小的左边(递增序列)不需要动
                    r--;
                } else if (nums[mid] < nums[r]) {
                    // mid的右侧是递增的
                    r = mid;
                } else {
                    // 至少说明mid的左侧是递增的
                    // 大于等于第一个数说明在左边，往右搜索
                    l = mid;
                }
                //System.out.printf("l = %d, r = %d, mid = %d\n", l, r, mid);
            }
            return Math.min(nums[l], nums[r]);
        }
    }
}