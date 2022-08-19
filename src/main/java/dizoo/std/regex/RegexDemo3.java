package dizoo.std.regex;

public class RegexDemo3 {

	public static void main(String[] args) {

		// 匹配电话号码
		String phoneNumber = "010-53517230";
		boolean b = phoneNumber.matches("\\d{3,4}-\\d{7,8}");
		if (b) {
			System.out.println("电话号码格式正确");
		} else {
			System.out.println("电话号码格式不正确");
		}
		// 匹配手机号码
		String phone = "13143188844";
		System.out.println(phone.matches("[1][3-9]\\d{9}"));

		// 匹配用户名：字母开头，数字字母下划线组合
		String username = "xb1314";
		System.out.println(username.matches("[a-zA-Z]+[\\w|_]*"));
		// 匹配IP地址
		String ip = "20,10,20,123";
		System.out.println(ip.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}"));

		// 匹配网址
		String addr = "http://java.lampbrother.net";
		System.out.println(addr.matches("http://\\w+.\\w+.\\S*"));

		// 匹配年龄
		String age = "18";
		System.out.println(age.matches("\\d{1,2}"));

		// 匹配金额
		String price = "19.8";
		System.out.println(price.matches("\\d+.\\d+"));
	}

}
