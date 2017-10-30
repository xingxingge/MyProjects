package com.hx.regex;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试
 *
 * @author XG
 */
public class RegexTest  extends TestCase{

  @Test
  public void matchTest() {
    // 给定字符串，如果头部匹配给定的字符串模式，那么拿到匹配字符串后的所有内容
    String regStr = "(UpLoad Time =)|(hip:)|(tation:|tation)|(titude:)|(tude:)|(pth:)|(profile:)";
    Pattern fieldPattern = Pattern.compile(regStr, Pattern.CASE_INSENSITIVE);
    String s = "123* UpLoad Time = Mar 25 2015 09:18:26";
    Matcher matcher = fieldPattern.matcher(s);
    if (matcher.find()) {
      System.out.println(matcher.end());
      System.out.println(s.substring(matcher.end()).toUpperCase());
    }
    System.out.println("----------------");

    // 或者
    StringBuffer sb1 = new StringBuffer();
    // matcher = matcher.usePattern(fieldPattern);
    matcher = fieldPattern.matcher(s);
    while (matcher.find()) {
      matcher.appendReplacement(sb1, "");
    }
    matcher.appendTail(sb1);
    System.out.println(sb1.toString().replace("*", "").trim());

    // 输出匹配的各个分组字符串
    s = "  1re           emp            al          igma           urb          oundfaasf";

    Pattern tableFieldPattern =
            Pattern.compile("(re)\\s+(emp)\\s+(al)\\s+(igma)\\s+(urb)\\s+(ound)",
                    Pattern.CASE_INSENSITIVE);
    Pattern t1 =
            Pattern.compile("(1re)\\s+(emp)\\s+(al)\\s+(igma)\\s+(urb)\\s+(ound)",
                    Pattern.CASE_INSENSITIVE);
    matcher = tableFieldPattern.matcher(s);
    // 注意字符串没变
    StringBuffer sb = new StringBuffer();
    String s1 = "  1re           emp            al          igma           urb          oundfaasf";
    if (matcher.usePattern(t1).reset(s1).find()) {

      for (int i = 1; i <= matcher.groupCount(); i++) {
        sb.append(matcher.group(i) + "\t");
      }
      System.out.println(sb.toString());
      // 打印整个匹配的字符串
      System.out.println(matcher.group());

    }
  }

