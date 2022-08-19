package dizoo.std.base;

/**
 * 本文件研究参数传递相关内容
 * **/
public class ParameterTransfer {

	public static void main(String[] args) {
		/**
		 * 1.值传递，这是java程序设计经常使用的参数传递方式，方法接收到的只是参数的一个副本，即时方法修改了值，实参的值还是不变的
		 * **/
		String id = "12345";
		int score = 90;
		System.out.println("变量id的值不变，还是："+id);
		/**
		 * 2. 参数传递有两种：基本数据类型和对象引用，基本数据类型不能被修改，对象引用则可以被修改.对象引用也是值传递，但是由于引用拷贝
		 * 引用同一个对象，所以可以通过传递对象引用轻易修改对象的域。
		 * **/
		Student zsf = new Student();
		//为对象属性赋值
		zsf.setScore(score);
		//以对象为参数，调用方法，对象zsf和x指向同一个内存堆，所以变量的值也是相同的。
	    Student.setScore3(zsf);
	    //第一个对象的参数值被修改了
	    System.out.println(zsf.getScore());	
	    System.out.println(score);
	    /**
	     * 3. 验证java对象调用使用的是值传递还是引用传递
	     * **/
	    System.out.println("\nTesting swap:");
		Employee123 a = new Employee123("Alice", 70000);
		Employee123 b = new Employee123("Bob", 60000);
		System.out.println("Before: a=" + a.getName());
		System.out.println("Before: b=" + b.getName());
		swap(a, b);
		System.out.println("After: a=" + a.getName());
		System.out.println("After: b=" + b.getName());
	}
	public static void swap(Employee123 x, Employee123 y) {
		Employee123 temp = x;
		x = y;
		y = temp;
		System.out.println("End of method: x=" + x.getName());
		System.out.println("End of method: y=" + y.getName());
	}
}
class Student{
	private String id;
	private String name;
	private static int score;
	public Student() {
	}
	public Student(String id){
		System.out.println("测试值传递：参数修改前变量id的值是: "+id);
		id = "2345";
		System.out.println("使用方法修改后变量id的值是: "+id);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score1) {
		score = score1;
	}
//	public int raiseScore(int raise){
//		score=score+raise;
//		return score;
//	}
	public static void setScore3(Student x){
	score+=3;
	}

}

class Employee123 // simplified Employee class
{
	private String name;
	private double salary;

	public Employee123(String n, double s) {
		name = n;
		salary = s;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}