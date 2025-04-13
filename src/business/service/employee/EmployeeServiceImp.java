package business.service.employee;

import business.dao.Employee.EmployeeDAOImp;
import business.model.Emloyee.Employee;

import java.util.List;

public class EmployeeServiceImp implements EmployeeService {
    EmployeeDAOImp employeeDAO;
    public EmployeeServiceImp() {
        employeeDAO = new EmployeeDAOImp();
    }

    public List<Employee> displayListEmployees(int currentPage, int pageSize) {
        return employeeDAO.paginationList(currentPage, pageSize);
    }

    public int numberEmployees() {
        return employeeDAO.numberEmployee();
    }
    public Employee getEmployeeById(String id) {
        return employeeDAO.searchEmployee(id);
    }
    public List<Employee> getEmployeesByName(String name) {
        return employeeDAO.searchEmployeesByName(name);
    }
    public List<Employee> getEmployeesByAge(int ageStart, int ageEnd) {
        return employeeDAO.searchEmployeesByAge(ageStart, ageEnd);
    }

    public void sortBySalary() {
        employeeDAO.sortBySalary();
    }

    public void sortByName() {
        employeeDAO.sortByName();
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public boolean update(Employee employee) {
        return employeeDAO.update(employee);
    }

    @Override
    public boolean delete(Employee employee) {
        return employeeDAO.delete(employee);
    }
}
