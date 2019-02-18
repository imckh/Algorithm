import java.util.PriorityQueue;

/*
215. 数组中的第K个最大元素

https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/50/sorting-and-searching/98/

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
*/

public class P215 {
    public static void main(String[] args) {
        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        int k = 4;
        System.out.println(new Solution().findKthLargest(nums, k));
        System.out.println(new Solution_2().findKthLargest(nums, k));
    }

    // 堆排序 时间复杂度 O(NlogK)，空间复杂度 O(K)
    static class Solution {
        public int findKthLargest(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k > nums.length) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }

            // 优先队列
            // 堆
            PriorityQueue<Integer> heap = new PriorityQueue<>(k);

            for (int i = 0; i < nums.length; i++) {
                if (heap.size() < k) {
                    heap.offer(nums[i]);
                } else if (heap.peek() < nums[i]) {
                    // 比堆中最大的还大
                    heap.poll();
                    heap.offer(nums[i]);
                }
            }
            return heap.peek();
        }
    }

    // 快速选择 时间复杂度 O(N)，空间复杂度 O(1)
    // 快排的思想
    /*
     * 1. 每次选取一个 pivot，将大于 pivot 的数放在 pivot 左边，将小于 pivot 的数放在 pivot 右边。
     *  2. 如果 pivot 所在的位置小于 K ，则说明数组中的第 K 个最大元素位于 pivot 的右边。此时，假设 piv
     * t 的位置为 which_max，which_max 是几就代表 pivot 是数组中的第几个最大元素。这时候，我们再从 p
     * vot 右边的数据中找到第 (K-which_max) 个最大元素即可
     *  3. 如果 pivot 所在的位置大于 K ，则说明数组中的第 K 个最大元素位于 pivot 的左边。这时候，pivot
     * 左边的数据全部大于 pivot，我们继续从 pivot 左边的数据中找第 K 个最大元素即可。
     * 
     */
    static class Solution_2 {
        public int findKthLargest(int[] nums, int k) {
            return quick_sort2(nums, 0, nums.length - 1, k);
        }

        int quick_sort(int[] nums, int left, int right, int k) {
            int i = left;
            int j = right;
            int pivot = nums[right];
            int len = right - left + 1;

            if (left < right) {
                // 从大到小对数组进行快排
                while(i < j) {
                    while(i < j && nums[i] >= pivot) { // 从左往右找第一个比 pivot 小的数 
                        i++;
                    }
                    if (i < j) {
                        nums[j--] = nums[i];
                    }

                    while(i < j && nums[j] <= pivot) {// 从右往左找第一个比 pivot 大的数
                        j--;
                    }
                    if (i < j) {
                        nums[i++] = nums[j];
                    }
                }
                
                nums[i] = pivot; // 此时 i == j

                // pivot 此时位于索引 i 处，i - left + 1 表示 pivot 是第几大的数
                int which_max = i - left + 1;
                if (which_max == k) {// pivot 正好是第 k 大的数
                    return pivot;
                } else if(which_max < k) {
                    // 第 k 大的数在 pivot 右边，问题转化为找右边数组第 (k - which_max) 大的元素
                    // 比如 pivot 是第四大的数，要找第五大的数，则继续找右边数组第一大的数即可
                    return quick_sort(nums, i + 1, right, k - which_max);
                } else {
                    // 第 k 大的数在 pivot 左边，问题转化为找左边数组第 k 大的元素
                    // 比如 pivot 是第三大的数，要找第二大的数，则继续找左边数组第二大的数即可
                    return quick_sort(nums, left, i - 1, k);
                }
            } else {
                return pivot;
            }
        }
        // 两种不同的划分方式
        int quick_sort2(int[] nums, int left, int right, int k) {
            int i = left;
            int j = left;
            int pivot = nums[right];
            int len = right - left + 1;

            if (left < right) {
                // 从大到小对数组进行快排
                for (; j < right; j++) {
                    if (nums[j] > pivot) {
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        i++;
                    }
                }

                nums[j] = nums[i];
                nums[i] = pivot;
                
                nums[i] = pivot; // 此时 i == j

                // pivot 此时位于索引 i 处，i - left + 1 表示 pivot 是第几大的数
                int which_max = i - left + 1;
                if (which_max == k) {// pivot 正好是第 k 大的数
                    return pivot;
                } else if(which_max < k) {
                    // 第 k 大的数在 pivot 右边，问题转化为找右边数组第 (k - which_max) 大的元素
                    // 比如 pivot 是第四大的数，要找第五大的数，则继续找右边数组第一大的数即可
                    return quick_sort(nums, i + 1, right, k - which_max);
                } else {
                    // 第 k 大的数在 pivot 左边，问题转化为找左边数组第 k 大的元素
                    // 比如 pivot 是第三大的数，要找第二大的数，则继续找左边数组第二大的数即可
                    return quick_sort(nums, left, i - 1, k);
                }
            } else {
                return pivot;
            }
        }
    }
}