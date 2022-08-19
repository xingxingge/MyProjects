package dizoo.std.collectionframe;

public class Person {
	private String name;
	private int age;

	public Person() {
		super();
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		System.out.println("result=" + result);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("equals....");//hashcode相同时候才调用这个方法
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/*
	 * @Override public int hashCode() { return super.hashCode(); }
	 *//**
	 * 重写equals方法
	 * 
	 * @return
	 */
	/*
	 * @Override public boolean equals(Object obj) { if(obj==null){ return
	 * false; } if(obj instanceof Person){ Person p = (Person)obj;
	 * if(this.name.equals(p.name) && this.age==p.age){ return true; }else{
	 * return false; } }else{ return false; }
	 * 
	 * }
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
