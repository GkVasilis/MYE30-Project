package mye30.project.db3_5030_5152.etl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.poi.ss.usermodel.*;

public class RankingsAndCategoriesDataTransformer {

    public static void main(String[] args) {

        String projectRoot = System.getProperty("user.dir");

        String inputCsvFile1 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "journal_ranking_data_raw.csv";
        String inputCsvFile2 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "bestSubjectArea.csv";
        String inputCSVFile3 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "iCore26_KilledColumnsForLoading.csv";
        String inputTsvFile4 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "DataForJournal.tsv";
        String inputTsvFile5 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "DataForConference.tsv";
        String inputXlsxFile6 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "icoreCategories.xlsx";

        String outputTSVFile1 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Journal_rankings_Data.tsv";
        String outputTSVFile2 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Conference_rankings_Data.tsv";
        //String outputTSVFile3 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "PrimaryFoRs_Data.tsv";
        String outputTSVFile4 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Conference_Categories_Data.tsv";

        String line;
        String column1 = "journal_ID,";

        List<String> linesJournalRankings = new ArrayList<>();
        List<String> linesConferenceRankings = new ArrayList<>();
        List<String> linesJournalData = new ArrayList<>();
        List<String> linesConferenceData = new ArrayList<>();
        List<String> linesBestSubjectArea = new ArrayList<>();

        Map<String, String> journalMap = new HashMap<>();
        Map<String, String> conferenceMap = new HashMap<>();

        System.out.println("Transforming data...");

        // Reads journal_data
        try (BufferedReader br = new BufferedReader(new FileReader(inputTsvFile4))) {
            while ((line = br.readLine()) != null) {
                linesJournalData.add(line);

                String[] array = line.split("\t");
                if (array.length > 1) {
                    String cleanTitle = array[1].toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();
                    journalMap.put(cleanTitle, array[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reads conference_data
        try (BufferedReader br = new BufferedReader(new FileReader(inputTsvFile5))) {
            while ((line = br.readLine()) != null) {
                linesConferenceData.add(line);

                String[] array = line.split("\t");
                if (array.length > 1) {
                    String cleanTitle = array[1].toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();
                    conferenceMap.put(cleanTitle, array[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reads bestSubjectArea
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile2))) {
            while ((line = br.readLine()) != null) {
                linesBestSubjectArea.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Reads journal_ranking_data_raw
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile1))) {
            String[] firstLinePieces = br.readLine().split(",", -1);
            linesJournalRankings.add("journal_ID" + "\t" + firstLinePieces[0].trim() + "\t" + firstLinePieces[1].trim() + "\t" + firstLinePieces[9].trim() + "\t" + firstLinePieces[10].trim()
                    + "\t" + firstLinePieces[3].trim() + "\t" + firstLinePieces[8].trim() + "\t" + firstLinePieces[22].trim());

            while ((line = br.readLine()) != null) {
                String[] linePieces = line.split(",", -1);

                if (linePieces[0] != null && linePieces[1] != null && linePieces[9] != null && linesBestSubjectArea.contains(linePieces[9])) {

                    ///  Compare Titles   ///////////
                    String thisTitle = linePieces[1].toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();

                    if (journalMap.containsKey(thisTitle)) {
                        String journalId = journalMap.get(thisTitle);
                        linesJournalRankings.add(journalId + "\t" + linePieces[0].trim() + "\t" + linePieces[1].trim() + "\t" + linePieces[9].trim() + "\t" + linePieces[10].trim()
                                + "\t" + linePieces[3].trim() + "\t" + linePieces[8].trim() + "\t" + linePieces[22].trim());
                    }
                    ///    ////////////

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Reads iCore26_KilledColumnsForLoading
        try (BufferedReader br = new BufferedReader(new FileReader(inputCSVFile3))) {
            String[] firstLinePieces = br.readLine().split(",", -1);
            linesConferenceRankings.add("conference_ID" + "\t" + firstLinePieces[0].trim() + "\t" + firstLinePieces[1].trim() + "\t" + firstLinePieces[4].trim() + "\t" + firstLinePieces[6].trim()); // TODO primaryFoR

            while ((line = br.readLine()) != null) {
                String[] linePieces = line.split(",", -1);

                if (linePieces.length > 6 && linePieces[0] != null && linePieces[1] != null && linePieces[6] != null) {

                    ///  Compare Titles   ///////////
                    String thisTitle = linePieces[1].toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();

                    if (conferenceMap.containsKey(thisTitle)) {
                        String conferenceId = conferenceMap.get(thisTitle);
                        linesConferenceRankings.add(conferenceId + "\t" + linePieces[0].trim() + "\t" + linePieces[1].trim() + "\t" + linePieces[4].trim() + "\t" + linePieces[6].trim()); // TODO primaryFoR
                    }
                    ///    ////////////

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Reads icoreCategories TODO
        /*
        try (BufferedReader br6 = new BufferedReader(new FileReader(inputXlsxFile6))) {
            linesConferenceRankings.add("ID" + "\t" + "title"); //"conference_ID" + "\t" + //id = Primaryfor

            while ((line = br6.readLine()) != null) {
                String[] linePieces = line.split("");
                if (linePieces[0] != null && linePieces[1] != null) {


                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        */


        // Writes journal_ranking_Data
        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(outputTSVFile1))) {
            for (String editedLine : linesJournalRankings) {
                bw2.write(editedLine);
                bw2.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Writes Conference_rankings_Data
        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(outputTSVFile2))) {
            for (String editedLine : linesConferenceRankings) {
                bw2.write(editedLine);
                bw2.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Data transformation complete!");
    }
}