/*
16. 最接近的三数之和

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

https://leetcode-cn.com/problems/3sum-closest/
*/
import java.util.Arrays;

public class P16 {
    public static void main(String[] args) {
        //int[] arr = {-1, 0, 1, 2, -1, -4};
        int[] arr = {-1,2,1,-4};
        System.out.println(new Solution().threeSumClosest(arr, 1));
    }
}

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        int minGap = Integer.MAX_VALUE; // 与target的最小距离
        Arrays.sort(nums);

        // 跟三数和一样的道理
        
        // 分别遍历以每个数为第一个树, 让剩下的两个数使用双指针夹逼
        for (int i = 0; i < nums.length - 1; i++) {
            int left = i + 1; // 当前数的后一个
            int right = nums.length - 1; // // 最后一个

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int gap = Math.abs(sum - target); // 当前距离

                if (gap < minGap) {
                    // 找最小距离
                    result = sum;
                    minGap = gap;
                }
                // 当前的三数和小于target, 左侧向右靠近
                // 大于target同样道理
                // 相等说明存在三个数和target相等, 直接返回target
                if (sum < target) ++left;
                else if (sum > target) --right;
                else return target;
            }
        }

        return result;
    }
}