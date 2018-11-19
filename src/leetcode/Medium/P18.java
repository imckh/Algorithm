/*
18. 四数之和
https://leetcode-cn.com/problems/4sum/
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：

答案中不可以包含重复的四元组。

示例：

给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/

import java.util.*;

public class P18 {
    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        var target = 0;
        System.out.println(new Solution().fourSum(nums, target));
        System.out.println(new Solution().fourSum2(nums, target));
    }
}

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (len < 4) return result;
        
        Arrays.sort(nums);

        int max = nums[len - 1];
        if (4 * max < target) return result;

        for (int k = 0; k < nums.length - 3; k++) {
            if (nums[k] * 4 > target) break;
            if (nums[k] + 3 * max < target) {
                while (nums[k] == nums[++k] && k < len - 3) ;
                continue;
            }
            //if (k > 0 && nums[k] == nums[k - 1]) continue;
            
            // 这里完全就是3sum, 
            // 不同的是i不是从0开始的, 是从k的下一个
            // nums[i] + nums[k] + nums[left] + nums[right] < target
            for (int i = k + 1; i < nums.length - 2; i++) {
                int subSum = nums[i] + nums[k];
                if (nums[i] + nums[k] * 3 > target) break;
                if (subSum + 2 * max < target) {
                    while (nums[i] == nums[++i] && i < len - 2) ;
                    continue;
                }
                //if (i > k + 1 && nums[i] == nums[i - 1]) continue; // 去重
                int left = i + 1; // 当前数的后一个
                int right = nums.length - 1; // 最后一个
    
                // 这里可以理解为2sum
                while (left < right) {
                    if (nums[i] + nums[k] + nums[left] + nums[right] < target) {
                        left++; // 小于target, 说明后边的两个数需要增大, left更小所以left增大
                        while (nums[left] == nums[left - 1] && left < right) left++; // 去重
                    } else if (nums[i] + nums[k] + nums[left] + nums[right] > target) {
                        right--; // 跟上面同理
                        while (nums[right] == nums[right + 1] && left < right) right--; // 去重
                    } else {
                        // 存入结果中, 并将left, right向中间移动, 
                        // 因为左侧边界值和右侧边界值已经判断过了, 而且数组是排好序的, 所以比更left更左或者比right更右的并不存在
                        // 所以都进行移动
                        // 准备进行下一次寻找剩下的两位
                        result.add(Arrays.asList(nums[k], nums[i], nums[left++], nums[right--]));
    
                        // 去重
                        while (nums[left] == nums[left - 1] && left < right) left++;
                        while (nums[right] == nums[right + 1] && left < right) right--;
                    }
                }
            }
        }

        return result;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        if (len < 4) return Collections.emptyList();
        int max = nums[len - 1];
        if (4 * max < target) return Collections.emptyList();
        return kSum(nums, 0, 4, target);
     }

    // 从 Two Sum/3Sum 到现在的 4Sum, 其实都是把高阶降为低阶, 
    // 那么我们就可以写出 kSum 的函数来对其进行降阶处理, 降到 2Sum 后那么我们就可以对其进行最后的判断了
    public List<List<Integer>> kSum(int[] nums, int start, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();

        if (k == 2) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> twosum = new LinkedList<>();
                    twosum.add(nums[left]);
                    twosum.add(nums[right]);
                    res.add(twosum);
                    // 去重
                    while (nums[left] == nums[++left] && left < right);
                    while (nums[right] == nums[--right] && left < right);
                } else if (sum < target) left++;
                  else right--;
            }
        } else {
            int i = start, end = nums.length - (k - 1), max = nums[nums.length - 1];
            while (i < end) {
                // 边界值及去重
                if (nums[i] * k > target) return res;
                if (nums[i] + (k - 1) * max < target) {
                    while (nums[i] == nums[++i] && i < end);
                    continue;
                }
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
                for (List<Integer> t : temp) {
                    t.add(0, nums[i]);
                }
                res.addAll(temp);
                while (nums[i] == nums[++i] && i < end);
            }
        }

        return res;
    }
}