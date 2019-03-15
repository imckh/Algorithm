import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
239. 滑动窗口最大值

https://leetcode-cn.com/problems/sliding-window-maximum/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/132/

给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。

返回滑动窗口最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
注意：

你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。

进阶：

你能在线性时间复杂度内解决此题吗？
*/

public class P239 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(nums, 3)));
    }

    /*
    假设窗口大小为w，

    1、简单的方法：
        遍历数组，从数组第w-1位开始，每次移动一位，并计算窗口w的最大值。

        时间复杂度：

            计算窗口的最大值需O(w)，移动n-w+1次，时间复杂度为O(nw)

    2、最大堆方法:
        构建一个窗口w大小的最大堆，每次从堆中取出窗口的最大值，随着窗口往右滑动，需要将堆中不属于窗口的堆顶元素删除。

        时间复杂度：// java如果用最大堆的可以用优先队列, priorityqueue底层是堆结构

            正常情况下，往堆中插入数据为O(lgw)，如果数组有序，则为O(lgn),
            因为滑动过程中没有元素从堆中被删除，滑动n-w+1次，复杂度为O(nlgn).

    3、双队列方法：
        最大堆解法在堆中保存有冗余的元素，比如原来堆中元素为[10 5 3]，
        新的元素为11，则此时堆中会保存有[11 5 3]。
        其实此时可以清空整个队列，然后再将11加入到队列即可，即只在队列中保持[11]。
        使用双向队列可以满足要求，滑动窗口的最大值总是保存在队列首部，队列里面的数据总是从大到小排列。
        当遇到比当前滑动窗口最大值更大的值时，则将队列清空，并将新的最大值插入到队列中。
        如果遇到的值比当前最大值小，则直接插入到队列尾部。
        每次移动的时候需要判断当前的最大值是否在有效范围，
        如果不在，则需要将其从队列中删除。由于每个元素最多进队和出队各一次，因此该算法时间复杂度为O(N)。
    */
    static class Solution {
        // 双队列方法
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1) {
                return null;
            }
            int[] result = new int[nums.length - k + 1];
            Deque<Integer> q = new LinkedList<>(); // 其实可以换成一个数组

            // [0 -- k]之间
            for (int i = 0; i < k; i++) {
                while (!q.isEmpty() && nums[i] > nums[q.getLast()]) {
                    // 当遇到比当前滑动窗口最大值更大的值时，则将队列清空，并将新的最大值插入到队列中
                    q.pollLast();
                }
                q.addLast(i);
            }
            // [k 到 数组结尾]
            for (int i = k; i < nums.length; i++) {
                result[i-k] = nums[q.getFirst()];

                // 保证队列一直是升序的
                while(!q.isEmpty() && nums[i] >= nums[q.getLast()]) {
                    q.pollLast();
                }
                // 判断当前的最大值是否在有效范围
                while(!q.isEmpty() && q.getFirst() <= i - k) {
                    q.pollFirst();
                }
                q.addLast(i);
            }
            result[nums.length - k] = nums[q.getFirst()];
            return result;
        }
    }
}