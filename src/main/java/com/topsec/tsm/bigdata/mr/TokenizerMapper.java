package com.topsec.tsm.bigdata.mr;

/**
 * Created by hx on 15-10-8.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class TokenizerMapper
        extends Mapper<Object, Text, Text, IntWritable> {
  IntWritable one = new IntWritable(1);
  Text word = new Text();

  public void map(Object key, Text value, Context context)
          throws IOException, InterruptedException {
    StringTokenizer itr = new StringTokenizer(value.toString());
    while (itr.hasMoreTokens()) {
      word.set(itr.nextToken());
      context.write(word, one);
    }
  }
}


