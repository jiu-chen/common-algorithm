package com.hashtable;

import java.util.HashMap;

/*
 * final && static
 * final
 * 对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；
 * 如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。
 *
 * static
 * 方便在没有创建对象的情况下来进行调用（方法/变量）。
 * 很显然，被static关键字修饰的方法或者变量不需要依赖于对象(this)来进行访问，只要类被加载了，就可以通过类名去进行访问。
 *
 * <java编程思想>
 * static方法就是没有this的方法。在static方法内部不能调用非静态方法(因为非static方法依赖对象)，反过来是可以的。
 * 而且可以在没有创建任何对象的前提下，仅仅通过类本身来调用static方法。这实际上正是static方法的主要用途。
 *
 */

/*
 * 设计RandomPool结构
 *
 * 设计一种结构，在该结构上有如下三种功能:
 * insert(key): 将某个key加入到该结构，做到不重复加入
 * delete(key): 将原本在结构中的某个key移除
 * getRandom(): 等概率随机返回结构中的任何一个key
 *
 * [要求]
 * Insert, delete和getRandom方法的时间复杂度都是O(1)
 *
 * refer: images/pool.png
 */

public class RandomPool<K> {
  private final HashMap<K, Integer> keyIndexMap;
  private final HashMap<Integer, K> indexKeyMap;

  private int size;

  public RandomPool() {
    this.keyIndexMap = new HashMap<K, Integer>();
    this.indexKeyMap = new HashMap<Integer, K>();
    this.size = 0;
  }

  public void insert(K key) {
    // 不重复插入
    if (!this.keyIndexMap.containsKey(key)) {
      this.keyIndexMap.put(key, this.size);
      this.indexKeyMap.put(this.size++, key);
    }
  }

  public void delete(K key) {
    if (this.keyIndexMap.containsKey(key)) {
      int deleteIndex = this.keyIndexMap.get(key);
      int lastIndex = --this.size;
      K lastKey = this.indexKeyMap.get(lastIndex);
      this.keyIndexMap.put(lastKey, deleteIndex);
      this.indexKeyMap.put(deleteIndex, lastKey);
      this.keyIndexMap.remove(key);
      this.indexKeyMap.remove(lastIndex);
    }
  }

  public K getRandom() {
    if (this.size == 0) {
      return null;
    }
    int randomIndex = (int) (Math.random() * this.size);
    return this.indexKeyMap.get(randomIndex);
  }

  public String toString() {
    return "pool: " + this.indexKeyMap + ", pool size: " + this.size;
  }

  public static void main(String[] args) {
    RandomPool<String> pool = new RandomPool<>();
    pool.insert("a");
    pool.insert("b");
    pool.insert("b");
    pool.insert("v");

    System.out.println(pool);

    String str = pool.getRandom();
    System.out.println("random result: " + str);

    pool.delete("v");
    System.out.println(pool);
  }
}
