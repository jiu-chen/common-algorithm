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

  // 删除一个节点，并重新返回链表
  /* *
   * 问: 给一个链表中非头节点，能否删除该节点？
   * 不能。这种情况下永远无法删除链表的最后一个节点
   *
   */
  static ListNode deleteNode(ListNode head, int index) {
    if (head == null) {
      return null;
    }
    if (index == 1) {
      head = head.next;
      return head;
    }

    ListNode p = head;
    ListNode q;
    int n = 2;
    while (p != null) {
      q = p;
      p = p.next;
      if (n == index) {
        q.next = p.next;
      }
      n++;
    }
    return head;
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

  public static void main(String[] args) {
    // 通过结点类定义单链表
    //    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};  // midOrUpMidNode: 6,  midOrDownMidNode:
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //  midOrUpMidNode:5,  midOrDownMidNode:
    SingleLinkedList L = new SingleLinkedList(arr);
    printList(L.head);
    int index = 1;
    //    int index = 3;
    ListNode head = deleteNode(L.head, index);
    printList(head);
  }
}
