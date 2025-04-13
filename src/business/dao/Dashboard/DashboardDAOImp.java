package business.dao.Dashboard;

import business.config.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardDAOImp implements DashboardDAO {
    public Map<String, Integer> employeeInDepartment() {
        Connection conn = null;
        CallableStatement callSt = null;
        Map<String, Integer> result = new HashMap<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call employeeInDep()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                String departmentName = rs.getString("department_name");
                int totalEmployees = rs.getInt("total_employees");
                result.put(departmentName, totalEmployees);
            }
            return result;
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trính thống kê: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình thống kê" + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return null;
    }
    public int getTotalEmployees() {
        Connection conn = null;
        CallableStatement callSt = null;
        int total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call totalEmploy()}");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trính thống kê: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình thống kê" + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return total;
    }
    public void departmentWithMostEmployees() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call topDepartmetn()}");
            rs = callSt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("department_id");
                String name = rs.getString("department_name");
                int total = rs.getInt("total_employees");
                System.out.println("Phòng ban có nhiều nhân viên nhất:");
                System.out.println("ID: " + id + ", Tên: " + name + ", Số lượng nhân viên: " + total);
            } else {
                System.out.println("Không có dữ liệu.");
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trính thống kê: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình thống kê" + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
    public void departmentWithHighestSalary() {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call topSalary()}");
            rs = callSt.executeQuery();
            if (rs.next()) {
                String departmentName = rs.getString("department_name");
                double totalSalary = rs.getDouble("total_salary");
                System.out.println("Phòng ban có tổng lương cao nhất:");
                System.out.println("Tên phòng ban: " + departmentName + ", Tổng lương: " + totalSalary);
            } else {
                System.out.println("Không có dữ liệu.");
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trính thống kê: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình thống kê" + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

}
