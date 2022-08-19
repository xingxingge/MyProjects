package dizoo.std.innerclass;



public class InnerClass1 {

	public static void main(String[] args) {

		Dog d1 = new Dog("小花");
		d1.say();
		Dog.SmallDog child = d1.new SmallDog("小花花");
		child.talk();
		d1.childTalk();
	}

}

class Dog {
	protected String name;

	public Dog(String name) {
		this.name = name;

	}

	public void say() {
		System.out.println("My name is " + this.name);
	}

	class SmallDog {
		private String name;

		public SmallDog(String name) {
			this.name = name;
		}

		public void talk() {
			System.out.println("My name is " + this.name + ",My mother is "
					+ Dog.this.name);
		}

	}

	public void childTalk() {
		SmallDog child = new SmallDog("smalldog");
		child.talk();
	}

}
