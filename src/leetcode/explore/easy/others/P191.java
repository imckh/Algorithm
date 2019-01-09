/*
191. 位1的个数

https://leetcode-cn.com/problems/number-of-1-bits/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/64/


编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。

示例 1：

输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
示例 2：

输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
示例 3：

输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 

提示：

请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 

进阶:
如果多次调用这个函数，你将如何优化你的算法？
*/
public class P191 {
    public static void main(String[] args) {
        int a = 1111103;
        System.out.println(new Solution().hammingWeight(a));
        System.out.println(new Solution().hammingWeight2(a));
        System.out.println(new Solution().hammingWeight3(a));

    }

    static public class Solution {
        /*
         * 在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论
         * 整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
         *  在 Java 中，编译器使用二进制补码记法来表示有符号整数。 因此，在上面的 `11
         * 11111111111111111111111111101` 输入表示有符号整数 -3
         */
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            if (n == 0) {
                return 0;
            }

            int count = 1;
            while ((n & (n - 1)) != 0) {
                // 这个方法没看懂啊
                // https://stackoverflow.com/questions/15370250/counting-number-of-bits-how-does-this-line-work-n-nn-1
                n &= n - 1;
                count++;
            }
            return count;
        }

        /*
         * 位运算。数字分别和1,2,4,8……2^31做与运算，如果结果不是0说明对应位置上是1。统计个数即可。
         */
        public int hammingWeight2(int n) {
            int count = 0;
            /*
            n == 3 == 0011
            0011 分别于下边与运算
            0001 // 0&0=0;   0&1=0;    1&0=0;     1&1=1
            0010 当与运算是0时,说明该位不是1
            0100
            */
            for (int i = 0; i < 32; i++) {
                if ((n & (1 << i)) != 0) // `1 << i` 1左移i位
                    count++;
            }

            return count;
        }

        public int hammingWeight3(int n) {
            int sum = 0;
            for (int i = 0; i < 32; i++)
                sum += (n >> i & 1); // n右移i位, 和 1 与运算 // 0&0=0;   0&1=0;    1&0=0;     1&1=1
            return sum;
        }
    }
}