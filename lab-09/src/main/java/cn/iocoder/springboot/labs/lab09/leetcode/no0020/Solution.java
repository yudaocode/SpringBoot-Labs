package cn.iocoder.springboot.labs.lab09.leetcode.no0020;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public boolean isValid(String s) {
        List<Character> array = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '('
                || ch == '{'
                || ch == '[') {
                array.add(ch);
            } else {
                if (array.isEmpty()) {
                    return false;
                }
                int arrayCh = array.get(array.size() - 1);
                if ((arrayCh == '(' && ch == ')')
                    || (arrayCh == '{' && ch == '}')
                    || (arrayCh == '[' && ch == ']')) {
                    array.remove(array.size() - 1);
                } else {
                    return false;
                }
            }
        }
        return array.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("()"));
        System.out.println(new Solution().isValid("()[]{}"));
        System.out.println(new Solution().isValid("(]"));
        System.out.println(new Solution().isValid("([)]"));
        System.out.println(new Solution().isValid("{[]}"));
        System.out.println(new Solution().isValid("["));
        System.out.println(new Solution().isValid("]"));
    }

}
