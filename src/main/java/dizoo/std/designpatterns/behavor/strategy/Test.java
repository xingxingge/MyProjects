package dizoo.std.designpatterns.behavor.strategy;

public class Test {
	public static void main(String[] args) {
		//测试优等员工的工资
		Common employee = new Excellent("小红", 100.0);
		AllBonus bonus = new AllBonus(employee);
		System.out.println(employee.getName()+"的奖金是："+ bonus.myBonus(employee.getSalary()));
		
		employee = new Good("小白", 100.0);
		 bonus = new AllBonus(employee);
		System.out.println(employee.getName()+"的奖金是："+ bonus.myBonus(employee.getSalary()));
		
		employee = new Genaral("小明", 100.0);
		bonus = new AllBonus(employee);
		System.out.println(employee.getName()+"的奖金是："+ bonus.myBonus(employee.getSalary()));
		
	}
}
