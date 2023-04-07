package DAO;

import Model.City;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.LinkedHashMap;
import java.util.Map;

public class CityDAOImpl implements CityDAO {

	@Override
	public void addCity(City city) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		try {
			em.getTransaction().begin();
			em.persist(city);
			em.getTransaction().commit();
		} finally {
			closeEntityManager(em, emf);
		}
	}

	@Override
	public City findByID(int id) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		try {
			City city = em.find(City.class, id);
			return city == null ? new City() : city;
		} finally {
			closeEntityManager(em, emf);
		}
	}

	@Override
	public Map<Integer, String> getAll() {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		Map<Integer, String> cities = new LinkedHashMap<>();
		String str = "SELECT c FROM City c";
		try {
			TypedQuery<City> query = em.createQuery(str, City.class);
			for (City city : query.getResultList()) {
				cities.put(city.getId(), city.getName());
			}
			return cities;
		} finally {
			closeEntityManager(em, emf);
		}
	}

	@Override
	public void updateByID(int id, City city) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		try {
			em.getTransaction().begin();
			City mergedCity = em.find(City.class, id);
			mergedCity.setName(city.getName());
			em.merge(mergedCity);
			em.getTransaction().commit();
		} finally {
			closeEntityManager(em, emf);
		}
	}

	@Override
	public void deleteByID(int id) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = getEntityManager(emf);
		try {
			em.getTransaction().begin();
			em.remove(findByID(id));
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
