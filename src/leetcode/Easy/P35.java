/**
 * https://leetcode-cn.com/problems/search-insert-position/description/
 * @author CKH
 * @date 2018/8/6 21:20
 */
public class P35 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a = {1, 2, 3, 4, 5, 6, 8};

        System.out.println(s.searchInsert(a, -1));
        System.out.println(s.searchInsert(a, 1));
        System.out.println(s.searchInsert(a, 2));
        System.out.println(s.searchInsert(a, 3));
        System.out.println(s.searchInsert(a, 4));
        System.out.println(s.searchInsert(a, 5));
        System.out.println(s.searchInsert(a, 6));
        System.out.println(s.searchInsert(a, 7));
        System.out.println(s.searchInsert(a, 8));
    }


    static class Solution {
        public int searchInsert(int[] nums, int target) {
            int l = nums.length - 1;
            int middle = l / 2;
            int i = 0;
            while (i <= l) {
                if (target < nums[middle]) {
                    l = middle - 1;
                } else if (target > nums[middle]) {
                    i = middle + 1;
                } else {
                    i = middle;
                    break;
                }

                middle = (i + l) / 2;
            }

            return i;
        }
    }
}

