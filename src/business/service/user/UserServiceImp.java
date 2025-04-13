package business.service.user;

import business.dao.User.UserDAO;
import business.dao.User.UserDAOImp;
import business.model.User.User;

import java.util.List;

public class UserServiceImp implements UserService{
    private final UserDAO userDAO;

    public UserServiceImp() {
        userDAO = new UserDAOImp();
    }

    public User userLogin(String username, String password) {
        return userDAO.loginUser(username, password);
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
