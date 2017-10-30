package com.hx.base.operator;


/**
 * 
 *         |arti  |stat  |------reserved------|lat   |long  |time  |
 * STATION |63--60|59--56|55--52|51--48|47--44|43--40|39--36|35--32|
 *         |deep  |prec  |level |--------reserved-----------|observation|
 * MEASURE |31--28|27--24|23--20|19--16|15--12|11--08|07--04|03--00|
 * @author tangtaiding
 *
 */
public abstract class SimilarityHelper {
  
  public static int OBSERVATION_SECTION = 0;
  
  public static int LEVEL_SECTION = 5;
  
  public static int PRECISION_SECTION = 6;
  
  public static int DEEP_SECTION = 7;
  
  public static int TIME_SECTION = 8;
  
  public static int LONGITUDE_SECTION = 9;
  
  public static int LATITUDE_SECTION = 10;
  
  public static int STATION_SECTION = 14;
  
  public static int ARTIFICAL_SECTION = 15;
  //---mask----------------------------------------------
  // 站位段掩码
  public static long STATION_MASK = 0xFFFFFFFF00000000L;
  // 度量段掩码
  public static long MEASURE_MASK = 0x00000000FFFFFFFFL;
  
  public static int SECTION_MASK = 0x0000000F;
  // 站位相似性手工标记掩码
  public static long STATION_ARTIFICIAL_MASK = 0xF000000000000000L;
  // 站位相似性掩码
  public static long STATION_SIMILARITY_MASK = 0x0F00000000000000L;
  // 时间相似性掩码
  public static long TIME_SIMILARITY_MASK = 0x0000000F00000000L;
  // 经度掩码
  public static long LONGITUDE_SIMILARITY_MASK = 0x000000F000000000L;
  // 纬度掩码
  public static long LATITUDE_SIMILARITY_MASK = 0x00000F0000000000L;
  // 深度掩码 
  public static long DEEP_SIMILARITY_MASK = 0x00000000F0000000L;
  // 精度掩码
  public static long PRECISION_MASK = 0x000000000F000000L;
  // 层次掩码
  public static long LEVEL_SIMILARITY_MASK = 0x0000000000F00000L;
  // 值掩码
  public static long OBSERVATION_SIMILARITY_MASK = 0x000000000000000FL;
  //---similarity-----------------------------------------
  public static int SIMILARITY_DIFFERENT = 0X0;
  
  public static int SIMILARITY_IDENTICAL = 0X1;
  
  public static int SIMILARITY_SIMILAR = 0X2;
  
  public static int SIMILARITY_SUSPECTED = 0X3;
  //----artifical-----------------------------------------
  public static int ARTIFICAL_NO = 0X0;
  public static int ARTIFICAL_YES = 0X1;
  
  //前提是sign需要设置的位是0
  public static long setSectionValue(long sign, int section, int value) {
    if (section < 0 || section > 15) {
      throw new IllegalArgumentException("section is invalid!");
    }
    long tmp = value;
    tmp = tmp & SECTION_MASK;
    long data = tmp << (4*section);
    //sign在这个区域首先置0
    int  middle =  getSectionValue(sign, section);
    StringBuffer sb = new StringBuffer(Integer.toHexString(middle));
    for (int i = 0; i < section; i++) {
    	sb =sb.append("0");
	}
    tmp = Long.parseLong(sb.toString(), 16);
//  tmp =  (long) (middle * math.pow(2, 4*section)); //效率比较底下
    sign=sign-tmp;
    sign |= data;
    return sign;
  }
  
  public static void main(String[] args) {
	  Long a = setSectionValue(3547, 2, 3);
	  System.out.println(a);
	  System.out.println(Long.toBinaryString(a));
  }
  
  public static int getSectionValue(long sign, int section) {
    if (section < 0 || section > 15) {
      throw new IllegalArgumentException("section is invalid!");
    }
    sign = sign >> (4*section);
    return (int)(sign & SECTION_MASK);
  }
  
  public static long calcStationSimilarity(long sign) {
    int similarity = getSectionValue(sign, TIME_SECTION);//时间
    int tmp = getSectionValue(sign, LONGITUDE_SECTION);//纬度
    similarity = similarity > tmp ? similarity : tmp;
    tmp = getSectionValue(sign, LATITUDE_SECTION);//经度
    similarity = similarity > tmp ? similarity : tmp;
    return setSectionValue(sign, STATION_SECTION, similarity);
  }
  
  public static long setStationSimilarity(long sign, int similarity) {
    sign = setSectionValue(sign, STATION_SECTION, similarity);//设置站位相似性
    return setSectionValue(sign, ARTIFICAL_SECTION, ARTIFICAL_YES);//人工设置站次是相似的
  }
  
}
