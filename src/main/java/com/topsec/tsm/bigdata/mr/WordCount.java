package com.topsec.tsm.bigdata.mr;

/**
 * Created by hx on 15-10-8.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class WordCount {
  static {
    InputStream ins = WordCount.class.getClassLoader().getResourceAsStream("log.properties");
    PropertyConfigurator.configure(ins);
  }

  @Test
  public void mapreduceTest() throws IOException, ClassNotFoundException, InterruptedException {
    Configuration conf = new Configuration();
    conf.addResource(new Path("/e/develop/bigdata/hadoop/etc/hadoop/core-site.xml"));
    String[] args={"readme.txt","output"};
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    if (otherArgs.length != 2) {
      System.err.println("Usage: wordcount <in> <out>");
      System.exit(2);
    }
    Job job = new Job(conf, "hx-wordcount");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);


  }

}
