package DAO;

import Model.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface CityDAO {


	boolean addCity(City city);

	City findByID(int id);

	Map<Integer, String> getAll();

	boolean updateByID(int id, City city);

	boolean deleteByID(int id);
}
