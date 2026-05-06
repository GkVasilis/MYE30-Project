package mye30.project.db3_5030_5152.etl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BackupDBExtractor {

    public static void exportDatabase(String dbName, String dbUser, String dbPassword, String backupPath)
            throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("C:\\Tools\\MySQL\\bin\\mysqldump");//TODO
        command.add("--databases");
        command.add(dbName);
        command.add("--user=" + dbUser);
        command.add("--password=" + dbPassword);
        command.add("--result-file=" + backupPath);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException("Failed to export database. Exit code: " + exitCode);
        }
    }

    public static void main(String[] args) {
        String dbName = "MYE30_DB";
        String dbUser = "root";
        String dbPassword = "root";
        String backupPath = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\sql_scripts\\Backup_tables.sql";

        try {
            exportDatabase(dbName, dbUser, dbPassword, backupPath);
            System.out.println("Database backup successful.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
