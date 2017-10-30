package com.topsec.tsm.hbmobile;

import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hx on 15-9-25.
 */
public class HebeiMobileCsv {

  //返回文本读类
  public BufferedReader readFileBuffer(String filePath) throws FileNotFoundException {
    FileInputStream is = new FileInputStream(new File(filePath));
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    return br;
  }

  //返回文本写类
  public BufferedWriter writeFileBuffer(String filePath) throws IOException {
    FileOutputStream fs = new FileOutputStream(new File(filePath));
    OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(new File(filePath)));
    return new BufferedWriter(osw);
  }

  //4a从账号解析

  public StringBuffer readSlave4AFile(BufferedReader br) throws IOException {
    StringBuffer sb = new StringBuffer();
    String str = "";
    while ((str = br.readLine()) != null) {
      if (!str.endsWith("\"")) {
        sb.append(str+"\\\\n");
      } else {
        sb.append(str+"\n");
      }
    }
    return sb;
  }

  @Test
 public void  readSlave4AFileTest() throws  IOException{
    String filePath="/d/Product/HeBeiMobile/云审计系统已接入的源日志格式20150915/4a从账号变更/venus.v_itpro_log_slavechange.csv";
    String outputFile=filePath+".out";
    BufferedReader br = readFileBuffer(filePath);
    BufferedWriter bw = writeFileBuffer(outputFile);
    StringBuffer sb = readSlave4AFile(br);
    bw.write(sb.toString());
    bw.close();
    br.close();

  }


  public StringBuffer readLogAuthFile(BufferedReader br) throws IOException {
    StringBuffer sb = new StringBuffer();
    String str = "";
    while ((str = br.readLine()) != null) {
      if (str.endsWith("\",\"")) {
        sb.append(str);
        sb.append(readLogAuthFileField(br).toString());

      } else {
        sb.append(str+"\n");
      }
    }
    return sb;
  }


  private StringBuffer readLogAuthFileField(BufferedReader br) throws IOException {
    StringBuffer sb = new StringBuffer();
    String str = "";
    while ((str=br.readLine()) != null) {
      sb.append("\\\\n" + str);
      if (str.startsWith("\",\"")) {
        return sb.append("\n");
      }
    }
    return sb.append("\n");
  }

  //crm登录日志文件解析
  public StringBuffer readCrmLoginFile(BufferedReader br) throws IOException {
    StringBuffer sb = new StringBuffer();
    String str = "";
    while ((str = br.readLine()) != null) {
      //开始新的一条数据读取
      if (str.startsWith("class=\"")) {
        sb.append(str);
        //开始解析剩余的字段
        sb.append(readCrmLoginFileField(br).toString());

      }
    }
    return sb;
  }


  private StringBuffer readCrmLoginFileField(BufferedReader br) throws IOException {
    StringBuffer sb = new StringBuffer();
    String str = "";
    while ((str=br.readLine()) != null) {
      sb.append(str);
      if (str.contains("param=\"")) {
        return sb.append("\n");
      }
    }
    return sb.append("\n");
  }

  public List<String[]> readCrmLoginLine(BufferedReader br,String splitStr) throws IOException {
    List<String[]> list = new ArrayList<>();
    String str = "";
    while ((str=br.readLine()) != null) {
      str=str.replace("\" \"","\"\"");
      String[] array=str.split(splitStr);
      list.add(array);
    }
    return list;
  }

  public StringBuffer createCrmLoginFileOutput( List<String[]> list) throws IOException {
    StringBuffer sb = new StringBuffer();
    for (String[] array:list){
      for (String str:array){
        sb.append(createCrmLoginFileOutput(str) + "\t");
      }
      sb.append("\n");
    }
    return sb;
  }

  private  String createCrmLoginFileOutput( String str) throws IOException {
    String[] array = str.split("\\s*=\\s*");
    return  array[1].replace("\"","");
  }

  public List<String[]> readCrmLoginLineByEqual(BufferedReader br,String reg) throws IOException {
    Pattern pattern = Pattern.compile(reg);

    List<String[]> list = new ArrayList<>();
    String str = "";
    while ((str=br.readLine()) != null) {
      Matcher m = pattern.matcher(str);
      if (m.find()){
        for (int i = 0; i < m.groupCount() ; i++) {
          System.out.println(m.group(i));
        }
      }
    }
    return list;
  }


  //解析经分系统
  public String dateParse(String str,String inFormat,String outFormat) throws ParseException {
    SimpleDateFormat indDateFormat= new SimpleDateFormat(inFormat);
    SimpleDateFormat outDateFormat= new SimpleDateFormat(outFormat);
    return outDateFormat.format(indDateFormat.parse(str));
  }



  public StringBuffer readJinfenFile(BufferedReader br) throws IOException, ParseException {
    StringBuffer sb = new StringBuffer();
    String inFormat="yyyyMMdd HH:mm:ss:SSS";
    String outFormat="yyyy-MM-dd HH:mm:ss.SSS";
    String str = "";
    br.readLine();
    while ((str=br.readLine())!=null){
      int index = str.indexOf(',');
      String oldStr = str.substring(0, index);
      String newStr = dateParse(oldStr,inFormat,outFormat);
      sb.append(str.replace(oldStr, newStr));
      sb.append("\n");
    }
    return sb;
  }

  @Test
  public void parseJinfengTest() throws IOException, ParseException {
    String filePath="/d/Product/HeBeiMobile/云审计系统已接入的源日志格式20150915/经分登录和操作/BASS_NP.TB_LOG_INFO.csv";
    BufferedReader br = readFileBuffer(filePath);
    BufferedWriter bw = writeFileBuffer(filePath + ".out");
    StringBuffer sb = readJinfenFile(br);
    bw.write(sb.toString());
    br.close();
    bw.close();

  }


  @Test
  public void dateParseTest() throws ParseException {
    String inFormat="yyyyMMdd HH:mm:ss:SSS";
    String outFormat="yyyy-MM-dd HH:mm:ss.SSS";
    String str = "20120410 11:13:30:235,T30013235235-";
    int index = str.indexOf(',');
    String oldStr = str.substring(0, index);
    String newStr = dateParse(oldStr,inFormat,outFormat);
    str.replace(oldStr,newStr);
    String str1 = str.replace("20120410 11:13:30:235","2012-04-10 11:13:30.235");
    System.out.println(str1);
//    System.out.println();
//    String inFormat="yyyyMMdd HH:mm:ss:SSS";
//    String outFormat="yyyy-MM-dd HH:mm:ss.SSS";
//    String result = dateParse("20120410 11:13:30:235",inFormat,outFormat);
//    System.out.println(result);

  }

  @Test
  public void parseCrmTest() throws IOException {
    String filePath="/d/Product/HeBeiMobile/" +
            "云审计系统已接入的源日志格式20150915/crm登录日志/20150915-119000-1500.txt.out";
    BufferedReader br = readFileBuffer(filePath);
    List<String[]> list = readCrmLoginLine(br, "\\s{2,}");
    StringBuffer sb = createCrmLoginFileOutput(list);
    String outputFile=filePath+".out";
    BufferedWriter bw = writeFileBuffer(outputFile);
    bw.write(sb.toString());
    bw.flush();
    bw.close();
    br.close();

  }


  @Test
  public void readLogAuthFileTest() throws IOException {
    String filePath="/d/Product/HeBeiMobile/云审计系统已接入的源日志格式20150915/4a主从账号授权/venus.v_itpro_log_auth.csv";
    BufferedReader br =  readFileBuffer(filePath);
    StringBuffer sb = readLogAuthFile(br);
    String  outFilePath=filePath+".out";
    BufferedWriter bw = writeFileBuffer(outFilePath);
    bw.write(sb.toString());
    br.close();
    bw.close();
    System.out.println("\n");
  }

  @Test
  public void readCrmLoginFileTest() throws IOException {
    String filePath="/d/Product/HeBeiMobile/云审计系统已接入的源日志格式20150915/crm登录日志/20150915-119000-1500.txt";
    BufferedReader br =  readFileBuffer(filePath);
    StringBuffer sb = readCrmLoginFile(br);
    String  outFilePath=filePath+".out";
    BufferedWriter bw = writeFileBuffer(outFilePath);
    bw.write(sb.toString());
    br.close();
    bw.close();
    System.out.println("\n");
  }


}
