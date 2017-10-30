package com.topsec.tsm.bigdata.hadoop;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by hx on 15-9-29.
 */
public class HadoopUtilsTest {

  static {
//    InputStream ins = HadoopUtilsTest.class.getResourceAsStream("/log.properties");
    InputStream  ins = HadoopUtilsTest.class.getClassLoader().getResourceAsStream("log.properties");
    PropertyConfigurator.configure(ins);
  }

  @Test
  //上传本地文件到hdfs
  public  void createFileByInputStreamTest() throws IOException {
      String srcFilePath="/d/Product/HeBeiMobile/云审计系统已接入的源日志格式20150915/4a从账号变更/venus.v_itpro_log_slavechange.csv";
      File srcFile=new File(srcFilePath);
      String destDirectory="hdfs://192.168.59.12:8020/user/root/";
      String destFilePath=destDirectory+srcFile.getName();
      InputStream ins = new FileInputStream(srcFilePath);
      HadoopUtils.createFileByInputStream(ins, destFilePath);
  }

  @Test
  public void listFilesTest() throws  IOException{
    String directory="hdfs://192.168.59.12:8020/user/root/";
    List<String> hdfsFileList = HadoopUtils.listFiles(directory);
    for (String str : hdfsFileList){
      System.out.println(str);
    }
  }

  @Test
  public void deleteTest() throws  IOException{
    String deleFile= "hdfs://192.168.59.12:8020/user/root/venus.v_itpro_log_slavechange.csv";
    HadoopUtils.delete(deleFile);
  }

  @Test
  public void uploadLocalFileTest() throws  IOException{
    String libpath = System.getProperty("java.library.path");
    System.out.println("libpath=" + libpath);

    String localFile="/d/Product/HeBeiMobile/云审计系统已接入的源日志格式20150915/4a从账号变更/venus.v_itpro_log_slavechange.csv";
    String remoteDirectory= "hdfs://192.168.59.12:8020/user/root/";
    HadoopUtils.uploadLocalFile(localFile, remoteDirectory);
  }
}
