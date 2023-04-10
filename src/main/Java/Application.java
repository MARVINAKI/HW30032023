import DAO.CityDAO;
import DAO.CityDAOImpl;
import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import Model.City;

public class Application {

	private static final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private static final CityDAO cityDAO = new CityDAOImpl();

	public static void main(String[] args) {
		System.out.println(employeeDAO.findById(5));
		System.out.println(employeeDAO.getAll());
		System.out.println(cityDAO.findIdByCity(new City("UFA")));
		System.out.println(cityDAO.getAll());
	}
}
