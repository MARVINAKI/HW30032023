package DAO;

import Model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {

	void addEmployee(Employee employee);

	Employee findById(int id);

	int findIdByEmployee(Employee employee);

	List<Employee> getAll();

	void updateById(int id, Employee employee);

	void deleteById(int id);

	void close();
}
