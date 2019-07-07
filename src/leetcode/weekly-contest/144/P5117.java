/*
5117. IP 地址无效化   
给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。

所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。

 

示例 1：

输入：address = "1.1.1.1"
输出："1[.]1[.]1[.]1"
示例 2：

输入：address = "255.100.50.0"
输出："255[.]100[.]50[.]0"
 

提示：

给出的 address 是一个有效的 IPv4 地址
*/

public class P5117 {
    public static void main(String[] args) {
        String s = "255.100.50.0";
        System.out.println(new Solution().defangIPaddr(s));
    }
}
class Solution {
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            if ('.' == address.charAt(i)) {
                sb.append('[').append(address.charAt(i)).append(']');
            } else {
                sb.append(address.charAt(i));
            }
        }
        return sb.toString();
    }
}