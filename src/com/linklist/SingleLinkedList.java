package com.linklist;

public class SingleLinkedList {

  // 内部类
  public static class ListNode {
    public int value;
    public ListNode next = null;

    public ListNode(int v) {
      value = v;
    }
  }

  public ListNode head;

  // 构造函数
  public SingleLinkedList(int m) {
    this.head = new ListNode(m);
  }

  // 构造函数重载
  public SingleLinkedList(int[] element) {
    // 有头节点: 这里头节点不同于首元节点
    //    this(); // 初始头结点为空
    //    ListNode rear = this.head;

    // 这里头节点就是首元节点，链表的第一个元素
    // leetcode
    this.head = new ListNode(element[0]);
    // 不要直接操作head指针
    ListNode rear = this.head;
    for (int i = 1; i < element.length; i++) {
      rear.next = new ListNode(element[i]);
      rear = rear.next;
    }
  }

  static void printList(ListNode head) {
    // 不要直接操作head指针
    ListNode p = head;
    System.out.print("link list: ");
    while (p != null) {
      System.out.print(p.value + " ");
      p = p.next;
    }
    System.out.println();
  }
}
