package com.linklist;

/*
 * 找到链表的第一个入环节点，如果无环，返回null
 * 思路: refer: images/CircleList*.png
 **/
public class LoopLinkedList {
  public static class ListNode {
    int value;
    ListNode next;

    public ListNode(int v) {
      value = v;
    }
  }

  public ListNode head;

  public LoopLinkedList(int[] element) {
    // 有头节点: 这里头节点不同于首元节点
    //    this(); // 初始头结点为空
    //    Node rear = this.head;

    // 这里头节点就是首元节点，链表的第一个元素
    // leetcode
    this.head = new ListNode(element[0]);
    // 不要直接操作head指针
    ListNode rear = this.head;
    for (int i = 1; i < element.length; i++) {
      rear.next = new ListNode(element[i]);
      rear = rear.next;
    }
    //    rear.next = head.next.next.next; // 构造链表中的环, 注释后则没有环
    rear.next = head.next.next.next; // 构造链表中的环
  }

  public static ListNode firstNodeOfLoopList(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return null;
    }
    ListNode slow, fast;
    slow = head.next;
    fast = head.next.next;
    while (fast != null && slow != fast) {
      slow = slow.next;
      fast = fast.next.next;
    }
    if (slow != fast) { // 没有环
      return null;
    }
    fast = head;
    while (slow != null && fast != null && slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    if (slow != fast) {
      return null;
    }
    return slow;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    LoopLinkedList L = new LoopLinkedList(arr);
    ListNode node = firstNodeOfLoopList(L.head);
    if (node == null) {
      System.out.println("this is not circle list");
    } else {
      System.out.println("this is circle list, circle first node: " + node.value);
    }
    System.out.println("print list: ");
    printList(L.head);
  }

  static void printList(ListNode head) {
    ListNode cur = head;
    while (cur != null) {
      if (cur.next != null) {
        System.out.println("current node: " + cur.value + ", next node: " + cur.next.value);
      } else {
        System.out.println("current node: " + cur.value + ", next node is null");
      }
      if (cur.value == 10) {
        break;
      }
      cur = cur.next;
    }
  }
}
