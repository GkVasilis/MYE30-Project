package mye30.project.db3_5030_5152.etl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BackupDBExtractor {

    private static String findMysqldumpPath() {
        String[] commonPaths = {
                "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe",
                "C:\\Program Files\\MySQL\\MySQL Server 9.0\\bin\\mysqldump.exe",
                "C:\\Program Files\\MySQL\\MySQL Server 9.6\\bin\\mysqldump.exe",
                "C:\\Tools\\MySQL\\bin\\mysqldump.exe"
        };

        for (String path : commonPaths) {
            File file = new File(path);
            if (file.exists()) {
                return path;
            }
        }
        return "mysqldump";
    }

    public static void exportDatabase(String host, String port, String user, String password, String dbName, String outputFile) {
        File cnfFile = new File("mysqldump_temp.cnf");
        try {
            String mysqldumpExecutable = findMysqldumpPath();
            System.out.println("Using mysqldump binary located at: " + mysqldumpExecutable);

            // Create a temporary configuration file to securely feed the password to MySQL
            try (FileWriter writer = new FileWriter(cnfFile)) {
                writer.write("[mysqldump]\n");
                writer.write("host=" + host + "\n");
                writer.write("port=" + port + "\n");
                writer.write("user=" + user + "\n");
                writer.write("password=" + password + "\n"); // The comma is perfectly safe here!
            }

            // Reference the defaults file as the very first argument
            ProcessBuilder processBuilder = new ProcessBuilder(
                    mysqldumpExecutable,
                    "--defaults-extra-file=" + cnfFile.getAbsolutePath(),
                    "--single-transaction",
                    "--skip-column-statistics",
                    "--databases", dbName,
                    "-r", outputFile
            );

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read runtime log streams
            java.io.BufferedReader processLogReader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String logLine;
            while ((logLine = processLogReader.readLine()) != null) {
                System.out.println("-> " + logLine);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Database backup completed successfully! Saved to: " + outputFile);
            } else {
                System.err.println("Mysqldump process closed with an error code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Clean up and delete the temporary file from your storage drive
            if (cnfFile.exists()) {
                cnfFile.delete();
            }
        }
    }

    public static void main(String[] args) {
        String projectRoot = System.getProperty("user.dir");
        String backupFilePath = projectRoot + File.separator
                /*+ "db3_5030_5152" + File.separator*/
                + "db3_5030_5152" + File.separator
                + "src" + File.separator
                + "main" + File.separator
                + "resources" + File.separator
                + "sql_scripts" + File.separator
                + "Backup_tables.sql";

        System.out.println("Initiating database backup extraction stream...");
        exportDatabase("localhost", "3306", "root", "root", "mye30_db", backupFilePath);
    }
}