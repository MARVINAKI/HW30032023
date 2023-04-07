package DAO;

import Model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void addEmployee(Employee employee) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		try {
			em.getTransaction().begin();
			em.persist(employee);
			em.getTransaction().commit();
		} finally {
			closeEntityManager(em, emf);
		}
	}

	@Override
	public Employee findById(int id) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		try {
			Employee employee = em.find(Employee.class, id);
			return employee == null ? new Employee() : employee;
		} finally {
			closeEntityManager(em, emf);
		}
	}

	@Override
	public Map<Integer, Employee> getAll() {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		Map<Integer, Employee> employees = new LinkedHashMap<>();
		String str = "SELECT e FROM Employee e";
		try {
			TypedQuery<Employee> query = em.createQuery(str, Employee.class);
			for (Employee employee : query.getResultList()) {
				employees.put(employee.getId(), employee);
			}
			return employees;
		} finally {
			closeEntityManager(em, emf);
		}
	}

	@Override
	public void updateById(int id, Employee employee) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		try {
			em.getTransaction().begin();
			Employee mergedEmployee = em.find(Employee.class, id);
			mergedEmployee.setName(employee.getName());
			mergedEmployee.setLastName(employee.getLastName());
			mergedEmployee.setAge(employee.getAge());
			mergedEmployee.setCityID(employee.getCityID());
			em.merge(mergedEmployee);
			em.getTransaction().commit();
		} finally {
			closeEntityManager(em, emf);
		}
	}

	@Override
	public void deleteById(int id) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		try {
			em.getTransaction().begin();
			em.remove(em.find(Employee.class, id));
			em.getTransaction().commit();
		} finally {
			closeEntityManager(em, emf);
		}
	}

	private static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("myPersistenceUnit");
	}

	private static EntityManager getEntityManager(EntityManagerFactory emf) {
		return emf.createEntityManager();
	}

	private void closeEntityManager(EntityManager em, EntityManagerFactory emf) {
		em.close();
		emf.close();
	}
}
