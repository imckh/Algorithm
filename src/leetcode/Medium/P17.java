/*
P17. 电话号码的字母组合
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
        String nums = "1";
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

        lettersIndex(result, "", digits, 0);

        return result;
    }

    public String lettersIndex(List<String> result, String s, String digits, int index) {
        if (digits.length() <= index) {
            return s;
        }
        char cnum = digits.charAt(index);
        String letters = map[cnum - '1'];

        for (int i = 0; i < letters.length(); i++) {
            s = lettersIndex(result, letters.charAt(i) + "", digits, index + 1);
            result.add(s);
        }
        return s;
    }
}