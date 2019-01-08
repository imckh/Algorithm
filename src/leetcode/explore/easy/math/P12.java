/*
12. 整数转罗马数字

https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/25/math/63/
https://leetcode-cn.com/problems/integer-to-roman/
罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

示例 1:

输入: 3
输出: "III"
示例 2:

输入: 4
输出: "IV"
示例 3:

输入: 9
输出: "IX"
示例 4:

输入: 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.
示例 5:

输入: 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.
*/

public class P12 {
    public static void main(String[] args) {
        System.out.println(new Solution().intToRoman(599));
        System.out.println(new Solution().intToRoman2(599));
    }
    static class Solution {
        // 把每一位的0-9用罗马数字表示出来 
        // 也太投机取巧了吧
        public String intToRoman(int num) {
            // 数组 0 ---- 9
            // 千 百 十 个
            String M[] = {"", "M", "MM", "MMM"};
            String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
            String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    
            return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
        }
    
        // 将罗马数字的情况转为数字, 使用递减计算
        public String intToRoman2(int num) {
            String[] str = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
            int[] arr = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
            String s = "";
            for (int i = 0; i < arr.length; i++) {
                while (num >= arr[i]) {
                    num = num - arr[i];
                    s = s + str[i];
                }
    
            }
            return s;
    
        }
    }
    static class Solution2 {
        public int romanToInt(String s) {
            /*
            相同的数字连写，所表示的数等于这些数字相加得到的数，如 Ⅲ=3；
    
            小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数，如 Ⅷ=8、Ⅻ=12；
    
            小的数字（限于 Ⅰ、X 和 C）在大的数字的左边，所表示的数等于大数减小数得到的数，如 Ⅳ=4、Ⅸ=9。
    
            那么我们可以利用 map 来完成罗马数字的 7 个数字符号：I、V、X、L、C、D、M 和整数的映射关系
            */
            
            int len = s.length();
            int r = numberAt(s.charAt(len - 1));
            for(int i = len - 2; i >= 0; --i) {
                if(numberAt(s.charAt(i)) < numberAt(s.charAt(i + 1))) {
                    r -= numberAt(s.charAt(i));
                } else {
                    r += numberAt(s.charAt(i));
                }
            }
            
            return r;
        }
        
        public static int numberAt(char c){
            if(c=='I') return 1;
            else if(c=='V') return 5;
            else if(c=='X') return 10;
            else if(c=='L') return 50;
            else if(c=='C') return 100;
            else if(c=='D') return 500;
            else if(c== 'M') return 1000;
            else return 0;
        }
    }
}