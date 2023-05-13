package DAO;

import Model.Employee;

import javax.persistence.*;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

	@Override
	public void addEmployee(Employee employee) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(employee);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public Employee findById(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Employee.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public int findIdByEmployee(Employee employee) {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
			if (query.getResultList().contains(employee)) {
				return query.getResultStream().filter(x -> x.equals(employee)).findFirst().get().getId();
			} else {
				throw new EntityNotFoundException();
			}
		} finally {
			em.close();
		}
	}

	@Override
	public List<Employee> getAll() {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public void updateById(int id, Employee employee) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Employee mergedEmployee = em.find(Employee.class, id);
			mergedEmployee.setName(employee.getName());
			mergedEmployee.setLastName(employee.getLastName());
			mergedEmployee.setAge(employee.getAge());
			mergedEmployee.setCity(employee.getCity());
			em.merge(mergedEmployee);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteById(int id) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.find(Employee.class, id));
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public void close() {
		emf.close();
	}

	private static EntityManager getEntityManager() {
		return EmployeeDAOImpl.emf.createEntityManager();
	}
}
