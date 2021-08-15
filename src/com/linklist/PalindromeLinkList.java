package com.linklist;

import java.util.Stack;

public class PalindromeLinkList {
  public static void main(String[] args) {
    //    int[] arr = {1, 2, 3, 2, 1};
    int[] arr = {1, 2, 3, 2, 1, 4};
    SingleLinkedList L = new SingleLinkedList(arr);
    SingleLinkedList.printList(L.head);
    System.out.println("link list is palindrome: " + isPalindrome(L.head));
  }

  /*
   * 输入链表头节点(首元节点)，判断一个链表是否是回文数
   * 思路: 将其放入栈中，然后一次从栈中取出元素，看是否与当前链表中的元素相同。
   * 栈特点: 先进后出
   * */
  public static boolean isPalindrome(SingleLinkedList.ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    Stack<SingleLinkedList.ListNode> stack = new Stack<SingleLinkedList.ListNode>();
    SingleLinkedList.ListNode p = head;
    while (p != null) {
      stack.push(p);
      p = p.next;
    }
    SingleLinkedList.ListNode q = head;
    while (q != null) {
      if (q.value != stack.pop().value) {
        return false;
      }
      q = q.next;
    }
    return true;
  }
}
