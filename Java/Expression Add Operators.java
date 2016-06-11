import java.util.LinkedList;
import java.util.List;

/**
 * Author: stk
 * Date: 2016/6/7
 * Time: 20:39
 *
 * https://leetcode.com/problems/expression-add-operators/
 * 282. Expression Add Operators
 */
public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> list = new LinkedList<>();
        int len = num.length();
        if (len < 2) return list;
        int[] ops = new int[len - 1];
        ops(ops, len - 2, list, target, num);
        return list;
    }

    public void ops(int[] ops, int i, List<String> list, int target, String num) {
        if (i == -1) {
            char[] number = num.toCharArray();
            String exp = format(number, ops);
            if (calculate(exp) == target) list.add(exp);
            return;
        }
        ops[i] = 0;
        ops(ops, i - 1, list, target, num);
        ops[i] = 1;
        ops(ops, i - 1, list, target, num);
        ops[i] = 2;
        ops(ops, i - 1, list, target, num);
        ops[i] = 3;
        ops(ops, i - 1, list, target, num);
    }

    public int calculate(String exp) {
        char[] expArray = exp.toCharArray();
        int[] numStack = new int[3];
        char[] opStack = new char[2];
        int stackPtr = 0, expPtr = 0;
        String number = "";
        char operator = '/';
        while (expPtr < expArray.length && expArray[expPtr] <= '9' && expArray[expPtr] >= '0') {
            number += expArray[expPtr];
            expPtr++;
        }
        if (number.length() > 1 && number.charAt(0) == '0') return Integer.MAX_VALUE;
        try {
            numStack[0] = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
        while (stackPtr > -1) {
            if (stackPtr != 2) {
                if (expPtr < expArray.length) {
                    char c = expArray[expPtr];
                    if (c <= '9' && c >= '0') {
                        number += c;
                    } else {
                        if (operator != '/') {
                            if (number.length() > 1 && number.charAt(0) == '0') return Integer.MAX_VALUE;
                            try {
                                numStack[stackPtr + 1] = Integer.parseInt(number);
                            } catch (NumberFormatException e) {
                                return Integer.MAX_VALUE;
                            }
                            opStack[stackPtr] = operator;
                            stackPtr++;
                        }
                        operator = c;
                        number = "";
                    }
                    expPtr++;
                    continue;
                } else {
                    if (operator != '/') {
                        if (number.length() > 1 && number.charAt(0) == '0') return Integer.MAX_VALUE;
                        try {
                            numStack[stackPtr + 1] = Integer.parseInt(number);
                        } catch (NumberFormatException e) {
                            return Integer.MAX_VALUE;
                        }
                        opStack[stackPtr] = operator;
                        operator = '/';
                        stackPtr++;
                    } else stackPtr--;
                }
            }
            if (stackPtr == 2 && opStack[1] == '*') {
                numStack[1] *= numStack[2];
            } else {
                switch (opStack[0]) {
                    case '+': numStack[0] += numStack[1]; break;
                    case '-': numStack[0] -= numStack[1]; break;
                    case '*': numStack[0] *= numStack[1]; break;
                }
                numStack[1] = numStack[2];
                opStack[0] = opStack[1];
            }
            stackPtr--;
        }
        return numStack[0];
    }

    public String format(char[] number, int[] ops) {
        String res = String.valueOf(number[0]);
        for (int i = 0; i < ops.length; i++) {
            switch (ops[i]) {
                case 0: res += ""; break;
                case 1: res += "+"; break;
                case 2: res += "-"; break;
                case 3: res += "*"; break;
            }
            res += number[i + 1];
        }
        return  res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        List<String> list= solution.addOperators("3456237490", 9191);
//        List<String> list= solution.addOperators("000", 0);
        List<String> list= solution.addOperators("105", 5);
        list.forEach(System.out::println);
    }
}
