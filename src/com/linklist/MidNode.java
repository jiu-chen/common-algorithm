package com.linklist;

public class MidNode {
  /*
   * 输入链表头节点(首元节点)，奇数长度返回中点，偶数长度返回`上`中点
   * 快慢指针 快指针每次走两步，慢指针每次走一步
   * */
  public static Node midOrUpMidNode(Node head) {
    if (head == null || head.next == null || head.next.next == null) {
      return head;
    }
    Node slow = head.next;
    Node fast = head.next.next;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  /*
   * 输入链表头节点(首元节点)，奇数长度返回中点，偶数长度返回`下`中点
   * 快慢指针 快指针每次走两步，慢指针每次走一步
   * */
  public static Node midOrDownMidNode(Node head) {
    if (head == null || head.next == null || head.next.next == null) {
      return head;
    }
    Node slow = head.next;
    Node fast = head.next; // note here
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public static void main(String[] args) {
    // 通过结点类定义单链表
    //    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};  // midOrUpMidNode: 6,  midOrDownMidNode:
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //  midOrUpMidNode:5,  midOrDownMidNode:
    SingleLinkedList L = new SingleLinkedList(arr);
    SingleLinkedList.printList(L.head);

    // 找中点和上中点
    Node targetNode1 = midOrUpMidNode(L.head);
    System.out.println("middle or up middle node value: " + targetNode1.value);

    // 找中点和下中点
    Node targetNode2 = midOrDownMidNode(L.head);
    System.out.println("middle or down middle node value: " + targetNode2.value);
  }
}
