/**

5070. 与目标颜色间的最短距离 

给你一个数组 colors，里面有  1、2、 3 三种颜色。

我们需要在 colors 上进行一些查询操作 queries，其中每个待查项都由两个整数 i 和 c 组成。

现在请你帮忙设计一个算法，查找从索引 i 到具有目标颜色 c 的元素之间的最短距离。

如果不存在解决方案，请返回 -1。

 

示例 1：

输入：colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
输出：[3,0,3]
解释： 
距离索引 1 最近的颜色 3 位于索引 4（距离为 3）。
距离索引 2 最近的颜色 2 就是它自己（距离为 0）。
距离索引 6 最近的颜色 1 位于索引 3（距离为 3）。
示例 2：

输入：colors = [1,2], queries = [[0,3]]
输出：[-1]
解释：colors 中没有颜色 3。
 

提示：

1 <= colors.length <= 5*10^4
1 <= colors[i] <= 3
1 <= queries.length <= 5*10^4
queries[i].length == 2
0 <= queries[i][0] < colors.length
1 <= queries[i][1] <= 3

 */
class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        //int[] result = new int[queries.length];
        List<Integer> result = new ArrayList(queries.length);
        
        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        List<Integer> thr = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 1) {
                one.add(i);
            } else if (colors[i] == 2) {
                two.add(i);
            } else if (colors[i] == 3) {
                thr.add(i);
            }            
        }
        
        for (int i = 0; i < queries.length; i++) {
            int from = queries[i][0];
            int toColor = queries[i][1];
            
            if (toColor == 1) {
                if (one.isEmpty()) result.add(-1);
                else result.add(findCloset(from, one));
            } else if (toColor == 2) {
                if (two.isEmpty()) result.add(-1);
                else result.add(findCloset(from, two));
            } else if (toColor == 3) {
                if (thr.isEmpty()) result.add(-1);
                else result.add(findCloset(from, thr));
            }
        }
        return result;
    }
    
    public int findCloset(int index, List<Integer> list) {
        int i = 0, j = list.size(), mid = 0;
        
        while (i < j) {
            mid = (i + j) / 2;
            if (list.get(mid) < index) {
                j = mid;
            } else if (list.get(mid) > index) {
                i = mid;
            } else {
                return mid;
            }
        }
        
        return mid;
    }
}