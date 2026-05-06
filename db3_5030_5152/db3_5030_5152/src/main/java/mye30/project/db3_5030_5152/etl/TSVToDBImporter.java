package mye30.project.db3_5030_5152.etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class TSVToDBImporter {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/MYE30_DB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    // Chech if blank character
    private static boolean isWhiteSpace(String input) {
        Pattern pattern = Pattern.compile("^\\s*$");
        return pattern.matcher(input).matches();
    }

    // Insert data to db
    private static void insertDataToDBTable(Connection connection, String tsvFile, String tableName) throws IOException, SQLException {
        int recordsInserted = 0;
        long startTime = System.currentTimeMillis();

        try (BufferedReader reader = new BufferedReader(new FileReader(tsvFile))) {
            reader.readLine(); // Read the header line

            // Get the columns of the table dynamically
            List<String> columns = getTableColumns(connection, tableName);

            // Construct the SQL query
            String insertQuery = constructInsertQuery(tableName, columns);

            // Prepare the insert statement
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                String[] line;
                while ((line = reader.readLine().split("\t")) != null) {
                    try {
                        // Set parameters for the prepared statement based on the TSV columns and their indices
                        for (int i = 0; i < columns.size(); i++) {
                            if(isWhiteSpace(line[i])) {
                                preparedStatement.setString(i + 1, null);
                            }
                            else {
                                preparedStatement.setString(i + 1, line[i]);
                            }
                        }
                        preparedStatement.executeUpdate();
                        recordsInserted++;
                    } catch (SQLException e) {
                        System.err.println("Error inserting data: " + e.getMessage());
                        System.err.println("Problematic data: " + Arrays.toString(line));
                    }
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // Calculate elapsed time for each csv insertion
        long endTime = System.currentTimeMillis();
        int timeElapsed = (int) (endTime - startTime);
        System.out.println(recordsInserted + " records inserted into " + tableName + " in " + (double)timeElapsed/1000 + " seconds.\n");
    }

    // Method to retrieve the columns of the table
    private static List<String> getTableColumns(Connection connection, String tableName) throws SQLException {
        List<String> columns = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getColumns(null, null, tableName, null)) {
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                columns.add(columnName);
            }
        }
        return columns;
    }

    // Method to construct the INSERT query dynamically
    private static String constructInsertQuery(String tableName, List<String> columns) {
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
        queryBuilder.append(tableName).append(" (");
        // Append column names
        for (int i = 0; i < columns.size(); i++) {
            queryBuilder.append(columns.get(i));
            if (i < columns.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        // Append values placeholders
        queryBuilder.append(") VALUES (");
        for (int i = 0; i < columns.size(); i++) {
            queryBuilder.append("?");
            if (i < columns.size() - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");
        return queryBuilder.toString();
    }


    public static void main(String[] args) {
        String path = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\transformed_data";
        String[] csvFile = {"DataForJournal.tsv",
                "DataForConference.csv",
                "Journal_Articles_Data.tsv",
                "Conference_Articles_Data.tsv",
                "Articles_Data.tsv",
                "Authors_Data.tsv",
                "Journal_rankings_Data.tsv",
                "Conference_rankings_Data.tsv",
                "Conference_Categories_Data.tsv"};

        String[] tableName = {"journals", "conferences", "journal_articles", "conference_articles",
                "articles", "authors", "journal_rankings", "conference_rankings", "conference_categories"};

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            int len = csvFile.length;
            // First add countries and the missing ones
            /*
            System.out.println("Inserting data to table: countries");
            insertDataToDBTable(connection, path+"Tf_Countries_Data.csv", "countries");
            connection.setAutoCommit(false);
            MissingCountriesFinder finder = new MissingCountriesFinder();
            finder.findMissingCountries();
            connection.commit();
            connection.setAutoCommit(true);
            */
            for(int i = 0; i<len; i++) {
                System.out.println("\nInserting data to table: " + tableName[i]);
                insertDataToDBTable(connection, path+csvFile[i], tableName[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data insertion complete!");
    }
}
