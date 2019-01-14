import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
118. 杨辉三角

https://leetcode-cn.com/problems/pascals-triangle/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/67/

给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。

https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif

在杨辉三角中，每个数是它左上方和右上方的数的和。
*/
public class P118{
    public static void main(String[] args) {
        List<List<Integer>> list = new Solution().generate(9);
        System.out.println("[");
        for (List<Integer> ls : list) {
            System.out.println(Arrays.toString(ls.toArray()) + ",");
        }
        System.out.println("]");
    }
}
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> level = new ArrayList<Integer>();
            list.add(level);
            for (int j = 0; j < i + 1; j++) {
                if (j > 0 && j < i && i > 1) {
                    // 边界以内
                    level.add(list.get(i-1).get(j-1) + list.get(i-1).get(j));
                } else {
                    // 边界
                    level.add(1);
                }
            }
        }
        return list;
    }

    public void print(List<List<Integer>> list) {
        System.out.println("[");
        for (List<Integer> ls : list) {
            System.out.println(Arrays.toString(ls.toArray()) + ",");
        }
        System.out.println("]");
    }
}