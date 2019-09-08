/**
5067. 统计只含单一字母的子串

给你一个字符串 S，返回只含 单一字母 的子串个数。

示例 1：

输入： "aaaba"
输出： 8
解释： 
只含单一字母的子串分别是 "aaa"， "aa"， "a"， "b"。
"aaa" 出现 1 次。
"aa" 出现 2 次。
"a" 出现 4 次。
"b" 出现 1 次。
所以答案是 1 + 2 + 4 + 1 = 8。
示例 2:

输入： "aaaaaaaaaa"
输出： 55
 

提示：

1 <= S.length <= 1000
S[i] 仅由小写英文字母组成。

 */
class Solution {
    public int countLetters(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        
        if (S.length() == 1) return 1;
        
        int result = 1;
        
        // 找到长连续字母的字串
        int i = 1, firstIndex = 0;
        for (; i < S.length(); i++) {
            
            if (S.charAt(i) != S.charAt(i-1)) {
                result += getOneToN(i - firstIndex - 1);
                firstIndex = i; // i位置和上一个不同, 计算i到相同字符串开始位置的值                
            }

            result++;

        }
        //System.out.println(i + " " + firstIndex);
        result += getOneToN(i - firstIndex - 1);// 数组末尾处
        return result;
    }
    
    public int getOneToN(int n) {
        int r = 0;
        for (int i = 1; i <= n; i++) {
            r += i;
        }
        return r;
    }
}