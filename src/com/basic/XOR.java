package com.basic;

/*
 * ^ 异或符号， 异或操作满足交换律和结合律。a^b=b^a
 * 异或就是无进位相加
 * a ^a = 0
 * a * 0 = a
 *
 * swap a, b:
 * a = a^b,  b=a^b, a=a^b
 *
 *
 * N的二进制bit最右面1的值为: N & ((~N) + 1);
 */
public class XOR {
  public static void main(String[] args) {
    int[] arr = {1, 4, 7, 2, 1, 3, 5, 3, 4, 5, 8, 2, 8};
    int element = printOddTimesNum1(arr);
    System.out.println("target elem: " + element); // 7
    System.out.println("numbers nit 1 int 7: " + bit1Count(arr[2]));
  }

  // 一个数组中只有唯一一个元素出现了奇数次，找出它
  static int printOddTimesNum1(int[] arr) {
    int xor = 0;
    for (int i = 0; i < arr.length; i++) {
      xor = xor ^ arr[i];
    }
    return xor;
  }

  static int bit1Count(int N) {
    int count = 0;
    int rightOne;
    // N:           0101 1100
    // rightOne:    0000 0100
    // N&rightOne:  0101 1000
    while (N != 0) {
      rightOne = N & ((~N) + 1);
      count++;
      N = N ^ rightOne;
    }
    return count;
  }
}
