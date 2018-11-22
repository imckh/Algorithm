/*
33. 搜索旋转排序数组

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
示例 2:

输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
*/
public class P33 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 3;
        System.out.println(new Solution().search(nums, target));
    }

    static class Solution {
        /*
        旋转一次, 所以肯定有一半及以上的序列仍然是具有递增性质的, 
        观察到如果中间的数比左面的数大的话, 那么左半部分序列是递增的, 否则右半部分就是递增的,
        那么我们就可以确定给定值是否在递增序列中, 从而决定取舍哪半边
        */
        public int search(int[] nums, int target) {
            int l = 0, r = nums.length - 1, mid;

            while (l <= r) {
                mid = l + r >>> 1;
                // l r 是相互逼近的
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] >= nums[l]) {
                    // 至少说明mid的左侧是递增的
                    if (nums[l] <= target && target < nums[mid]) // 如果target在 [l, mid) 区间, 更新r
                        r = mid - 1;
                    else
                        l = mid + 1;
                } else {
                    // mid的右侧是递增的
                    if (nums[mid] < target && target <= nums[r]) // 如果target在 (mid, r] 区间, 更新l
                        l = mid + 1;
                    else
                        r = mid - 1;
                }
            }
            return -1;
        }
    }
}