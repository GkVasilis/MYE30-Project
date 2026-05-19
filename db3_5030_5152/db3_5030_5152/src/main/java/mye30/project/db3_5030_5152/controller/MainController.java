package mye30.project.db3_5030_5152.controller;

import mye30.project.db3_5030_5152.dataModel.*;
import mye30.project.db3_5030_5152.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/journalProfile")
    public String getJournalProfile(Model model) {

        return "JournalProfile";
    }
}
