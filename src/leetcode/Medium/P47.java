/*
47. 全排列 II

https://leetcode-cn.com/problems/permutations-ii/

给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

*/
import java.util.*;
public class P47 {
    public static void main(String[] args) {
        int[] nums = {1, 1};
        System.out.println(new Solution().permute(nums));
    }

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            // 使用深度优先搜索算法
            // 使用 visited 数组记录某个数是否被放到 permutation 里了
            List<List<Integer>> results = new ArrayList<>();
            if (nums == null) {
                return results;
            }
            
            Arrays.sort(nums); // 排序这样所有重复的数
            
            dfs(nums, new boolean[nums.length], new ArrayList<>(), results);
            return results;
        }

        /**
         * 深度优先遍历
         * 
         * @param nums 数组
         * @param visited 到现在为止的数组是否被放进了permutation中
         * @param permutation 当前序列
         * @param results 最终结果
         */
        private void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> results) {
            if (nums.length == permutation.size()) {
                // 递归的终点: permutation的长度与原始数组相同
                results.add(new ArrayList<>(permutation));
                return;
            }
            
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    // 当前元素已经访问过了
                    continue;
                }

                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                    // 跳过会造成重复的情况
                    // 因为已经排好序了, 相同的数肯定是挨着的, 如果上一个数跟当前的一样且没有被访问过, 跳过这次排列
                    // 注意 `!visited[i - 1]` , 如果相同元素已经访问过, 说明是从递归里过来的 而不是这个for循环
                    continue;
                }

                // 没有访问过的, 依次加入permutation序列, 并进行下一次递归调用
                permutation.add(nums[i]);
                visited[i] = true;
                dfs(nums, visited, permutation, results);
                // 需要注意为了for循环的下一次做状态的恢复
                visited[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }
    }
}