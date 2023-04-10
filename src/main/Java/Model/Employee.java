package Model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "id", nullable = false, insertable = false, updatable = false)
	private int id;

	@NotNull
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@NotNull
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@NotNull
	@Column(name = "age", nullable = false)
	private int age;

	public Employee(String name, String lastName, int age, City city) {
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.city = city;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return id == employee.id && age == employee.age && Objects.equals(name, employee.name) && Objects.equals(lastName, employee.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, lastName, age, city);
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				", city=" + city +
				'}';
	}
}
