package com.basic;

import java.util.HashMap;
import java.util.Stack;

class Solution {
  public static int romanToInt(String s) {
    //    HashMap<Character, Integer> map = new HashMap<>();
    //    map.put('I', 1);
    //    map.put('V', 5);
    //    map.put('X', 10);
    //    map.put('L', 50);
    //    map.put('C', 100);
    //    map.put('D', 500);
    //    map.put('M', 1000);

    HashMap<Character, Integer> map =
        new HashMap<>() {
          {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
          }
        };

    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      // 只处理前 len-1 个字符
      if (i < s.length() - 1 && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
        result = result - map.get(s.charAt(i));
      } else { // 最后一个字符进入 else
        result += map.get(s.charAt(i));
      }
    }

    return result;
  }

  public static boolean isValid(String s) {
    HashMap<Character, Character> map =
        new HashMap<>() {
          {
            put('[', ']');
            put('{', '}');
            put('(', ')');
            put('?', '?'); // 必须要有，否则 []) 中的 ')' 会出现error
          }
        };

    Stack<Character> stack = new Stack<>();
    stack.push('?'); // 防止出现空栈
    for (int i = 0; i < s.length(); i++) {
      if (map.containsKey(s.charAt(i))) {
        stack.push(s.charAt(i));
      } else {
        if (map.get(stack.pop()) != s.charAt(i))
          return false; // 必须要有'?' 否则 null != char type;  // error
      }
    }

    return stack.size() == 1;
  }

  public static void main(String[] args) {
    HashMap<Integer, Integer> tmap = new HashMap<>();
    tmap.put(2, 2);
    System.out.println("key 1: " + tmap.get(1));

    System.out.println("====test romanToInt method====");
    String str = "LVIII";
    System.out.println("romon to int: " + romanToInt(str));

    System.out.println("====test isValid method====");
    System.out.println("isValid result: " + isValid("[])"));

    //    char c = null;  // wrong syntax

  }
}
