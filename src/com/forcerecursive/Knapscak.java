package com.forcerecursive;

/*
 * 给定两个长度都为N的数组weights和values, weights[i]和values[i]分别代表
 * i号物品的重量和价值。给定一个正数bag, 表示一个载重bag的袋子，你装的物品不能超过
 * 这个重量。返回你能装下最多的价值是多少？
 */
public class Knapscak {
  public static int maxValue(int[] weights, int[] values, int bag) {
    return process(weights, values, 0, 0, bag);
  }

  // i... 的货物自由选择，形成最大价值返回
  // 重要永远不要超过bag
  // 之前做的决定，所达到的重量，alreadyWeight

  // ! bug
  private static int process(int[] weights, int[] values, int i, int alreadyWeight, int bag) {
    if (alreadyWeight > bag) {
      return 0;
    }
    if (i == weights.length) {
      return 0;
    }
    return Math.max(
        process(weights, values, i + 1, alreadyWeight, bag), // 当前物品不放入袋子中，递归下一个物品
        values[i]
            + process(
                weights, values, i + 1, alreadyWeight + weights[i], bag) // 当前物品放入袋子中，继续递归下一个物品
        );
  }

  // dp
  public static int maxValue2(int[] c, int[] p, int bag) {
    int[][] dp = new int[c.length + 1][bag + 1];
    for (int i = c.length - 1; i >= 0; i--) {
      for (int j = bag; j >= 0; j--) {
        dp[i][j] = dp[i + 1][j];
        if (j + c[j] <= bag) {
          dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
        }
      }
    }
    return dp[0][0];
  }

  public static void main(String[] args) {
    int[] weights = {1, 2, 3, 5};
    int[] values = {8, 4, 2, 7};
    int bag = 7;
    System.out.println("max value: " + maxValue(weights, values, bag));
    //    System.out.println("max value: " + maxValue2(weights, values, bag));
  }
}
