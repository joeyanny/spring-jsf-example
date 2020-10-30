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
        Object object = null;
        Connection connection = dataSource.getConnection();

        if(connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            try {
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet != null) {
                    object = getResultsFromResultSet(resultSet);
                    resultSet.close();
                }

                preparedStatement.close();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }

        return object;
    }

    /**
     * Run query to save a new object
     */
    public void runSaveQuery(DataSource dataSource, String sqlQuery, List<String> parameters) throws SQLException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            setSaveParameters(ps, parameters);

            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Run query to update an existing object
     */
    public void runUpdateQuery(DataSource dataSource, String sqlQuery, List<String> parameters) throws SQLException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            setUpdateParameters(ps, parameters);

            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Run query to delete an existing object
     */
    public void runDeleteQuery(DataSource dataSource, String sqlQuery) throws SQLException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery);

            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                connection.close();
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
