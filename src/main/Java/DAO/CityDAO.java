package DAO;

import Model.City;

import java.util.Map;

public interface CityDAO {


	void addCity(City city);

	City findByID(int id);

	Map<Integer, String> getAll();

	void updateByID(int id, City city);

	void deleteByID(int id);
}
