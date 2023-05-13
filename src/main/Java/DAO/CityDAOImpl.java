package DAO;

import Model.City;

import javax.persistence.*;
import java.util.List;

public class CityDAOImpl implements CityDAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

	@Override
	public void addCity(City city) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(city);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public City findByID(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(City.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public int findIdByCity(City city) {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<City> query = em.createQuery("SELECT c FROM City c", City.class);
			if (query.getResultList().contains(city)) {
				return query.getResultStream().filter(x -> x.equals(city)).findFirst().get().getId();
			} else {
				throw new EntityNotFoundException("Город не найден в базе");
			}
		} finally {
			em.close();
		}
	}

	@Override
	public List<City> getAll() {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<City> query = em.createQuery("SELECT c FROM City c", City.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public void updateByID(int id, City city) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			City mergedCity = em.find(City.class, id);
			mergedCity.setName(city.getName());
			em.merge(mergedCity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteByID(int id) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.find(City.class, id));
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public final void close() {
		emf.close();
	}

	private static EntityManager getEntityManager() {
		return CityDAOImpl.emf.createEntityManager();
	}
}
