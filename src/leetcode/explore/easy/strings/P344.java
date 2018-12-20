import java.util.Arrays;

/*
344. 反转字符串
https://leetcode-cn.com/problems/reverse-string/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/5/strings/32/

编写一个函数，其作用是将输入的字符串反转过来。

示例 1:

输入: "hello"
输出: "olleh"
示例 2:

输入: "A man, a plan, a canal: Panama"
输出: "amanaP :lanac a ,nalp a ,nam A"
*/

public class P344 {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";

        System.out.println(new Solution().reverseString(s));
    }

    static class Solution {
        public String reverseString(String s) {
            if (s == null || s.length() == 1) return s;

            char[] sc = s.toCharArray();
            char[] nsc = new char[sc.length];
            for (int i = 0, j = sc.length-1; i < sc.length; i++, j--) {
                nsc[i] = sc[j];
            }
            return new String(nsc);
        }

        public String reverseString_n2(String s) {
            // 1/2的时间
            char[] chars = s.toCharArray();
            int start = 0;
            int end = chars.length-1;
            char temp =0;
            
            while(start<end){
                temp = chars[start];
                chars[start] = chars[end];
                chars[end]=temp;
                start++;
                end--;
            }
            
            return new String(chars);
        }
    }
}