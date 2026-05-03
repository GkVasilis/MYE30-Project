package mye30.project.db3_5030_5152.etl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConferenceDataTransformer {

    public static void main(String[] args) {
        String inputCsvFile = "src/main/resources/data/input_inproceedings.csv";
        String outputCSVFile1 = "src/main/resources/transformed_data/DataForConference.csv";
        String outputCSVFile2 = "src/main/resources/transformed_data/TempDataForConferenceArticles.csv";
        String line;

        System.out.println("Transforming data...");

        List<String> lines = new ArrayList<>();
        // Read and modify original lines
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Write Data to a new file which is ready to be imported in the db
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputCSVFile1))) {
            List<String> outputLines = new ArrayList<>();
            //List<String> titleExceptions = new ArrayList<>(List.of("", "(paper withdrawn)", "(paper retracted)", "(duplicate entry was deleted)", "(was never published)"));

            String firstLine = lines.get(0);
            String[] firstLinePieces = firstLine.split(";");
            outputLines.add(firstLinePieces[0].trim() + "\t" + firstLinePieces[2].trim());

            for (int i=1; i<lines.size(); i++) {
                String thisLine = lines.get(i);
                String[] linePieces = thisLine.split(";");

                if (linePieces[2] != null && !linePieces[2].isEmpty()) {
                    outputLines.add(linePieces[0].trim() + "\t" + linePieces[2].trim());
                }

            }

            for (int i=0; i<outputLines.size(); i++) {
                bw.write(outputLines.get(i));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(outputCSVFile2))) {
            List<String> outputLines = new ArrayList<>();
            //List<String> authorData = new ArrayList<>();
            List<String> titleExceptions = new ArrayList<>(List.of("", "(paper withdrawn)", "(paper retracted)", "(duplicate entry was deleted)", "(was never published)"));

            String firstLine = lines.get(0);
            String[] firstLinePieces = firstLine.split(";");
            outputLines.add(firstLinePieces[0].trim() + "\t" + firstLinePieces[2].trim() + "\t" + firstLinePieces[19].trim() + "\t" + firstLinePieces[3].trim()
                    + "\t" + firstLinePieces[6].trim() + "\t" + firstLinePieces[16].trim() + "\t" + firstLinePieces[22].trim() + "\t" + firstLinePieces[15].trim()
                    + "\t" + firstLinePieces[11].trim() + "\t" + firstLinePieces[23].trim() + "\t" + firstLinePieces[10].trim());

            for (int i=1; i<lines.size(); i++) {
                String thisLine = lines.get(i);
                String[] linePieces = thisLine.split(";");

                if (linePieces[19] != null && !titleExceptions.contains(linePieces[19].trim())) {
                    outputLines.add(linePieces[0].trim() + "\t" + linePieces[2].trim() + "\t" + linePieces[19].trim() + "\t" + linePieces[3].trim()
                            + "\t" + linePieces[6].trim() + "\t" + linePieces[16].trim() + "\t" + linePieces[22].trim() + "\t" + linePieces[15].trim()
                            + "\t" + linePieces[11].trim() + "\t" + linePieces[23].trim() + "\t" + linePieces[10].trim());
                    //authorData.add(linePieces[1].trim());
                }

            }

            for (int i=0; i<outputLines.size(); i++) {
                bw2.write(outputLines.get(i));
                bw2.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Data transformation complete!");
    }

}
