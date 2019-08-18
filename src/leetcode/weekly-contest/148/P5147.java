import java.util.*;

public class P5147 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new Solution().movesToMakeZigzag(nums));
    }

    static class Solution {
        public int movesToMakeZigzag(int[] nums) {
            if (nums == null || nums.length == 1) return 0;
            int result = 0;

            // 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
            

            return result;
        }
    }
}