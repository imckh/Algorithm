/*
166. 分数到小数

https://leetcode-cn.com/problems/fraction-to-recurring-decimal/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/53/math/118/


给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。

如果小数部分为循环小数，则将循环的部分括在括号内。

示例 1:

输入: numerator = 1, denominator = 2
输出: "0.5"
示例 2:

输入: numerator = 2, denominator = 1
输出: "2"
示例 3:

输入: numerator = 2, denominator = 3
输出: "0.(6)"
*/
import java.util.*;
public class P166 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.fractionToDecimal(1, 3));
        System.out.println(solution.fractionToDecimal(1, 2));
        
        // 0.(1764705882352941)
        System.out.println(solution.fractionToDecimal(3, 17));
        System.out.println(solution.fractionToDecimal(-2147483648, -1));

    }

    static class Solution {
        /**
        首先排除2种情况：

            分子为0，返回“0”。
            分母为0，返回空字符串。
            接着，来考虑3种情况：

            可以整除，这样就可以直接转换成字符串。
            不可以整除，但小数点后不是循环小数，这也同样可以直接转换成字符串。
            不可以整除，且是循环小数，这就比较尴尬了，下面来详细探讨。
         */
        public String fractionToDecimal(int numerator, int denominator) {
            if (numerator == 0) {
                return "0";
            }
            if (denominator == 0) {
                return "";
            }

            // 如果两个数符号不同，结果为负数  >>>为无符号右移动，int类型为32位，在计算机中以
            //补码进行计算，无符号右移31位剩下补码的符号位，正数会是0，负数会是1，再进行异或^操作，
            //都为正数会得到(0 ^ 0) == 0,都为负数会得到(1 ^ 1 == 0),一正一负会得到(0 ^ 1 == 1)
            String sign = (denominator >>> 31 ^ numerator >>> 31) == 1 ? "-" : "";
            // 相当于 两数同为正或负, 结果是正数, 仅有一个为负数则结果为负数

            // 先把除数和被除数转为long，以免除法和绝对值运算的时候溢出
            // 比如 -2147483648/-1 = -2147483648, abs(-2147483648) = -2147483648
            long num = numerator;
            long den = denominator;
            num = Math.abs(num);
            den = Math.abs(den);

             //商
            long major = num / den;
            //余数
            long rem = num % den;

            if (rem == 0) {
                return sign + major;
            }

            StringBuilder sb = new StringBuilder(sign).append(major).append('.');

            Map<Long, Integer> map = new HashMap<>();
            while (rem != 0) {
                //如果余数已经出现过一次, 则要开始循环了
                if (map.containsKey(rem)) {
                    int index = map.get(rem);
                    sb.insert(index, '(').append(')');
                    break;
                } else {
                    // 想象一下除法列竖式 // https://baike.baidu.com/item/%E7%AB%96%E5%BC%8F#2_4
                    // 不够借一位, *10
                    // 把商放进sb
                    sb.append(rem * 10 / den);
                    map.put(rem, sb.length() - 1);
                }
                // 余数
                rem = rem * 10 % den;
            }
            return sb.toString();
        }
    }
}