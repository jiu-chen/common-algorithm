package com.forcerecursive;

import java.util.Stack;

/*
 * 给你一个栈，请逆序这个栈，不能申请额外的数据结构，只能使用递归函数。
 * 思路:
 * 利用递归过程的压栈特点
 * refer: iamges/reverseStack1.png
 * refer: iamges/reverseStack2.png
 */
public class ReverseStackUsingRecursive {
  public static void reverse(Stack<Integer> stack) {
    if (stack.empty()) {
      return;
    }
    int i = removeStackButtonElem(stack);
    reverse(stack);
    stack.push(i);
  }

  //  移除栈底元素
  private static int removeStackButtonElem(Stack<Integer> stack) {
    int result = stack.pop();
    if (stack.empty()) {
      return result;
    } else {
      int last = removeStackButtonElem(stack);
      stack.push(result);
      return last;
    }
  }

  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(3);
    stack.push(2);
    stack.push(1);
    System.out.println("stack top elem: " + stack.peek());

    reverse(stack);
    //    System.out.println("reversed stack: " + stack);
    System.out.print("打印stack: ");
    while (!stack.empty()) {
      System.out.print(stack.pop() + " ");
    }
  }
}
