/*
Z字型变换

将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：

P   A   H   N
A P L S I I G
Y   I   R
之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"

实现一个将字符串进行指定行数变换的函数:

string convert(string s, int numRows);
示例 1:

输入: s = "PAYPALISHIRING", numRows = 3
输出: "PAHNAPLSIIGYIR"
示例 2:

输入: s = "PAYPALISHIRING", numRows = 4
输出: "PINALSIGYAHRPI"
解释:

P     I    N
A   L S  I G
Y A   H R
P     I

https://leetcode-cn.com/problems/zigzag-conversion/

*/
public class P6 {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        Solution sl = new Solution();
        System.out.println(sl.convert(s, 3));
        System.out.println(sl.convert(s, 4));
    }
}
class Solution {
    /*
    0                           2n-2                        4n-4
    1                    2n-3   2n-1                 4n-5   4n-5
    2              2n-4         2n               4n-6       .
    .           .               .             .             .
    .       n+1                 .          3n-1             .
    n-2   n                     3n-4   3n-2                 5n-6
    n-1                         3n-3                        5n-5
    */
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;

        int len = s.length();
        char[] chars = s.toCharArray();

        int cycle = 2 * (numRows - 1); // 一个来回 V字型
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i+=cycle) {
            // Z字形的首行
            sb.append(chars[i]);
        }

        // 1 --- (numRows-1)
        for (int i = 1; i < numRows - 1; i++) {
            int step = 2 * i;
            for (int j = i; j < len; j+=step) {
                sb.append(chars[j]);
                step = cycle - step;
            }
        }

        for (int i = numRows - 1; i < len; i += cycle) {
            // Z字形的尾行
            sb.append(chars[i]);
        }

        return sb.toString();
    }

    // 然后模拟波浪生成的样子分别插入到相应的 StringBuilder 对象
    public String convert2(String s, int numRows) {
        if (numRows <= 1) return s;
        int len = s.length();
        char[] chars = s.toCharArray();
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        int i = 0;
        while (i < len) {
            for (int j = 0; j < numRows && i < len; ++j) {
                sbs[j].append(chars[i++]);
            }
            for (int j = numRows - 2; j >= 1 && i < len; --j) {
                sbs[j].append(chars[i++]);
            }
        }
        for (i = 1; i < numRows; i++) {
            sbs[0].append(sbs[i]);
        }
        return sbs[0].toString();
    }
}