  @Test
  public void test() {
    // 查找以Java开头,任意结尾的字符串
    Pattern pattern = Pattern.compile("^Java.*");
    Matcher matcher = pattern.matcher("Java不是人");
    boolean b = matcher.matches(); // 当条件满足时，将返回true，否则返回false
    System.out.println(b);
    // 以多条件分割字符串时
    pattern = Pattern.compile("[, |]+");
    String[] strs = pattern.split("Java Hello World Java,Hello,,World|Sun");
    for (int i = 0; i < strs.length; i++) {
      System.out.print(strs[i] + "\t");
    }

    // 文字替换（首次出现字符）
    pattern = Pattern.compile("正则表达式");
    matcher = pattern.matcher("正则表达式  Hello World,正则表达式 Hello World"); // 替换第一个符合正则的数据
    System.out.println("\n" + matcher.replaceFirst("Java"));

    // 文字替换（全部）
    pattern = Pattern.compile("正则表达式");
    matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World"); // 替换第全部符合正则的数据
    System.out.println(matcher.replaceAll("hx"));
    // 文字替换（置换字符）
    pattern = Pattern.compile("正则表达式");
    matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World ");
    StringBuffer sbr = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sbr, "Java");
    }
    matcher.appendTail(sbr);
    System.out.println(sbr.toString());

    // 验证是否为邮箱地址
    String str = "ceponline@yahoo.com.cn";
    pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+", Pattern.CASE_INSENSITIVE);
    matcher = pattern.matcher(str);
    System.out.println(matcher.matches());
    // 查找html中对应条件字符串
    pattern = Pattern.compile("href=\"(.+?)\".+(主页)");
    matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
    if (matcher.find()) {

      for (int i = 1; i <= matcher.groupCount(); i++) {
        System.out.println(matcher.group(i));

      }
    }
    // 截取http://地址/ 截取url
    pattern = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+");
    matcher = pattern.matcher("dsdsds<http://www.baidu.com:8080/gfgffdfd>fdf");
    StringBuffer buffer = new StringBuffer();
    while (matcher.find()) {
      buffer.append(matcher.group());
      buffer.append("\r\n");
      System.out.println(buffer.toString());
    }
    // 替换指定{}中文字
    str = "Java目前的发展史是由{0}年-{1}年";
    String[][] object = {new String[]{"\\{0\\}", "1995"}, new String[]{"\\{1\\}", "2007"}};
    System.out.println(replace(str, object));
  }


  public String replace(String sourceString, Object[] object) {
    String temp = sourceString;
    for (int i = 0; i < object.length; i++) {
      String[] result = (String[]) object[i];
      Pattern pattern = Pattern.compile(result[0]);
      Matcher matcher = pattern.matcher(temp);
      temp = matcher.replaceAll(result[1]);
    }
    return temp;
  }

  @Test
  public void groupTest() {
    String str = "        0.0    00      29.2000    00      -33.7200    00       0.0300    01";
    String regex = "(\\s+-?\\d+\\.?\\d+){6,}";
    Matcher matcher = Pattern.compile(regex).matcher(str);
//     if (matcher.matches()) {
//     System.out.println(matcher.start());
//
//     }
//     System.out.println(matcher.find());
    if (matcher.find()) {
      System.out.println(matcher.group());
//      System.out.println(matcher.groupCount());
      for (int i = 1; i <= matcher.groupCount(); i++) {
        System.out.println(matcher.group(i));
      }
    }
  }

  @Test
  public void fromBook() {
    Scanner in = new Scanner(System.in);
    System.out.println("Enter pattern: ");
    String patternString = in.nextLine();
    Pattern pattern = Pattern.compile(patternString);
    while (true) {
      System.out.println("Enter string to match: ");
      String input = in.nextLine();
      if (input == null || input.equals("")) {
        in.close();
        return;
      }
      Matcher matcher = pattern.matcher(input);
      if (matcher.matches()) {
        System.out.println("Match");
        int g = matcher.groupCount();
        if (g > 0) {
          for (int i = 0; i < input.length(); i++) {
            // Print any empty groups
            for (int j = 1; j <= g; j++)
              if (i == matcher.start(j) && i == matcher.end(j)) System.out.print("()");
            // Print ( for non-empty groups starting here
            for (int j = 1; j <= g; j++)
              if (i == matcher.start(j) && i != matcher.end(j)) System.out.print('(');
            System.out.print(input.charAt(i));
            // Print ) for non-empty groups ending here
            for (int j = 1; j <= g; j++)
              if (i + 1 != matcher.start(j) && i + 1 == matcher.end(j)) System.out.print(')');
          }
          System.out.println();
        }
      } else
        System.out.println("No match");
    }
  }

  @Test
  public void IPAddress() {
    String regexp = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";
    Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher("199.01.1.1");
    System.out.println(matcher.matches());
    if ("1.1.1.1".matches(regexp)) {
      System.out.println(true);
    }
  }

  @Test
  public void floatTest() {
    String regexp = "[-+]?[0-9]*\\.?[0-9]+";
    regexp = "^ELEVALUE=\\d+(\\s*\\,\\s*([+-]?\\d+)(\\.\\d+)?){14}$";
    Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
    String str = "ELEVALUE=20140620083228,   1713.93,   50.00, 113.338538,  13.040273, 4296.90,   99.65, 113.333717,  13.030583, 4298.00,   50.00, -171.00,    0.55,  204.95,    75.90";
    Matcher matcher = pattern.matcher(str);
    System.out.println(matcher.matches());

//    System.out.println(matcher.groupCount());
//    for (int i = 1; i <=  matcher.groupCount(); i++) {
//      System.out.println(matcher.group(i));
//
//    }

  }

  @Test
  public void domainNameFetchTest() {
    String string = "尊敬的用户：您的积分已满足www.baidu.com兑换396.20元的现金，请登陆 http://wap.ydjfc10086.com 按提示激活领取。【中国";
    Pattern pattern = Pattern.compile("([\\w\\-_]+\\.)+[a-zA-Z]+");
    Matcher matcher = pattern.matcher(string);

//    System.out.println(matcher.groupCount());
//    System.out.println(matcher.matches());
    if (matcher.find()) {
      System.out.println(matcher.groupCount());
      System.out.println(matcher.group(0));
      System.out.println(matcher.group(1));
    }
    if (matcher.find()) {
      System.out.println(matcher.group());
    }
//    System.out.println(matcher.group());
//    System.out.println(matcher.group());
//    System.out.println(matcher.group());

//    while (matcher.find())
//    {
//      int start = matcher.start();
//      int end = matcher.end();
//      String match = string.substring(start, end);
//      System.out.println(match);
//    }


//    for (int i = 1; i <= matcher.groupCount(); i++) {
//      System.out.println(matcher.group(i));
//
//    }

  }

  @Test
  public  void getStation(){
    String[] strings = new String[3];
    String reg="([a-zA-Z]+\\d+)";
    Pattern pattern = Pattern.compile(reg);
    Matcher matcher = pattern.matcher("o1t1");
    if (matcher.find()){
      strings[0]=matcher.group();
      strings[1]=matcher.group();
    }
    if (matcher.find()){
      strings[1]=matcher.group();
    }
    if (matcher.find()){
      strings[2]=matcher.group();
    }
    System.out.println(strings[0]);
    System.out.println(strings[1]);
    System.out.println(strings[2]);
    System.out.println(strings[2]==null);
  }

  @Test
  public  void stringTest(){
    String dataRegString = "^\\s*ELEVALUE=([[^,]\\S\\s]*,){12,}([[^,]\\S\\s]*),?$";
    String line="ELEVALUE=,0cm,25.5cm,1502.1m/s,100000Hz,27.40dB/m,1.41g/cm3,78.03%,0.004mm, , ,黏土质粉砂, ,";
    System.out.println(Pattern.compile(dataRegString).matcher(line).find());
    String[] strings = line.split(",");
    for (int i=0;i<strings.length;i++){
      if (strings[i]!=null)strings[i]=strings[i].trim();
    }
    String time = strings[0];
    try {
      System.out.println(time.indexOf('='));
      System.out.println(time.substring(9));
      time = time.substring(time.indexOf('=') + 1);
      System.out.println(time);
    }catch (Exception e){
      e.printStackTrace();
      time=null;
    }
  }

  public void test1(){
    String str = "ff jj-/|f.f/";
    System.out.println(str.replaceAll("[/\\s-]|(\\.)", "_"));
  }


}
