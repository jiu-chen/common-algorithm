package com.linklist;

import java.util.HashMap;

public class RandList {
  // 内部类
  public static class Node {
    public int value;
    public Node next = null;
    public Node rand = null;

    public Node(int v) {
      value = v;
    }
  }

  public Node head;

  public RandList(int[] element) {
    // 这里头节点就是首元节点，链表的第一个元素
    // leetcode
    this.head = new Node(element[0]);
    // 不要直接操作head指针
    Node rear = this.head;
    for (int i = 1; i < element.length; i++) {
      rear.next = new Node(element[i]);
      rear = rear.next;
    }

    Node cur = this.head;
    cur.rand = rear;

    while (cur.next != null && cur.next.next != null) {
      cur.next.rand = cur;
      cur = cur.next;
    }
    if (cur.next != null) {
      cur.next.rand = null;
    } else {
      cur.rand = null;
    }
  }

  /*
   * 深拷贝一个带有rand指针的单链表
   * 思路: 使用HashMap. key: Node, value: new Node
   * 先拷贝节点上面的值，然后拷贝节点的next指针和rand指针
   * // 旧Node:  cur
   * // 新Node: map.get(cur)
   * cur.next 是旧Node的下一个旧Node, map.get(cur.next)表示与该旧Node匹配的新Node
   * map.get(cur).next = map.get(cur.next)
   * */
  public static Node copyListWithRand(Node head) {
    HashMap<Node, Node> map = new HashMap<Node, Node>();
    Node cur = head;
    while (cur != null) {
      map.put(cur, new Node(cur.value));
      cur = cur.next;
    }
    cur = head;
    while (cur != null) {
      // 旧  cur
      // 新 map.get(cur)
      map.get(cur).next = map.get(cur.next);
      map.get(cur).rand = map.get(cur.rand);
      cur = cur.next;
    }
    return map.get(head);
  }

  public static void main(String[] args) {

    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    RandList L = new RandList(arr);

    System.out.print("random raw list: ");
    printRandList(L.head);
    System.out.println("\nrandom raw list with rand order: ");
    printListWithRandInfo(L.head);
    Node copy = copyListWithRand(L.head);
    System.out.print("random copied list: ");
    printRandList(copy);
    System.out.println("\nrandom copied list with rand order: ");
    printListWithRandInfo(copy);
  }

  public static void printRandList(Node head) {
    Node cur = head;
    while (cur != null) {
      System.out.print(cur.value + " ");
      cur = cur.next;
    }
  }

  public static void printListWithRandInfo(Node head) {
    Node cur = head;
    while (cur != null) {
      if (cur.rand != null) {
        System.out.println("cur node " + cur.value + " rand node value: " + cur.rand.value + " ");
      } else {
        System.out.println("cur node " + cur.value + " rand node is null");
      }
      cur = cur.next;
    }
  }
}
