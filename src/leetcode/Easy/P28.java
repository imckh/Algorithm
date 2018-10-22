// 28. implement strStr()
// https://leetcode-cn.com/problems/implement-strstr/description/
public class P28 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String haystack = "hello";
        String needle = "ll";
        String haystack2 = "aaaaa", needle2 = "bba";
        System.out.println(s.strStr(haystack, needle));
        System.out.println(s.strStr(haystack2, needle2));
    }
}


class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null)
            return -1;
        if (needle.equals(""))
            return 0;
        int index = -1;
        for (int i = 0; i < haystack.length(); i++) {
            int testi = i;
            for (int j = 0; j < needle.length(); j++) {
                if (!(haystack.charAt(testi++) == haystack.charAt(j))) {
                    break;
                }
            }
            index = i;
            return index;
        }
        return -1;
    }
   
    // java  indexof
    public int strStr1(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.equals("")) {
            return 0;
        }
        // compara till end
        int end = haystack.length() - needle.length();
        for (int i = 0; i <= end; i++) {

            // find first letter
            if (haystack.charAt(i) != needle.charAt(0)) {
                while (++i <= end && haystack.charAt(i) != needle.charAt(0));
            }
            
            // compare from second letter
            if (i <= end) {
                int k = i + 1;
                int neelend = k + needle.length() - 1;
                // control length for loop
                for (int j = 1; k < neelend && haystack.charAt(k) == needle.charAt(j); j++, k++);
                if (k == neelend) {
                    return i;
                }
            }
        }
        return -1;
    }

    
    public int strStr2(String haystack, String needle) {
        int len1=haystack.length();
        int len2=needle.length();
        boolean flag=true;
        
        if(len2==0)
            return 0;
        else if (len1<len2)
            return -1;
        
        int i=0,j=0,temp;
        while(i<=len1-len2){
            temp=i;
            j=0;
            flag=true;
            while(j<len2){
                if(haystack.charAt(temp)==needle.charAt(j)){
                    temp++;
                    j++;
                }    
                else{
                    flag=false;
                    break;
                }
            }
            if(flag==true)
                return i;
            i++;       
        }
        
        return -1;
    }
}