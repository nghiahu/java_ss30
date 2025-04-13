package business.dao.Employee;

import business.dao.AppDAO;
import business.model.Emloyee.Employee;

import java.util.List;

public interface EmployeeDAO extends AppDAO<Employee> {
    List<Employee> paginationList(int currentPage, int pageSize);
}
