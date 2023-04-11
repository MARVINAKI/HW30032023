package DAO;

import Model.City;
import Model.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeDAOImplTest {

	@Mock
	private CityDAO cityDAOMock;
	@Mock
	private EmployeeDAO employeeDAOMock;
	@InjectMocks
	private CityDAOImpl cityDAOImplMock;
	@InjectMocks
	private EmployeeDAOImpl employeeDAOImplMock;

	private static final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private static final CityDAO cityDAO = new CityDAOImpl();

	private static final String CITY_NAME = "TestCityName";
	private static final String CITY_NAME_UPDATED = "TestCityNameUpdated";
	private static final String CITY_NAME_WRONG = "TSET";
	private static final String EMPLOYEE_NAME = "TestEmployeeName";
	private static final String EMPLOYEE_NAME_UPDATED = "TestEmployeeNameUpdated";
	private static final String EMPLOYEE_NAME_WRONG = "TsetEeyolpmeEmanDetadpu";
	private static final String EMPLOYEE_LASTNAME = "TestEmployeeLastname";
	private static final String EMPLOYEE_LASTNAME_UPDATED = "TestEmployeeLastnameUpdated";
	private static final String EMPLOYEE_LASTNAME_WRONG = "TsetEeyolpmeEmantsal";
	private static final int EMPLOYEE_AGE = 25;
	private static final int EMPLOYEE_AGE_UPDATED = 35;
	private static final City city = new City(CITY_NAME);
	private static final City cityUpdated = new City(CITY_NAME_UPDATED);
	private static final City cityNotInDB = new City(CITY_NAME_WRONG);
	private static final Employee employee = new Employee(EMPLOYEE_NAME, EMPLOYEE_LASTNAME, EMPLOYEE_AGE, city);
	private static final Employee employeeUpdated = new Employee(EMPLOYEE_NAME_UPDATED, EMPLOYEE_LASTNAME_UPDATED, EMPLOYEE_AGE_UPDATED, cityUpdated);
	private static final Employee employeeNotInDB = new Employee(EMPLOYEE_NAME_WRONG, EMPLOYEE_LASTNAME_WRONG, EMPLOYEE_AGE, cityNotInDB);

	@BeforeAll
	static void fillDataBase() {
		cityDAO.addCity(city);
		assertTrue(cityDAO.getAll().contains(city));

		cityDAO.addCity(cityUpdated);
		assertTrue(cityDAO.getAll().contains(cityUpdated));

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
		assertFalse(cityDAO.getAll().contains(cityNotInDB));
		assertFalse(employeeDAO.getAll().contains(employee));
		assertFalse(employeeDAO.getAll().contains(employeeUpdated));
		assertFalse(employeeDAO.getAll().contains(employeeNotInDB));
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

	@Test
	void shouldBeUpdatedEmployee() {
		employeeDAO.updateById(employeeDAO.findIdByEmployee(employee), employeeUpdated);

		assertFalse(employeeDAO.getAll().contains(employee));
		assertTrue(employeeDAO.getAll().contains(employeeUpdated));
	}

	@Test
	void shouldCatchEntityNotFoundException() {
		assertThrows(EntityNotFoundException.class, () -> employeeDAO.findIdByEmployee(employeeNotInDB));
	}

}
