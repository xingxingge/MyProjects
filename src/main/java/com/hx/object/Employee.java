package com.hx.object;

import java.util.Arrays;

public class Employee {
	private String name;
	private int age;
	private double salary;
	private String[] subjects;

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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		// final int prime = 31;
		// int result = 1;
		// result = prime * result + age;
		// result = prime * result + ((name == null) ? 0 : name.hashCode());
		// long temp;
		// temp = Double.doubleToLongBits(salary);
		// result = prime * result + (int) (temp ^ (temp >>> 32));
		// return result;

		// 这个方法相当于对各个参数调用hashcode方法并组合
		// return 3 * Objects.hash(name,age,salary);
//		return 2 * Objects.hashCode(name) + Objects.hashCode(age)
//				+ Objects.hashCode(salary);
		return Arrays.hashCode(subjects);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double
				.doubleToLongBits(other.salary))
			return false;
		return true;
	}

}
