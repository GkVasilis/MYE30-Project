package mye30.project.db3_5030_5152.etl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JournalDataTransformer {

    public static void main(String[] args) {
        String inputCsvFile = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\data\\input_article.csv";
        String outputCSVFile1 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\transformed_data\\DataForJournal.tsv";
        String outputCSVFile2 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\transformed_data\\TempDataForJournalArticles.tsv";
        String line;
        List<String> lines = new ArrayList<>();

        System.out.println("Transforming data...");


        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Write file for table journals
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputCSVFile1))) {
            List<String> outputLines = new ArrayList<>();

            
            String firstLine = lines.get(0);
            String[] firstLinePieces = firstLine.split(";", -1);
            outputLines.add(firstLinePieces[0].trim() + "\t" + firstLinePieces[10].trim() + "\t" + firstLinePieces[17].trim());

            for (int i=1; i<lines.size(); i++) {
                String[] linePieces = lines.get(i).split(";", -1);

                if (linePieces[10] != null && !linePieces[10].isEmpty()) {
                    outputLines.add(linePieces[0].trim() + "\t" + linePieces[10].trim() + "\t" + linePieces[17].trim());
                }

            }

            for (int i=0; i<outputLines.size(); i++) {
                bw.write(outputLines.get(i));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Write temp file for table journal_articles
        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(outputCSVFile2))) {
            List<String> outputLines = new ArrayList<>();
            List<String> titleExceptions = new ArrayList<>(List.of("", "(paper withdrawn)", "(paper retracted)", "(duplicate entry was deleted)", "(was never published)"));

            String firstLine = lines.get(0);
            String[] firstLinePieces = firstLine.split(";", -1);
            outputLines.add(firstLinePieces[23].trim() + "\t" + firstLinePieces[0].trim() + "\t" + firstLinePieces[10].trim() + "\t" + firstLinePieces[17].trim()
                        + "\t" + firstLinePieces[3].trim() + "\t" + firstLinePieces[6].trim() + "\t" + firstLinePieces[12].trim() + "\t" + firstLinePieces[28].trim()
                        + "\t" + firstLinePieces[26].trim() + "\t" + firstLinePieces[16].trim() + "\t" + firstLinePieces[18].trim() + "\t" + firstLinePieces[11].trim() + "\t" + firstLinePieces[11].trim());


            for (int i=1; i<lines.size(); i++) {
                String thisLine = lines.get(i);
                String[] linePieces = thisLine.split(";", -1);

                if (linePieces[23] != null && !titleExceptions.contains(linePieces[23].trim())) {
                    outputLines.add(linePieces[23].trim() + "\t" + linePieces[0].trim() + "\t" + linePieces[10].trim() + "\t" + linePieces[17].trim()
                            + "\t" + linePieces[3].trim() + "\t" + linePieces[6].trim() + "\t" + linePieces[12].trim() + "\t" + linePieces[28].trim()
                            + "\t" + linePieces[26].trim() + "\t" + linePieces[16].trim() + "\t" + linePieces[18].trim() + "\t" + linePieces[11].trim() + "\t" + linePieces[1].trim());
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


