package com.forcerecursive;

/*
 * 题目: images/pokerCardTopoc.png
 */
public class CardsInLine {
  public static int win(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
  }

  private static int f(int[] arr, int L, int R) {
    if (L == R) return arr[L];
    else {
      // arr[L] + s(arr, L + 1, R)  如果拿了L位置，则在[L+1, R]上是后手
      // arr[R] + s(arr, L, R-1)   如果拿了R位置，则在[L, R-1]上是后手
      return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
    }
  }

  private static int s(int[] arr, int L, int R) {
    if (L == R) return 0;
    else {
      // f(arr, L + 1, R)  如果对方拿了L位置，则我在[L+1, R]上先手
      // f(arr, L, R-1)   如果拿了R位置，则我在[L, R-1]上先手
      // 对方会决定对我最不利的，所以取最小值
      return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
    }
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 100, 4};
    System.out.println("winner score: " + win(arr));
    int[] arr2 = {1, 100, 4};
    System.out.println("run 2 -> winner score: " + win(arr2));
  }
}
