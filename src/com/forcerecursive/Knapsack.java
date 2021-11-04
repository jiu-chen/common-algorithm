package com.forcerecursive;

/*
 * 给定两个长度都为N的数组weights和values, weights[i]和values[i]分别代表
 * i号物品的重量和价值。给定一个正数bag, 表示一个载重bag的袋子，你装的物品不能超过
 * 这个重量。返回你能装下最多的价值是多少？
 */
public class Knapsack {

  // n: 第一个物品 W: 背包重量
  // weights: 物品重量数组，values 价值重量数组
  // 倒序计算
  public static int package1(int[] weights, int[] values, int n, int W) {
    if (n == 0 || W == 0) // 当物品数量为0，或者背包容量为0时，最优解为0
    {
      return 0;
    }
    if (weights[n - 1] > W) {
      return package1(weights, values, n - 1, W);
    } else {
      return Math.max(
          package1(weights, values, n - 1, W), // 当前物品不放入袋子中，递归下一个物品
          values[n - 1]
              + package1(weights, values, n - 1, W - weights[n - 1]) // 当前物品放入袋子中，继续递归下一个物品
          );
    }
  }

  // dp 0-1背包问题
  // dp[i][j] 表示第i个物品装进限重为j的背包可以获得的最大价值
  // i-1表示数组的下标
  public static int package02(int[] weights, int[] values, int n, int W) {
    int[][] dp = new int[n + 1][W + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= W; j++) {
        if (j < weights[i - 1]) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
        }
      }
    }
    return dp[n][W];
  }

  // todo: 01背包问题空间优化
  // https://blog.nowcoder.net/n/c1e86561094844308cbe97cf71c88425?f=comment
  public static int package03(int[] weights, int[] values, int n, int W) {
    return 0;
  }

  public static void main(String[] args) {
    int[] weights = {1, 2, 3, 5};
    int[] values = {2, 9, 2, 7};
    int bag = 7;
    System.out.println("max value: " + package1(weights, values, 4, bag));
    System.out.println("max value: " + package02(weights, values, 4, bag)); // 15=8+7
  }
}
