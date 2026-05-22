package mye30.project.db3_5030_5152.etl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class TSVToDBImporter {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mye30_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    private static boolean isWhiteSpace(String input) {
        if (input == null) return true;
        Pattern pattern = Pattern.compile("^\\s*$");
        return pattern.matcher(input).matches();
    }

    private static void insertDataWithExplicitColumns(Connection connection, String tsvFile, String tableName, List<String> columns) throws IOException, SQLException {
        int recordsInserted = 0;
        long startTime = System.currentTimeMillis();

        try (BufferedReader reader = new BufferedReader(new FileReader(tsvFile))) {
            reader.readLine();

            String insertQuery = constructInsertQuery(tableName, columns);

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                String rawLine;
                while ((rawLine = reader.readLine()) != null) {
                    if (rawLine.trim().isEmpty()) continue;

                    String[] line = rawLine.split("\t", -1);

                    try {
                        for (int i = 0; i < columns.size(); i++) {
                            if (i >= line.length || isWhiteSpace(line[i])) {
                                preparedStatement.setNull(i + 1, Types.VARCHAR);
                            } else {
                                preparedStatement.setString(i + 1, line[i].trim());
                            }
                        }

                        preparedStatement.addBatch();
                        recordsInserted++;

                        if (recordsInserted % 1000 == 0) {
                            preparedStatement.executeBatch();
                            preparedStatement.clearBatch();
                            connection.commit();

                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    } catch (SQLException e) {
                        System.err.println("Skipping bad row in " + tableName + ": " + e.getMessage());
                    }
                }
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
                connection.commit();
            }
        } catch (IOException e1) {
            System.err.println("Could not find file: " + tsvFile);
        }

        long endTime = System.currentTimeMillis();
        System.out.println(recordsInserted + " records successfully pushed to " + tableName + " in " + (double)(endTime - startTime)/1000 + " seconds.\n");
    }

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

    private static String constructInsertQuery(String tableName, List<String> columns) {
        StringBuilder queryBuilder = new StringBuilder("INSERT IGNORE INTO ");
        queryBuilder.append(tableName).append(" (");
        for (int i = 0; i < columns.size(); i++) {
            queryBuilder.append(columns.get(i));
            if (i < columns.size() - 1) {
                queryBuilder.append(", ");
            }
        }
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
        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator;

        String[] csvFile = {
                "DataForJournal.tsv",
                "DataForConference.tsv",
                "Articles_Data.tsv",
                "Journal_Articles_Data.tsv",
                "Conference_Articles_Data.tsv",
                "Journal_rankings_Data.tsv",
                "Conference_rankings_Data.tsv",
                "Authors_Data.tsv"
                /*"Conference_Categories_Data.tsv"*/
        };

        String[] tableName = {
                "journals", "conferences", "articles", "journal_articles",
                "conference_articles",
                "journal_rankings",
                "conference_rankings",
                "authors"/*, "conference_categories"*/
        };

        List<List<String>> allColumns = Arrays.asList(
                Arrays.asList("journal_ID", "journal_name", "publisher"),
                Arrays.asList("conference_ID", "conference_name"),
                Arrays.asList("article_ID", "title", "published_year"),
                Arrays.asList("article_ID", "title", "journal_ID", "journal_name", "publisher", "cdrom", "crossref", "mdate", "published_year", "url", "pages", "publtype", "journal_key"),
                Arrays.asList("article_ID", "conference_ID", "conference_name", "title", "cdrom", "crossref", "publtype", "url", "pages", "mdate", "published_year", "conference_key"),
                Arrays.asList("journal_ID", "j_rank", "title", "bestSubjectArea", "bestSubjectRank", "country", "bestCategories", "journal_language"),
                Arrays.asList("conference_ID", "conf_rank_ID", "title", "c_rank", "primaryFoR"),
                Arrays.asList("author_ID", "author_name", "article_ID", "title")
                //Arrays.asList("conference_ID", "category_name")
        );

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            // Fast batching mode enabled!
            connection.setAutoCommit(false);

            int len = csvFile.length;
            for(int i = 0; i < len; i++) {
                System.out.println("Inserting data to table: " + tableName[i]);

                insertDataWithExplicitColumns(connection, path + csvFile[i], tableName[i], allColumns.get(i));
            }

            connection.setAutoCommit(true);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data insertion complete!");
    }
}
