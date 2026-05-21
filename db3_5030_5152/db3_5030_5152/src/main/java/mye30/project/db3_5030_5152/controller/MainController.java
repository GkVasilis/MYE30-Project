package mye30.project.db3_5030_5152.controller;

import mye30.project.db3_5030_5152.dataModel.*;
import mye30.project.db3_5030_5152.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private JournalService journalService;

    @Autowired
    private YearService yearService;

    @GetMapping("/homeMenu")
    public String getHomeMenu(Model model) {
        return "HomeMenu";
    }

    @GetMapping("/listJournals")
    public String getListJournals(Model model) {
        List<String> journalList = journalService.findAllJournals();
        model.addAttribute("Journals", journalList);
        return "ListOfJournals";
    }

    @GetMapping("/listConferences")
    public String getListConferences(Model model) {
        List<String> confList = conferenceService.findAllConferences();
        model.addAttribute("Conferences", confList);
        return "List of Conferences";
    }

    @GetMapping("/listAuthors")
    public String getListAuthors(Model model) {
        List<String> authorsList = authorService.findAllAuthors();
        model.addAttribute("Authors", authorsList);
        return "List of Authors";
    }

    @GetMapping("/listYears")
    public String getListYears(Model model) {
        List<Integer> yearList = yearService.findAllYears();
        model.addAttribute("Years", yearList);
        return "List of Years";
    }

    // Journal

    @GetMapping("/journalProfile")
    public String getJournalProfile(@RequestParam("journalName") String journalName, @RequestParam(value = "minYear", required = false) Integer minYear, @RequestParam(value = "maxYear", required = false) Integer maxYear,Model model) {
        printJournalRanking(journalName, model);
        model.addAttribute("journalName", journalName);
        if (minYear != null && maxYear != null) {
            printJournalStatsRange(journalName, minYear, maxYear, model);
            model.addAttribute("isFiltered", true);
            model.addAttribute("minYear", minYear);
            model.addAttribute("maxYear", maxYear);
        } else {
            printJournalStats(journalName, model);
            model.addAttribute("isFiltered", false);
        }
        return "Journal Profile";
    }

    private void printJournalRanking(String name, Model model){
        JournalRanking ranking = journalService.findJournalRanking(name);
        model.addAttribute("Ranking", ranking);
    }

    private void printJournalStats(String name, Model model){
        List<Object[]> firstYear = journalService.findJournalByFirstYear(name);
        List<Object[]> lastYear = journalService.findJournalByLastYear(name);
        int numOfAuthors = journalService.findNumOfJournalAuthors(name);
        int avgAuthorsByJournal = journalService.findAvgAuthorsByJournal(name);
        int avgAuthorsByYear = journalService.findAvgAuthorsByYear(name);
        List<Article> articles = journalService.findJournalArticles(name);
        List<Author> authors = journalService.findJournalAuthors(name);
        int avgJA = journalService.findAvgJournalArticles(name);
        model.addAttribute("YearFirstJournalPublished", firstYear);
        model.addAttribute("YearLastJournalPublished", lastYear);
        model.addAttribute("NumberJournalAuthors", numOfAuthors);
        model.addAttribute("AvgAuthorsOfJournal ", avgAuthorsByJournal);
        model.addAttribute("AvgAuthorsByYear", avgAuthorsByYear);
        model.addAttribute("Articles", articles);
        model.addAttribute("Authors", authors);
        model.addAttribute("AvgArticlesByYear", avgJA);
    }

    private void printJournalStatsRange(String name, int min, int max, Model model){
        int numOfAuthors = journalService.findNumOfJournalAuthorsRange(name, min, max);
        int avgAuthorsByJournal = journalService.findAvgAuthorsByJournalRange(name, min, max);
        int avgAuthorsByYear = journalService.findAvgAuthorsByYearRange(name, min, max);
        List<Article> articles = journalService.findJournalArticlesRange(name, min, max);
        List<Author> authors = journalService.findJournalAuthorsRange(name, min, max);
        int avgJA = journalService.findAvgJournalArticlesRange(name, min, max);

        model.addAttribute("NumberJournalAuthors", numOfAuthors);
        model.addAttribute("AvgAuthorsOfJournal ", avgAuthorsByJournal);
        model.addAttribute("AvgAuthorsByYear", avgAuthorsByYear);
        model.addAttribute("Articles", articles);
        model.addAttribute("Authors", authors);
        model.addAttribute("AvgArticlesByYear", avgJA);
    }

    // Conference

    @GetMapping("/conferenceProfile")
    public String getConferenceProfile(@RequestParam("conferenceName") String conferenceName, @RequestParam(value = "minYear", required = false) Integer minYear, @RequestParam(value = "maxYear", required = false) Integer maxYear,Model model) {
        printConferenceRanking(conferenceName, model);
        model.addAttribute("conferenceName", conferenceName);
        if (minYear != null && maxYear != null) {
            printConferenceStatsRange(conferenceName, minYear, maxYear, model);
            model.addAttribute("isFiltered", true);
            model.addAttribute("minYear", minYear);
            model.addAttribute("maxYear", maxYear);
        } else {
            printConferenceStats(conferenceName, model);
            model.addAttribute("isFiltered", false);
        }
        return "Conference Profile";
    }

    private void printConferenceRanking(String name, Model model){
        ConferenceRanking ranking = conferenceService.findConferenceRanking(name);
        model.addAttribute("Ranking", ranking);
    }

    private void printConferenceStats(String name, Model model){
        List<Object[]> firstYear = conferenceService.findConferenceByFirstYear(name);
        List<Object[]> lastYear = conferenceService.findConferenceByLastYear(name);
        int numOfAuthors = conferenceService.findNumOfConferenceAuthors(name);
        int avgAuthorsByConference = conferenceService.findAvgAuthorsByConference(name);
        int avgAuthorsByYear = conferenceService.findAvgAuthorsByYear(name);
        List<Article> articles = conferenceService.findConferenceArticles(name);
        List<Author> authors = conferenceService.findConferenceAuthors(name);
        int avgJA = conferenceService.findAvgConferenceArticles(name);
        model.addAttribute("YearFirstConferencePublished", firstYear);
        model.addAttribute("YearLastConferencePublished", lastYear);
        model.addAttribute("NumberConferenceAuthors", numOfAuthors);
        model.addAttribute("AvgAuthorsofConference ", avgAuthorsByConference);
        model.addAttribute("AvgAuthorsbyyear", avgAuthorsByYear);
        model.addAttribute("Articles", articles);
        model.addAttribute("Authors", authors);
        model.addAttribute("Avgarticlesbyyear", avgJA);
    }

    private void printConferenceStatsRange(String name, int min, int max, Model model){
        int numOfAuthors = conferenceService.findNumOfConferenceAuthorsRange(name, min, max);
        int avgAuthorsByConference = conferenceService.findAvgAuthorsByConferenceRange(name, min, max);
        int avgAuthorsByYear = conferenceService.findAvgAuthorsByYearRange(name, min, max);
        List<Article> articles = conferenceService.findConferenceArticlesRange(name, min, max);
        List<Author> authors = conferenceService.findConferenceAuthorsRange(name, min, max);
        int avgJA = conferenceService.findAvgConferenceArticlesRange(name, min, max);

        model.addAttribute("NumberConferenceAuthors", numOfAuthors);
        model.addAttribute("AvgAuthorsofConference ", avgAuthorsByConference);
        model.addAttribute("AvgAuthorsbyyear", avgAuthorsByYear);
        model.addAttribute("Articles", articles);
        model.addAttribute("Authors", authors);
        model.addAttribute("Avgarticlesbyyear", avgJA);
    }

    // Author

    @GetMapping("/authorProfile")
    public String getAuthorProfile(@RequestParam("authorName") String authorName,Model model) {
        int firstYear = authorService.findFirstPublishedYear(authorName);
        int lastYear = authorService.findLastPublishedYear(authorName);
        List<Article> authorArticles = authorService.findAllArticles(authorName);
        int authAvgArticlesByYear = authorService.findAvgArticlesByYear(authorName);
        model.addAttribute("firstYear", firstYear);
        model.addAttribute("lastYear", lastYear);
        model.addAttribute("authorArticles", authorArticles);
        model.addAttribute("authAvgArticlesByYear", authAvgArticlesByYear);

        return "AuthorProfile";
    }

    // Years

    @GetMapping("/yearProfile")
    public String getYearProfile(@RequestParam("yearValue") Integer yearValue, @RequestParam(value = "journalName", required = false) String journalName, @RequestParam(value = "conferenceName", required = false) String conferenceName, @RequestParam(value = "authorName", required = false) String authorName, Model model) {
        int publishedArticles = yearService.findPublishedArticles(yearValue);
        int numOfJournals = yearService.findNumOfJournals(yearValue);
        int numOfConferences = yearService.findNumOfConferences(yearValue);
        List<Object[]> numOfAuthors = yearService.findNumOfAuthors(yearValue);

        model.addAttribute("publishedArticles", publishedArticles);
        model.addAttribute("numOfJournals", numOfJournals);
        model.addAttribute("numOfConferences", numOfConferences);
        model.addAttribute("numOfAuthors", numOfAuthors);

        if (journalName != null) {
            List<Object[]> publicationsByJournal = yearService.findPublicationByJournal(yearValue, journalName);
            model.addAttribute("ContextType", "Journal");
            //model.addAttribute("ContextName", journalName);
            model.addAttribute("publicationsByJournal", publicationsByJournal);
        } else if (conferenceName != null) {
            List<Object[]> publicationsByConferences = yearService.findPublicationByConference(yearValue, conferenceName);
            model.addAttribute("ContextType", "Conference");
            //model.addAttribute("ContextName", conferenceName);
            model.addAttribute("publicationsByConferences", publicationsByConferences);
        } else if (authorName != null) {
            List<Object[]> publicationsByAuthors = yearService.findPublicationByAuthor(yearValue, authorName);
            model.addAttribute("ContextType", "Author");
            //model.addAttribute("ContextName", authorName);
            model.addAttribute("publicationsByAuthors", publicationsByAuthors);
        }

        return "Year Profile";
    }

    // LINE CHARTS

    @GetMapping("/lineCharts")
    public String getLineCharts(
            @RequestParam(value = "journalNames", required = false) List<String> journalNames,
            @RequestParam(value = "conferenceNames", required = false) List<String> conferenceNames,
            @RequestParam(value = "authorNames", required = false) List<String> authorNames,
            @RequestParam(value = "journalCategories", required = false) List<String> journalCategories,
            @RequestParam(value = "conferenceCategories", required = false) List<String> conferenceCategories,
            @RequestParam(value = "minYear", required = false) Integer minYear,
            @RequestParam(value = "maxYear", required = false) Integer maxYear,
            Model model) {

        List<Map<String, Object>> combinedChartData = new ArrayList<>();

        // Set default fallbacks if range boundaries aren't passed
        int startYear = (minYear != null) ? minYear : 0;
        int endYear = (maxYear != null) ? maxYear : Integer.MAX_VALUE;

        // 1. Extract Article Counts per Year (Uses if-else if)
        extractArticleCounts(journalNames, conferenceNames, authorNames, startYear, endYear, combinedChartData);

        // 2. Extract Author Counts per Year (Uses if-else if)
        extractAuthorCounts(journalNames, conferenceNames, startYear, endYear, combinedChartData);

        extractArticleCountsByCategory(journalCategories, conferenceCategories, combinedChartData);

        // Chronological sort so D3 connects points seamlessly from left to right
        combinedChartData.sort(Comparator.comparing(m -> (String) m.get("years").toString()));//todo intValue()

        // Keep inputs on the UI model to pre-populate inputs/text filters
        model.addAttribute("selectedJournals", journalNames);
        model.addAttribute("selectedConferences", conferenceNames);
        model.addAttribute("selectedAuthors", authorNames);
        model.addAttribute("selectedJournalsCategories", journalCategories);
        model.addAttribute("selectedConferencesCategories", conferenceCategories);
        model.addAttribute("minYear", minYear);
        model.addAttribute("maxYear", maxYear);

        // Serialize the combined, filtered dataset down to the Thymeleaf model once
        try {
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("chartDataJson", mapper.writeValueAsString(combinedChartData));
        } catch (Exception e) {
            model.addAttribute("chartDataJson", "[]");
        }

        return "Line Chart";
    }


    private void extractArticleCounts(List<String> journalNames, List<String> conferenceNames, List<String> authorNames,
                                      int startYear, int endYear, List<Map<String, Object>> targetList) {

        if (journalNames != null && !journalNames.isEmpty()) {
            for (String jName : journalNames) {
                List<Object[]> numOfJournalArticlesByYear = journalService.findNumOfJournalArticlesByYear(jName);
                if (numOfJournalArticlesByYear != null) {
                    for (Object[] row : numOfJournalArticlesByYear) {
                        if (row != null && row.length >= 2) {
                            int year = ((Number) row[0]).intValue();
                            if (year >= startYear && year <= endYear) {
                                Map<String, Object> dataPoint = new HashMap<>();
                                dataPoint.put("name", jName);
                                dataPoint.put("years", year);
                                dataPoint.put("number", ((Number) row[1]).longValue());
                                targetList.add(dataPoint);
                            }
                        }
                    }
                }
            }
        }
        else if (conferenceNames != null && !conferenceNames.isEmpty()) {
            for (String cName : conferenceNames) {
                // MATCHING INNER LOGIC: Utilizing your pre-aggregated database method!
                List<Object[]> numOfConferenceArticlesByYear = conferenceService.findNumOfConferenceArticlesByYear(cName);
                if (numOfConferenceArticlesByYear != null) {
                    for (Object[] row : numOfConferenceArticlesByYear) {
                        if (row != null && row.length >= 2) {
                            int year = ((Number) row[0]).intValue();
                            if (year >= startYear && year <= endYear) {
                                Map<String, Object> dataPoint = new HashMap<>();
                                dataPoint.put("name", cName);
                                dataPoint.put("years", year);
                                dataPoint.put("number", ((Number) row[1]).longValue());
                                targetList.add(dataPoint);
                            }
                        }
                    }
                }
            }
        }
        else if (authorNames != null && !authorNames.isEmpty()) {
            for (String aName : authorNames) {
                // MATCHING INNER LOGIC: Utilizing your pre-aggregated database method!
                List<Object[]> numOfAuthorArticlesByYear = authorService.findNumArticlesByYear(aName);
                if (numOfAuthorArticlesByYear != null) {
                    for (Object[] row : numOfAuthorArticlesByYear) {
                        if (row != null && row.length >= 2) {
                            int year = ((Number) row[0]).intValue();
                            if (year >= startYear && year <= endYear) {
                                Map<String, Object> dataPoint = new HashMap<>();
                                dataPoint.put("name", aName);
                                dataPoint.put("years", year);
                                dataPoint.put("number", ((Number) row[1]).longValue());
                                targetList.add(dataPoint);
                            }
                        }
                    }
                }
            }
        }
    }


    private void extractAuthorCounts(List<String> journalNames, List<String> conferenceNames,
                                     int startYear, int endYear, List<Map<String, Object>> targetList) {

        if (journalNames != null && !journalNames.isEmpty()) {
            for (String jName : journalNames) {
                List<Object[]> numOfJournalAuthorsByYear = journalService.findNumOfJournalAuthorsByYear(jName);
                if (numOfJournalAuthorsByYear != null) {
                    for (Object[] row : numOfJournalAuthorsByYear) {
                        if (row != null && row.length >= 3) { // Changed to >= 3 because your query now returns 3 columns

                            int year = ((Number) row[0]).intValue();

                            if (year >= startYear && year <= endYear) {
                                long totalAuthors = ((Number) row[1]).longValue();    // COUNT(ath.author_ID)
                                long distinctAuthors = ((Number) row[2]).longValue(); // COUNT(DISTINCT (ath.author_ID))

                                // LINE 1: Data point for Total Authors
                                Map<String, Object> totalPoint = new HashMap<>();
                                totalPoint.put("name", jName + " (Total Authors)"); // Line Identity
                                totalPoint.put("years", year);               // X-Axis
                                totalPoint.put("number", totalAuthors);          // Y-Axis
                                targetList.add(totalPoint);

                                // LINE 2: Data point for Distinct Authors
                                Map<String, Object> distinctPoint = new HashMap<>();
                                distinctPoint.put("name", jName + " (Distinct Authors)"); // Line Identity
                                distinctPoint.put("years", year);                  // X-Axis
                                distinctPoint.put("number", distinctAuthors);          // Y-Axis
                                targetList.add(distinctPoint);
                            }
                        }
                    }
                }
            }
        }
        else if (conferenceNames != null && !conferenceNames.isEmpty()) {
            for (String cName : conferenceNames) {
                // MATCHING INNER LOGIC: Utilizing your pre-aggregated database method!
                List<Object[]> numOfConferenceAuthorsByYear = conferenceService.findNumOfConferenceAuthorsByYear(cName);
                if (numOfConferenceAuthorsByYear != null) {
                    for (Object[] row : numOfConferenceAuthorsByYear) {
                        if (row != null && row.length >= 3) {
                            int year = ((Number) row[0]).intValue();

                            if (year >= startYear && year <= endYear) {
                                long totalAuthors = ((Number) row[1]).longValue();    // COUNT(ath.author_ID)
                                long distinctAuthors = ((Number) row[2]).longValue(); // COUNT(DISTINCT (ath.author_ID))

                                // LINE 1: Data point for Total Authors
                                Map<String, Object> totalPoint = new HashMap<>();
                                totalPoint.put("name", cName + " (Total Authors)"); // Line Identity
                                totalPoint.put("years", year);               // X-Axis
                                totalPoint.put("number", totalAuthors);          // Y-Axis
                                targetList.add(totalPoint);

                                // LINE 2: Data point for Distinct Authors
                                Map<String, Object> distinctPoint = new HashMap<>();
                                distinctPoint.put("name", cName + " (Distinct Authors)"); // Line Identity
                                distinctPoint.put("years", year);                  // X-Axis
                                distinctPoint.put("number", distinctAuthors);          // Y-Axis
                                targetList.add(distinctPoint);
                            }
                        }
                    }
                }
            }
        }
    }

    private void extractArticleCountsByCategory(List<String> journalCategory, List<String> conferenceCategory,
                                       List<Map<String, Object>> targetList) {

        if (journalCategory != null && !journalCategory.isEmpty()) {
            for (String categName : journalCategory) {
                List<Object[]> numOfJournalArticlesByCategory = journalService.findNumOfJournalByCategory(categName);
                if (numOfJournalArticlesByCategory != null) {
                    for (Object[] row : numOfJournalArticlesByCategory) {
                        if (row != null && row.length >= 2) {
                            Map<String, Object> dataPoint = new HashMap<>();
                            dataPoint.put("name", categName);
                            dataPoint.put("years", ((Number) row[0]).intValue());
                            dataPoint.put("number", ((Number) row[1]).longValue());
                            targetList.add(dataPoint);
                        }
                    }
                }
            }
        }
        else if (conferenceCategory != null && !conferenceCategory.isEmpty()) {
            for (String categName : conferenceCategory) {
                // MATCHING INNER LOGIC: Utilizing your pre-aggregated database method!
                List<Object[]> numOfConferenceArticlesByCategory = conferenceService.findNumOfConferenceByCategory(categName);
                if (numOfConferenceArticlesByCategory != null) {
                    for (Object[] row : numOfConferenceArticlesByCategory) {
                        if (row != null && row.length >= 2) {
                            Map<String, Object> dataPoint = new HashMap<>();
                            dataPoint.put("name", categName);
                            dataPoint.put("years", ((Number) row[0]).intValue());
                            dataPoint.put("number", ((Number) row[1]).longValue());
                            targetList.add(dataPoint);
                        }
                    }
                }
            }
        }
    }

    // BAR CHARTS

    @GetMapping("/groupedBarCharts")
    public String getGroupedBarCharts(
            @RequestParam(value = "journalNames", required = false) List<String> journalNames,
            @RequestParam(value = "conferenceNames", required = false) List<String> conferenceNames,
            @RequestParam(value = "publisherNames", required = false) List<String> publisherNames,
            Model model) {

        List<Map<String, Object>> groupedBarDataset = new ArrayList<>();

        // Route 1: Process Journals exclusively if provided
        if (journalNames != null && !journalNames.isEmpty()) {
            for (String jName : journalNames) {
                List<Article> totalArticlesList = journalService.findJournalArticles(jName);
                long totalArticlesCount = (totalArticlesList != null) ? totalArticlesList.size() : 0;
                int avgArticlesByYear = journalService.findAvgJournalArticles(jName);
                int avgAuthorsPerArticle = journalService.findAvgAuthorsByJournal(jName);

                Map<String, Object> bar1 = new HashMap<>();
                bar1.put("state", jName + " (Journal)");
                bar1.put("age", "Total Articles Volume");
                bar1.put("population", totalArticlesCount);
                groupedBarDataset.add(bar1);

                Map<String, Object> bar2 = new HashMap<>();
                bar2.put("state", jName + " (Journal)");
                bar2.put("age", "Avg Articles/Year");
                bar2.put("population", (long) avgArticlesByYear);
                groupedBarDataset.add(bar2);

                Map<String, Object> bar3 = new HashMap<>();
                bar3.put("state", jName + " (Journal)");
                bar3.put("age", "Avg Authors/Article");
                bar3.put("population", (long) avgAuthorsPerArticle);
                groupedBarDataset.add(bar3);
            }
        }
        // Route 2: Fall back to Conferences if Journals are empty
        else if (conferenceNames != null && !conferenceNames.isEmpty()) {
            for (String cName : conferenceNames) {
                List<Article> totalArticlesList = conferenceService.findConferenceArticles(cName);
                long totalArticlesCount = (totalArticlesList != null) ? totalArticlesList.size() : 0;
                int avgArticlesByYear = conferenceService.findAvgConferenceArticles(cName);
                int avgAuthorsPerArticle = conferenceService.findAvgAuthorsByConference(cName);

                Map<String, Object> bar1 = new HashMap<>();
                bar1.put("state", cName + " (Conference)");
                bar1.put("age", "Total Articles Volume");
                bar1.put("population", totalArticlesCount);
                groupedBarDataset.add(bar1);

                Map<String, Object> bar2 = new HashMap<>();
                bar2.put("state", cName + " (Conference)");
                bar2.put("age", "Avg Articles/Year");
                bar2.put("population", (long) avgArticlesByYear);
                groupedBarDataset.add(bar2);

                Map<String, Object> bar3 = new HashMap<>();
                bar3.put("state", cName + " (Conference)");
                bar3.put("age", "Avg Authors/Article");
                bar3.put("population", (long) avgAuthorsPerArticle);
                groupedBarDataset.add(bar3);
            }
        }
        // Route 3: Fall back to Publishers if both Journals and Conferences are empty
        else if (publisherNames != null && !publisherNames.isEmpty()) {
            for (String pName : publisherNames) {
                // Executing your new aggregate query method here
                int publisherPublicationsCount = journalService.findPublisherPublications(pName);

                // Bar 1: Total Publisher Volume (Mapped directly to your query)
                Map<String, Object> bar1 = new HashMap<>();
                bar1.put("state", pName + " (Publisher)");
                bar1.put("age", "Total Articles Volume");
                bar1.put("population", (long) publisherPublicationsCount);
                groupedBarDataset.add(bar1);

                // Bar 2: Constant Baseline (Keeps chart categories uniform across the UI views)
                Map<String, Object> bar2 = new HashMap<>();
                bar2.put("state", pName + " (Publisher)");
                bar2.put("age", "Avg Articles/Year");
                bar2.put("population", 0L);
                groupedBarDataset.add(bar2);

                // Bar 3: Constant Baseline
                Map<String, Object> bar3 = new HashMap<>();
                bar3.put("state", pName + " (Publisher)");
                bar3.put("age", "Avg Authors/Article");
                bar3.put("population", 0L);
                groupedBarDataset.add(bar3);
            }
        }

        model.addAttribute("selectedJournals", journalNames);
        model.addAttribute("selectedConferences", conferenceNames);
        model.addAttribute("selectedPublishers", publisherNames);

        try {
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("barChartDataJson", mapper.writeValueAsString(groupedBarDataset));
        } catch (Exception e) {
            model.addAttribute("barChartDataJson", "[]");
        }

        return "BarChart";
    }

    // SCATTER PLOTS


}
