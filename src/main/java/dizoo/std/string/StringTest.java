package dizoo.std.string;


import org.junit.Test;

/**
 * String
 * */
public class StringTest {
	private static final String x = "example";
	private static  String y = "example";
	private String str = new String("abc");
	private char[] ch = {'1','2','3'};
	public void change(String str,char[] ch) {
		str = "def";
		ch[0]='p';
	}
	
	public static void stringChangeTest() {
		StringTest t = new StringTest();
		t.change(t.str,t.ch);
		System.out.print(t.str+"\t");
		System.out.println(t.ch);
	}
	public static void main(String[] args) {
		stringChangeTest();
		replaceTest();
	}

//	@ReentrantLockTest
	public static  void replaceTest(){
		String a = "abcdefgdabcd";
		String b = a.replace('q','p');
		System.out.println(a==b);
	}

	public int test(int t){
		return 0;
	}
	public int test(int t,String a){
		return 0;
	}

	public int test(String a){
		return 0;
	}

	@Test
	public void stringTransTest(){
		System.out.println("\u0002");
		String a = "12345";
		stringTransTest(a);
		System.out.println(a);
	}
	@Test
	public void stringReplaceTest(){
		System.out.println("\u0002");
		String a = "Oct 29 00:00:04 H3C %%11DATALOG/2/ATTACK(l):-DEV_TYPE=IPS-PN=210231A93WB128000047 data_type(1)=attack;log_type(2)=alert;attack_name(4)=(352329661)TLS Protocol Session Renegotiation Event;app_protocol_name(6)=(84021364)HTTPS;protocol(7)=6;segment_direct(28)=0;src_ip(22)=14.208.20.123;src_port(23)=6557;dst_ip(24)=61.129.61.130;dst_port(25)=443;ifname_in(16)=xeth0/0;ifname_out(17)=xeth0/0;aggre_count(26)=1;aggre_offset(43)=0;vlan(125)=;smac(126)=54:75:d0:5b:1d:8c;dmac(127)=00:01:d7:ec:e5:04;agent_ip(128)=;";
		System.out.println(a);
		System.out.println(a.replace("\n","\\n"));
		String abs = "xxxxx.ok";
		System.out.println(abs.indexOf(".ok"));
		System.out.println(abs.substring(0,abs.indexOf(".ok")));
	}

	private void stringTransTest(String a){
		a="45678";

	}

}
