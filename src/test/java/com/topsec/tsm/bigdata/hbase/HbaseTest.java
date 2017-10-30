package com.topsec.tsm.bigdata.hbase;

import org.junit.Test;

public class HbaseTest {

  @Test
  public void hbaseTest() throws Exception {
    // 删除表
    HbaseTemplate.deleteTable("blog2");

    // 创建表
    String tableName = "blog2";
    String[] family = {"article", "author"};
    HbaseTemplate.creatTable(tableName, family);

    // 为表添加数据

    String[] column1 = {"title", "content", "tag"};
    String[] value1 =
        {
            "Head First HBase",
            "HBase is the Hadoop database. Use it when you need random, realtime read/write access to your Big Data.",
            "Hadoop,HBase,NoSQL"};
    String[] column2 = {"name", "nickname"};
    String[] value2 = {"nicholas", "lee"};
    HbaseTemplate.addData("rowkey1", "blog2", column1, value1, column2, value2);
    HbaseTemplate.addData("rowkey2", "blog2", column1, value1, column2, value2);
    HbaseTemplate.addData("rowkey3", "blog2", column1, value1, column2, value2);

    // 遍历查询
    HbaseTemplate.getResultScann("blog2", "rowkey4", "rowkey5");
    // 根据row key范围遍历查询
    HbaseTemplate.getResultScann("blog2", "rowkey4", "rowkey5");

    // 查询
    HbaseTemplate.getResult("blog2", "rowkey1");

    // 查询某一列的值
    HbaseTemplate.getResultByColumn("blog2", "rowkey1", "author", "name");

    // 更新列
    HbaseTemplate.updateTable("blog2", "rowkey1", "author", "name", "bin");

    // 查询某一列的值
    HbaseTemplate.getResultByColumn("blog2", "rowkey1", "author", "name");

    // 查询某列的多版本
    HbaseTemplate.getResultByVersion("blog2", "rowkey1", "author", "name");

    // 删除一列
    HbaseTemplate.deleteColumn("blog2", "rowkey1", "author", "nickname");

    // 删除所有列
    HbaseTemplate.deleteAllColumn("blog2", "rowkey1");

    // 删除表
    HbaseTemplate.deleteTable("blog2");

  }
}
