/* Also is a AuthorDataTransformer */

package mye30.project.db3_5030_5152.etl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticlesDataTransformer {

    public static void main(String[] args) {

        String projectRoot = System.getProperty("user.dir");

        String inputCsvFile1 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "TempDataForJournalArticles.tsv";
        String inputCsvFile2 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "TempDataForConferenceArticles.tsv";
        String outputCSVFile1 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Journal_Articles_Data.tsv";
        String outputCSVFile2 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Conference_Articles_Data.tsv";
        String outputCSVFile3 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Articles_Data.tsv";
        String outputTSVFile4 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Authors_Data.tsv";

        String line;
        int article_ID = 1;
        int author_ID = 1;
        String column1 = "article_ID,";
        String column2 = "article_ID,";

        System.out.println("Transforming data...");

        List<String> linesArticle = new ArrayList<>();
        List<String> linesJournal = new ArrayList<>();
        List<String> linesConference = new ArrayList<>();
        List<String> linesAuthors = new ArrayList<>();
        Map<String, Integer> authorsMap = new HashMap<>();


        // Reads temp journal_articles
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile1))) {
            column1 += br.readLine();
            while ((line = br.readLine()) != null) {
                article_ID++;
                linesJournal.add(article_ID+"\t"+line);

                String[] linePieces = line.split("\t", -1);
                linesArticle.add(article_ID + "\t" + linePieces[0] + "\t" + linePieces[7]);
                linesAuthors.add(linePieces[12] + "\t" + article_ID + "\t" + linePieces[0]);
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

                String[] linePieces = line.split("\t", -1);
                linesArticle.add(article_ID + "\t" + linePieces[2] + "\t" + linePieces[9]);
                linesAuthors.add(linePieces[11] + "\t" + article_ID + "\t" + linePieces[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Articles Writer
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


        // Journal_articles writer
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


        // Conference_articles writer
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


        // Author_data Writer
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputTSVFile4))) {
            bw.write("author_ID" + "\t" + "author_name" + "\t" + "article_ID" + "\t" + "title");
            bw.newLine();
            for (int i=1; i<linesAuthors.size(); i++) {
                String[] linePieces = linesAuthors.get(i).split("\t");
                String[] authorNames = linePieces[0].split("\\|");

                for (int j=0; j<authorNames.length; j++) {
                    if (authorsMap.containsKey(authorNames[j].trim())) {
                        bw.write(authorsMap.get(authorNames[j].trim()) + "\t" + authorNames[j].trim() + "\t" + linePieces[1].trim() + "\t" + linePieces[2].trim());
                        bw.newLine();
                    } else {
                        bw.write(author_ID + "\t" + authorNames[j].trim() + "\t" + linePieces[1].trim() + "\t" + linePieces[2].trim());
                        bw.newLine();
                        authorsMap.put(authorNames[j].trim(), author_ID);
                        author_ID++;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Data transformation complete!");
    }
}
