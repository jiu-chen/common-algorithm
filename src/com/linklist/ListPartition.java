package com.linklist;

/*
 * 将链表排列成左边小，中间相等，右边大的形式
 * */
public class ListPartition {
  public static void main(String[] args) {
    int[] arr = {1, 6, 3, 4, 7, 5};
    SingleLinkedList L = new SingleLinkedList(arr);
    SingleLinkedList.printList(L.head);
    int pivot1 = 4;
    SingleLinkedList.ListNode res1 = listPartition1(L.head, pivot1);
    System.out.printf("pivot:%d 方法1: ", pivot1);
    SingleLinkedList.printList(res1);

    int pivot2 = 5;
    System.out.printf("\npivot:%d 方法2: ", pivot2);
    SingleLinkedList.ListNode res = listPartition2(L.head, pivot2);
    SingleLinkedList.printList(res);
  }

  public static SingleLinkedList.ListNode listPartition1(
      SingleLinkedList.ListNode head, int pivot) {
    int len = 0;
    SingleLinkedList.ListNode p = head;
    while (p != null) {
      len++;
      p = p.next;
    }
    SingleLinkedList.ListNode[] nodeArr = new SingleLinkedList.ListNode[len];
    p = head;
    for (int i = 0; i < nodeArr.length; i++) {
      nodeArr[i] = p;
      p = p.next;
    }
    arrPartition(nodeArr, pivot);
    int j;
    for (j = 1; j < nodeArr.length; j++) {
      nodeArr[j - 1].next = nodeArr[j];
    }
    nodeArr[j - 1].next = null;
    return nodeArr[0];
  }

  /*
   * 1 6 3 4 7 5
   * min=-1, max=6, pivot=4, index=0
   * 第1遍: 1 6 3 4 7 5 min=0, max=6, index=1
   * 第2遍: 1 5 3 4 7 6 min=0, max=5, index=1 (!)
   * 第3遍: 1 7 3 4 5 6 min=0, max=4, index=1 (!)
   * 第4遍: 1 4 3 7 5 6 min=0, max=3, index=1 (!)
   * 第5遍: 1 4 3 7 5 6 min=0, max=3, index=2 (!)
   * 第6遍: 1 3 4 7 5 6 min=1, max=3, index=3 (!)
   * inde==max, end while
   **/
  public static void arrPartition(SingleLinkedList.ListNode[] arr, int pivot) {
    int min = -1;
    int max = arr.length;
    int index = 0;
    while (index != max) {
      if (arr[index].value < pivot) {
        min++;
        swap(arr, min, index);
        index++;
        //        swap(arr, ++min, index++);
      } else if (arr[index].value == pivot) {
        index++;
      } else {
        max--;
        swap(arr, index, max);
      }
    }
  }

  public static void swap(SingleLinkedList.ListNode[] arr, int i, int j) {
    int temp = arr[i].value;
    arr[i].value = arr[j].value;
    arr[j].value = temp;
  }

  public static SingleLinkedList.ListNode listPartition2(
      SingleLinkedList.ListNode head, int pivot) {
    SingleLinkedList.ListNode sH = null;
    SingleLinkedList.ListNode sT = null;
    SingleLinkedList.ListNode eH = null;
    SingleLinkedList.ListNode eT = null;
    SingleLinkedList.ListNode rH = null;
    SingleLinkedList.ListNode rT = null;
    SingleLinkedList.ListNode tmp;
    SingleLinkedList.ListNode p = head;
    while (p != null) {
      tmp = p.next;
      p.next = null;
      if (p.value < pivot) {
        if (sH == null) {
          sH = p;
          sT = p;
        } else {
          sT.next = p;
          sT = p;
        }
      } else if (p.value == pivot) {
        if (eH == null) {
          eH = p;
          eT = p;
        } else {
          eT.next = p;
          eT = p;
        }
      } else {
        if (rH == null) {
          rH = p;
          rT = p;
        } else {
          rT.next = p;
          rT = p;
        }
      }
      p = tmp;
    }

    if (sT != null) {
      sT.next = eH;
      if (eT == null) {
        eT = sT;
      }
    }
    if (eT != null) {
      eT.next = rH;
    }
    //
    //    if (sH != null) {
    //      return sH;
    //    }
    //    if (eH != null) {
    //      return eH;
    //    }
    //    return rH;
    return (sH != null) ? sH : (eH != null ? eH : rH);
  }
}
