package Model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = false, updatable = false)
	private int id;
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;
	@Column(name = "age", nullable = false)
	private int age;
	@Column(name = "city_id")
	private Integer cityID;

	public Employee(String name, String lastName, int age) {
		this.name = name.trim();
		this.lastName = lastName.trim();
		this.age = Math.abs(age);
		this.cityID = null;
	}

	public Employee(String name, String lastName, int age, Integer cityID) {
		this.name = name.trim();
		this.lastName = lastName.trim();
		this.age = Math.abs(age);
		this.cityID = Math.abs(cityID);
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "id")
//	private City city;
}
