package example.spring.jsf.sql.preparedstatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import example.spring.jsf.dao.UserEntity;
import example.spring.jsf.dao.UserListEntity;
import example.spring.jsf.sql.keys.UserTableKeys;

public class UserListPreparedStatement extends SQLPreparedStatements {

    @Override
    public Object getResultsFromResultSet(ResultSet resultSet) throws SQLException {
        UserListEntity rows = new UserListEntity();

        if(resultSet != null) {
	        while(resultSet.next()) {
	        	UserEntity row = new UserEntity();
	            row.setId(resultSet.getLong(UserTableKeys.ID.getKey()));
	            row.setFirstName(resultSet.getString(UserTableKeys.FIRST_NAME.getKey()));
	            row.setLastName(resultSet.getString(UserTableKeys.LAST_NAME.getKey()));
	            row.setHouseNumber(resultSet.getString(UserTableKeys.HOUSE_NUMBER.getKey()));
	            row.setStreet(resultSet.getString(UserTableKeys.STREET.getKey()));
	            row.setCity(resultSet.getString(UserTableKeys.CITY.getKey()));
	            row.setPostcode(resultSet.getString(UserTableKeys.POSTCODE.getKey()));

	            rows.getUserList().add(row);
	        }
    	}

    	return rows;
    }

    @Override
    public void setSaveParameters(PreparedStatement preparedStatement, List<String> parameters) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setUpdateParameters(PreparedStatement preparedStatement, List<String> parameters) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
