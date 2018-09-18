import java.util.Arrays;

// https://leetcode-cn.com/problems/merge-sorted-array/description/
public class P88 {
    public static void main(String[] args) {
        System.out.println("------P88------");
        
        Solution solution = new Solution();
        // int[] n1 = {2,5,6,0,0,0};
        // int m = 3;
        // int[] n2 = {1,2,3};
        // int n = 3;
        int[] n1 = {0};
        int m = 0;
        int[] n2 = {1, 2, 3};
        int n = 1;
        solution.merge(n1, m, n2, n);
        System.out.println("result: " + Arrays.toString(n1));

        System.out.println("------P88------");
    }
}
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length == 0) {
            return;
        }
        if (nums2 == null || nums2.length == 0) {
            return;
        }
        if (m == 0) {
            System.out.println(Arrays.toString(nums1));
            System.out.println(Arrays.toString(nums2));
            System.out.println(m + " " + n);
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        if (n < 1) {
            return;
        }

        int[] temp = new int[m];
        System.arraycopy(nums1, 0, temp, 0, m);
        for (int i = 0, j = 0, k = 0; i < m + n; i++) {
            nums1[i] = temp[j] < nums2[k] ? temp[j++] : nums2[k++];
            System.out.println("i:" + i + " j:" + j + " k:" + k);
            System.out.println(Arrays.toString(nums1));
            if (j >= m) {
                System.arraycopy(nums2, k, nums1, i + 1, n - k);
                System.out.println("j >= m:" + Arrays.toString(nums1));
                return;
            }
            if (k >= n) {
                System.arraycopy(temp, j, nums1, i + 1, m - j);
                System.out.println("k >= n:" + Arrays.toString(nums1));
                return;
            }
        }
    }
}