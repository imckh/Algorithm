import java.util.*;

/*
350. 两个数组的交集 II

https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/26/

给定两个数组，编写一个函数来计算它们的交集。

示例 1:

输入: nums1 = [1,2,2,1], nums2 = [2,2]
输出: [2,2]
示例 2:

输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出: [4,9]
说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
我们可以不考虑输出结果的顺序。
进阶:

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
*/

public class P350 {
    public static void main(String[] args) {
        //int[] nums1 = {4,9,5}, nums2 = {9,4,9,8,4};
        //int[] nums1 = {2, 2}, nums2 = {1, 2, 2, 1};

        int[] nums1 = new int[100000];
        int[] nums2 = new int[100000];

        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            nums1[i] = random.nextInt();
            nums2[i] = random.nextInt();
        }
        long startt = System.currentTimeMillis();
        System.out.println(Arrays.toString(new Solution().intersect(nums1, nums2)));
        long endt = System.currentTimeMillis();
        System.out.println(endt - startt + "ms");

        startt = System.currentTimeMillis();
        // 其实还是map比较快, 虽然上边的方法是LeetCode第一, 但是它是直接遍历查找的
        System.out.println(Arrays.toString(new Solution().intersect_map_list(nums1, nums2)));
        endt = System.currentTimeMillis();
        System.out.println(endt - startt + "ms");
    }

    static class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            if(nums1.length == 0 || nums2.length == 0)   {
                return new int[0];
            }
            int[] ret1 = new int[Math.max(nums1.length, nums2.length)];
            int len1 = 0;
            boolean[] bl1 = new boolean[ret1.length];
            for (int i=0; i < nums2.length; i++) {
                int start = 0;
                while( (start = find(nums1, nums2[i], start)) != -1 ) {
                    if(bl1[start] == false) {
                        ret1[len1++] = nums2[i];
                        bl1[start] = true;
                        break;
                    }
                    start++;
                }
            }
            return Arrays.copyOfRange(ret1, 0, len1);
        }
    
        private int find(int[] nums, int val, int start) {
            for (; start < nums.length; start ++) {
                if (nums[start] == val) {
                    return start;
                }
            }
            return -1;
        }

        public int[] intersect_map_list(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
                return new int[0];
            }
            Map<Integer, Integer> map = new HashMap<>();
            List<Integer> results = new ArrayList<Integer>();

            for (int i = 0; i < nums1.length; i++) {
                map.put(nums1[i], map.containsKey(nums1[i]) ? map.get(nums1[i]) + 1 : 1);
            }
            for (int i = 0; i < nums2.length; i++) {
                if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                    results.add(nums2[i]);
                    map.put(nums2[i], map.get(nums2[i]) - 1); // 重复的数字
                }
            }
            //int[] r = results.toArray(new int[results.size()]);
            // Integer[] r = results.stream().toArray(Integer[]::new);

            int result[] = new int[results.size()];
            for(int i = 0; i < results.size(); ++i)
                result[i] = results.get(i);

            return result;
        }
    }
}