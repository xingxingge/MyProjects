package dizoo.std.base;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
/**
 * �����
 * */
public class Operators {
	//++�����
	public static void main(String[] args) {
		int a = 10;
		int b = 10;
		int c = a++;
		int d = ++b;
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("c="+c);
		System.out.println("d="+d);
		//�߼������, ע�⣬&&��||�Ƕ�·�����ֻҪ��һ����������ȷ�����ʽ��ֵ����û�б�Ҫ�����ж���,����Ա���һЩ����
		if (a == 11 && b == 11) {
			System.out.println("a == 11 && b == 11,it's ok");
		}
		if (c >= 1 || d ==20) {
			System.out.println("c >= 1 || d ==20,it's ok");
		}
		if (c != 1) {
			System.out.print("c != 1,it's ok\n");
		}
		//��Ԫ������
		String max = d>c?"d>c":"d<c";
		System.out.println("c��d��ȣ�"+max);
		//System.out.println((char)a);
		/**
		 * ʹ�ð�λ���ж�a������ĸ���Ķ�����num,��λ�����㣬����ȫ����1������1��������0
		 * */
		int forthnumFromRight1 = (a & 0b1000) / 0b1000;
		System.out.println("λ�����㣬forthnumFromRight1 = (a & 0b1000) / 0b1000��a��������ĸ�bitλ�ǣ�"+forthnumFromRight1);
		/**
		 * ��λ�����㣬����һ����1������1
		 * */
		int num1 = 0b1001;
		String num1Bin = Integer.toBinaryString(num1);
		int num2 = 0b1100;
		String num2Bin = Integer.toBinaryString(num2);
		int numOrResult = num1 | num2;
		int numXorResult = num1 ^ num2;
		String numOrResultBin = Integer.toBinaryString(numOrResult);
		String numXorResultBin = Integer.toBinaryString(numXorResult);
		System.out.println("��һ������:"+num1Bin);
		System.out.println("�ڶ�������:"+num2Bin);
		System.out.println("��������:"+numOrResultBin);
		/**
		 *��λ������㣬��ͬΪ0����ͬΪ1
		 * */
		System.out.println("���������:"+numXorResultBin);
		/**
		 * λ�ƣ�ʹ��λ���ж��������λ
		 * >>>�ǲ����ŵ�����,��λ��0��䣬>>�Ǵ��ŵ����ƶ�����λ�÷��λ���,ע�⣬����������ͨ���Σ��Ҳ������Ҫģ��32����������long�����Ҳ������Ҫģ��64
		 * */
		int forthnumFromRight2 = (a & (1 << 35)) >>> 3; 
		System.out.println("λ�����㣺forthnumFromRight2 = (a & (1 << 35)) >>> 3��a��������ĸ�bitλ�ǣ�"+forthnumFromRight2);
		/**
		 * ��ѧ�����볣��
		 * */
		double x = 4;
		double sqrtOfX = Math.sqrt(x);
		double powerOfX = pow(x,3);//���м���import static java.lang.Math.*;�Ͳ�������Mathǰ׺��
		System.out.println(x+"������ƽ�����ǣ�"+sqrtOfX); //��system����System.out���println������ͬ��Math����sqrt��������Ĳ��Ƕ������ַ���������̬����;
		System.out.println(x+"��3�����ǣ�"+powerOfX); 
		double pi = PI;
		System.out.println("Pi="+pi);
		/**
		 * ��ֵ����ת��,��ѭ���С��ԭ������Զ�ת��
		 * */
		int intNum = 123456789;
		float floatNum = intNum;
		System.out.println("12345679ת��Ϊ�����Ϊ��"+floatNum);//��ʧ������
		/**
		 * ǿ������ת��,��תС����Ҫ�ֶ�ǿ������ת��
		 * */
		double doubleNum = 12.945;
		intNum = (int)doubleNum;//ע��д�� 
		System.out.println("double���͵�12.945ת����int���ǣ�"+intNum);
		//������������
		intNum = (int)Math.round(doubleNum);//round����ص�long���͵���
		System.out.println("double���͵�12.945���������int���ǣ�"+intNum);
		/**
		 * ö������
		 * */
//		public enum Size{a,b,c,d,e};
		//Size s =  Size.a;
	}
}



