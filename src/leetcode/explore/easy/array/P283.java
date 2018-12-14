import java.util.Arrays;

/*
283. 移动零

https://leetcode-cn.com/problems/move-zeroes/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/28/

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。
*/
public class P283 {
    public static void main(String[] args) {
        int[] arr = { 1, 0 };
        new Solution().moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }

    static class Solution {
        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length <= 1) return;

            int nonzero = 0; // 非0的位置(个数)
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    // 如果nums[i]!=0, 就将i位置的元素移到非0索引的位置
                    if (nonzero != i) {
                        /* 防止序列全非0, 造成不必要的赋值 */
                        nums[nonzero] = nums[i];
                    }
                    nonzero++;
                }
            }
            for (int i = nonzero; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }
}