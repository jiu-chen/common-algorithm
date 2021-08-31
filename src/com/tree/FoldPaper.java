package com.tree;

/*
 * 题目: 准备一张细纸条，不断地对折，折痕的方向要么为凹，要么凸
 * 打印折痕的方向（凹或者凸）
 * 都收折叠之后就会发现这是一颗
 * 头节点为凹折痕，
 * 每一个左子树的头节点左都为凹折痕，
 * 每一颗右子树的头节点都为凸折痕
 * 的一颗二叉树
 *
 * 中序遍历这个（脑海中的）二叉树 即可得到折痕方向
 */
public class FoldPaper {
  public static void printAllFolds(int N) {
    printProcess(1, N, true);
  }

  // 递归过程，来到某一个节点
  // 递归的底层是栈，这里只用了N个空间，而暴力解法需要用到（2^N-1) 个空间，因为节点总个数就是 (2^N-1)
  // i是节点的层数，N是总层数，dow==true 凹 down==false 凸
  private static void printProcess(int i, int N, boolean down) {
    if (i > N) return;

    // 中序遍历
    printProcess(i + 1, N, true);
    System.out.print(down ? "凹 " : "凸 ");
    printProcess(i + 1, N, false);
  }

  public static void main(String[] args) {
    int N = 3;
    System.out.print("折纸从上到下方向: ");
    printAllFolds(N);
  }
}
