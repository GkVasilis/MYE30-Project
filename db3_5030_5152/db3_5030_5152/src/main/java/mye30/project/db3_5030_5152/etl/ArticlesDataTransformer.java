package mye30.project.db3_5030_5152.etl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticlesDataTransformer {

    public static void main(String[] args) {
        String inputCsvFile1 = "src/main/resources/transformed_data/TempDataForJournalArticles.tsv";
        String inputCsvFile2 = "src/main/resources/transformed_data/TempDataForConferenceArticles.tsv";
        String outputCSVFile1 = "src/main/resources/transformed_data/Journal_Articles_Data.tsv";
        String outputCSVFile2 = "src/main/resources/transformed_data/Conference_Articles_Data.tsv";
        String outputCSVFile3 = "src/main/resources/transformed_data/Articles_Data.tsv";
        String line;
        int article_ID = 0;
        String column1 = "article_ID,";
        String column2 = "article_ID,";

        System.out.println("Transforming data...");

        List<String> linesArticle = new ArrayList<>();
        List<String> linesJournal = new ArrayList<>();
        List<String> linesConference = new ArrayList<>();


        // Reads temp journal_articles
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile1))) {
            column1 += br.readLine();
            while ((line = br.readLine()) != null) {
                article_ID++;
                linesJournal.add(article_ID+"\t"+line);

                String[] linePieces = line.split(";");
                linesArticle.add(article_ID + "\t" + linePieces[0] + "\t" + linePieces[7]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Reads temp conference_articles
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile2))) {
            column2 += br.readLine();
            while ((line = br.readLine()) != null) {
                article_ID++;
                linesConference.add(article_ID+"\t"+line);

                String[] linePieces = line.split(";");
                linesArticle.add(article_ID + "\t" + linePieces[2] + "\t" + linePieces[9]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Articles
        try (BufferedWriter bw3 = new BufferedWriter(new FileWriter(outputCSVFile3))) {
            bw3.write("article_ID" + "\t" + "title" + "\t" + "published_year");
            bw3.newLine();
            for (String editedLine : linesArticle) {
                bw3.write(editedLine);
                bw3.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Journal_articles
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputCSVFile1))) {
            bw.write(column1);
            bw.newLine();
            for (String editedLine : linesJournal) {
                bw.write(editedLine);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Conference_articles
        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(outputCSVFile2))) {
            bw2.write(column2);
            bw2.newLine();
            for (String editedLine : linesConference) {
                bw2.write(editedLine);
                bw2.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Data transformation complete!");
    }
}
