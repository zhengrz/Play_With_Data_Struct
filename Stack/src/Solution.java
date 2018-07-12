import java.util.Stack;

/**
 * 题意:
 *      给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *      有效字符串需满足(注意空字符串可被认为是有效字符串。)：
 *          1. 左括号必须用相同类型的右括号闭合。
 *          2. 左括号必须以正确的顺序闭合。
 * @author zhengrz
 * @date 2018/7/12 11:32
 */
public class Solution {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[')
                stack.push(s.charAt(i));
            else {
                if (stack.isEmpty())
                    return false;
                else {
                    char topChar = stack.pop();
                    if (c == ')' && topChar != '(') return false;
                    if (c == '}' && topChar != '{') return false;
                    if (c == ']' && topChar != '[') return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("()[]{}"));
        System.out.println(new Solution().isValid("()"));
        System.out.println(new Solution().isValid("["));
        System.out.println(new Solution().isValid(""));
    }
}
