package DAO;

import Model.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.LinkedHashMap;
import java.util.Map;

public class CityDAOImpl implements CityDAO {

	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
	private final EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public boolean addCity(City city) {
		entityManager.getTransaction().begin();
		entityManager.persist(city);
		entityManager.getTransaction().commit();
		boolean response = entityManager.contains(city);
		closeEntityManager();
		return response;
	}

	@Override
	public City findByID(int id) {
		City city = entityManager.find(City.class, id);
		entityManager.close();
		entityManagerFactory.close();
		return city == null ? new City() : city;
	}

	@Override
	public Map<Integer, String> getAll() {
		Map<Integer, String> cities = new LinkedHashMap<>();
		String str = "SELECT c FROM City c";
		TypedQuery<City> query = entityManager.createQuery(str, City.class);
		for (City city : query.getResultList()) {
			cities.put(city.getId(), city.getName());
		}
		closeEntityManager();
		return cities;
	}

	@Override
	public boolean updateByID(int id, City city) {
		entityManager.getTransaction().begin();
		city = entityManager.merge(findByID(id));
		entityManager.getTransaction().commit();
		boolean response = entityManager.contains(city);
		closeEntityManager();
		return response;
	}

	@Override
	public boolean deleteByID(int id) {
		entityManager.getTransaction().begin();
		entityManager.remove(findByID(id));
		entityManager.getTransaction().commit();
		boolean response = !entityManager.contains(findByID(id));
		closeEntityManager();
		return response;
	}

	private void closeEntityManager() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
