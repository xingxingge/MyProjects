package com.topsec.tsm.bigdata.spark.ml;

import com.topsec.tsm.bigdata.spark.SqlContextTest;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 16-8-4.
 */
public class MlibBasicStatisticsTest implements Serializable {
  public static JavaSparkContext javaSparkContext;
  @Test
  public void basicStatistics(){
    javaSparkContext= SqlContextTest.javaSparkContext;

    Vector v1 = Vectors.dense(new double[]{1,2,3,4,5,6});
    Vector v2 = Vectors.dense(new double[]{3,3,7,7,7,8});
    List<Vector> vectorList=new ArrayList<>();
    vectorList.add(v1);
    vectorList.add(v2);
    JavaRDD<Vector> mat = javaSparkContext.parallelize(vectorList);
    MultivariateStatisticalSummary summary = Statistics.colStats(mat.rdd());
    System.out.println(summary.mean()); // 均值
    System.out.println(summary.variance()); //标准差
    System.out.println(summary.numNonzeros()); //每列非零数


  }


}
