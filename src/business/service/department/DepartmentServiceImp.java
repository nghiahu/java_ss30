package business.service.department;

import business.dao.Department.DepartmentDAOImp;
import business.model.Department.Department;

import java.util.List;

public class DepartmentServiceImp implements DepartmentService {
    DepartmentDAOImp departmentDAO;
    public DepartmentServiceImp() {
        departmentDAO = new DepartmentDAOImp();
    }

    public List<Department> displayPagination(int currentPage, int pageSize) {
        return departmentDAO.paginationList(currentPage, pageSize);
    }
    public int numberDepartments() {
        return departmentDAO.countDepartment();
    }

    public Department searchDepartmentByName(String departmentName) {
        return departmentDAO.selectDepartmentByName(departmentName);
    }

    public Department selectDepartmentById(int id) {
        return departmentDAO.selectDepartmentById(id);
    }

    public int isEmployeeInDepartment(int departmentId) {
        return departmentDAO.isEmployee(departmentId);
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public boolean save(Department department) {
        return departmentDAO.save(department);
    }

    @Override
    public boolean update(Department department) {
        return departmentDAO.update(department);
    }

    @Override
    public boolean delete(Department department) {
        return departmentDAO.delete(department);
    }
}
