/*
P17. 电话号码的字母组合
https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/49/backtracking/91/
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png

示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

*/

import java.util.*;

public class P17 {
    public static void main(String[] args) {
        String nums = "233";
        System.out.println(new Solution().letterCombinations(nums));
    }
}

class Solution {
    private static String[] map = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() < 1) {
            return new ArrayList<>();
        }

        lettersIndex(result, "", digits);

        return result;
    }

    /**
     * result: 结果保存的列表
     * s:      当前的状态, 将要传递给下一层递归
     * digits: 数字, 当前数字递归到第几位由当前状态s的长度表示
     */
    public void lettersIndex(List<String> result, String s, String digits) {
        if (digits.length() == s.length()) {
            result.add(s);
            return;
        }
        // 取得当前状态数字表示的字母表
        char cnum = digits.charAt(s.length());
        String letters = map[cnum - '2'];

        // 对字母表进行添加, 并以前一个状态的s加上相应的字母传递到下一层递归
        for (int i = 0; i < letters.length(); i++) {
            lettersIndex(result, s + letters.charAt(i), digits);
        }
    }
}