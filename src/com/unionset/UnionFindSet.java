package com.unionset;

import java.util.*;

class Element<V> {
  public V value;

  public Element(V value) {
    this.value = value;
  }

  public String toString() {
    return (String) this.value;
  }
}

public class UnionFindSet<V> {
  public HashMap<V, Element<V>> elementMap;
  // key 某个元素value 该元素的父
  public HashMap<Element<V>, Element<V>> fatherMap;
  // key 某个集合的代表元素，value 该集合大小
  public HashMap<Element<V>, Integer> sizeMap;

  public UnionFindSet(List<V> list) {
    elementMap = new HashMap<>();
    fatherMap = new HashMap<>();
    sizeMap = new HashMap<>();
    for (V value : list) {
      Element<V> element = new Element<V>(value);
      elementMap.put(value, element);
      fatherMap.put(element, element); // 构造函数中每个元素都指向自己
      sizeMap.put(element, 1);
    }
  }

  public boolean isSameSet(V a, V b) {
    if (elementMap.containsKey(a) || elementMap.containsKey(b)) {
      return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
    }
    return false;
  }

  // 找到集合的头
  // 给定一个element, 往上一直找，把代表元素返回
  private Element<V> findHead(Element<V> element) {
    Stack<Element<V>> stack = new Stack<>();
    while (element != fatherMap.get(element)) {
      stack.push(element);
      element = fatherMap.get(element);
    }
    // 到这里说明 代表元素找到，赋值为element
    while (!stack.empty()) {
      // 让路径上的所有元素都指向代表元素
      fatherMap.put(stack.pop(), element);
    }
    return element;
  }

  public void union(V a, V b) {
    if (elementMap.containsKey(a) || elementMap.containsKey(b)) {
      Element<V> aF = findHead(elementMap.get(a));
      Element<V> bF = findHead(elementMap.get(b));
      if (aF != bF) { // else a与b在一个集合中，无须union
        Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
        // small 取"短"的那个集合
        Element<V> small = (big == aF) ? bF : aF;
        fatherMap.put(small, big);
        sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
        sizeMap.remove(small);
      }
    }
  }

  public static void main(String[] args) {
    List<String> arrayList = new ArrayList<>();
    arrayList.add("a");
    arrayList.add("b");
    arrayList.add("c");
    arrayList.add("d");

    UnionFindSet<String> uSet = new UnionFindSet<>(arrayList);

    System.out.println("a and b is in same set: " + uSet.isSameSet("a", "b"));
    uSet.union("a", "b");
    uSet.union("a", "d");
    uSet.union("b", "c");
    System.out.println("a and c is in same set: " + uSet.isSameSet("a", "c"));

    uSet.printSet();
  }

  private void printSet() {
    // 方法一 在for-each循环中使用entries来遍历
    for (Map.Entry<Element<V>, Element<V>> entry : this.fatherMap.entrySet()) {
      System.out.println(
          "element = "
              + entry.getKey().toString()
              + ", father element = "
              + entry.getValue().toString());
    }
  }
}
