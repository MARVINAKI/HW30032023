package DAO;

import Model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeeDAOImpl implements EmployeeDAO {

	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
	private final EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public boolean addEmployee(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		boolean response = entityManager.contains(employee);
		closeEntityManager();
		return response;
	}

	@Override
	public Employee findById(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		closeEntityManager();
		return employee == null ? new Employee() : employee;
	}

	@Override
	public Map<Integer, Employee> getAll() {
		Map<Integer, Employee> employees = new LinkedHashMap<>();
		String str = "SELECT e FROM Employee e";
		TypedQuery<Employee> query = entityManager.createQuery(str, Employee.class);
		for (Employee employee : query.getResultList()) {
			employees.put(employee.getId(), employee);
		}
		closeEntityManager();
		return employees;
	}

	@Override
	public boolean updateById(int id, Employee employee) {
		entityManager.getTransaction().begin();
		employee = entityManager.merge(entityManager.find(Employee.class, id));
		entityManager.getTransaction().commit();
		boolean response = entityManager.contains(employee);
		closeEntityManager();
		return response;
	}

	@Override
	public boolean deleteById(int id) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(Employee.class, id));
		entityManager.getTransaction().commit();
		boolean response = !entityManager.contains(entityManager.find(Employee.class, id));
		closeEntityManager();
		return response;
	}

	private void closeEntityManager() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
