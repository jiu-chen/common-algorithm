package com.forcerecursive;

/*
 * 岛问题
 * 类似题型: https://leetcode-cn.com/problems/surrounded-regions/
 * [题目]
 * 一个矩阵只有0和1两中值，每个位置都可以和自己的上下左右四个位置相连，如果有一片1连在一起，这个部分叫做一个岛
 * 求一个矩阵有多少个岛。
 * 举例:
 * 0 0 1 0 1 0
 * 1 1 1 0 1 0
 * 1 0 0 1 0 0
 * 0 0 0 0 0 0
 *
 * 这个矩阵有三个岛
 * [进阶]
 * 如何设计一个并行算法解决这个问题
 * ==> 并查集
 */
public class IsLand {
  // 复杂度: O(M*N)
  static int M, N;

  public static int countIsLand(int[][] m) {
    M = m.length; // 行数
    N = m[0].length; // 列数
    int res = 0;

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if (m[i][j] == 1) {
          res++;
          infect(m, i, j);
        }
      }
    }
    return res;
  }

  // 将1周围所有是1元素的全部"感染"成为2, 并继续递归
  private static void infect(int[][] m, int i, int j) {
    if (i < 0 || i >= M || j < 0 || j >= N || m[i][j] != 1) {
      return;
    }
    m[i][j] = 2;
    infect(m, i - 1, j);
    infect(m, i + 1, j);
    infect(m, i, j - 1);
    infect(m, i, j + 1);
  }

  public static void main(String[] args) {
    int[][] arr = {
      {0, 0, 0, 0},
      {0, 1, 1, 0},
      {0, 1, 1, 0},
      {0, 0, 0, 0}
    };

    System.out.println("island count: " + countIsLand(arr));

    int[][] arr2 = {
      {0, 0, 1, 0, 1, 0},
      {1, 1, 1, 0, 1, 0},
      {1, 0, 0, 1, 0, 0},
      {0, 0, 0, 0, 0, 0}
    };
    System.out.println("island count: " + countIsLand(arr2));
  }
}
