// https://leetcode-cn.com/problems/length-of-last-word/description/
public class P58 {
    public static void main(String[] args) {
        System.out.println("------P53------");
        Solution solution = new Solution();
        String s  = "fsfef World    ";
        System.out.println(solution.lengthOfLastWord(s));
    }
}
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            /*
                "   "
                "World"
                "World    "
                "   World    "
                "hello   World    "
            */
            if (s.charAt(i) != ' ') {
                max++;
                if (i == 0 || s.charAt(i - 1) == ' '){
                    break;
                }
            }
        }
        return max;
    }
}