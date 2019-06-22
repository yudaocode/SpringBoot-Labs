package cn.iocoder.springboot.labs.lab09.leetcode.no0242;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        // 创建 s 使用的哈希表
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            Integer counts = map.get(ch);
            counts = counts != null ? counts + 1 : 1;
            map.put(ch, counts);
        }
        // 判断 t 是否有
        for (char ch : t.toCharArray()) {
           Integer counts = map.get(ch);
           if (counts == null) {
               return false;
           }
           counts--;
           if (counts == 0) {
               map.remove(ch);
           } else {
               map.put(ch, counts);
           }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isAnagram("anagram", "nagaram"));
        System.out.println(solution.isAnagram("rat", "cat"));
        System.out.println(solution.isAnagram("ccac", "aacc"));
    }

}
