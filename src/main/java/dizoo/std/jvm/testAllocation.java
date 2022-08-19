package dizoo.std.jvm;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class testAllocation {
	
	private static final int  _1MB=1024*1024;
	public static void main(String[] xargs){
//		testTenuringThreshold2();
		testHandlePromotion();
	}
	public static void main1(String[] xargs){
		byte[] b1,b2,b3,b4;
		b1 = new byte[2*_1MB];
		b2 = new byte[2*_1MB];
		b3 = new byte[2*_1MB];
		b4 = new byte[4*_1MB];
	}
	
	/*-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 
	 * -XX:PretenureSizeThreshold=3145728  -XX:+UseParNewGC
	 * 
	 * */
	public void testPretenureSizeThredshold(){
		byte[] allocation;
		allocation = new byte[4*_1MB];
	}
	
	/*-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 
	 * -XX:MaxTenuringThreshold=1  -XX:+UseParNewGC -XX:+PrintTenuringDistribution
	 * 
	 * */
	public static void testTenuringThreshold(){
		byte[] b1,b2,b3;
		b1 = new byte[_1MB/4];
		b2 = new byte[4*_1MB];
		b3 = new byte[4*_1MB];
		b3 = null;
		b3 = new byte[4*_1MB];
	}
	
	/*-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 
	 * -XX:MaxTenuringThreshold=15  -XX:+UseParNewGC -XX:+PrintTenuringDistribution
	 * 
	 * */
	public static void testTenuringThreshold2(){
		byte[] b1,b2,b3,b4;
		b1 = new byte[_1MB/4];
		b2 = new byte[_1MB/4];
		b3 = new byte[4*_1MB];
		b4 = new byte[4*_1MB];
		b4 = null;
		b4 = new byte[4*_1MB];
	}
	
	/**
	 * VM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
	 */
	@SuppressWarnings("unused")
	public static void testHandlePromotion() {
	    byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
	    allocation1 = new byte[2 * _1MB];
	    allocation2 = new byte[2 * _1MB];
	    allocation3 = new byte[2 * _1MB];
	    allocation1 = null;
	    allocation4 = new byte[2 * _1MB];
	    allocation5 = new byte[2 * _1MB];
	    allocation6 = new byte[2 * _1MB];
	    allocation4 = null;
	    allocation5 = null;
	    allocation6 = null;
	    allocation7 = new byte[2 * _1MB];
	}
	
}
