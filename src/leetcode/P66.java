import java.util.Arrays;

// https://leetcode-cn.com/problems/plus-one/description/
public class P66 {
    public static void main(String[] args) {
        System.out.println("------P66------");
        
        Solution solution = new Solution();
        int[] s = {9, 9, 9};
        int[] s1 = {1, 2, 3};
        int[] s2 = {1, 9, 9};
        System.out.println(Arrays.toString(solution.plusOne(s)));
        System.out.println(Arrays.toString(solution.plusOne(s1)));
        System.out.println(Arrays.toString(solution.plusOne(s2)));

        System.out.println("------P66------");
    }
}
class Solution {
    public int[] plusOne(int[] digits) {
        boolean flag = false;   // [1, 9, 9]
        boolean needExpand = true;  // [9, 9, 9]
        for (int i = digits.length - 1; i >= 0; i--) {
            // carry
            flag = (digits[i] == 9) ? true : false;
            if (flag) {
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                needExpand = false;
                break;
            }
        }
        int[] results;
        if (needExpand) {
            results = new int[digits.length + 1];
            results[0] = 1;
            System.arraycopy(digits, 0, results, 1, digits.length);
        } else {
            results = new int[digits.length];
            System.arraycopy(digits, 0, results, 0, digits.length);
        }
        return results;
    }
}