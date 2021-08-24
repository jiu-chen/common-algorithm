package com.linklist;

// 内部类
class Node {
  public int value;
  public Node next = null;

  public Node(int v) {
    value = v;
  }
}

public class SingleLinkedList {

  public Node head;

  // 构造函数
  public SingleLinkedList(int m) {
    this.head = new Node(m);
  }

  // 构造函数重载
  public SingleLinkedList(int[] element) {
    // 有头节点: 这里头节点不同于首元节点
    //    this(); // 初始头结点为空
    //    Node rear = this.head;

    // 这里头节点就是首元节点，链表的第一个元素
    // leetcode
    this.head = new Node(element[0]);
    // 不要直接操作head指针
    Node rear = this.head;
    for (int i = 1; i < element.length; i++) {
      rear.next = new Node(element[i]);
      rear = rear.next;
    }
  }

  // 删除一个节点，并重新返回链表
  /* *
   * 问: 给一个链表中非头节点，能否删除该节点？
   * 不能。这种情况下永远无法删除链表的最后一个节点
   *
   */
  static Node deleteNode(Node head, int index) {
    if (head == null) {
      return null;
    }
    if (index == 1) {
      head = head.next;
      return head;
    }

    Node p = head;
    Node q;
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

  static void printList(Node head) {
    // 不要直接操作head指针
    Node p = head;
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
    Node head = deleteNode(L.head, index);
    printList(head);
  }
}
