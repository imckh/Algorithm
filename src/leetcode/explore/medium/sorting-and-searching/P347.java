import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
347. 前K个高频元素

https://leetcode-cn.com/problems/top-k-frequent-elements/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/50/sorting-and-searching/97/

给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
说明：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
*/
public class P347 {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5 };
        System.out.println(new Solution().topKFrequent(nums, 2));
    }

    static class Solution {
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            // 使用了堆的结构来进行排序
            PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
                    (e1, e2) -> e1.getValue() - e2.getValue());
            // 上面的lambda等同于下面代码
            // PriorityQueue<Map.Entry<Integer, Integer>> queue = new
            // PriorityQueue<Map.Entry<Integer, Integer>>(
            // new Comparator<Map.Entry<Integer, Integer>>() {
            // public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer,
            // Integer> e2) {
            // return e1.getValue() - e2.getValue();
            // }
            // });

            // 统计数字出现次数
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], 1);
                } else {
                    map.put(nums[i], map.get(nums[i]) + 1);
                }
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (heap.size() < k) {
                    // 堆中 只放k个元素
                    heap.offer(entry);
                } else if (heap.peek().getValue() < entry.getValue()) {
                    // 如果个数比堆中最大的还大
                    heap.poll();
                    heap.offer(entry);
                }
            }

            List<Integer> ans = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : heap) {
                ans.add(entry.getKey());
            }
            return ans;
        }
    }

    static class Solution2 {
        public List<Integer> topKFrequent(int[] nums, int k) {
            if (nums == null || nums.length == 0)
                return null;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i : nums) { // 找到最大最小数
                if (i < min)
                    min = i;
                if (i > max)
                    max = i;
            }

            int[] count = new int[max - min + 1]; // 各个数字出现的频数

            for (int i : nums) {
                count[i - min]++;
            }

            int max_count = Integer.MIN_VALUE;

            for (int i : count) {
                if (i > max_count)
                    max_count = i;
            }

            int[] aid = new int[max_count + 1]; // 各个频数所包含的key数

            for (int i : count) {
                if (i > 0) {
                    aid[i]++;
                }
            }

            int min_count = 1;
            int curr = 0;
            for (int i = aid.length - 1; i >= 1; i--) {
                if (aid[i] > 0) {
                    curr += aid[i];
                    if (curr >= k) {
                        min_count = i;
                        break;
                    }
                }
            }

            List<Integer> ans = new ArrayList<Integer>(k);
            for (int i = 0; i < count.length; i++) {
                if (count[i] >= min_count) {
                    ans.add(i + min);
                }
            }
            return ans;
        }
    }
}