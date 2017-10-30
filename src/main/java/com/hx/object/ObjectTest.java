package com.hx.object;

import org.junit.Test;

public class ObjectTest {
	
	//hashcode方法
	@Test
	public   void testHashcode() {
		Object o = new Object();
		System.out.println(o.hashCode());
		String s = new String("huangxing");
		// 计算字符串的hashcode计算方式
		int hash = 0;
		for (int i = 0; i < s.length(); i++) {
			hash = hash * 31 + s.charAt(i);
		}
		System.out.println(hash);
 		System.out.println(s.hashCode());
	}


	@Test
	public   void testClone() {
		CloneTest c = new CloneTest();
		c.cloneTest2=new CloneTest2();
		CloneTest b = c.clone();
		System.out.println(b.i);
	}

}

class CloneTest implements  Cloneable {
	public int i = 1;
	public CloneTest2 cloneTest2;

	public CloneTest clone() {
		try {
			CloneTest c = (CloneTest) super.clone();
			c.cloneTest2=cloneTest2.clone();//clone时默认只是引用原来属性,这样可以生成新的
			return c;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}


class CloneTest2 implements Cloneable{
	public CloneTest2 clone() {
		try {
			CloneTest2 c = (CloneTest2) super.clone();
			return c;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}


}
