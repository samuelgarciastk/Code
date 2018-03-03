/**
 * Author: stk
 * Date: 2016/7/9
 * Time: 17:24
 *
 * https://leetcode.com/problems/reverse-integer/
 * 7. Reverse Integer
 */
public class Solution {
    public int reverse(int x) {
        String s = String.valueOf(x);
        boolean isNegative = false;
        if (s.charAt(0) == '-') {
            s = s.substring(1);
            isNegative = true;
        }
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        int res;
        try {
            res = Integer.valueOf(sb.toString());
        } catch (Exception e) {
            return 0;
        }
        if (isNegative)
            res *= -1;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(-1000000003));
    }
}
