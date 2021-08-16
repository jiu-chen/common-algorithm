package com.linklist;

/* *
 * 判断两个链表是否相交，有公共节点，
 * 如果相交，返回公共节点，否则返回null
 * 思路:
 * 找到两个链表的最后一个节点，如果地址不相同，则没有交点
 * 否则，使用gap记录两个链表长度上的差值，
 * 让长一点的链表先走gap长度
 * 然后两个链表一次走，当两个链表的节点相等时候就是交点
 * */
public class CrossList {
  public static SingleLinkedList.ListNode commonNodeofLists(
      SingleLinkedList.ListNode head1, SingleLinkedList.ListNode head2) {
    if (head1 == null || head2 == null) {
      return null;
    }
    int gap = 0; // gap表示两个链表长度上的差值，可能为负数
    SingleLinkedList.ListNode cur1 = head1;
    SingleLinkedList.ListNode cur2 = head2;
    while (cur1.next != null) {
      cur1 = cur1.next;
      gap++;
    }
    while (cur2.next != null) {
      cur2 = cur2.next;
      gap--;
    }

    if (cur1 != cur2) { // 不存在交点，否则最后一个节点（对象）必然相等
      return null;
    }

    cur1 = gap > 0 ? head1 : head2; // 谁长，谁的头给cur1
    cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头给cur2

    gap = Math.abs(gap);

    while (gap != 0) {
      cur1 = cur1.next; // 长的链表先走完差值的那一段
      gap--;
    }
    while (cur1 != cur2) { // 不满足条件的时候就是
      cur1 = cur1.next;
      cur2 = cur2.next;
    }
    return cur1;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    SingleLinkedList L1 = new SingleLinkedList(arr);
    SingleLinkedList L2 = new SingleLinkedList(8);
    int i = 1;
    SingleLinkedList.ListNode p1 = L1.head;
    SingleLinkedList.ListNode p2 = L2.head;
    while (i <= 3) {
      p2.next = new SingleLinkedList.ListNode(2 * i);
      i++;
      p1 = p1.next.next;
      p2 = p2.next;
    }
    p2.next = p1;
    System.out.println("print list1: ");
    SingleLinkedList.printList(L1.head);
    System.out.println("print list2: ");
    SingleLinkedList.printList(L2.head);

    SingleLinkedList.ListNode node = commonNodeofLists(L1.head, L2.head);
    if (node == null) {
      System.out.println("no common node in list1 and list2");
    } else {
      System.out.println("common node: " + node.value);
    }
  }
}
