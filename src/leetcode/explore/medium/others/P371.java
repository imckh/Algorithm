/*
371. 两整数之和

https://leetcode-cn.com/problems/sum-of-two-integers/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/54/others/119/

不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。

示例 1:

输入: a = 1, b = 2
输出: 3
示例 2:

输入: a = -2, b = 3
输出: 1
*/
public class P371 {
    public static void main(String[] args) {
        System.out.println(new Solution().getSum(7, 99));
    }

    static class Solution {
        /*
        https://blog.csdn.net/liyuanbhu/article/details/51803974
        两个二进制整数 a 和 b，
        如果相加的过程中如果没有进位，那么 a+b=a⊗b，这里 ⊗ 表示异或。
        那么 a+b 的进位为多少呢，只有 1+1 时才会出现进位。
        所以 a+b 的进位可以表示为 2×(a & b)，这里 & 表示两个数字的按位与运算。
        之所以要乘以 2，是因为要向上进一位。
        */
        public int getSum(int a, int b) {
            if (a == 0) return b;
            if (b == 0) return a;

            // 不断的异或和与，
            // 异或的结果是不含进位的加，
            // 与得到的是每一位的进位，
            // 让结果和进位继续异或（无进位加），直到进位为0.

            int c = 0;
            while (b != 0) {
                System.out.println("a = " + Integer.toBinaryString(a));
                System.out.println("b = " + Integer.toBinaryString(b));
                c = a ^ b;
                System.out.println("a ^ b = " + Integer.toBinaryString(c));
                b = a & b;
                System.out.println("a & b = " + Integer.toBinaryString(b));
                b <<= 1;
                System.out.println("b << 1 = " + Integer.toBinaryString(b));
                a = c;
                System.out.println("======================");
            }
            return a;
        }
    }
}