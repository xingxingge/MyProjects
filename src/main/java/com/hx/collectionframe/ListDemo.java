package com.hx.collectionframe;

import java.util.*;

/*
 * 需要关注：
 1.实现方式
 2.是否有序
 3.是否允许元素为空
 4.是否允许重复
 5.元素增长方式
 6.优点
 7.缺点
 * */

/**
 * List接口： 1、允许重复元素 2、必须是有序的 3、允许null元素
 * 
 * @author lamp
 * 
 */
public class ListDemo {
	/*
	 * LinkedList
	 * 1.双向链表实现
	 * 2.有序序列
	 * 3.允许元素空值
	 * 4.允许重复元素 
	 * 5.增长方式为添加对象之间的关联关系
	 * 6.优点：插入和删除操作高效，因为不需要移动元素，只是修改了指针指向
	 * 7.缺点：查找第n个元素比较低效，因为需要从开头或者结尾遍历(java可以计算长度和索引值的关系
	 * ，从而确定从开始还是结尾处开始搜索)。对于经常使用get(n)方法访问list时候，应该使用arraylist
	 * 根据LinkedList的双向链表特征，java提供了一个Iterator接口的实现类，
	 * 增加了previous()和hasPrevious方法()，这样可以双向遍历
	 */

	public static void linkedlistTest() {

		LinkedList<String> list = new LinkedList<String>();
		list.add("语文");
		list.add("数学");
		list.add("外语");
		list.add(2,"物理");
		list.add(null);
		//遍历链表
		System.out.println("-----前向遍历-----");
		Iterator<String> itr = list.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
			
		}
		
		//后向遍历
		System.out.println("-----后向遍历-----");
		ListIterator<String> l = list.listIterator(list.size());
		while (l.hasPrevious()) {
			System.out.println(l.previous());
			
		}
		//开头处插入多个元素,返回元素的下标
		System.out.println("-----开头除插入多个元素,不要使用循环遍历-----");
		ListIterator<String> l1 = list.listIterator();
		l1.next();
		l1.add("生物");
		l1.add("化学");
		l1 =  list.listIterator(1);//从第下标为n的元素开始迭代
		while (l1.hasNext()) {
			System.out.println("第"+(l1.nextIndex()+1)+"个元素："+l1.next());
			
		}
		
		//使用ListIterator接口进行删除和添加，集合之间的关系运算
		List<String> a = new LinkedList<>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");

		List<String> b = new LinkedList<>();
		b.add("Bob");
		b.add("Doug");
		b.add("Frances");
		b.add("Gloria");

		// merge the words from b into a

		ListIterator<String> aIter = a.listIterator();
		Iterator<String> bIter = b.iterator();

		while (bIter.hasNext()) {
			if (aIter.hasNext())
				aIter.next();
			aIter.add(bIter.next());//add后指向下一个
		}

		System.out.println(a);

		// remove every second word from b

		bIter = b.iterator();
		while (bIter.hasNext()) {
			bIter.next(); // skip one element
			if (bIter.hasNext()) {
				bIter.next(); // skip next element
				bIter.remove(); // remove that element
			}
		}

		System.out.println(b);

		// bulk operation: remove all words in b from a

		a.removeAll(b);

		System.out.println(a);
	}

	/* 
	 * Vector：
	 * 1.动态数组实现，默认构造方法初始大小是10
	 * 2.有序序列
	 * 3.允许元素空值
	 * 4.允许重复元素 
	 * 5.增长方式为动态数组扩充：如果有指定增量，当前容量+增量；如果没有增量：原容量*2 
	 * 6.优点：随机读取的效率高，线程安全
	 * 7.缺点：与ArrayList类似，插入和删除效率底下
	 */
	
	public static void vectorTest() {
		Vector<String> vector = new Vector<String>();
		vector.add("huangxing");
		vector.add("lihao");
		vector.add("haosu");
		Vector<String> a = new Vector<String>(10, 5);
		a.clear();
		System.out.println(vector);

	}

	/* ArrayList：
	 * 1.动态数组实现，默认构造方法初始大小是10
	 * 2.有序序列
	 * 3.允许元素空值
	 * 4.允许重复元素 
	 * 5.增长方式为动态数组扩充：如果数组已存满,新数组长度算法是：(原数组长度*3/2)+1，大约是原数组的一半左右
	 * 6.优点：随机读取效率高，如果已知元素个数，可以使用指定初始容量的构造方法创建ArrayList对象，这样可以 有效的避免数组扩充的次数过多，从而提高效率
	 * 7.缺点：插入和删除元素效率底下，线程不安全
	 */
	
	public static void arraylistTest() {
		// 创建ArrayList对象
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(10);
		list.add("huangxing");
		list.add(true);
		// 添加元素.....

		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(16);
		list.add(17);
		list.add(18);
		list.add(17);
		// 插入方法
		list.add(5, 50);
		System.out.println("一共有元素：" + list.size());
		System.out.println("第5个元素是：" + list.get(5));//下标从1开始
		// 清除所有元素
		// list.clear();
		// System.out.println(list.size());
		// 如果此列表中包含指定的元素，则返回 true。
		System.out.println(list.contains(17));
		// 查找指定元素的位置(首次)
		System.out.println(list.indexOf(17));
		// 检查集合是否为空
		System.out.println(list.isEmpty());
		// 删除指定索引的元素
		System.out.println(list.remove(1));

	}

	public static void main(String[] args) {
		 arraylistTest();
		linkedlistTest();
		vectorTest();

	}

}
