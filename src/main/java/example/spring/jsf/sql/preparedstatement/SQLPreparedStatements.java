package example.spring.jsf.sql.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public abstract class SQLPreparedStatements {

    /**
     * Run an SQL query using a prepared statement
     */
    public Object runGetQuery(DataSource dataSource, String query) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            try(ResultSet resultSet = preparedStatement.executeQuery()) {
	                return getResultsFromResultSet(resultSet);
	            }
            }
        }
    }

    /**
     * Run query to save a new object
     */
    public void runSaveQuery(DataSource dataSource, String query, List<String> parameters) throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
        	try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            setSaveParameters(preparedStatement, parameters);
	            preparedStatement.executeUpdate();
        	}
        }
    }

    /**
     * Run query to update an existing object
     */
    public void runUpdateQuery(DataSource dataSource, String query, List<String> parameters) throws SQLException {
    	try(Connection connection = dataSource.getConnection()) {
    		try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            setUpdateParameters(preparedStatement, parameters);
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
    		}
        }
    }

    /**
     * Run query to delete an existing object
     */
    public void runDeleteQuery(DataSource dataSource, String query) throws SQLException {
    	try(Connection connection = dataSource.getConnection()) {
    		try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    			preparedStatement.executeUpdate();
    			preparedStatement.close();
    		}
        }
    }

    /**
     * Create an object using the result of the query
     */
    public abstract Object getResultsFromResultSet(ResultSet resultSet) throws SQLException;

    /**
     * Set the parameters to save the new object
     */
    public abstract void setSaveParameters(PreparedStatement preparedStatement, List<String> parameters) throws SQLException;

    /**
     * Set the parameters to update the object
     */
    public abstract void setUpdateParameters(PreparedStatement preparedStatement, List<String> parameters) throws SQLException;
}
