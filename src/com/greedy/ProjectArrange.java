package com.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class ProjectArrange {
  public static class Project {
    public int startTime;
    public int endTime;

    public Project(int s, int t) {
      startTime = s;
      endTime = t;
    }
  }

  public static class ProectComparator implements Comparator<Project> {
    /*
     * refer: https://blog.csdn.net/u013066244/article/details/78895747
     * 返回值 负数决定其是否要调整顺序：
     * 如果想升序，那么o1比o2小就是我想要的；所以返回-1，类比成false；表示我不想调整顺序
     * 如果想降序，那么o1比o2小不是我想要的；所以返回1，类比成true；表示我想调整顺序
     */
    @Override
    public int compare(Project p1, Project p2) {
      if (p1.endTime < p2.endTime) {
        return -1; // -1表示不调整顺序
      } else if (p1.endTime > p2.endTime) {
        return 1; // 1表示调整顺序
      } else {
        return 0;
      }
      //      return p1.endTime - p2.endTime;
    }
  }

  /*
   * 思路:
   * 贪心算法不容易证明，可以列举想到的可能想然后举出反例进行排除 比如:
   * 1. 按照会议持续时间的顺序安排会议; 反例: 6-11, 10-13, 11-17, 这样只能安排10-13一场会议
   * 2. 按照会议开始时间安排会议，开始时间早的会议会被安排; 反例: 6-17, 9-10, 11-13, 15-18这样只能安排6-17这一场会议
   * 3. 按照会议结束时间安排会议，结束时间早的会议被优先安排
   */
  /**
   * @param projects 项目数组
   * @param point: 会议室可以使用的时间
   * @return 项目场次
   */
  public static int bestArrange(Project[] projects, int point) {
    Arrays.sort(projects, new ProectComparator());
    int count = 0;
    for (Project project : projects) {
      if (point <= project.startTime) {
        count++;
        point = project.endTime;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Project[] projects = {
      new Project(6, 8),
      new Project(16, 17),
      new Project(16, 18),
      new Project(10, 12),
      new Project(9, 12),
      new Project(7, 9)
    };

    int startPoint = 6;
    int count = bestArrange(projects, startPoint);
    printProjectInfo(projects);
    System.out.println("最多可以宣讲的项目: " + count);
  }

  private static void printProjectInfo(Project[] projects) {
    for (Project project : projects) {
      System.out.println(
          "project start time: " + project.startTime + ", end time: " + project.endTime);
    }
  }
}
