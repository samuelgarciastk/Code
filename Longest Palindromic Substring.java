/**
 * Author: stk
 * Date: 2016/6/10
 * Time: 19:28
 *
 * https://leetcode.com/problems/longest-palindromic-substring/
 * 5. Longest Palindromic Substring
 */
public class Solution {
    public String longestPalindrome(String s) {
        //在字符串中插入'#'，开头加入'$'
        char[] str = new char[(s.length() + 1) * 2];
        str[0] = '$';
        str[1] = '#';
        for (int i = 1; i <= s.length(); i++) {
            str[i * 2] = s.charAt(i - 1);
            str[i * 2 + 1] = '#';
        }
        //计算p[i]
        int[] p = new int[str.length];
        int maxRight = 0, pos = 0;
        for (int i = 1; i < str.length; i++) {
            p[i] = maxRight > i ? Math.min(p[2 * pos - i], maxRight - i) : 1;
            while (i - p[i] >= 0 && i + p[i] < str.length && str[i + p[i]] == str[i - p[i]])
                p[i]++;
            if (p[i] + i > maxRight) {
                maxRight = p[i] + i;
                pos = i;
            }
        }
        //找最大
        int max = 0, index = 0;
        for (int i = 0; i < str.length; i++) {
            if (p[i] > max) {
                max = p[i];
                index = i;
            }
        }
        return s.substring((index - max) / 2, (index + max) / 2 - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("ewqabcdcbaer"));
    }
}
