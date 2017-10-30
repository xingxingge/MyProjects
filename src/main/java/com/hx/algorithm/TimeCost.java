package com.hx.algorithm;

public class TimeCost {
  /**
   * 在这里我们可以使用分析最高频度的基本操作的方法来得到算法merge的时间复杂度。 我们可以观察到在算法中主要操作有元素的赋值和比较两种操作，而赋值是使用频度最高的
   * 基本造作，因为每个元素都必须要放入新的数组，而并不是每个元素都需要比较才能放入新 的数组。在算法中的每个元素至少赋值一次，至多也赋值一次，因此共有m+n次赋值，因
   * 此算法merge的时间复杂度T(n) = Θ(m+n)。
   * 
   * @param a
   * @param b
   * @return
   */
  public static int[] merge(int[] a, int[] b) {
    int pa = 0;
    int pb = 0;
    int pc = 0;
    int m = a.length;
    int n = b.length;
    int[] c = new int[m + n];

    while (pa < m && pb < n) {
      if (a[pa] < b[pb])
        c[pc++] = a[pa++];
      else
        c[pc++] = b[pb++];
    }

    if (pa < m) // 表明a数组元素还没添加完成，b添加完成了。
      while (pa < m)
        c[pc++] = a[pa++];
    else
      while (pb < n)
        c[pc++] = b[pb++];
    return c;
  }


  // 均摊分析
  public static void function4(int[] a) {
    int p = 0, n = a.length;
    int[] array = new int[n];
    for (int i = 0; i < n; i++) {
      if (a[i] % 2 == 0) // 如果是偶数
        while (p > 0 && array[p - 1] % 2 != 0)
          array[p--] = 0; // 删除前面的奇数
      array[p++] = a[i];
    }
    System.out.println("");
    for (int d : array) {
      System.out.print(d + " ");
    }
    return;
  }

  public static void main(String[] xargs) {
    int[] a = {3, 5, 12, 56, 34};
    int[] b = {4, 3, 4, 6, 23, 56, 24};
    int[] c = merge(a, b);
    for (int d : c) {
      System.out.print(d + " ");
    }
    function4(b);
  }

}