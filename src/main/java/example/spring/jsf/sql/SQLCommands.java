package example.spring.jsf.sql;

import java.util.List;

public class SQLCommands {

    /** Query to retrieve all rows from a table */
    private static String GET_LIST = "SELECT * FROM %s";

    /** Query to retrieve a row from a table */
    private static String GET_ITEM = "SELECT * FROM %s WHERE %s = %s";

    /** Query to add a row to a table */
    private static String SAVE_ITEM = "INSERT INTO %s (%s) VALUES (%s)";

    /** Query to update a row in a table */
    private static String UPDATE_ITEM = "UPDATE %s SET %s WHERE %s = %s";

    /** Query to delete a row from a table */
    private static String DELETE_ITEM = "DELETE FROM %s WHERE %s = %s";

    /**
     * Get the SQL to retrieve all rows from a table
     */
    public static String getGetAllQuery(String tableName) {
        return String.format(GET_LIST, tableName);
    }

    /**
     * Get the SQL to retrieve a row from a table
     */
    public static String getGetRowQuery(String table1Name, String primaryKey, long value) {
        return String.format(GET_ITEM, table1Name, primaryKey, value);
    }

    /**
     * Get the SQL to add a row to a table
     */
    public static String getSaveQuery(String table1Name, List<String> keys, int numParams) {
        StringBuilder columns = new StringBuilder();
        for(int i=0; i<keys.size(); i++) {
            columns.append(keys.get(i));

            if(i < keys.size()-1) {
                columns.append(",");
            }
        }

        StringBuilder parameters = new StringBuilder();
        for(int i=0; i<numParams; i++) {
            parameters.append("?");

            if(i < numParams-1) {
                parameters.append(",");
            }
        }

        return String.format(SAVE_ITEM, table1Name, columns.toString(), parameters.toString());
    }

    /**
     * Get the SQL to update a row in a table
     */
    public static String getUpdateQuery(String table1Name, List<String> keys, String primaryKey, long value) {
        StringBuilder columnsBuilder = new StringBuilder();
        for(int i=0; i<keys.size(); i++) {
            columnsBuilder.append(keys.get(i) + " = ?");

            if(i < keys.size()-1) {
                columnsBuilder.append(",");
            }
        }
        String columns = columnsBuilder.toString();

        return String.format(UPDATE_ITEM, table1Name, columns, primaryKey, String.valueOf(value));
    }

    /**
     * Get the SQL to delete a row from a table
     */
    public static String getDeleteQuery(String tableName, String primaryKey, long value) {
        return String.format(DELETE_ITEM, tableName, primaryKey, value);
    }
}