import DAO.CityDAOImpl;
import DAO.EmployeeDAOImpl;
import Model.City;
import Model.Employee;

public class Application {

	private static final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	private static final CityDAOImpl cityDAO = new CityDAOImpl();

	public static void main(String[] args) {

		cityDAO.updateByID(8, new City("Iglino"));
		System.out.println(cityDAO.getAll());
		employeeDAO.updateById(30, new Employee("TEST", "TESTER", 33, 8));
		System.out.println(employeeDAO.findById(30));
	}
}
