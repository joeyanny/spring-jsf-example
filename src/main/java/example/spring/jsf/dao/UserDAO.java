package example.spring.jsf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import example.spring.jsf.sql.SQLCommands;
import example.spring.jsf.sql.keys.UserTableKeys;
import example.spring.jsf.sql.preparedstatement.UserListPreparedStatement;
import example.spring.jsf.sql.preparedstatement.UserPreparedStatement;

/**
 * Data access object (DAO) for handling the persisting of the user data.
 */
@Component("userDao")
public class UserDAO {

	private static Logger LOG = LogManager.getLogger(UserDAO.class);

    private DataSource dataSource;

    /**
     * Get all users
     */
    public UserListEntity getUserList() throws SQLException {
        String sqlQuery = SQLCommands.getGetAllQuery(UserTableKeys.TABLE_NAME.getKey());
        LOG.debug("Query to get all users: " + sqlQuery);

        return (UserListEntity) new UserListPreparedStatement().runGetQuery(dataSource, sqlQuery);
    }

    /**
     * Get one user
     */
    public UserEntity getUser(long id) throws SQLException {
        String sqlQuery = SQLCommands.getGetRowQuery(UserTableKeys.TABLE_NAME.getKey(), UserTableKeys.ID.getKey(), id);
        LOG.debug("Query to get user: " + sqlQuery);

        return (UserEntity) new UserPreparedStatement().runGetQuery(dataSource, sqlQuery);
    }

    /**
     * Save new user
     */
    public void saveUser(UserEntity user) throws SQLException {
        String sqlQuery = SQLCommands.getSaveQuery(UserTableKeys.TABLE_NAME.getKey(), UserTableKeys.getKeys(), UserTableKeys.getKeys().size());
        LOG.debug("Query to save user: " + sqlQuery);

        new UserPreparedStatement().runSaveQuery(dataSource, sqlQuery, getParameters(user));
    }

    /**
     * Update user
     */
    public void updateUser(UserEntity user) throws SQLException {
        String sqlQuery = SQLCommands.getUpdateQuery(UserTableKeys.TABLE_NAME.getKey(), UserTableKeys.getKeys(), UserTableKeys.ID.getKey(), user.getId());
        LOG.debug("Query to update user: " + sqlQuery);

        new UserPreparedStatement().runUpdateQuery(dataSource, sqlQuery, getParameters(user));
    }

    /**
     * Delete user
     */
    public void deleteUser(long id) throws SQLException {
        String sqlQuery = SQLCommands.getDeleteQuery(UserTableKeys.TABLE_NAME.getKey(), UserTableKeys.ID.getKey(), id);
        LOG.debug("Query to delete user: " + sqlQuery);

        new UserPreparedStatement().runDeleteQuery(dataSource, sqlQuery);
    }

    /**
     * Get the list of parameters for a user
     */
    private List<String> getParameters(UserEntity user) {
        List<String> parameterList = new ArrayList<String>();
        parameterList.add(user.getFirstName());
        parameterList.add(user.getLastName());
        parameterList.add(user.getHouseNumber());
        parameterList.add(user.getStreet());
        parameterList.add(user.getCity());
        parameterList.add(user.getPostcode());

        return parameterList;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}