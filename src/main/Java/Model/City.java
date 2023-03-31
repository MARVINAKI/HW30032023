package Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class City {
	private int id;
	private String name;

	public City(String name) {
		this.name = name.trim();
	}
}
