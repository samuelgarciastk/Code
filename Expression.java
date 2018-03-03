import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Author: stk
 * Date: 2016/6/14
 * Time: 21:46
 *
 * 中缀、后缀表达式计算
 */
public class Expression {
    /**
     * 中缀表达式转后缀表达式
     * @param infix 中缀表达式
     * @return 后缀表达式
     */
    public static String infixToSuffix(String infix) {
        Stack<Character> stack = new Stack<>();
        String suffix = "";
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            switch (c) {
                case ' ': break;
                case '(': stack.push(c); break;
                case '+':
                case '-':
                    while (stack.size() != 0) {
                        Character tmp = stack.pop();
                        if (tmp == '(') {
                            stack.push('(');
                            break;
                        }
                        suffix += " " + tmp;
                    }
                    stack.push(c);
                    suffix += " ";
                    break;
                case '*':
                case '/':
                    while (stack.size() != 0) {
                        Character tmp = stack.pop();
                        if (tmp == '(' || tmp == '+' || tmp == '-') {
                            stack.push(tmp);
                            break;
                        } else suffix += " " + tmp;
                    }
                    stack.push(c);
                    suffix += " ";
                    break;
                case ')':
                    while (stack.size() != 0) {
                        Character tmp = stack.pop();
                        if (tmp == '(') break;
                        suffix += " " + tmp;
                    }
                    break;
                default: suffix += c;
            }
        }
        while (stack.size() != 0) suffix += " " + stack.pop();
        return suffix;
    }

    /**
     * 后缀表达式求值
     * @param suffix 后缀表达式
     * @return 表达式值
     */
    public static double calculateSuffix(String suffix) {
        Pattern pattern = Pattern.compile("\\d+||(\\d+\\.\\d+)");
        String[] str = suffix.trim().split("\\s+");
        Stack<Double> stack = new Stack<>();
        for (String i: str) {
            if (pattern.matcher(i).matches()) stack.push(Double.parseDouble(i));
            else {
                double y = stack.pop(), x = stack.pop();
                switch (i) {
                    case "+": stack.push(x + y); break;
                    case "-": stack.push(x - y); break;
                    case "*": stack.push(x * y); break;
                    case "/": stack.push(x / y); break;
                }
            }
        }
        return stack.pop();
    }

    /**
     * 中缀表达式求值
     * @param infix 中缀表达式
     * @return 表达式值
     */
    public static double calculateInfix(String infix) {
        return calculateSuffix(infixToSuffix(infix));
    }
    
    public static void main(String[] args) {
        System.out.println(Expression.calculateInfix("1+2*3+(4*5+6)*7"));
    }
}
