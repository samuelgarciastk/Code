/**
 * Author: stk
 * Date: 2016/6/14
 * Time: 23:16
 *
 * https://leetcode.com/problems/zigzag-conversion/
 * 6. ZigZag Conversion
 */
public class Main {
    public String convert(String s, int numRows) {
        int len = s.length();
        if (len <= numRows || numRows == 1)
            return s;
        String res = "";
        boolean[] used = new boolean[len];
        int interval = 2 * numRows - 2;
        for (int i = 0; i < len; i += interval) {
            res += s.charAt(i);
            used[i] = true;
        }
        for (int i = 1; i < numRows; i++) {
            res += s.charAt(i);
            used[i] = true;
            for (int j = interval; j < len + interval; j += interval) {
                if ((j - i >= 0) && (j - i < len) && (!used[j - i])) {
                    res += s.charAt(j - i);
                    used[j - i] = true;
                }
                if ((j + i >= 0) && (j + i < len) && (!used[j + i])) {
                    res += s.charAt(j + i);
                    used[j + i] = true;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Main().convert("ABCD", 3));
    }
}
