package example.spring.jsf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.spring.jsf.model.User;
import example.spring.jsf.model.UserList;
import example.spring.jsf.sql.SQLCommands;
import example.spring.jsf.sql.keys.UserTableKeys;
import example.spring.jsf.sql.preparedstatement.UserListPreparedStatement;
import example.spring.jsf.sql.preparedstatement.UserPreparedStatement;

@Component("userDao")
public class UserDao {
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Get all users
     */
    public UserList getUserList() throws SQLException {
        String sqlQuery = SQLCommands.getGetAllQuery(UserTableKeys.TABLE_NAME.getKey());
        return (UserList) new UserListPreparedStatement().runGetQuery(dataSource, sqlQuery);
    }

    /**
     * Get one user
     */
    public User getUser(long id) throws SQLException {
        String sqlQuery = SQLCommands.getGetRowQuery(UserTableKeys.TABLE_NAME.getKey(), UserTableKeys.ID.getKey(), id);
        return (User) new UserPreparedStatement().runGetQuery(dataSource, sqlQuery);
    }

    /**
     * Save new user
     */
    public void saveUser(User user) throws SQLException {
        String sqlQuery = SQLCommands.getSaveQuery(UserTableKeys.TABLE_NAME.getKey(), UserTableKeys.getKeys(), UserTableKeys.getKeys().size());
        new UserPreparedStatement().runSaveQuery(dataSource, sqlQuery, getParameters(user));
    }

    /**
     * Update user
     */
    public void updateUser(User user) throws SQLException {
        String sqlQuery = SQLCommands.getUpdateQuery(UserTableKeys.TABLE_NAME.getKey(), UserTableKeys.getKeys(), UserTableKeys.ID.getKey(), user.getId());
        new UserPreparedStatement().runUpdateQuery(dataSource, sqlQuery, getParameters(user));
    }

    /**
     * Delete user
     */
    public void deleteUser(long id) throws SQLException {
        String sqlQuery = SQLCommands.getDeleteQuery(UserTableKeys.TABLE_NAME.getKey(), UserTableKeys.ID.getKey(), id);
        new UserPreparedStatement().runDeleteQuery(dataSource, sqlQuery);
    }

    /**
     * Get the list of parameters for a user
     */
    private List<String> getParameters(User user) {
        List<String> parameterList = new ArrayList<String>();
        parameterList.add(user.getFirstName());
        parameterList.add(user.getLastName());
        parameterList.add(user.getHouseNumber());
        parameterList.add(user.getStreet());
        parameterList.add(user.getCity());
        parameterList.add(user.getPostcode());

        return parameterList;
    }
}