package dizoo.std.collectionframe;

public class Emp implements Comparable<Emp> {
	private String name;
	private int age;

	public Emp(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Emp() {
		super();
	}

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
		return "Emp [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Emp o) {
		if (o == null) {
			throw new NullPointerException("参数不能为空");
		}
		if (this.age < o.age) {
			return -1;
		} else if (this.age > o.age) {
			return 1;
		}
		return 0;
	}

}
