/*
190. 颠倒二进制位

https://leetcode-cn.com/problems/reverse-bits/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/66/


颠倒给定的 32 位无符号整数的二进制位。

示例 1：

输入: 00000010100101000001111010011100
输出: 00111001011110000010100101000000
解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
示例 2：

输入：11111111111111111111111111111101
输出：10111111111111111111111111111111
解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
      因此返回 3221225471 其二进制表示形式为 10101111110010110010011101101001。
 

提示：

请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 

进阶:
如果多次调用这个函数，你将如何优化你的算法？
*/
public class P190 {
    public static void main(String[] args) {
        int a = -3;
        System.out.println(new Solution().reverseBits(a));
        System.out.println(new Solution().reverseBits2(a));
    }

    static public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int result = 0;
            for (int i = 0; i < 32; i++) {
                // n >> i & 1 n二进制的倒数第i位
                // 然后将其移动到结果整数二进制对应的位置, 使用或运算填充
                result |= (n >> i & 1) << (31 - i); 
            }

            return result;
        }

        /*
        分而治之的思想，先将高16位与低16位交换，
        再同时交换每16位中的高低两个8位，
        然后基本类似地二分做下去，
        直到最后是每两位之间交换高低两个一位。
        可见，解法二要比解法一更高效，解法一是 O( log2(sizeof(int)) )

        这个方法不对啊, 提交上去答案错误
        */
        @Deprecated
        public int reverseBits2(int n) {
            n = (n >> 16) | (n << 16);
            n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);//注意优先级 
            n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
            n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
            n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
            return n;
        }
    }
}