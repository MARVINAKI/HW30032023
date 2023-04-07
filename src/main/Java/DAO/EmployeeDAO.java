package DAO;

import Model.Employee;

import java.util.Map;

public interface EmployeeDAO {

	void addEmployee(Employee employee);

	Employee findById(int id);

	Map<Integer, Employee> getAll();

	void updateById(int id, Employee employee);

	void deleteById(int id);
}
