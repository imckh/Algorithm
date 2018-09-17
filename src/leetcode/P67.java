// https://leetcode-cn.com/problems/add-binary/description/
public class P67 {
    public static void main(String[] args) {
        System.out.println("------P67------");
        
        Solution solution = new Solution();
        String s1 = "1010";
        String s2 = "1011";
        System.out.println(solution.addBinary(s1, s2));
        String s3 = "11110";
        String s4 =  "1111";
        System.out.println(solution.addBinary(s3, s4));
        String s5 = "11";
        String s6 =  "1";
        System.out.println(solution.addBinary(s5, s6));

        System.out.println("------P67------");
    }
}
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int j = b.length() - 1;
        int k = a.length() - 1;
        boolean hasCarry = false;
        for (; j >= 0 || k >= 0; j--, k--) {
            // fills 0 --> k or j negative
            char ac = k >= 0 ? a.charAt(k) : '0';
            char bc = j >= 0 ? b.charAt(j) : '0';

            if (ac == '0' && bc == '0') {// 0 + 0
                if (hasCarry) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
                hasCarry = false;
            } else if (ac == '1' && bc == '1') { // 1 + 1
                if (hasCarry) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
                hasCarry = true;
            } else {// 0+1 || 1+0
                if (hasCarry) {
                    sb.append('0');
                    hasCarry = true;
                } else {
                    sb.append('1');
                    hasCarry = false;
                }
            }
        }
        if (hasCarry) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }
}