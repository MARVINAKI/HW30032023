import DAO.CityDAOImpl;
import DAO.EmployeeDAOImpl;

public class Application {

	private static final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	private static final CityDAOImpl cityDAO = new CityDAOImpl();

	public static void main(String[] args) {

		System.out.println(employeeDAO.getAll());

		System.out.println(cityDAO.getAll());

	}
}
