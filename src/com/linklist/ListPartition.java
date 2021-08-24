package com.linklist;

/*
 * 将链表中的节点排列成左边小，中间相等，右边大的形式
 * */
public class ListPartition {
  public static void main(String[] args) {
    //    int[] arr = {1, 6, 3, 4, 7, 5};
    //    int[] arr = {1, 6, 3, 8, 7, 4, 4, 4, 5};
    int[] arr = {5, 3, 5, 1, 4, 7, 6};
    SingleLinkedList L = new SingleLinkedList(arr);
    SingleLinkedList.printList(L.head);
    int pivot1 = 5;
    Node res1 = listPartition1(L.head, pivot1);
    System.out.printf("pivot:%d 方法1: ", pivot1);
    SingleLinkedList.printList(res1);

    int pivot2 = 5;
    System.out.printf("\npivot:%d 方法2: ", pivot2);
    Node res = listPartition2(L.head, pivot2);
    SingleLinkedList.printList(res);
  }

  public static Node listPartition1(Node head, int pivot) {
    int len = 0;
    Node p = head;
    while (p != null) {
      len++;
      p = p.next;
    }
    Node[] nodeArr = new Node[len];
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
   * 方法1:
   * index: 当前数下标
   * left: 左侧位置
   * right: 右侧位置
   * 比较当前数值和pivot，
   * 如果当前数值小于pivot,左侧位置向右移动一位，交换当前数与左侧位置数值，当前位置右移一位
   * 如果等于pivot, 当前位置右移一位
   * 如果大于pivot，右侧位置向左移动一位，交换当前数与右侧位置数值
   * 1 6 3 4 7 5
   * left=-1, right=6, pivot=4, index=0
   * 第0遍: 1 6 3 4 7 5 left=0, right=6, index=1
   * 第2遍: 1 5 3 4 7 6 left=0, right=5, index=1 (!)
   * 第3遍: 1 7 3 4 5 6 left=0, right=4, index=1 (!)
   * 第4遍: 1 4 3 7 5 6 left=0, right=3, index=1 (!)
   * 第5遍: 1 4 3 7 5 6 left=0, right=3, index=2 (!)
   * 第6遍: 1 3 4 7 5 6 left=1, right=3, index=3 (!)
   * inde==max, end while
   **/
  public static void arrPartition(Node[] arr, int pivot) {
    int left = -1;
    int right = arr.length; //
    int index = 0;
    while (index != right) {
      if (arr[index].value < pivot) {
        left++;
        swap(arr, left, index);
        index++;
        //        swap(arr, ++min, index++);
      } else if (arr[index].value == pivot) {
        index++;
      } else {
        right--;
        swap(arr, index, right);
      }
    }
  }

  public static void swap(Node[] arr, int i, int j) {
    int temp = arr[i].value;
    arr[i].value = arr[j].value;
    arr[j].value = temp;
  }

  // 方法2: refer: images/ListPartition2.png
  public static Node listPartition2(Node head, int pivot) {
    Node sH = null;
    Node sT = null;
    Node eH = null;
    Node eT = null;
    Node rH = null;
    Node rT = null;
    Node tmp;
    Node p = head;
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
