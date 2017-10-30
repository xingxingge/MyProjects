package com.topsec.tsm.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;

import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.api.java.*;
import scala.Tuple2;
import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by hx on 16-8-5.
 */
public class SparkStreamingTest implements Serializable {
  public static JavaStreamingContext jssc;
  static {
    String master="spark://192.168.76.53:7077";
    master="local";
    SparkConf sparkConf = new SparkConf()
            .setAppName("spark streaming test")
            .setMaster(master);
    jssc=new JavaStreamingContext(sparkConf, Durations.seconds(3));

  }

  @Test
  public void networkWordCount() {
    JavaReceiverInputDStream<String> lines = jssc.socketTextStream("localhost", 9999);
    // Split each line into words
//    JavaDStream<String> words;words=null;
// Split each line into words
    JavaDStream<String> words = lines.flatMap(
            new FlatMapFunction<String, String>() {
              @Override public Iterable<String> call(String x) {
                return Arrays.asList(x.split(" "));
              }
            });


// Count each word in each batch
    JavaPairDStream<String, Integer> pairs = words.mapToPair(
            new PairFunction<String, String, Integer>() {
              @Override public Tuple2<String, Integer> call(String s) {
                return new Tuple2<String, Integer>(s, 1);
              }
            });
    JavaPairDStream<String, Integer> wordCounts = pairs.reduceByKey(
            new Function2<Integer, Integer, Integer>() {
              @Override public Integer call(Integer i1, Integer i2) {
                return i1 + i2;
              }
            });

// Print the first ten elements of each RDD generated in this DStream to the console
    wordCounts.print();
    jssc.start();              // Start the computation
    jssc.awaitTermination();   // Wait for the computation to terminate
  }
}
