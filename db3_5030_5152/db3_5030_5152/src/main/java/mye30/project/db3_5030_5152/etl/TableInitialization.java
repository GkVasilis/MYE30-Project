package mye30.project.db3_5030_5152.etl;

import java.io.File;
import java.sql.Connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.Statement;

public class TableInitialization {

    public static void executeScript(String jdbcUrl, String username, String password, String sqlScriptPath) {
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            Statement statement = connection.createStatement();

            BufferedReader reader = new BufferedReader(new FileReader(sqlScriptPath));
            StringBuilder scriptContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                scriptContent.append(line).append("\n");
            }

            reader.close();

            String[] sqlStatements = scriptContent.toString().split(";");

            for (String sqlStatement : sqlStatements) {
                if (!sqlStatement.trim().isEmpty()) {
                    statement.executeUpdate(sqlStatement);
                }
            }

            statement.close();
            connection.close();
            System.out.println("SQL script executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/mye30_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true";
        String username = "root";
        String password = "root";

        System.out.println("Initializing DataBase tables...");
        String projectRoot = System.getProperty("user.dir");
        executeScript(jdbcUrl, username, password, projectRoot + File.separator /*+ "db3_5030_5152" + File.separator */ + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "sql_scripts" + File.separator + "Drop_tables.sql");
        executeScript(jdbcUrl, username, password, projectRoot + File.separator /*+ "db3_5030_5152" + File.separator */ + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "sql_scripts" + File.separator + "Article_table.sql");
        executeScript(jdbcUrl, username, password, projectRoot + File.separator /*+ "db3_5030_5152" + File.separator */ + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "sql_scripts" + File.separator + "PK_tables.sql");
        executeScript(jdbcUrl, username, password, projectRoot + File.separator /*+ "db3_5030_5152" + File.separator */ + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "sql_scripts" + File.separator + "FK_tables.sql");

        System.out.println("Table script executing complete.");
    }
}
