package DAO;

import Model.City;

import java.util.List;
import java.util.Map;

public interface CityDAO {

	void addCity(City city);

	City findByID(int id);

	List<City> getAll();

	void updateByID(int id, City city);

	void deleteByID(int id);

	int findIdByCity(City city);

	void close();
}
