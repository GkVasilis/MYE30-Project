package mye30.project.db3_5030_5152.etl;

import java.sql.Connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableInitialization {

    public static void executeScript(String jdbcUrl, String username, String password, String sqlScriptPath) {
        try {
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Creating a statement object to execute SQL commands
            Statement statement = connection.createStatement();

            // Reading the SQL script file
            BufferedReader reader = new BufferedReader(new FileReader(sqlScriptPath));
            StringBuilder scriptContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                scriptContent.append(line).append("\n");
            }

            // Closing the reader
            reader.close();

            // Splitting script content into individual statements
            String[] sqlStatements = scriptContent.toString().split(";");

            // Executing each SQL statement
            for (String sqlStatement : sqlStatements) {
                if (!sqlStatement.trim().isEmpty()) {
                    statement.executeUpdate(sqlStatement);
                }
            }

            // Closing the statement and connection
            statement.close();
            connection.close();
            System.out.println("SQL script executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/MYE30_DB";
        String username = "root";
        String password = "root";
        // Execute scripts for table initialization
        System.out.println("Initializing DataBase tables...");
        executeScript(jdbcUrl, username, password, "src/main/resources/sql_scripts/Article_table.sql");
        executeScript(jdbcUrl, username, password, "src/main/resources/sql_scripts/PK_tables.sql");
        executeScript(jdbcUrl, username, password, "src/main/resources/sql_scripts/FK_tables.sql");
        executeScript(jdbcUrl, username, password, "src/main/resources/sql_scripts/Drop_tables.sql");
        System.out.println("Table script executing complete.");
    }
}
