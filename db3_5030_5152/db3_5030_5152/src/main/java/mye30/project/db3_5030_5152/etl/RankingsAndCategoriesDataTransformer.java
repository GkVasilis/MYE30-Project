package mye30.project.db3_5030_5152.etl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RankingsAndCategoriesDataTransformer {

    public static void main(String[] args) {

        String projectRoot = System.getProperty("user.dir");

        String inputCsvFile1 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "journal_ranking_data_raw.csv";
        String inputCsvFile2 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "bestSubjectArea.csv";
        String inputCSVFile3 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "iCore26_KilledColumnsForLoading.csv";
        String inputTsvFile4 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "DataForJournal.tsv";
        String inputTsvFile5 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "DataForConference.tsv";

        String outputTSVFile1 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Journal_rankings_Data.tsv";
        String outputTSVFile2 = projectRoot + File.separator + "db3_5030_5152" + File.separator + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Conference_rankings_Data.tsv";

        String line;

        List<String> linesJournalRankings = new ArrayList<>();
        List<String> linesConferenceRankings = new ArrayList<>();

        Map<String, String> journalMap = new HashMap<>();
        Map<String, String> conferenceMap = new HashMap<>();

        // FIXED: Switched to HashSet for sanitized, fast O(1) containment matching
        Set<String> safeBestSubjectAreas = new HashSet<>();

        System.out.println("Transforming data...");

        // 1. Reads journal_data (.tsv format)
        try (BufferedReader br = new BufferedReader(new FileReader(inputTsvFile4))) {
            while ((line = br.readLine()) != null) {
                String[] array = line.split("\t");
                if (array.length > 1) {
                    String cleanTitle = array[1].toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();
                    journalMap.put(cleanTitle, array[0].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Reads conference_data (.tsv format)
        try (BufferedReader br = new BufferedReader(new FileReader(inputTsvFile5))) {
            while ((line = br.readLine()) != null) {
                String[] array = line.split("\t");
                if (array.length > 1) {
                    String cleanTitle = array[1].toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();
                    conferenceMap.put(cleanTitle, array[0].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. Reads bestSubjectArea and trims hidden linebreaks completely
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile2))) {
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    // Extract just the value if the file is a single-column CSV list
                    String cleanArea = line.split(",")[0].replace("\"", "").trim();
                    safeBestSubjectAreas.add(cleanArea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. Reads journal_ranking_data_raw
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile1))) {
            String header = br.readLine();
            if (header != null) {
                // FIXED: Regex splits on commas only if they are outside literal quotation marks
                String[] firstLinePieces = header.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                linesJournalRankings.add("journal_ID\t" + firstLinePieces[0].trim() + "\t" + firstLinePieces[1].trim() + "\t" + firstLinePieces[9].trim() + "\t" + firstLinePieces[10].trim()
                        + "\t" + firstLinePieces[3].trim() + "\t" + firstLinePieces[8].trim() + "\t" + firstLinePieces[22].trim());
            }

            while ((line = br.readLine()) != null) {
                String[] linePieces = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (linePieces.length > 22) {
                    String rankIdx = linePieces[0].replace("\"", "").trim();
                    String rawTitle = linePieces[1].replace("\"", "").trim();
                    String subjectArea = linePieces[9].replace("\"", "").trim();

                    // FIXED: Cleans validation target and checks sanitized Set collection
                    if (!rankIdx.isEmpty() && !rawTitle.isEmpty() && !subjectArea.isEmpty() && safeBestSubjectAreas.contains(subjectArea)) {
                        String thisTitle = rawTitle.toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();

                        if (journalMap.containsKey(thisTitle)) {
                            String journalId = journalMap.get(thisTitle);
                            linesJournalRankings.add(journalId + "\t" + rankIdx + "\t" + rawTitle + "\t" + subjectArea + "\t" + linePieces[10].replace("\"", "").trim()
                                    + "\t" + linePieces[3].replace("\"", "").trim() + "\t" + linePieces[8].replace("\"", "").trim() + "\t" + linePieces[22].replace("\"", "").trim());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5. Reads iCore26_KilledColumnsForLoading
        try (BufferedReader br = new BufferedReader(new FileReader(inputCSVFile3))) {
            String header = br.readLine();
            if (header != null) {
                String[] firstLinePieces = header.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                linesConferenceRankings.add("conference_ID\t" + firstLinePieces[0].trim() + "\t" + firstLinePieces[1].trim() + "\t" + firstLinePieces[4].trim() + "\t" + firstLinePieces[6].trim());
            }

            while ((line = br.readLine()) != null) {
                String[] linePieces = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (linePieces.length > 6) {
                    String confIdx = linePieces[0].replace("\"", "").trim();
                    String rawTitle = linePieces[1].replace("\"", "").trim();
                    String fieldOfResearch = linePieces[6].replace("\"", "").trim();

                    if (!confIdx.isEmpty() && !rawTitle.isEmpty() && !fieldOfResearch.isEmpty()) {
                        String thisTitle = rawTitle.toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();

                        if (conferenceMap.containsKey(thisTitle)) {
                            String conferenceId = conferenceMap.get(thisTitle);
                            linesConferenceRankings.add(conferenceId + "\t" + confIdx + "\t" + rawTitle + "\t" + linePieces[4].replace("\"", "").trim() + "\t" + fieldOfResearch);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw1 = new BufferedWriter(new FileWriter(outputTSVFile1))) {
            for (String editedLine : linesJournalRankings) {
                bw1.write(editedLine);
                bw1.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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