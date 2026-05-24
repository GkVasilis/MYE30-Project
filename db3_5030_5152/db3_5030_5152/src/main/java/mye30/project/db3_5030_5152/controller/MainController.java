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
        return "ListOfConferences";
    }
    /*
    @GetMapping("/listAuthors")
    public String getListAuthors(Model model) {
        List<String> authorsList = authorService.findAllAuthors();
        model.addAttribute("Authors", authorsList);
        return "ListOfAuthors";
    }*/
    @GetMapping("/listAuthors")
    public String getListAuthors(Model model, @RequestParam(defaultValue = "0") int page) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, 100);
        org.springframework.data.domain.Page<String> authorsPage = authorService.findAllAuthors(pageable);

        model.addAttribute("Authors", authorsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", authorsPage.getTotalPages());

        return "ListOfAuthors";
    }


    @GetMapping("/listYears")
    public String getListYears(Model model) {
        List<Integer> yearList = yearService.findAllYears();
        model.addAttribute("Years", yearList);
        return "ListOfYears";
    }

    // JOURNALS

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
        return "JournalProfile";
    }

    private void printJournalRanking(String name, Model model) {
        if (name != null) {
            String cleanName = name.trim();
            JournalRanking ranking = journalService.findJournalRanking(cleanName);
            model.addAttribute("Ranking", ranking);
        } else {
            model.addAttribute("Ranking", null);
        }
    }

    private void printJournalStats(String name, Model model){
        Integer firstYear = journalService.findJournalByFirstYear(name);
        Integer lastYear = journalService.findJournalByLastYear(name);
        Integer numOfAuthors = journalService.findNumOfJournalAuthors(name);
        Double avgAuthorsByJournal = journalService.findAvgAuthorsByJournal(name);
        Double avgAuthorsByYear = journalService.findAvgAuthorsByYear(name);
        List<Article> articles = journalService.findJournalArticles(name);
        List<Author> authors = journalService.findJournalAuthors(name);
        Double avgJA = journalService.findAvgJournalArticles(name);
        model.addAttribute("YearFirstJournalPublished", firstYear);
        model.addAttribute("YearLastJournalPublished", lastYear);
        model.addAttribute("NumberJournalAuthors", numOfAuthors);
        model.addAttribute("AvgAuthorsOfJournal", avgAuthorsByJournal);
        model.addAttribute("AvgAuthorsByYear", avgAuthorsByYear);
        model.addAttribute("Articles", articles);
        model.addAttribute("Authors", authors);
        model.addAttribute("AvgArticlesByYear", avgJA);
    }

    private void printJournalStatsRange(String name, int min, int max, Model model){
        Integer numOfAuthors = journalService.findNumOfJournalAuthorsRange(name, min, max);
        Double avgAuthorsByJournal = journalService.findAvgAuthorsByJournalRange(name, min, max);
        Double avgAuthorsByYear = journalService.findAvgAuthorsByYearRange(name, min, max);
        List<Article> articles = journalService.findJournalArticlesRange(name, min, max);
        List<Author> authors = journalService.findJournalAuthorsRange(name, min, max);
        Double avgJA = journalService.findAvgJournalArticlesRange(name, min, max);

        model.addAttribute("NumberJournalAuthors", numOfAuthors);
        model.addAttribute("AvgAuthorsOfJournal", avgAuthorsByJournal);
        model.addAttribute("AvgAuthorsByYear", avgAuthorsByYear);
        model.addAttribute("Articles", articles);
        model.addAttribute("Authors", authors);
        model.addAttribute("AvgArticlesByYear", avgJA);
    }

    // CONFERENCES

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
        return "ConferenceProfile";
    }

    private void printConferenceRanking(String name, Model model) {
        if (name != null) {
            String cleanName = name.trim();
            ConferenceRanking ranking = conferenceService.findConferenceRanking(cleanName);
            model.addAttribute("Ranking", ranking);
        } else {
            model.addAttribute("Ranking", null);
        }
    }

    private void printConferenceStats(String name, Model model){
        Integer firstYear = conferenceService.findConferenceByFirstYear(name);
        Integer lastYear = conferenceService.findConferenceByLastYear(name);
        Integer numOfAuthors = conferenceService.findNumOfConferenceAuthors(name);
        Double avgAuthorsByConference = conferenceService.findAvgAuthorsByConference(name);
        Double avgAuthorsByYear = conferenceService.findAvgAuthorsByYear(name);
        List<Article> articles = conferenceService.findConferenceArticles(name);
        List<Author> authors = conferenceService.findConferenceAuthors(name);
        Double avgJA = conferenceService.findAvgConferenceArticles(name);
        model.addAttribute("YearFirstConferencePublished", firstYear);
        model.addAttribute("YearLastConferencePublished", lastYear);
        model.addAttribute("NumberConferenceAuthors", numOfAuthors);
        model.addAttribute("AvgAuthorsOfConference", avgAuthorsByConference);
        model.addAttribute("AvgAuthorsByYear", avgAuthorsByYear);
        model.addAttribute("Articles", articles);
        model.addAttribute("Authors", authors);
        model.addAttribute("AvgArticlesByYear", avgJA);
    }

    private void printConferenceStatsRange(String name, int min, int max, Model model){
        Integer numOfAuthors = conferenceService.findNumOfConferenceAuthorsRange(name, min, max);
        Double avgAuthorsByConference = conferenceService.findAvgAuthorsByConferenceRange(name, min, max);
        Double avgAuthorsByYear = conferenceService.findAvgAuthorsByYearRange(name, min, max);
        List<Article> articles = conferenceService.findConferenceArticlesRange(name, min, max);
        List<Author> authors = conferenceService.findConferenceAuthorsRange(name, min, max);
        Double avgJA = conferenceService.findAvgConferenceArticlesRange(name, min, max);

        model.addAttribute("NumberConferenceAuthors", numOfAuthors);
        model.addAttribute("AvgAuthorsOfConference", avgAuthorsByConference);
        model.addAttribute("AvgAuthorsByYear", avgAuthorsByYear);
        model.addAttribute("Articles", articles);
        model.addAttribute("Authors", authors);
        model.addAttribute("AvgArticlesByYear", avgJA);
    }

    // AUTHORS

    @GetMapping("/authorProfile")
    public String getAuthorProfile(@RequestParam("authorName") String authorName,Model model) {
        int firstYear = authorService.findFirstPublishedYear(authorName);
        int lastYear = authorService.findLastPublishedYear(authorName);
        List<Article> authorArticles = authorService.findAllArticles(authorName);
        Double authAvgArticlesByYear = authorService.findAvgArticlesByYear(authorName);
        model.addAttribute("firstYear", firstYear);
        model.addAttribute("lastYear", lastYear);
        model.addAttribute("authorArticles", authorArticles);
        model.addAttribute("authAvgArticlesByYear", authAvgArticlesByYear);

        return "AuthorProfile";
    }

    // YEARS

    @GetMapping("/yearProfile")
    public String getYearProfile(
            @RequestParam("yearValue") Integer yearValue,
            @RequestParam(value = "journalName", required = false) String journalName,
            @RequestParam(value = "conferenceName", required = false) String conferenceName,
            @RequestParam(value = "authorName", required = false) String authorName,
            Model model) {

        Integer publishedArticles = yearService.findPublishedArticles(yearValue);
        Integer numOfJournals = yearService.findNumOfJournals(yearValue);
        Integer numOfConferences = yearService.findNumOfConferences(yearValue);
        List<Object[]> numOfAuthors = yearService.findNumOfAuthors(yearValue);

        model.addAttribute("yearValue", yearValue);
        model.addAttribute("publishedArticles", publishedArticles);
        model.addAttribute("numOfJournals", numOfJournals);
        model.addAttribute("numOfConferences", numOfConferences);

        if (numOfAuthors != null && !numOfAuthors.isEmpty()) {
            Object[] firstRow = numOfAuthors.get(0);
            model.addAttribute("numOfAuthors", firstRow[0]);
            model.addAttribute("numOfAuthorsDistinct", firstRow[1]);
        } else {
            model.addAttribute("numOfAuthors", 0);
            model.addAttribute("numOfAuthorsDistinct", 0);
        }

        model.addAttribute("publicationsByJournal", new ArrayList<>());
        model.addAttribute("publicationsByConferences", new ArrayList<>());
        model.addAttribute("publicationsByAuthors", new ArrayList<>());

        if (journalName != null && !journalName.trim().isEmpty()) {
            String cleanJournalName = journalName.trim();
            List<Object[]> publicationsByJournalRaw = yearService.findPublicationByJournal(yearValue, cleanJournalName);
            List<Object[]> publicationsByJournal = new ArrayList<>();
            if (publicationsByJournalRaw != null) {
                for (Object[] row : publicationsByJournalRaw) {
                    Object[] mappedRow = new Object[3];
                    mappedRow[0] = row[0]; mappedRow[1] = row[1]; mappedRow[2] = row[2];
                    publicationsByJournal.add(mappedRow);
                }
            }
            model.addAttribute("ContextType", "Journal");
            model.addAttribute("ContextName", cleanJournalName);
            model.addAttribute("publicationsByJournal", publicationsByJournal);

        } else if (conferenceName != null && !conferenceName.trim().isEmpty()) {
            String cleanConferenceName = conferenceName.trim();
            List<Object[]> publicationsByConferencesRaw = yearService.findPublicationByConference(yearValue, cleanConferenceName);
            List<Object[]> publicationsByConferences = new ArrayList<>();
            if (publicationsByConferencesRaw != null) {
                for (Object[] row : publicationsByConferencesRaw) {
                    Object[] mappedRow = new Object[3];
                    mappedRow[0] = row[0]; mappedRow[1] = row[1]; mappedRow[2] = row[2];
                    publicationsByConferences.add(mappedRow);
                }
            }
            model.addAttribute("ContextType", "Conference");
            model.addAttribute("ContextName", cleanConferenceName);
            model.addAttribute("publicationsByConferences", publicationsByConferences);

        } else if (authorName != null && !authorName.trim().isEmpty()) {
            String cleanAuthorName = authorName.trim();
            List<Object[]> publicationsByAuthorsRaw = yearService.findPublicationByAuthor(yearValue, cleanAuthorName);
            List<Object[]> publicationsByAuthors = new ArrayList<>();
            if (publicationsByAuthorsRaw != null) {
                for (Object[] row : publicationsByAuthorsRaw) {
                    Object[] mappedRow = new Object[3];
                    mappedRow[0] = row[0]; mappedRow[1] = row[1]; mappedRow[2] = row[2];
                    publicationsByAuthors.add(mappedRow);
                }
            }
            model.addAttribute("ContextType", "Author");
            model.addAttribute("ContextName", cleanAuthorName);
            model.addAttribute("publicationsByAuthors", publicationsByAuthors);

        } else {
            model.addAttribute("ContextType", "Journal");
            model.addAttribute("ContextName", "");
        }

        return "YearProfile";
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

        int startYear = (minYear != null) ? minYear : 0;
        int endYear = (maxYear != null) ? maxYear : Integer.MAX_VALUE;

        extractArticleCounts(journalNames, conferenceNames, authorNames, startYear, endYear, combinedChartData);

        extractAuthorCounts(journalNames, conferenceNames, startYear, endYear, combinedChartData);

        extractArticleCountsByCategory(journalCategories, conferenceCategories, combinedChartData);

        combinedChartData.sort(Comparator.comparing(m -> (String) m.get("years").toString()));//todo intValue()

        model.addAttribute("selectedJournals", journalNames);
        model.addAttribute("selectedConferences", conferenceNames);
        model.addAttribute("selectedAuthors", authorNames);
        model.addAttribute("selectedJournalsCategories", journalCategories);
        model.addAttribute("selectedConferencesCategories", conferenceCategories);
        model.addAttribute("minYear", minYear);
        model.addAttribute("maxYear", maxYear);

        try {
            ObjectMapper mapper = new ObjectMapper();
            model.addAttribute("chartDataJson", mapper.writeValueAsString(combinedChartData));
        } catch (Exception e) {
            model.addAttribute("chartDataJson", "[]");
        }

        return "LineChart";
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
                                dataPoint.put("name", jName + " (Total Articles)");
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
                List<Object[]> numOfConferenceArticlesByYear = conferenceService.findNumOfConferenceArticlesByYear(cName);
                if (numOfConferenceArticlesByYear != null) {
                    for (Object[] row : numOfConferenceArticlesByYear) {
                        if (row != null && row.length >= 2) {
                            int year = ((Number) row[0]).intValue();
                            if (year >= startYear && year <= endYear) {
                                Map<String, Object> dataPoint = new HashMap<>();
                                dataPoint.put("name", cName + " (Total Articles)");
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
                List<Object[]> numOfAuthorArticlesByYear = authorService.findNumArticlesByYear(aName);
                if (numOfAuthorArticlesByYear != null) {
                    for (Object[] row : numOfAuthorArticlesByYear) {
                        if (row != null && row.length >= 2) {
                            int year = ((Number) row[0]).intValue();
                            if (year >= startYear && year <= endYear) {
                                Map<String, Object> dataPoint = new HashMap<>();
                                dataPoint.put("name", aName + " (Total Articles)");
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
                        if (row != null && row.length >= 3) {

                            int year = ((Number) row[0]).intValue();

                            if (year >= startYear && year <= endYear) {
                                long totalAuthors = ((Number) row[1]).longValue();
                                long distinctAuthors = ((Number) row[2]).longValue();

                                Map<String, Object> totalPoint = new HashMap<>();
                                totalPoint.put("name", jName + " (Total Authors)");
                                totalPoint.put("years", year);
                                totalPoint.put("number", totalAuthors);
                                targetList.add(totalPoint);

                                Map<String, Object> distinctPoint = new HashMap<>();
                                distinctPoint.put("name", jName + " (Distinct Authors)");
                                distinctPoint.put("years", year);
                                distinctPoint.put("number", distinctAuthors);
                                targetList.add(distinctPoint);
                            }
                        }
                    }
                }
            }
        }
        else if (conferenceNames != null && !conferenceNames.isEmpty()) {
            for (String cName : conferenceNames) {
                List<Object[]> numOfConferenceAuthorsByYear = conferenceService.findNumOfConferenceAuthorsByYear(cName);
                if (numOfConferenceAuthorsByYear != null) {
                    for (Object[] row : numOfConferenceAuthorsByYear) {
                        if (row != null && row.length >= 3) {
                            int year = ((Number) row[0]).intValue();

                            if (year >= startYear && year <= endYear) {
                                long totalAuthors = ((Number) row[1]).longValue();
                                long distinctAuthors = ((Number) row[2]).longValue();

                                Map<String, Object> totalPoint = new HashMap<>();
                                totalPoint.put("name", cName + " (Total Authors)");
                                totalPoint.put("years", year);
                                totalPoint.put("number", totalAuthors);
                                targetList.add(totalPoint);

                                Map<String, Object> distinctPoint = new HashMap<>();
                                distinctPoint.put("name", cName + " (Distinct Authors)");
                                distinctPoint.put("years", year);
                                distinctPoint.put("number", distinctAuthors);
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
            @RequestParam(value = "journalNames", required = false) String journalNamesRaw,
            @RequestParam(value = "conferenceNames", required = false) String conferenceNamesRaw,
            @RequestParam(value = "publisherNames", required = false) String publisherNamesRaw,
            Model model) {

        List<Map<String, Object>> groupedBarDataset = new ArrayList<>();

        List<String> journalNames = (journalNamesRaw != null && !journalNamesRaw.trim().isEmpty())
                ? java.util.Arrays.asList(journalNamesRaw.split("\\s*;\\s*")) : new ArrayList<>();

        List<String> conferenceNames = (conferenceNamesRaw != null && !conferenceNamesRaw.trim().isEmpty())
                ? java.util.Arrays.asList(conferenceNamesRaw.split("\\s*;\\s*")) : new ArrayList<>();

        List<String> publisherNames = (publisherNamesRaw != null && !publisherNamesRaw.trim().isEmpty())
                ? java.util.Arrays.asList(publisherNamesRaw.split("\\s*;\\s*")) : new ArrayList<>();

        if (!journalNames.isEmpty()) {
            for (String jName : journalNames) {
                List<Article> totalArticlesList = journalService.findJournalArticles(jName);
                long totalArticlesCount = (totalArticlesList != null) ? totalArticlesList.size() : 0;
                Double avgArticlesByYear = journalService.findAvgJournalArticles(jName);
                Double avgAuthorsByYear = journalService.findAvgAuthorsByYear(jName);

                Map<String, Object> bar1 = new HashMap<>();
                bar1.put("state", jName + " (Journal)");
                bar1.put("age", "Total Articles Volume");
                bar1.put("population", totalArticlesCount);
                groupedBarDataset.add(bar1);

                Map<String, Object> bar2 = new HashMap<>();
                bar2.put("state", jName + " (Journal)");
                bar2.put("age", "Average Articles/Year");
                bar2.put("population", (avgArticlesByYear != null) ? avgArticlesByYear : 0.0);
                groupedBarDataset.add(bar2);

                Map<String, Object> bar3 = new HashMap<>();
                bar3.put("state", jName + " (Journal)");
                bar3.put("age", "Average Authors/Year");
                bar3.put("population", (avgAuthorsByYear != null) ? avgAuthorsByYear : 0.0);
                groupedBarDataset.add(bar3);
            }
        }
        else if (!conferenceNames.isEmpty()) {
            for (String cName : conferenceNames) {
                List<Article> totalArticlesList = conferenceService.findConferenceArticles(cName);
                long totalArticlesCount = (totalArticlesList != null) ? totalArticlesList.size() : 0;
                Double avgArticlesByYear = conferenceService.findAvgConferenceArticles(cName);
                Double avgAuthorsByYear = conferenceService.findAvgAuthorsByYear(cName);

                Map<String, Object> bar1 = new HashMap<>();
                bar1.put("state", cName + " (Conference)");
                bar1.put("age", "Total Articles Volume");
                bar1.put("population", totalArticlesCount);
                groupedBarDataset.add(bar1);

                Map<String, Object> bar2 = new HashMap<>();
                bar2.put("state", cName + " (Conference)");
                bar2.put("age", "Average Articles/Year");
                bar2.put("population", (avgArticlesByYear != null) ? avgArticlesByYear : 0.0);
                groupedBarDataset.add(bar2);

                Map<String, Object> bar3 = new HashMap<>();
                bar3.put("state", cName + " (Conference)");
                bar3.put("age", "Average Authors/Year");
                bar3.put("population", (avgAuthorsByYear != null) ? avgAuthorsByYear : 0.0);
                groupedBarDataset.add(bar3);
            }
        }
        else if (!publisherNames.isEmpty()) {
            for (String pName : publisherNames) {
                int publisherPublicationsCount = journalService.findPublisherPublications(pName);

                Map<String, Object> bar1 = new HashMap<>();
                bar1.put("state", pName + " (Publisher)");
                bar1.put("age", "Total Articles Volume");
                bar1.put("population", (long) publisherPublicationsCount);
                groupedBarDataset.add(bar1);

                Map<String, Object> bar2 = new HashMap<>();
                bar2.put("state", pName + " (Publisher)");
                bar2.put("age", "Average Articles/Year");
                bar2.put("population", 0.0);
                groupedBarDataset.add(bar2);

                Map<String, Object> bar3 = new HashMap<>();
                bar3.put("state", pName + " (Publisher)");
                bar3.put("age", "Average Authors/Year");
                bar3.put("population", 0.0);
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
