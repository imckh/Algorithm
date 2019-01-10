/*
461. 汉明距离

https://leetcode-cn.com/problems/hamming-distance/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/65/

两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

给出两个整数 x 和 y，计算它们之间的汉明距离。

注意：
0 ≤ x, y < 231.

示例:

输入: x = 1, y = 4

输出: 2

解释:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

上面的箭头指出了对应二进制位不同的位置。
*/
public class P461 {
    public static void main(String[] args) {
        int x = 86746372, y = 4312421;
        System.out.println(new Solution().hammingDistance(x, y));
        System.out.println(new Solution().hammingDistance1(x, y));

    }

    static class Solution {
        public int hammingDistance(int x, int y) {
            if (x == y) {
                return 0;
            }

            int count = 0;
            // ^ 异或: 如果相对应位值相同，则结果为0，否则为1
            for (int c = x ^ y; c != 0; c >>>= 1) { // >>> 	按位右移补零操作符。左操作数的值按右操作数指定的位数右移，移动得到的空位以零填充。
                // 右移之后只比较最右的那一位
                count += c & 1; // & 如果相对应位都是1，则结果为1，否则为0
            }
            return count;
        }

        public int hammingDistance1(int x, int y) {
            int Distance=0;
            
            while ( x != 0 || y != 0 ) {
                if ( x % 2 != y % 2 ) {
                    Distance ++;
                }
                x /= 2;
                y /= 2;
            }
            return Distance;
        }
        public int hammingDistance2(int x, int y) {
            int res = x ^ y;
            int cnt = 0;
            for (int i = 0; i < 32; i++) {
                if ((res & (0x01 << i)) != 0) {
                    cnt += 1;
                }
            }
            return cnt;
        }
    }
}