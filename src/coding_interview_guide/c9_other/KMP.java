public class KMP {
    public static void main(String[] args) {
        System.out.println(new KMP().getIndexOf("aaaaaaab", "ab"));
    }

    public int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < 1) {
            return -1;
        }

        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms); // 得到m字符串的nextArr数组

        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                // s m 匹配
                si++;
                mi++;
            } else if (next[mi] == -1) {
                // m 字符串中i位置没有匹配前缀
                si++;
            } else {
                // 
                mi = next[mi];
            }
        }

        return mi == ms.length ? si - mi : -1;
    }

    public int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] {-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;

        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                // 如果相等，pos位置的之前的字符串的最长前缀和后缀匹配区域可以确定
                next[pos++] = ++cn;
            } else if (cn > 0) {
                // 如果不等，向前跳，找前一个字符的最长匹配
                cn = next[cn];
            } else {
                // 直到跳到最左位置此时cn = 0, next[0] = -1， 说明该字符不存在前缀后缀匹配的情况
                // 直接令next[i]=0
                next[pos++] = 0;
            }
        }
        return next;
    }
}