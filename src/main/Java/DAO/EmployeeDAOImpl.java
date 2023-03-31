package DAO;

import Config.ApplicationConnection;
import Model.Employee;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class EmployeeDAOImpl implements EmployeeDAO {

	private final ApplicationConnection appConnection = new ApplicationConnection();
	private final CityDAOImpl cityDAO = new CityDAOImpl();

	@Override
	public boolean addEmployee(Employee employee) {
		try (PreparedStatement preparedStatement = appConnection.getPreparedStatement("INSERT INTO employee(name, last_name, age, city_id) VALUES (?, ?, ?, ?);")) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getLast_name());
			preparedStatement.setInt(3, employee.getAge());
			if (employee.getCity_id() == null || String.valueOf(employee.getCity_id()).trim().isEmpty()) {
				preparedStatement.setNull(4, Types.INTEGER);
			} else {
				preparedStatement.setInt(4, employee.getCity_id()); // если не выходит за рамки city id!!!
			}
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Employee findById(int id) {

		return null;
	}

	@Override
	public Map<Integer, Employee> getAll() {
		return null;
	}

	@Override
	public boolean updateById(int id, Employee employee) {
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		return false;
	}
}
