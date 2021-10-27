package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

  /*
   * 二叉树
   *          1
   *        2   3
   *      4 5  6 7
   */
  // 递归序:  1 2 4 4 4 2 5 5 5 2 1 3 6 6 6 3 7 7 7 1
  // 先序遍历: 数字第1次出现的顺序: 1 2 4 5 3 6 7
  // 中序遍历: 数字第2次出现的顺序: 4 2 5 1 6 3 7
  // 后序遍历: 数字第3次出现的顺序: 4 5 2 6 7 3 1
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
   * 或者这么想:
   * 中序遍历:
   *  左根右
   *     左根右
   *        左根右
   *            ...
   * 每个右子树都要被分解为左根右
   */
  public static void inOrder(Node head) {
    if (head == null) {
      System.exit(1);
    }
    Node cur = head;

    Stack<Node> stack = new Stack<>();
    while (!stack.isEmpty() || cur != null) {
      if (cur != null) {
        stack.push(cur);
        cur = cur.left;
      } else {
        cur = stack.pop();
        System.out.print(cur.value + " ");
        cur = cur.right;
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
   * 思路: 使用一个size变量记录当前queue的大小，
   * 然后每一个批次豆浆同一层的元素装入到queue中
   * 这样queue中的元素个数最大值就是二叉树的最大宽度
   */
  public static int maxWidth(Node head) {
    if (head == null) return 0;

    Queue<Node> queue = new LinkedList<>();
    queue.add(head);

    int max = 0; // 最大宽度

    while (!queue.isEmpty()) {
      int size = queue.size();
      max = Math.max(size, max);
      for (int i = 1; i <= size; i++) {
        Node cur = queue.poll();
        if (cur.left != null) {
          queue.add(cur.left);
        }
        if (cur.right != null) {
          queue.add(cur.right);
        }
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
    // Condition 'head2 == null' is always 'true' when reached  why ???
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
    //                  1
    //             /       \
    //           2          3
    //        /   \       /   \
    //      4      5     6     7
    //     / \    / \   /
    //     8 9   10 11 12
    System.out.print("二叉树层序遍历: ");
    level(rootLevelNode);

    Node testNode = createTestTree();
    //    int maxWidth = maxWidth(rootNode);
    //    int maxWidth = maxWidth(rootLevelNode);
    int maxWidth = maxWidth(testNode);
    System.out.println("max width: " + maxWidth);

    //    int[] smallArr = {3, 6, 7};
    int[] smallArr = {3, 6, 8};
    Node smallNode = createBinaryTree(smallArr, 0);
    System.out.print("small tree 层序遍历: ");
    level(smallNode);

    boolean contains = isContains(rootNode, smallNode);
    System.out.println("big contains small: " + contains);
  }

  private static Node createTestTree() {
    Node head = new Node(1);
    head.left = new Node(2);
    head.right = new Node(4);
    head.left.right = new Node(3);
    head.left.right.left = new Node(5);
    return head;
  }
}
