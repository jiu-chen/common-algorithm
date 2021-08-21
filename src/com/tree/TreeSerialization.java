package com.tree;

import java.util.LinkedList;
import java.util.Queue;
/* *
 * 序列化就是将二叉树以数组的方式存放起来
 * 思路:
 * 类似与先序遍历
 */
public class TreeSerialization {

  // 先序方式序列化: 将二叉树存储为队列
  public static Queue<String> preSerial(BinTree.Node head) {
    Queue<String> ans = new LinkedList<>();
    pres(head, ans);
    return ans;
  }

  public static void pres(BinTree.Node head, Queue<String> ans) {
    if (head == null) {
      ans.add(null);
    } else {
      ans.add(String.valueOf(head.value));
      pres(head.left, ans);
      pres(head.right, ans);
    }
  }

  // 二叉树先序方式反序列化: 将队列转换为二叉树
  public static BinTree.Node preDeserial(Queue<String> prelist) {
    if (prelist == null || prelist.size() == 0) {
      return null;
    }
    return pred(prelist);
  }

  public static BinTree.Node pred(Queue<String> prelist) {
    String value = prelist.poll();
    if (value == null) {
      return null;
    }
    BinTree.Node head = new BinTree.Node(Integer.parseInt(value));
    head.left = pred(prelist);
    head.right = pred(prelist);

    return head;
  }

  // 二叉树层序遍历序列化
  public static Queue<String> levelSerial(BinTree.Node head) {
    Queue<String> ans = new LinkedList<>();
    if (head == null) {
      ans.add(null);
    } else {
      ans.add(String.valueOf(head.value));
      Queue<BinTree.Node> queue = new LinkedList<>();
      queue.add(head);
      while (!queue.isEmpty()) {
        head = queue.poll();
        if (head.left != null) {
          ans.add(String.valueOf(head.left.value));
          queue.add(head.left);
        } else {
          ans.add(null);
        }
        if (head.right != null) {
          ans.add(String.valueOf(head.right.value));
          queue.add(head.right);
        } else {
          ans.add(null);
        }
      }
    }
    return ans;
  }

  public static BinTree.Node levelDeserial(Queue<String> levelList) {
    if (levelList == null || levelList.size() == 0) {
      return null;
    }
    BinTree.Node head = generateNode(levelList.poll());
    Queue<BinTree.Node> queue = new LinkedList<>();
    if (head != null) {
      queue.add(head);
    }
    BinTree.Node node = null;
    while (!queue.isEmpty()) {
      node = queue.poll();
      node.left = generateNode(levelList.poll());
      node.right = generateNode(levelList.poll());
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
    return head;
  }

  private static BinTree.Node generateNode(String value) {
    if (value == null) {
      return null;
    }
    return new BinTree.Node(Integer.parseInt(value));
  }

  /*
   * 1 2 3 n 4 5 n n n n n
   * 二叉树结构:
   *              1
   *          2       3
   *            4   5
   *
   * 层序遍历: 1 2 3 4 5
   * 先序遍历(根左右): 1 2 4 5 3
   */
  public static Queue<String> createLevelQueue() {
    Queue<String> queue = new LinkedList<>();
    queue.add(String.valueOf(1));
    queue.add(String.valueOf(2));
    queue.add(String.valueOf(3));
    queue.add(null);
    queue.add(String.valueOf(4));
    queue.add(String.valueOf(5));
    queue.add(null);
    queue.add(null);
    queue.add(null);
    queue.add(null);
    queue.add(null);
    return queue;
  }

  public static Queue<String> createPreQueue() {
    Queue<String> queue = new LinkedList<>();
    queue.add(String.valueOf(1));
    queue.add(String.valueOf(2));
    queue.add(String.valueOf(4));
    queue.add(null);
    queue.add(null);
    queue.add(String.valueOf(5));
    queue.add(null);
    queue.add(null);
    queue.add(String.valueOf(3));
    queue.add(String.valueOf(6));
    queue.add(null);
    queue.add(null);
    queue.add(String.valueOf(7));
    queue.add(null);
    queue.add(null);
    return queue;
  }

  public static void printQueue(Queue<String> queue) {
    System.out.print("print queue: ");
    while (!queue.isEmpty()) {
      System.out.print(queue.poll() + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    BinTree.Node rootNode = BinTree.createBinaryTree(arr, 0);
    System.out.println("先序方式序列化");
    Queue<String> ans = preSerial(rootNode);
    printQueue(ans);

    System.out.println("先序方式反序列化");
    Queue<String> queue = createPreQueue();
    BinTree.Node root = preDeserial(queue);
    LevelTraverse.level(root);

    System.out.println("层序遍历序列化");
    Queue<String> ans2 = levelSerial(rootNode);
    printQueue(ans2); // pop/poll

    System.out.println("层序遍历反序列化");
    Queue<String> queue2 = createLevelQueue();
    BinTree.Node root2 = levelDeserial(queue2);
    printQueue(queue2);
    LevelTraverse.level(root2);
    BinTree.preOrder(root2);
  }
}
