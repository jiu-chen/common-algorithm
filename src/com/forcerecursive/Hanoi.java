package com.forcerecursive;

/*
 * 汉诺塔问题:
 * 打印n层汉诺塔从最左边移动到最右边的全部过程
 * 汉诺塔: 由高到低每层面积依次变大，
 * 移动过程中依然要保持汉诺塔结构（大层不能在小层上面）
 */
public class Hanoi {
  public static void hanio(int n) {
    if (n > 0) {
      func(n, "左", "右", "中");
    }
  }
  // 1~i 圆盘 目标是from ~ to, other是另一个
  // 使用递归思想去考虑问题，read: readme.txt
  // refer: images/hanio.png
  public static void func(int i, String start, String end, String other) {
    if (i == 1) {
      System.out.println("Move 1 from " + start + " to " + end);
    } else {
      func(i - 1, start, other, end);
      System.out.println("Move " + i + " from " + start + " to " + end);
      func(i - 1, other, end, start);
    }
  }

  public static void main(String[] args) {
    int n = 3;
    hanio(n);
  }
}
