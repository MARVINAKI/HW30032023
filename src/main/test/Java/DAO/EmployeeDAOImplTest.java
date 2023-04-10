package DAO;

import Model.City;
import Model.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class EmployeeDAOImplTest {

	private static final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private static final CityDAO cityDAO = new CityDAOImpl();

	private static final String CITY_NAME = "TestCityName";
	private static final String CITY_NAME_UPDATED = "TestCityNameUpdated";
	private static final String EMPLOYEE_NAME = "TestEmployeeName";
	private static final String EMPLOYEE_NAME_UPDATED = "TestEmployeeNameUpdated";
	private static final String EMPLOYEE_LASTNAME = "TestEmployeeLastname";
	private static final String EMPLOYEE_LASTNAME_UPDATED = "TestEmployeeLastnameUpdated";
	private static final int EMPLOYEE_AGE = 25;
	private static final int EMPLOYEE_AGE_UPDATED = 35;
	private static final City city = new City(CITY_NAME);
	private static final City cityUpdated = new City(CITY_NAME_UPDATED);
	private static final Employee employee = new Employee(EMPLOYEE_NAME, EMPLOYEE_LASTNAME, EMPLOYEE_AGE, city);
	private static final Employee employeeUpdated = new Employee(EMPLOYEE_NAME_UPDATED, EMPLOYEE_LASTNAME_UPDATED, EMPLOYEE_AGE_UPDATED, cityUpdated);

	@BeforeAll
	static void fillDataBase() {
		cityDAO.addCity(city);
		assertTrue(cityDAO.getAll().contains(city));

		employeeDAO.addEmployee(employee);
		assertTrue(employeeDAO.getAll().contains(employee));
	}

	@AfterAll
	static void clearAllTestsEntityInDB() {
		while (cityDAO.getAll().contains(city)) {
			cityDAO.deleteByID(cityDAO.findIdByCity(city));
		}

		while (cityDAO.getAll().contains(cityUpdated)) {
			cityDAO.deleteByID(cityDAO.findIdByCity(cityUpdated));
		}

		assertFalse(cityDAO.getAll().contains(city));
		assertFalse(cityDAO.getAll().contains(cityUpdated));
		assertFalse(employeeDAO.getAll().contains(employee));
		assertFalse(employeeDAO.getAll().contains(employeeUpdated));
	}

	@Test
	void checkOfSuccessfulAdditionEmployeeInDB() {
		assertTrue(employeeDAO.getAll().contains(employee));
	}

	@Test
	void shouldBeCheckForThrowsInMethodFindId() {
		assertDoesNotThrow(() -> employeeDAO.findIdByEmployee(employee));
	}

	@Test
	void shouldBeCheckNotEmptyListOfEmployeeAndCity() {
		assertTrue(employeeDAO.getAll().size() > 0);
	}

	@Test
	void shouldBeCheckNotEmptyListOfCity() {
		assertTrue(cityDAO.getAll().size() > 0);
	}
}
