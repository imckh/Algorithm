/*
1154. 一年中的第几天

题目难度 Easy
给你一个按 YYYY-MM-DD 格式表示日期的字符串 date，请你计算并返回该日期是当年的第几天。

通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。

 

示例 1：

输入：date = "2019-01-09"
输出：9
示例 2：

输入：date = "2019-02-10"
输出：41
示例 3：

输入：date = "2003-03-01"
输出：60
示例 4：

输入：date = "2004-03-01"
输出：61
 

提示：

date.length == 10
date[4] == date[7] == '-'，其他的 date[i] 都是数字。
date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日。
*/
import java.util.*;
import java.text.*;

public class P1154 {
    public static void main(String[] args) {
        System.out.println(new Solution().ordinalOfDate("2003-03-21"));
    }

    static class Solution {
        public int ordinalOfDate(String date) {
            int year = Integer.valueOf(date.substring(0, 4));
            int month = Integer.valueOf(date.substring(5, 7));
            int day = Integer.valueOf(date.substring(8, 10));
            System.out.println(year + " " + month + " " + day);
            return whichDay(year, month, day);
        }

        public static int whichDay(int year, int month, int day) {
            final int[] monthes = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
            int i = 0,num = 0;
            while(i < month - 1)
                num += monthes[i++];
            num += day;
            if(month < 3)
                return num;
            return num+isLeap(year);
        }

        public static int isLeap(int year) {
            if (year % 4 == 0 && year % 100 != 0)
                return 1;
            if (year % 400 == 0)
                return 1;
            else return 0;
        }
    }
}