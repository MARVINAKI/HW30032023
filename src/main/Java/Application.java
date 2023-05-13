import DAO.CityDAO;
import DAO.CityDAOImpl;
import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import Model.City;
import Model.Employee;

public class Application {

	private static final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private static final CityDAO cityDAO = new CityDAOImpl();

	public static void main(String[] args) {
	}
}
