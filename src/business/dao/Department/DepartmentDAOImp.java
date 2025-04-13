package business.dao.Department;

import business.config.ConnectionDB;
import business.model.Department.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDAOImp implements DepartmentDAO {

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public boolean save(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addDepartment(?,?,?)}");
            callSt.setString(1,department.getDepartment_name());
            callSt.setString(2,department.getDescription());
            callSt.setBoolean(3,department.isStatus());
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
    public boolean update(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateDepartment(?,?,?,?)}");
            callSt.setInt(1,department.getDepartment_id());
            callSt.setString(2,department.getDepartment_name());
            callSt.setString(3,department.getDescription());
            callSt.setBoolean(4,department.isStatus());
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
    public boolean delete(Department department) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delDepartment(?)}");
            callSt.setInt(1,department.getDepartment_id());
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

    @Override
    public List<Department> paginationList(int currentPage, int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Department> listDepartment = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call displayListDepartment(?,?)}");
            callSt.setInt(1, currentPage);
            callSt.setInt(2, pageSize);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDepartment_id(rs.getInt("department_id"));
                department.setDepartment_name(rs.getString("department_name"));
                department.setDescription(rs.getString("description"));
                department.setStatus(rs.getBoolean("status"));
                listDepartment.add(department);
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình hiển thị danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình hiển thị danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listDepartment;
    }

    @Override
    public int countDepartment() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call numDepartment(?)}");
            callSt.registerOutParameter(1, Types.INTEGER);
            callSt.execute();
            return callSt.getInt(1);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println( e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public Department selectDepartmentByName(String departmentName) {
        Connection conn = null;
        CallableStatement callSt = null;
        Department department = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call searchDepByName(?)}");
            callSt.setString(1, departmentName);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                department = new Department();
                department.setDepartment_id(rs.getInt("department_id"));
                department.setDepartment_name(rs.getString("department_name"));
                department.setDescription(rs.getString("description"));
                department.setStatus(rs.getBoolean("status"));
                return department;
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trính tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm" + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return null;
    }

    @Override
    public Department selectDepartmentById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Department department = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call searchDepById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                department = new Department();
                department.setDepartment_id(rs.getInt("department_id"));
                department.setDepartment_name(rs.getString("department_name"));
                department.setDescription(rs.getString("description"));
                department.setStatus(rs.getBoolean("status"));
                return department;
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trính tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm" + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return null;
    }

    public int isEmployee(int department_id) {
        Connection conn = null;
        CallableStatement callSt = null;
        int count = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call isEmployee(?)}");
            callSt.setInt(1, department_id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }catch (SQLException e) {
            System.out.println("Có lỗi trong quá trính tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm" + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return count;
    }
}
