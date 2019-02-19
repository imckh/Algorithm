/*
56. 合并区间
https://leetcode-cn.com/problems/merge-intervals/
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
*/
import java.util.*;

public class P56 {
    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1,2));
        list.add(new Interval(2,6));
        list.add(new Interval(6,10));
        list.add(new Interval(15,18));
        
        System.out.println(new Solution().merge(list));
        System.out.println(new Solution().merge1(list));
        System.out.println(list);
    }

    static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
    

    static class Solution {
        public List<Interval> merge(List<Interval> intervals) {
            if (intervals == null || intervals.size() == 0) return Collections.emptyList();
            intervals.sort(new Comparator<Interval>() {
                @Override
                public int compare(Interval o1, Interval o2) {
                    // 按start升序排序
                    if (o1.start < o2.start) return -1;
                    if (o1.start > o2.start) return 1;
                    return 0;
                }
            });
            // lambda
            // intervals.sort((o1, o2) -> o1.start - o2.start);

            List<Interval> result = new ArrayList<>();
            int start = intervals.get(0).start;
            int end = intervals.get(0).end;
            for (int i = 1; i < intervals.size(); i++) {
                Interval interval = intervals.get(i);
                // 如果前一个end >= 后一个start, 说明有重叠
                // 则以 前一个interval的start为start
                // 以 两个interval的end中大的一个为end
                if (end >= interval.start) { // 区间 [1,4] 和 [4,5] 可被视为重叠区间
                    end = Math.max(interval.end, end);
                } else {
                    // 当找到下一个区间的时候将上一个区间添加进result
                    result.add(new Interval(start, end));
                    start = interval.start;
                    end = interval.end;
                }
            }
            result.add(new Interval(start, end));
            return result;
        }

        // 思路一样, 更简洁的写法
        public List<Interval> merge1(List<Interval> intervals) {
            List<Interval> ans = new ArrayList<>();
    
            intervals.sort(Comparator.comparing(i -> i.start));  //lambda 匿名函数：输入i  返回i.start
    
            Interval last = null;
            for (Interval item : intervals) {
                if (last == null || last.end < item.start) {
                    ans.add(item);
                    last = item; // 这里其实用深复制更好, 因为else后边改end的时候会将原来的list中的数据改掉, 不过这里用不到
                } else {
                    last.end = Math.max(last.end, item.end);
                }
            }
            return ans;
        }
    }
}