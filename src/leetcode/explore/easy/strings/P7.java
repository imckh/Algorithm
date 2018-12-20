/*
7. 整数反转

https://leetcode-cn.com/problems/reverse-integer/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/33/

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
*/
public class P7 {
    public static void main(String[] args) {
        
        int i = 2147483647;
        System.out.println(i);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new Solution().reverse(Integer.MAX_VALUE));

    }

    static class Solution {
        public int reverse(int x) {
            long r = 0;
            
            for(; x != 0; x /= 10) {
                r = r * 10 + x % 10;
            }
            
            // 当它的逆序整型数溢出的话，那么就返回 0
            return (r < Integer.MAX_VALUE) && (r > Integer.MIN_VALUE) ? (int) r : 0;
        }

        // 思想一样, 为什么这个执行用时：21 ms
        // 上边的69ms
        public int reverse2(int x) {
            long result = 0L;
    
            while (x != 0) {
                int r = x % 10;
                x = x / 10;
                result = result * 10 + r;
            }
    
            if (result >= Integer.MAX_VALUE || result <= Integer.MIN_VALUE) {
                return 0;
            }
    
            return (int)result;
        }
    }
}