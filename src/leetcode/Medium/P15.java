import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/*
15. 三数之和
给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]

https://leetcode-cn.com/problems/3sum/

*/

public class P15 {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        System.out.println(arr.length);
        System.out.println(new Solution().threeSum(arr));
    }
}

class Solution {
    // 先排序，然后左右夹逼，注意跳过重复的数
    // Time Complexity: O(n^2)，Space Complexity: O(1)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (len < 3) return result;
        Arrays.sort(nums);
        final int target = 0;

        // 以当前元素的下一个, 和最后一个为左右两个指针
        // 最后一个元素right用不着遍历
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            int left = i + 1; // 当前数的后一个
            int right = nums.length - 1; // 最后一个

            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    left++; // 小于target, 说明后边的两个数需要增大, left更小所以left增大
                    while (nums[left] == nums[left - 1] && left < right) left++; // 去重
                } else if (nums[i] + nums[left] + nums[right] > target) {
                    right--; // 跟上面同理
                    while (nums[right] == nums[right + 1] && left < right) right--; // 去重
                } else {
                    // 存入结果中, 并将left, right向中间移动, 
                    // 因为左侧边界值和右侧边界值已经判断过了, 而且数组是排好序的, 所以比更left更左或者比right更右的并不存在
                    // 所以都进行移动
                    // 准备进行下一次寻找剩下的两位
                    result.add(Arrays.asList(nums[i], nums[left++], nums[right--]));

                    // 去重
                    while (nums[left] == nums[left - 1] && left < right) left++;
                    while (nums[right] == nums[right + 1] && left < right) right--;
                }
            }
        }
        return result;
    }
}