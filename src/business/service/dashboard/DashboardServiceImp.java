package business.service.dashboard;

import business.dao.Dashboard.DashboardDAOImp;

import java.util.Map;

public class DashboardServiceImp implements DashboardService {
    DashboardDAOImp dashboardDAOImp = new DashboardDAOImp();
    public DashboardServiceImp(){
        dashboardDAOImp = new DashboardDAOImp();
    }
    public Map<String, Integer> employeeInDep() {
        return dashboardDAOImp.employeeInDepartment();
    }
    public int getTotalEmployees(){
        return dashboardDAOImp.getTotalEmployees();
    }
    public void departmentWithMostEmployees(){
        dashboardDAOImp.departmentWithMostEmployees();
    }
    public void departmentWithHighestSalary() {
        dashboardDAOImp.departmentWithHighestSalary();
    }
}
