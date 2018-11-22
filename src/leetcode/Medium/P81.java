/*
81. 搜索旋转排序数组II

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。

编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

示例 1:

输入: nums = [2,5,6,0,0,1,2], target = 0
输出: true
示例 2:

输入: nums = [2,5,6,0,0,1,2], target = 3
输出: false
进阶:

这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
*/
public class P81 {
    public static void main(String[] args) {
        int[] nums = {1,3,1,1,1};
        int target = 3;
        System.out.println(new Solution().search(nums, target));
    }

    static class Solution {
    // 这个问题在面试中不会让实现完整程序
    // 只需要举出能够最坏情况的数据是 [1,1,1,1... 1] 里有一个0即可。
    // 在这种情况下是无法使用二分法的，复杂度是O(n)
    // 因此写个for循环最坏也是O(n)，那就写个for循环就好了
    //  如果你觉得，不是每个情况都是最坏情况，你想用二分法解决不是最坏情况的情况，那你就写一个二分吧。
    //  反正面试考的不是你在这个题上会不会用二分法。这个题的考点是你想不想得到最坏情况。
    public boolean search1(int[] A, int target) {
        for (int i = 0; i < A.length; i ++) {
            if (A[i] == target) {
                return true;
            }
        }
        return false;
    }

        /*
        旋转一次, 所以肯定有一半及以上的序列仍然是具有递增性质的, 
        观察到如果中间的数比左面的数大的话, 那么左半部分序列是递增的, 否则右半部分就是递增的,
        那么可以确定给定值是否在递增序列中, 从而决定取舍哪半边
        */
        public boolean search(int[] nums, int target) {
            int l = 0, r = nums.length - 1, mid;

            while (l <= r) {
                mid = l + r >>> 1;
                // l r 是相互逼近的
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[mid] > nums[l]) {
                    // 至少说明mid的左侧是递增的
                    if (nums[l] <= target && target < nums[mid]) // 如果target在 [l, mid) 区间, 更新r
                        r = mid - 1;
                    else
                        l = mid + 1;
                } else if (nums[mid] < nums[l]){
                    // mid的右侧是递增的
                    if (nums[mid] < target && target <= nums[r]) // 如果target在 (mid, r] 区间, 更新l
                        l = mid + 1;
                    else
                        r = mid - 1;
                } else {
                    // 如果nums[mid] == nums[l], 移动l到下一个位置
                    // 所以最坏情况是O(N)
                    l++;
                }
            }
            return false;
        }
    }
}