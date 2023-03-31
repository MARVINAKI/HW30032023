import DAO.CityDAOImpl;
import DAO.EmployeeDAOImpl;
import Model.City;
import Model.Employee;

import java.sql.SQLException;

public class Application {

	private static final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	private static final CityDAOImpl cityDAO = new CityDAOImpl();

	public static void main(String[] args) throws SQLException {
		System.out.println(cityDAO.updateByID(1, new City("UFA")));
	}
}
