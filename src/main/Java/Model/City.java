package Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "city")
@NoArgsConstructor
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = false, updatable = false)
	private int id;
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	public City(String name) {
		this.name = name.trim();
	}

//	@OneToMany
//	@JoinColumn(name = "id")
//	private Collection<Employee> employees;
}
