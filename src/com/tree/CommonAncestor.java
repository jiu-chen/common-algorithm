package com.tree;

/*
 * 找出树上两个节点的最小公共祖先
 *
 * 只能有两种情况:
 * 1. o1是o2的LCA, 或o2是o1的LCA
 * 2. o1与o2不互为LCA, 需要往上去找他们的共同祖先
 *
 * 对于1, 不妨假设o1是o2的LCA, 然后画图理解
 * // 2021 1110 画图理解
 * 如果某个节点下的子树既没有o1也没有o2, 则一定返回空
 */
public class CommonAncestor {
  public static Node lowestAncestor(Node root, Node o1, Node o2) {
    if (root == null || root == o1 || root == o2) { // 递归的base条件
      return root;
    }
    Node left = lowestAncestor(root.left, o1, o2);
    Node right = lowestAncestor(root.right, o1, o2);
    // 如果left为空，说明这两个节点在root结点的右子树上，我们只需要返回右子树查找的结果即可
    if (left == null) return right;
    // 如果right为空，说明这两个节点在root结点的左子树上，我们只需要返回左子树查找的结果即可
    if (right == null) return left;
    // 如果left和right都不为空，说明这两个节点一个在root的左子树上一个在root的右子树上，
    // 我们只需要返回cur结点即可。
    return root;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    Node root = BinTree.createBinaryTree(arr, 0);
    Node o5 = root.left.right;
    Node o6 = root.right.left;
    System.out.println("o5和o6的最小公共祖先的值为: " + lowestAncestor(root, o5, o6).value);

    Node o2 = root.left;
    System.out.println("o2和o5的最小公共祖先的值为: " + lowestAncestor(root, o2, o5).value);
  }
}
