package com.linklist;

/* *
 * 反转链表:
 * 过一遍这个 Linked List，边过边把这个 node 的 next 指针指向前面那个 node，直到过完全部。
 * refer: https://segmentfault.com/a/1190000037518253
 */
public class ReverseList {
  public static SingleLinkedList.ListNode reverseList(SingleLinkedList.ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    SingleLinkedList.ListNode prev = null;
    SingleLinkedList.ListNode cur = head;
    SingleLinkedList.ListNode nxt;
    while (cur != null) {
      nxt = cur.next;
      cur.next = prev;
      prev = cur;
      cur = nxt;
    }
    return prev;
  }

  public static void main(String[] args) {
    // 通过结点类定义单链表
    //    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};  // midOrUpMidNode: 6,  midOrDownMidNode:
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //  midOrUpMidNode:5,  midOrDownMidNode:
    SingleLinkedList L = new SingleLinkedList(arr);
    SingleLinkedList.printList(L.head);
    SingleLinkedList.ListNode resNode = reverseList(L.head);
    SingleLinkedList.printList(resNode);

    // test `null`
    String s = null;
    System.out.println(s);

    // null 只能作为引用类型的初始化值，却不能作为基本类型的初始化值，因为基本类型有自己的初始化值，比如说 int 的为 0
    try {
      Integer j = null;
      int k = j;
      System.out.println(k);
    } catch (Exception e) {
      //      e.printStackTrace();
      System.out.println("Exception thrown  :" + e);
    }
  }
}
