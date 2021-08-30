package com.tree;

/*
 * 找出树上两个节点的最小公共祖先
 *
 * 只能有两种情况:
 * 1. o1是o2的LCA, 或o2是o1的LCA
 * 2. o1与o2不互为LCA, 需要往上去找他们的共同祖先
 *
 * 对于1, 不妨假设o1是o2的LCA, 然后画图理解
 * 如果某个节点下的子树既没有o1也没有o2, 则一定返回空
 */
public class CommonAncetor {
  public static Node lowestAncetor(Node head, Node o1, Node o2) {
    if (head == null || head == o1 || head == o2) { // 递归的base条件
      return head;
    }
    Node left = lowestAncetor(head.left, o1, o2);
    Node right = lowestAncetor(head.right, o1, o2);
    // 当左孩子和右孩子都不为空时，就返回父节点
    if (left != null && right != null) {
      return head;
    }
    return left != null ? left : right;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    Node root = BinTree.createBinaryTree(arr, 0);
    Node o5 = root.left.right;
    Node o6 = root.right.left;
    System.out.println("o5和o6的最小公共祖先的值为: " + lowestAncetor(root, o5, o6).value);

    Node o2 = root.left;
    System.out.println("o2和o5的最小公共祖先的值为: " + lowestAncetor(root, o2, o5).value);
  }
}
