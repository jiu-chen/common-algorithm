package com.linklist;

public class SingleLinkedList {
  public static class ListNode {
    public int value;
    public ListNode next = null;

    public ListNode(int v) {
      value = v;
    }
  }

  public ListNode head;

  public SingleLinkedList() {
    this.head = new ListNode(0);
  }

  public SingleLinkedList(int[] element) {
    // 有头节点: 这里头节点不同于首元节点
    //    this(); // 初始头结点为空
    //    ListNode rear = this.head;

    // 这里头节点就是首元节点，链表的第一个元素
    // leetcode
    this.head = new ListNode(1);
    ListNode rear = this.head;
    for (int i = 1; i < element.length; i++) {
      rear.next = new ListNode(element[i]);
      rear = rear.next;
    }
  }

  //
  public static ListNode midOrUpMidNode(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return head;
    }
    ListNode slow = head.next;
    ListNode fast = head.next.next;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public static void main(String[] args) {
    // 通过结点类定义单链表
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    SingleLinkedList L = new SingleLinkedList(arr);
    printList(L.head);

    ListNode targetNode = midOrUpMidNode(L.head);
    System.out.println("middle node value: " + targetNode.value);
  }

  static void printList(ListNode head) {
    ListNode p = head;
    System.out.print("link list: ");
    while (p != null) {
      System.out.print(p.value + " ");
      p = p.next;
    }
    System.out.println();
  }
}
