package mye30.project.db3_5030_5152.etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RankingsAndCategoriesDataTransformer {

    public static void main(String[] args) {

        String inputCsvFile1 = "src/main/resources/data/journal_ranking_data_raw.csv";
        String inputCsvFile2 = "src/main/resources/data/bestSubjectArea.csv";
        String inputCSVFile3 = "src/main/resources/data/iCore26_KilledColumnsForLoading.csv";
        String inputTsvFile4 = "src/main/resources/transformed_data/DataForJournal.tsv";

        String outputTSVFile1 = "src/main/resources/transformed_data/Journal_rankings_Data.tsv";
        String outputTSVFile2 = "src/main/resources/transformed_data/Conference_rankings_Data.tsv";
        String outputTSVFile3 = "src/main/resources/transformed_data/PrimaryFoRs_Data.tsv";
        String outputTSVFile4 = "src/main/resources/transformed_data/Conference_Categories_Data.tsv";

        String line;
        String column1 = "journal_ID,";

        List<String> linesJournalRankings = new ArrayList<>();
        List<String> linesConferenceRankings = new ArrayList<>();
        List<String> linesJournalData = new ArrayList<>();


        // Reads journal_data
        try (BufferedReader br = new BufferedReader(new FileReader(inputTsvFile4))) {
            while ((line = br.readLine()) != null) {
                linesJournalData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Reads journal_ranking_data_raw
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile1))) {
            String[] firstLinePieces = br.readLine().split(",");
            linesJournalRankings.add("journal_ID" + "\t" + firstLinePieces[0].trim() + "\t" + firstLinePieces[1].trim() + "\t" + firstLinePieces[9].trim() + "\t" + firstLinePieces[10].trim()
                                                + "\t" + firstLinePieces[3].trim() + "\t" + firstLinePieces[8].trim() + "\t" + firstLinePieces[22].trim());

            while ((line = br.readLine()) != null) {
                String[] linePieces = line.split(",");
                if (linePieces[0] != null && linePieces[1] != null && linePieces[9] != null) {

                    ///  Compare Titles   ///////////
                    String thisTitle = linePieces[1].toLowerCase().replaceAll("\b(the|a|an)\b", "").replaceAll("\\p{Punct}", "").trim();
                    for (int i = 0; i < linesJournalData.size(); i++) {
                        String[] array = linesJournalData.get(i).split("\t");
                        String otherTitle = array[1].toLowerCase().replaceAll("\b(the|a|an)\b", "").replaceAll("\\p{Punct}", "").trim();
                        if (thisTitle.equals(otherTitle)) {
                            linesJournalRankings.add(array[0] + "\t" + linePieces[0].trim() + "\t" + linePieces[1].trim() + "\t" + linePieces[9].trim() + "\t" + linePieces[10].trim()
                                    + "\t" + linePieces[3].trim() + "\t" + linePieces[8].trim() + "\t" + linePieces[22].trim());
                        }
                    }
                    ///    ////////////

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
