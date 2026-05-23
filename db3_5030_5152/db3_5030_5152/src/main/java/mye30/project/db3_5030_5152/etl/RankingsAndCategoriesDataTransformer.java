package mye30.project.db3_5030_5152.etl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import org.apache.poi.ss.usermodel.*;

public class RankingsAndCategoriesDataTransformer {

    private static String[] parseCsvLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;
        char quoteChar = 0;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (!inQuotes && (c == '"' || c == '\'')) {
                inQuotes = true;
                quoteChar = c;
            } else if (inQuotes && c == quoteChar) {
                inQuotes = false;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString().trim());
        return tokens.toArray(new String[0]);
    }

    public static void main(String[] args) {

        String projectRoot = System.getProperty("user.dir");

        String inputCsvFile1 = projectRoot + File.separator + "db3_5030_5152" + File.separator  + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "journal_ranking_data_raw.csv";
        String inputCsvFile2 = projectRoot + File.separator + "db3_5030_5152" + File.separator  + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "bestSubjectArea.csv";
        String inputCSVFile3 = projectRoot + File.separator + "db3_5030_5152" + File.separator  + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "iCore26_KilledColumnsForLoading.csv";
        String inputTsvFile4 = projectRoot + File.separator + "db3_5030_5152" + File.separator  + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "DataForJournal.tsv";
        String inputTsvFile5 = projectRoot + File.separator + "db3_5030_5152" + File.separator  + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "DataForConference.tsv";
        String inputXlsxFile6 = projectRoot + File.separator + "db3_5030_5152" + File.separator  + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data" + File.separator + "icoreCategories.xlsx";

        String outputTSVFile1 = projectRoot + File.separator + "db3_5030_5152" + File.separator  + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Journal_rankings_Data.tsv";
        String outputTSVFile2 = projectRoot + File.separator + "db3_5030_5152" + File.separator  + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Conference_rankings_Data.tsv";
        String outputTSVFile4 = projectRoot + File.separator + "db3_5030_5152" + File.separator  + "db3_5030_5152" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "transformed_data" + File.separator + "Conference_Categories_Data.tsv";

        String line;
        String column1 = "journal_ID,";

        List<String> linesJournalRankings = new ArrayList<>();
        List<String> linesConferenceRankings = new ArrayList<>();
        List<String> linesJournalData = new ArrayList<>();
        List<String> linesConferenceData = new ArrayList<>();
        List<String> linesBestSubjectArea = new ArrayList<>();

        Map<String, String> journalMap = new HashMap<>();
        Map<String, String> journalIssnMap = new HashMap<>(); // NEW: Track by ISSN fallbacks
        Map<String, String> conferenceMap = new HashMap<>();

        System.out.println("Transforming data...");

        // Reads journal_data
        try (BufferedReader br = new BufferedReader(new FileReader(inputTsvFile4))) {
            while ((line = br.readLine()) != null) {
                linesJournalData.add(line);
                String[] array = line.split("\t");
                if (array.length > 1) {
                    String rawTitle = array[1].trim();
                    String cleanTitle = rawTitle.toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();
                    journalMap.put(cleanTitle, array[0].trim());

                    // FIXED: If your DataForJournal file contains an ISSN column, capture it here:
                    if (array.length > 2) {
                        String cleanIssn = array[2].replace("-", "").trim();
                        if (!cleanIssn.isEmpty()) {
                            journalIssnMap.put(cleanIssn, array[0].trim());
                        }
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }

        // Reads conference_data
        try (BufferedReader br = new BufferedReader(new FileReader(inputTsvFile5))) {
            while ((line = br.readLine()) != null) {
                linesConferenceData.add(line);
                String[] array = line.split("\t");
                if (array.length > 1) {
                    String rawTitle = array[1].trim();
                    String cleanTitle = rawTitle.toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();
                    conferenceMap.put(cleanTitle, array[0].trim());
                    conferenceMap.put(rawTitle.toLowerCase(), array[0].trim());
                }
            }
        } catch (IOException e) { e.printStackTrace(); }

        // Reads bestSubjectArea
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile2))) {
            while ((line = br.readLine()) != null) { linesBestSubjectArea.add(line); }
        } catch (IOException e) { e.printStackTrace(); }

        // Reads journal_ranking_data_raw
        try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile1))) {
            String header = br.readLine();
            if (header != null) {
                String[] firstLinePieces = parseCsvLine(header);
                linesJournalRankings.add("journal_ID\t" + firstLinePieces[0].trim() + "\t" + firstLinePieces[1].trim() + "\t" + firstLinePieces[9].trim() + "\t" + firstLinePieces[10].trim()
                        + "\t" + firstLinePieces[3].trim() + "\t" + firstLinePieces[8].trim() + "\t" + firstLinePieces[22].trim());
            }

            while ((line = br.readLine()) != null) {
                String[] linePieces = parseCsvLine(line);
                if (linePieces.length > 9 && linePieces[0] != null && linePieces[1] != null) {
                    String rankIdx = linePieces[0].replace("\"", "").trim();
                    String rawTitle = linePieces[1].replace("\"", "").trim();
                    String subjectArea = linePieces[9].replace("[", "").replace("]", "").replace("'", "").trim();

                    if (!rankIdx.isEmpty() && !rawTitle.isEmpty()) {
                        ///  Compare Titles   ///////////
                        String thisTitle = rawTitle.toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();
                        String rawIssn = (linePieces.length > 3) ? linePieces[3].replace("-", "").replace("\"", "").trim() : "";

                        String journalId = null;

                        // SMART MATCHING DECK FOR JOURNALS:
                        if (journalMap.containsKey(thisTitle)) {
                            journalId = journalMap.get(thisTitle);
                        } else if (!rawIssn.isEmpty() && journalIssnMap.containsKey(rawIssn)) {
                            // Match by unique ISSN identifier fallback
                            journalId = journalIssnMap.get(rawIssn);
                        } else {
                            // REVELATION FIX: Partial sub-phrase lookahead lookup check
                            for (Map.Entry<String, String> entry : journalMap.entrySet()) {
                                if (thisTitle.contains(entry.getKey()) || entry.getKey().contains(thisTitle)) {
                                    journalId = entry.getValue();
                                    break;
                                }
                            }
                        }

                        if (journalId != null) {
                            String hIndex = (linePieces.length > 5) ? linePieces[5].trim() : "0";
                            String issn = (linePieces.length > 3) ? linePieces[3].trim() : "-";
                            String sjr = (linePieces.length > 4) ? linePieces[4].trim() : "0";
                            String citations = (linePieces.length > 22) ? linePieces[22].trim() : "0";

                            linesJournalRankings.add(journalId + "\t" + rankIdx + "\t" + rawTitle + "\t" + subjectArea + "\t" + hIndex
                                    + "\t" + issn + "\t" + sjr + "\t" + citations);
                        }
                        ///    ////////////
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }

        // Reads iCore26_KilledColumnsForLoading
        try (BufferedReader br = new BufferedReader(new FileReader(inputCSVFile3))) {
            String header = br.readLine();
            if (header != null) {
                String[] firstLinePieces = parseCsvLine(header);
                linesConferenceRankings.add("conference_ID\t" + firstLinePieces[0].trim() + "\t" + firstLinePieces[1].trim() + "\t" + firstLinePieces[4].trim() + "\t" + firstLinePieces[6].trim()); // TODO primaryFoR
            }

            while ((line = br.readLine()) != null) {
                String[] linePieces = parseCsvLine(line);
                if (linePieces.length > 4 && linePieces[0] != null && linePieces[1] != null) {
                    String confIdx = linePieces[0].trim();
                    String rawTitle = linePieces[1].trim();
                    String acronym = (linePieces.length > 2) ? linePieces[2].trim() : "";
                    String rank = (linePieces.length > 4) ? linePieces[4].trim() : "Unranked";
                    String fieldOfResearch = (linePieces.length > 6) ? linePieces[6].trim() : "0000";

                    if (!confIdx.isEmpty() && !rawTitle.isEmpty()) {
                        ///  Compare Titles   ///////////
                        String thisTitle = rawTitle.toLowerCase().replaceAll("\\b(the|a|an)\\b", "").replaceAll("\\p{Punct}", "").trim();
                        String cleanAcronym = acronym.toLowerCase().trim();

                        String conferenceId = null;

                        if (conferenceMap.containsKey(thisTitle)) {
                            conferenceId = conferenceMap.get(thisTitle);
                        } else if (!cleanAcronym.isEmpty() && conferenceMap.containsKey(cleanAcronym)) {
                            conferenceId = conferenceMap.get(cleanAcronym);
                        }

                        if (conferenceId != null) {
                            linesConferenceRankings.add(conferenceId + "\t" + rank + "\t" + rawTitle + "\t" + acronym + "\t" + fieldOfResearch); // TODO primaryFoR
                        }
                        ///    ////////////
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }

        // Writes journal_ranking_Data
        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(outputTSVFile1))) {
            for (String editedLine : linesJournalRankings) { bw2.write(editedLine); bw2.newLine(); }
        } catch (IOException e) { e.printStackTrace(); }

        // Writes Conference_rankings_Data
        try (BufferedWriter bw2 = new BufferedWriter(new FileWriter(outputTSVFile2))) {
            for (String editedLine : linesConferenceRankings) { bw2.write(editedLine); bw2.newLine(); }
        } catch (IOException e) { e.printStackTrace(); }

        System.out.println("Data transformation complete!");
    }
}