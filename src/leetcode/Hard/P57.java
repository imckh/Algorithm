/*
57. 插入区间
https://leetcode-cn.com/problems/insert-interval/

给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

示例 1:

输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
输出: [[1,5],[6,9]]
示例 2:

输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出: [[1,2],[3,10],[12,16]]
解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。

*/
import java.util.*;
public class P57 {
    public static void main(String[] args) {
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(4, 5);
        Interval i3 = new Interval(6, 7);
        Interval i4 = new Interval(8, 10);
        Interval i5 = new Interval(12, 16);
        List<Interval> ls = new ArrayList<>();
        ls.add(i1);
        ls.add(i2);
        ls.add(i3);
        ls.add(i4);
        ls.add(i5);

        System.out.println(new Solution().insert(ls, new Interval(2, 8)));
    }
    static class Solution {
        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
            // 1. 首先把有序区间中小于待插入区间的部分加入到结果中
            // 2. 其次是插入待插入区间, 如果有交集的话取两者交集的端点值
            // 3. 最后把有序区间中大于待插入区间的部分加入到结果中
            
            if (intervals.isEmpty()) return Collections.singletonList(newInterval);
            List<Interval> ans = new ArrayList<>();
            int i = 0, len = intervals.size();
            for (; i < len; i++) {
                // 1. 首先把有序区间中小于待插入区间的部分加入到结果中
                Interval interval = intervals.get(i);
                if (interval.end < newInterval.start) {
                    ans.add(interval);
                } else {
                    break;
                }
            }
            
            for (; i < len; i++) {
                // 2. 其次是插入待插入区间, 如果有交集的话取两者交集的端点值
                Interval interval = intervals.get(i);
                if (interval.start <= newInterval.end) {
                    newInterval.start = Math.min(newInterval.start, interval.start);
                    newInterval.end = Math.max(newInterval.end, interval.end);
                } else {
                    break;
                }
            }
            
            ans.add(newInterval);
            for (; i < len; i++) {
                // 3. 最后把有序区间中大于待插入区间的部分加入到结果中
                ans.add(intervals.get(i));
            }
            return ans;
        }
    }

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
}