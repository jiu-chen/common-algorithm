package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
  public int value;
  public Node left = null;
  public Node right = null;

  public Node(int v) {
    value = v;
  }
}

public class BinTree {

  // 递归发创建二叉树
  public static Node createBinaryTree(int[] arr, int index) {
    Node treeNode = null;
    if (index < arr.length) {
      treeNode = new Node(arr[index]);
      // 对于顺序存储的完全二叉树，如果某个节点的索引为index，其对应的左子树的索引为2*index+1，右子树为2*index+1
      treeNode.left = createBinaryTree(arr, 2 * index + 1);
      treeNode.right = createBinaryTree(arr, 2 * index + 2);
    }
    return treeNode;
  }

  // 递归序
  // 先序遍历 * 中序遍历 * 后序遍历
  public static void f(Node head) {
    if (head == null) {
      return;
    }
    // 先序遍历: 根左右
    //    System.out.print(head.value + " ");
    f(head.left);
    // 中序遍历: 左根右
    //    System.out.print(head.value + " ");
    f(head.right);
    // 后序遍历: 左右根
    System.out.print(head.value + " ");
  }

  // 非递归方法 遍历
  // 先序遍历: 根左右
  /* *
   * 思路: 压栈
   * 1. 弹出栈就打印
   * 2. 如果有右节点，压右节点
   * 3. 如果有左节点，压左节点
   */
  public static void preOrder(Node head) {
    if (head == null) {
      System.exit(1);
    }
    Stack<Node> stack = new Stack<>();
    stack.push(head);
    while (!stack.empty()) {
      head = stack.pop();
      System.out.print(head.value + " ");
      if (head.right != null) {
        stack.add(head.right);
      }
      if (head.left != null) {
        stack.add(head.left);
      }
    }
  }

  /* *
   *  后续遍历:
   *  由于先序遍历是: 根左右
   *  不妨构造一个 `根右左`的遍历，
   *  然后将其倒序输出，即是直线后续遍历: `左右根`
   */
  public static void postOrder(Node head) {
    if (head == null) {
      System.exit(1);
    }
    Stack<Node> stack = new Stack<>();
    Stack<Node> stack2 = new Stack<>();
    stack.add(head);
    while (!stack.empty()) {
      head = stack.pop();
      //      System.out.println(head.value + " ");  //根右左

      stack2.add(head);

      if (head.left != null) {
        stack.add(head.left);
      }
      if (head.right != null) {
        stack.add(head.right);
      }
    }
    while (!stack2.empty()) {
      System.out.print(stack2.pop().value + " ");
    }
  }

  /* *
   * 中序遍历: 左根右 (多看多想)
   * 1. 整条左边界依次入栈
   * 2. 如果1无法继续，弹出节点并打印，然后执行右子树
   *
   */
  public static void inOrder(Node head) {
    if (head == null) {
      System.exit(1);
    }

    Stack<Node> stack = new Stack<>();
    while (!stack.isEmpty() || head != null) {
      if (head != null) {
        stack.push(head);
        head = head.left;
      } else {
        head = stack.pop();
        System.out.print(head.value + " ");
        head = head.right;
      }
    }
    System.out.println();
  }

  /* *
   * 二叉树的层序遍历:
   * 弹出即打印，然后添加弹出节点的左右节点
   */
  static void level(Node head) {
    Queue<Node> queue = new LinkedList<>();
    queue.add(head);

    while (!queue.isEmpty()) {
      Node cur = queue.poll();
      System.out.print(cur.value + " ");
      if (cur.left != null) {
        queue.add(cur.left);
      }
      if (cur.right != null) {
        queue.add(cur.right);
      }
    }
    System.out.println();
  }

  /*
   * 求二叉树最大层宽度
   */
  public static int maxWidth(Node head) {
    if (head == null) return 0;

    Queue<Node> queue = new LinkedList<>();
    queue.add(head);
    Node curEnd = head; // 标记当前层最右节点
    Node nextEnd = null; // 标记下一层最右节点

    int max = 0; // 最大宽度
    int curLevelNodes = 0; // 当前层节点数

    while (!queue.isEmpty()) {
      Node cur = queue.poll();
      if (cur.left != null) {
        queue.add(cur.left);
        nextEnd = cur.left;
      }
      if (cur.right != null) {
        queue.add(cur.right);
        nextEnd = cur.right;
      }
      curLevelNodes++;

      if (cur == curEnd) {
        max = Math.max(max, curLevelNodes);
        curLevelNodes = 0;
        curEnd = nextEnd;
      }
    }
    return max;
  }

  /*
   * 判断一个二叉树是否包含另一个二叉树
   * 不断的往下递归
   */
  public static boolean isContains(Node big, Node small) {
    if (small == null) {
      return true;
    }

    // 到这里说明 small !=null
    if (big == null) {
      return false;
    }

    // 到这里说明 big != null && small!=null

    if (isSameValueStructure(big, small)) {
      return true;
    }
    return isSameValueStructure(big.left, small) || isSameValueStructure(big.right, small);
  }

  private static boolean isSameValueStructure(Node head1, Node head2) {
    if (head1 == null && head2 != null) {
      return false;
    }
    if (head1 != null && head2 == null) {
      return false;
    }
    // 实际上到这里 head2 肯定是null
    if (head1 == null && head2 == null) {
      return true;
    }
    if (head1.value != head2.value) {
      return false;
    }

    return isSameValueStructure(head1.left, head2.left)
        && isSameValueStructure(head1.right, head2.right);
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    Node rootNode = createBinaryTree(arr, 0);
    System.out.print("递归遍历: ");
    f(rootNode);
    System.out.print("\n非递归先序遍历: ");
    preOrder(rootNode);
    System.out.print("\n非递归后序遍历: ");
    postOrder(rootNode);
    System.out.print("\n非递归中序遍历: ");
    inOrder(rootNode);

    // 层次遍历, 宽度优先遍历
    int[] arrLevel = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    // 多看构建方法， 递归的奥秘
    // index就是数组的下标
    Node rootLevelNode = BinTree.createBinaryTree(arrLevel, 0);
    //                 1
    //          2            3
    //      4       5     6     7
    //     8 9    10 11 12
    System.out.print("二叉树层序遍历: ");
    level(rootLevelNode);

    int maxWidth = maxWidth(rootLevelNode);
    System.out.println("max width: " + maxWidth);

    //    int[] smallArr = {3, 6, 7};
    int[] smallArr = {3, 6, 8};
    Node smallNode = createBinaryTree(smallArr, 0);
    System.out.print("small tree 层序遍历: ");
    level(smallNode);

    boolean contains = isContains(rootNode, smallNode);
    System.out.println("big contains small: " + contains);
  }
}
