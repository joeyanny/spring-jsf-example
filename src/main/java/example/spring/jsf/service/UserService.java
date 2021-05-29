package example.spring.jsf.service;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import example.spring.jsf.dao.UserDAO;
import example.spring.jsf.dao.UserEntity;
import example.spring.jsf.dao.UserListEntity;
import example.spring.jsf.exception.ServiceException;
import example.spring.jsf.model.User;
import example.spring.jsf.model.UserList;
import example.spring.jsf.transformer.UserTransformer;

/**
 * Handles the processing of a user resource
 */
@Service
public class UserService {

    private static Logger LOG = LogManager.getLogger(UserService.class);

    private UserDAO userDao;
    private UserTransformer userTransformer;

    /**
     * Query the data source to retrieve a list of users.
     * 
     * @return users
     * @throws SQLException
     */
    public UserList getAll() throws ServiceException {
        try {
            UserListEntity entityList = userDao.getUserList();
            return userTransformer.transform(entityList);
        } catch (SQLException e) {
            LOG.error("Failed to retrieve list of users: " + e.getMessage());
            throw new ServiceException("Failed to retrieve list of users", e);
        }
    }

    /**
     * Query the data source to retrieve a user by their ID.
     * 
     * @param id
     * @return user
     * @throws SQLException
     */
    public User get(long id) throws ServiceException {
        try {
            UserEntity entity = userDao.getUser(id);
            return userTransformer.transform(entity);
        } catch (SQLException e) {
            LOG.error("Failed to retrieve user [" + id + "]: " + e.getMessage());
            throw new ServiceException("Failed to retrieve user with ID: " + id, e);
        }
    }

    /**
     * Store a new user in the data source.
     * Note: An ID will be auto-generated for the user.
     * 
     * @param user
     * @throws SQLException
     */
    public void save(User user) throws ServiceException {
        try {
            UserEntity entity = userTransformer.transform(user);
            userDao.saveUser(entity);
        } catch (SQLException e) {
            LOG.error("Failed to save user: " + e.getMessage());
            throw new ServiceException("Failed to save user", e);
        } 
    }

    /**
     * Update an existing user within the data source.
     * Note: This will overwrite every field.
     * 
     * @param id
     * @param user
     * @throws SQLException
     */
    public void update(User user) throws ServiceException {
        try {
            UserEntity entity = userTransformer.transform(user);
            userDao.updateUser(entity);
        } catch (SQLException e) {
            LOG.error("Failed to update user [" + user.getId() + "]: " + e.getMessage());
            throw new ServiceException("Failed to update user with ID: " + user.getId(), e);
        }
    }

    /**
     * Delete a user with a matching ID
     * 
     * @param id
     * @throws SQLException
     */
    public void delete(long id) throws ServiceException {
        try {
            userDao.deleteUser(id);
        } catch (SQLException e) {
            LOG.error("Failed to delete user [" + id + "]: " + e.getMessage());
            throw new ServiceException("Failed to delete user with ID: " + id, e);
        }
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void setUserTransformer(UserTransformer userTransformer) {
        this.userTransformer = userTransformer;
    }
}
