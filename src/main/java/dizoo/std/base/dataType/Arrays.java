package dizoo.std.base.dataType;

/**
 * 使用二位数组实现杨辉三角，组合方式：C(n,k)=n*(n-1)(n-2)*...*(n-k+1)/1*2*...*k
 * 
 * @author HuangXing
 * 
 */
public class Arrays {

	public static void main(String[] args) {
		yanghui(20);
	}

	public static void yanghui(int num) {
		// 生成一个杨辉三角 num * num
		Long[][] array = new Long[num][];
		// 初始化数组
		for (int i = 0; i < array.length; i++) {
			array[i] = new Long[i + 1];//关键写法
		}

		//赋值
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = assemble(i, j);
			}

		}
		
		//输出
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + "\t"); // 左对齐
				// System.out.printf("%4d",array[i][j]); //右对齐
			}
			System.out.println("");
		}
	}

	/**
	 * 组合求值方法
	 * 
	 * // n*(n-1)(n-2)*...*(n-k+1)/1*2*...*k
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static long assemble(int n, int k) {
		// 获得排列组合的值
		Long value = 1l;
		for (int i = 1; i <= k; i++) {
			value = value * (n - i + 1) / i;
		}
		return value;

	}

}
