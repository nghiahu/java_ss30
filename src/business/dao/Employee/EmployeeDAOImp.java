package business.dao.Employee;

import business.config.ConnectionDB;
import business.model.Emloyee.Emp_Status;
import business.model.Emloyee.Employee;
import business.model.Emloyee.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImp implements EmployeeDAO {
    @Override
    public List<Employee> paginationList(int currentPage, int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> listEmployee = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call displayListEmployee(?,?)}");
            callSt.setInt(1, currentPage);
            callSt.setInt(2, pageSize);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("emp_id"));
                employee.setEmployee_name(rs.getString("emp_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                employee.setGender(Gender.valueOf(rs.getString("gender")));
                employee.setSalary_grade(rs.getInt("salary_grade"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthday(rs.getDate("birthday").toLocalDate());
                employee.setAddress(rs.getString("address"));
                employee.setStatus(Emp_Status.valueOf(rs.getString("status")));
                employee.setDepartment_id(rs.getInt("department_id"));
                listEmployee.add(employee);
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình hiển thị danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình hiển thị danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listEmployee;
    }

    public int numberEmployee() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call numEmployee()}");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình hiển thị danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình hiển thị danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public boolean save(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addEmployee(?,?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1, employee.getEmployee_id());
            callSt.setString(2, employee.getEmployee_name());
            callSt.setString(3, employee.getEmail());
            callSt.setString(4, employee.getPhone_number());
            callSt.setString(5, employee.getGender().name());
            callSt.setInt(6, employee.getSalary_grade());
            callSt.setDouble(7, employee.getSalary());
            callSt.setDate(8, Date.valueOf(employee.getBirthday()));
            callSt.setString(9, employee.getAddress());
            callSt.setString(10, employee.getStatus().name());
            callSt.setInt(11,employee.getDepartment_id());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình thêm: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình thêm: " + e.getMessage());
        }finally {
            try {
                if (callSt != null) callSt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean update(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateEmployee(?,?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1, employee.getEmployee_id());
            callSt.setString(2, employee.getEmployee_name());
            callSt.setString(3, employee.getEmail());
            callSt.setString(4, employee.getPhone_number());
            callSt.setString(5, employee.getGender().name());
            callSt.setInt(6, employee.getSalary_grade());
            callSt.setDouble(7, employee.getSalary());
            callSt.setDate(8, Date.valueOf(employee.getBirthday()));
            callSt.setString(9, employee.getAddress());
            callSt.setString(10, employee.getStatus().name());
            callSt.setInt(11, employee.getDepartment_id());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình cập nhật: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình cập nhật: " + e.getMessage());
        }finally {
            try {
                if (callSt != null) callSt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean delete(Employee employee) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delEmployee(?)}");
            callSt.setString(1, employee.getEmployee_id());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trính xóa: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình xóa" + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    public Employee searchEmployee(String employee_id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Employee employee = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call searchEmpById(?)}");
            callSt.setString(1, employee_id);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()) {
                employee = new Employee();
                employee.setEmployee_id(rs.getString("emp_id"));
                employee.setEmployee_name(rs.getString("emp_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                employee.setGender(Gender.valueOf(rs.getString("gender")));
                employee.setSalary_grade(rs.getInt("salary_grade"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthday(rs.getDate("birthday").toLocalDate());
                employee.setAddress(rs.getString("address"));
                employee.setStatus(Emp_Status.valueOf(rs.getString("status")));
                employee.setDepartment_id(rs.getInt("department_id"));
                return employee;
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trính tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm" + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employee;
    }
    public List<Employee> searchEmployeesByName(String employee_name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> employees = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call searchEmpByName(?)}");
            callSt.setString(1, employee_name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("emp_id"));
                employee.setEmployee_name(rs.getString("emp_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                employee.setGender(Gender.valueOf(rs.getString("gender")));
                employee.setSalary_grade(rs.getInt("salary_grade"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthday(rs.getDate("birthday").toLocalDate());
                employee.setAddress(rs.getString("address"));
                employee.setStatus(Emp_Status.valueOf(rs.getString("status")));
                employee.setDepartment_id(rs.getInt("department_id"));
                employees.add(employee);
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình hiển thị danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình hiển thị danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employees;
    }
    public List<Employee> searchEmployeesByAge(int ageStart, int ageEnd) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Employee> employees = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call searchEmpByAge(?,?)}");
            callSt.setInt(1, ageStart);
            callSt.setInt(2, ageEnd);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("emp_id"));
                employee.setEmployee_name(rs.getString("emp_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                employee.setGender(Gender.valueOf(rs.getString("gender")));
                employee.setSalary_grade(rs.getInt("salary_grade"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthday(rs.getDate("birthday").toLocalDate());
                employee.setAddress(rs.getString("address"));
                employee.setStatus(Emp_Status.valueOf(rs.getString("status")));
                employee.setDepartment_id(rs.getInt("department_id"));
                employees.add(employee);
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình hiển thị danh sách: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình hiển thị danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return employees;
    }
    public void sortBySalary() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call sortBySalary()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("emp_id"));
                employee.setEmployee_name(rs.getString("emp_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                employee.setGender(Gender.valueOf(rs.getString("gender")));
                employee.setSalary_grade(rs.getInt("salary_grade"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthday(rs.getDate("birthday").toLocalDate());
                employee.setAddress(rs.getString("address"));
                employee.setStatus(Emp_Status.valueOf(rs.getString("status")));
                employee.setDepartment_id(rs.getInt("department_id"));
                employee.displayData();
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
    public void sortByName() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call sortByName()");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getString("emp_id"));
                employee.setEmployee_name(rs.getString("emp_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));
                employee.setGender(Gender.valueOf(rs.getString("gender")));
                employee.setSalary_grade(rs.getInt("salary_grade"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthday(rs.getDate("birthday").toLocalDate());
                employee.setAddress(rs.getString("address"));
                employee.setStatus(Emp_Status.valueOf(rs.getString("status")));
                employee.setDepartment_id(rs.getInt("department_id"));
                employee.displayData();
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
}
