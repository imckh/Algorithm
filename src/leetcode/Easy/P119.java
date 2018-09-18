import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。

https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif

在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 3
输出: [1,3,3,1]
进阶：

你可以优化你的算法到 O(k) 空间复杂度吗？
*/
public class P119{
    public static void main(String[] args) {
        List<Integer> list = new Solution().getRow(9);
        //System.out.println("[");
        //for (List<Integer> ls : list) {
            System.out.println(Arrays.toString(list.toArray()) + ",");
        //}
        //System.out.println("]");
    }
}
class Solution {
    // 平淡无奇的解法
    public List<Integer> getRowSimple(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; ++i) {
            res.add(1);
            for (int j = i - 1; j > 0; --j) {
                res.set(j, res.get(j - 1) + res.get(j));
            }
        }
        return res;
    }
    
    public List<Integer> getRow(int rowIndex) {
        return generate(rowIndex + 1).get(rowIndex);
    }

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

}