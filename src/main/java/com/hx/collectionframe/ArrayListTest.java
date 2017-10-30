package com.hx.collectionframe;

import org.junit.Test;

import java.util.*;

/**
 * 1.泛型数组列表是采用类型参数的泛型类
 * <p/>
 * 2.使用泛型数组列表的注意事项：
 * 如果确定不再添加元素，可以调用trimTosize方法回收多余空间，但是要保证不在增加，否则添加新元素又需要再次移动存储块
 * <p/>
 * 3.list可以转换成数组
 * <p/>
 * 4.进行list元素的添加，插入，删除操作
 **/
/**
 * 2.使用泛型数组列表的注意事项：
 * 如果确定不再添加元素，可以调用trimTosize方法回收多余空间，但是要保证不在增加，否则添加新元素又需要再次移动存储块
 * **/
/**
 * 3.list可以转换成数组
 * **/
/**
 * 4.进行list元素的添加，插入，删除操作
 * **/

public class ArrayListTest {

	public ArrayListTest() {
	}

	public static void main(String[] args) {
		// 即使手动指定了元素数量，也可以自动扩展。
		ArrayList<String> hx = new ArrayList<String>(2);// jdk1.7中<>中的类型参数可以不要。
		// 在调用前就分配包含10个对象的数组
		hx.ensureCapacity(10);
		hx.add("123");
		hx.add("1234");
		hx.add("12345");
		hx.set(1, "4321");
		hx.trimToSize();
		for (int i = 0; i < hx.size(); i++) {
			System.out.println(hx.get(i));

		}
		for (String s : hx) {
			System.out.println(s);

		}
		/**
		 * 由于arraylist采用类作为参数，所以下面的写法是错误的,但是可以进行类型转换
		 * **/
		// ArrayList<int> = new ArrayList<>();
		
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(i));
		}
		System.out.println(list);
		//把list拷贝给数组
		String[] b =  list.toArray(new String[list.size()]);
		System.out.println(Arrays.toString(b));
		//或者
		String[] c = new String[list.size()];
		list.toArray(c);
		System.out.println(Arrays.toString(c));
		//插入数组,新元素会插入index之前
		list.add(1, "11");
		System.out.println(list);
		//删除元素
		list.remove(1);
		System.out.println(list);

	}

	@Test
	public void test(){
		List<Double> list = new LinkedList<>();
		list.add(1d);
		List<Number> list1 = new ArrayList<Number>(list);
		System.out.println(list1);
		Object obj=((ArrayList)list1).clone();
		System.out.println(obj);

		list1 = new ArrayList<Number>(3);
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(5);
		list1.add(6);
		list1.add(7);
		list1.add(8);
		Iterator<Number> iterator = list1.iterator();
			System.out.println(iterator.next());
			System.out.println(iterator.next());
			System.out.println(iterator.next());
		iterator.remove();
		System.out.println(iterator.next());
		iterator.remove();


//		while(iterator.hasNext()){
//		}
//		System.out.println(list1.iterator());
	}

}
