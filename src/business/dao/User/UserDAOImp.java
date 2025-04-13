package business.dao.User;

import business.config.ConnectionDB;
import business.model.User.User;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class UserDAOImp implements UserDAO {
    @Override
    public User loginUser(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call login(?,?,?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            callSt.registerOutParameter(3, Types.INTEGER);

            boolean hasResultSet = callSt.execute();
            int returnCode = callSt.getInt(3);

            if (returnCode == 1 && hasResultSet) {
                rs = callSt.getResultSet();
                if (rs.next()) {
                    User user = new User();
                    user.setUser_id(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setStatus(rs.getBoolean("status"));
                    return user;
                }
            } else if (returnCode == 0) {
                System.out.println("Tài khoản hoặc mật khẩu không đúng.");
            } else if (returnCode == 2) {
                System.out.println("Tài khoản đã bị khóa.");
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình đăng nhập: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình đăng nhập: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return null;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public boolean save(Object o) {
        return false;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }
}
