package com.topsec.tsm.hbmobile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestImportCsv {

  private InputStreamReader fr = null;
  private BufferedReader br = null;

  public TestImportCsv(String f) throws IOException {
    fr = new InputStreamReader(new FileInputStream(f));
  }

  /**
   * 解析csv文件 到一个list中 每个单元个为一个String类型记录，每一行为一个list。 再将所有的行放到一个总list中
   */
  public List<List<String>> readCSVFile() throws IOException {
    br = new BufferedReader(fr);
    String rec = null;// 一行
    String str;// 一个单元格
    List<List<String>> listFile = new ArrayList<List<String>>();
    try {
      // 读取一行
      int line=0;
      while ((rec = br.readLine()) != null && line<=2) {
        line++;
        Pattern pCells = Pattern
                .compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
        Matcher mCells = pCells.matcher(rec);
        List<String> cells = new ArrayList<String>();// 每行记录一个list
        // 读取每个单元格
        while (mCells.find()) {
          str = mCells.group();
          str = str.replaceAll(
                  "(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
          str = str.replaceAll("(?sm)(\"(\"))", "$2");
          cells.add(str);
        }
        listFile.add(cells);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fr != null) {
        fr.close();
      }
      if (br != null) {
        br.close();
      }
    }
    return listFile;
  }

  public static void main(String[] args) throws Throwable {
    TestImportCsv test = new TestImportCsv("/d/Product/HeBeiMobile/云审计系统已接入的源日志格式20150915/4a从账号变更/venus.v_itpro_log_slavechange.csv");
    List<List<String>> csvList = test.readCSVFile();
  }

}
