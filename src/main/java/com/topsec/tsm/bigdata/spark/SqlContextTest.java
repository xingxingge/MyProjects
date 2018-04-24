package com.topsec.tsm.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Created by hx on 18-4-22.
 */
public class SqlContextTest {
  public static JavaSparkContext  javaSparkContext;
  static {
    String master="spark://192.168.76.53:7077";
    master="local";
    SparkConf sparkConf = new SparkConf()
            .setAppName("spark streaming test")
            .setMaster(master);
    javaSparkContext=new JavaSparkContext(sparkConf);

  }

}
