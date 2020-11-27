package example.spring.jsf.sql.preparedstatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import example.spring.jsf.dao.UserEntity;
import example.spring.jsf.sql.keys.UserTableKeys;

public class UserPreparedStatement extends SQLPreparedStatements {

    @Override
    public Object getResultsFromResultSet(ResultSet resultSet) throws SQLException {
    	if(resultSet != null) {
	        UserEntity row = new UserEntity();
	
	        if(resultSet.next()) {
	            row.setId(resultSet.getLong(UserTableKeys.ID.getKey()));
	            row.setFirstName(resultSet.getString(UserTableKeys.FIRST_NAME.getKey()));
	            row.setLastName(resultSet.getString(UserTableKeys.LAST_NAME.getKey()));
	            row.setHouseNumber(resultSet.getString(UserTableKeys.HOUSE_NUMBER.getKey()));
	            row.setStreet(resultSet.getString(UserTableKeys.STREET.getKey()));
	            row.setCity(resultSet.getString(UserTableKeys.CITY.getKey()));
	            row.setPostcode(resultSet.getString(UserTableKeys.POSTCODE.getKey()));
	        }
	
	        return row;
    	}

    	return null;
    }

    @Override
    public void setSaveParameters(PreparedStatement preparedStatement, List<String> parameters) throws SQLException {
        for(int i=0; i<parameters.size(); i++) {
            preparedStatement.setString(i+1, parameters.get(i));
        }
    }

    @Override
    public void setUpdateParameters(PreparedStatement preparedStatement, List<String> parameters) throws SQLException {
        for(int i=0; i<parameters.size(); i++) {
            preparedStatement.setString(i+1, parameters.get(i));
        }
    }
}
