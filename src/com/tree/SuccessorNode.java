package com.tree;

public class SuccessorNode {
  // 查找某个节点的中序遍历的后继节点，没有则返回空。
  /*
   * 1.节点的右子树不为空，则返回右子树中最左的节点；
   * 2.节点的右子树为空且节点是父节点的左节点，则返回父节点即可；
   * 3.节点右子树为空且是父节点的右节点，则一直向上查找，直至找到某个节点是其父节点的左节点，返回该父节点，找不到则返回空。
   */
  public static class Node {
    public Node left = null;
    public Node right = null;
    public Node parent = null; // 父节点
    public int value;

    public Node(int v) {
      value = v;
    }
  }

  public static Node getSuccessorNode(Node node) {
    if (node == null) {
      return null;
    }

    if (node.right != null) { // 右子树不为空，情况1
      node = node.right;
      while (node.left != null) {
        node = node.left;
      }
      return node;
      //    } else if (node.parent.left == node) { // 情况2
      //      return node.parent;
    } else {
      while (node.parent != null && node.parent.left != node) { // 情况3 && 情况2
        node = node.parent;
      }
      return node.parent;
    }
  }

  public static void main(String[] args) {
    Node root = createTree();

    System.out.print("递归中序遍历: ");
    inorder(root);
    System.out.println();
    System.out.println("节点7的后继节点值为: " + getSuccessorNode(root.left.right.right).value);
    System.out.println("节点3的后继节点值为: " + getSuccessorNode(root.left).value);
  }

  private static Node createTree() {
    Node o1 = new Node(1);
    Node o3 = new Node(3);
    o1.left = o3;
    Node o5 = new Node(5);
    o3.right = o5;
    Node o7 = new Node(7);
    o5.right = o7;
    o7.parent = o5;
    o5.parent = o3;
    o3.parent = o1;

    return o1;
  }

  private static void inorder(Node root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(" " + root.value);
    inorder(root.right);
  }
}
