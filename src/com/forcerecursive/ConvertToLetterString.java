package com.forcerecursive;

public class ConvertToLetterString {
  public static void main(String[] args) {
    // String str = "0111"; // 0
    String str = "2111";
    int count = number(str);
    System.out.println("count: " + count);
  }

  private static int number(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }
    char[] charArr = str.toCharArray();
    return process(charArr, 0);
  }

  // i之前的位置，如果转化已经做过决定了
  // i...有多少种转化的结果
  private static int process(char[] charArr, int i) {
    if (i == charArr.length) {
      return 1;
    }
    if (charArr[i] == '0') {
      return 0;
    }
    if (charArr[i] == '1') {
      int count = process(charArr, i + 1); // i自己作为单独的部分，后续有多少种做法
      if (i + 1 < charArr.length) {
        count += process(charArr, i + 2); // (i和i+1) 作为整体，后续有多少种做法
      }
      return count;
    }

    if (charArr[i] == '2') {
      int count = process(charArr, i + 1); // i自己作为单独的部分，后续有多少种做法
      // (i和i+1) 作为整体 并且没有超过26, 后续有多少种方法
      if (i + 1 < charArr.length && (charArr[i + 1] > '0' && charArr[i + 1] <= '6')) {
        count += process(charArr, i + 2);
      }
      return count;
    }

    // charArr[i] == 3~9
    return process(charArr, i + 1);
  }
}
