package mye30.project.db3_5030_5152.etl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import org.apache.poi.ss.usermodel.*;

public class RankingsAndCategoriesDataTransformer {

    public static void main(String[] args) {

        String inputCsvFile1 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\data\\journal_ranking_data_raw.csv";
        String inputCsvFile2 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\data\\bestSubjectArea.csv";
        String inputCSVFile3 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\data\\iCore26_KilledColumnsForLoading.csv";
        String inputTsvFile4 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\transformed_data\\DataForJournal.tsv";
        String inputTsvFile5 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\transformed_data\\DataForConference.tsv";
        String inputXlsxFile6 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\data\\icoreCategories.xlsx";

        String outputTSVFile1 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\transformed_data\\Journal_rankings_Data.tsv";
        String outputTSVFile2 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\transformed_data\\Conference_rankings_Data.tsv";
        //String outputTSVFile3 = "C:\Users\User\Desktop\MYE30-Project\db3_5030_5152\db3_5030_5152\src\main\resources\transformed_data\PrimaryFoRs_Data.tsv";
        String outputTSVFile4 = "C:\\Users\\User\\Desktop\\MYE30-Project\\db3_5030_5152\\db3_5030_5152\\src\\main\\resources\\transformed_data\\Conference_Categories_Data.tsv";

        String line;
        String column1 = "journal_ID,";

        List<String> linesJournalRankings = new ArrayList<>();
        List<String> linesConferenceRankings = new ArrayList<>();
        List<String> linesJournalData = new ArrayList<>();
        List<String> linesConferenceData = new ArrayList<>();
        List<String> linesBestSubjectArea = new ArrayList<>();


        // Reads journal_data
        try (BufferedReader br = new BufferedReader(new FileReader(inputTsvFile4))) {
            while ((line = br.readLine()) != null) {
                linesJournalData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Reads conference_data
        try (BufferedReader br = new BufferedReader(new FileReader(inputTsvFile5))) {
            while ((line = br.readLine()) != null) {
                linesConferenceData.add(line);
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








        // Reads iCore26_KilledColumnsForLoading
        try (BufferedReader br = new BufferedReader(new FileReader(inputCSVFile3))) {
            String[] firstLinePieces = br.readLine().split(",", -1);
            linesConferenceRankings.add("conference_ID" + "\t" + firstLinePieces[0].trim() + "\t" + firstLinePieces[1].trim() + "\t" + firstLinePieces[4].trim() + "\t" + firstLinePieces[6].trim()); // TODO primaryFoR

            while ((line = br.readLine()) != null) {
                String[] linePieces = line.split(",", -1);
                if (linePieces[0] != null && linePieces[1] != null && linePieces[9] != null) {


                    ///  Compare Titles   ///////////
                    String thisTitle = linePieces[1].toLowerCase().replaceAll("\b(the|a|an)\b", "").replaceAll("\\p{Punct}", "").trim();
                    for (int i = 0; i < linesConferenceData.size(); i++) {
                        String[] array = linesConferenceData.get(i).split("\t");
                        String otherTitle = array[1].toLowerCase().replaceAll("\b(the|a|an)\b", "").replaceAll("\\p{Punct}", "").trim();
                        if (thisTitle.equals(otherTitle)) {
                            linesConferenceRankings.add(array[0] + "\t" + linePieces[0].trim() + "\t" + linePieces[1].trim() + "\t" + linePieces[4].trim() + "\t" + linePieces[6].trim()); // TODO primaryFoR
                        }
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


    }

}
