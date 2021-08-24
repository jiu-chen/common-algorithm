package com.tree;

public class ContainTree {
  public static boolean containsTree(Node big, Node small) {
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
    if (head1 == null && head2 == null) {
      return true;
    }
    if (head1.value != head2.value) {
      return false;
    }

    return isSameValueStructure(head1.left, head2.left)
        && isSameValueStructure(head1.right, head2.right);
  }
}
