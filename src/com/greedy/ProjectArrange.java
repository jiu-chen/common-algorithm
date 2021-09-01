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
    // return
    // > 0：升序
    // < 0：降序
    // ===0: 相同
    @Override
    public int compare(Project p1, Project p2) {
      return p1.endTime - p2.endTime;
    }
  }

  /*
   * 思路:
   * 贪心算法不容易证明，可以列举想到的可能想然后举出反例进行排除 比如:
   * 1. 按照会议持续时间的顺序安排会议; 反例: 6-11, 10-13, 11-17, 这样只能安排10-13一场会议
   * 2. 按照会议开始时间安排会议，开始时间早的会议会被安排; 反例: 6-17, 9-10, 11-13, 15-18这样只能安排6-17这一场会议
   * 3. 按照会议结束时间安排狐疑，结束时间早的会议被优先安排
   */
  /**
   * @param projects 项目数组
   * @param point: 会议室可以使用的时间
   * @return 项目场次
   */
  public static int bestArrange(Project[] projects, int point) {
    Arrays.sort(projects, new ProectComparator());
    int count = 0;
    for (int i = 0; i < projects.length; i++) {
      if (point <= projects[i].startTime) {
        count++;
        point = projects[i].endTime;
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
