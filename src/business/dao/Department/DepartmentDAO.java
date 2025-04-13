package business.dao.Department;

import business.dao.AppDAO;
import business.model.Department.Department;

import java.util.List;

public interface DepartmentDAO extends AppDAO<Department> {
     List<Department> paginationList(int currentPage, int pageSize);
     int countDepartment();
     Department selectDepartmentByName(String departmentName);
     Department selectDepartmentById(int id);
}
