package business.dao.User;

import business.dao.AppDAO;
import business.model.User.User;

public interface UserDAO extends AppDAO {
    User loginUser(String username, String password);
}
