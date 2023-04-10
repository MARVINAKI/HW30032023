package Model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "city")
@NoArgsConstructor
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "id", nullable = false, insertable = false, updatable = false)
	private int id;

	@NotNull
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	public City(String name) {
		this.name = name.trim();
	}

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Collection<Employee> employees;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		City city = (City) o;
		return Objects.equals(name, city.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "City{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
