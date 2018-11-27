import java.util.*;

/*
554. 砖墙

https://leetcode-cn.com/problems/brick-wall/

你的面前有一堵方形的、由多行砖块组成的砖墙。 这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。

砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。

如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。

你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。

 

示例：

输入: [[1,2,2,1],
      [3,1,2],
      [1,3,2],
      [2,4],
      [3,1,2],
      [1,3,1,1]]

输出: 2

解释: 

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/brick_wall.png

提示：

每一行砖块的宽度之和应该相等，并且不能超过 INT_MAX。
每一行砖块的数量在 [1,10,000] 范围内， 墙的高度在 [1,10,000] 范围内， 总的砖块数量不超过 20,000。
*/

public class P554 {
    public static void main(String[] args) {
        List<List<Integer>> wall = new ArrayList<>();
        wall.add(Arrays.asList( 1, 2, 2, 1 ));
        wall.add(Arrays.asList( 3, 1, 2 ));
        wall.add(Arrays.asList( 1, 3, 2 ));
        wall.add(Arrays.asList( 2, 4 ));
        wall.add(Arrays.asList( 3, 1, 2 ));
        wall.add(Arrays.asList( 1, 3, 1, 1 ));
        System.out.println(wall);
        System.out.println(new Solution().leastBricks(wall));
    }

    static class Solution {
        public int leastBricks(List<List<Integer>> wall) {
            if (wall.size() == 0) return 0;

            // 只需要记录同一个长度出现的次数
            // 从开始到该行当前砖块右侧的长度
            Map<Integer, Integer> map = new HashMap<>();

            int max = 0;
            for (List<Integer> list : wall) {
                int length = 0;
                // 对于墙的各层，如果一个完整的砖块以左（包含自己）长度相等，
                // 那么从此长度往下划线的时候就不会穿过此砖块
                for (int i = 0; i < list.size() - 1; i++) {
                    length += list.get(i);
                    int times = map.containsKey(length) 
                        ? map.get(length) + 1
                        : 1; // 记录改长度的出现次数
                    map.put(length, times);
                    max = max < times ? times : max; // 找出现次数最多的
                }
            }

            // 如果以出现次数最多的长度往下划线，穿过的砖块数就越少
            // 总层数减去出现次数最多的长度, 就是最少交叉的数量
            return wall.size() - max;
        }
    }
}