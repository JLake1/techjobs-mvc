package org.launchcode.controllers;

import org.launchcode.Listing;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    private String jobKey;
    private String jobValue;
    private String name;
    private String position;
    private String employer;
    private String location;
    private String skill;
    private ArrayList<Listing> jobListings;

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")       // append url with "search" ?
    public String results(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {

        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);

        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        }

        this.jobListings = new ArrayList<>();

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchType", searchType);
        model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("jobListings", jobListings);
        model.addAttribute("name", name);
        model.addAttribute("position", position);
        model.addAttribute("employer", employer);
        model.addAttribute("location", location);
        model.addAttribute("skill", skill);

        for (HashMap<String, String> someJob : jobs) {

            for (Map.Entry<String, String> e : someJob.entrySet()) {

                jobKey = e.getKey();
                jobValue = e.getValue();

                if (jobKey.equals("name")) {
                    this.name = jobValue;
                } else if (jobKey.equals("position type")) {
                    this.position = jobValue;
                } else if (jobKey.equals("employer")) {
                    this.employer = jobValue;
                } else if (jobKey.equals("location")) {
                    this.location = jobValue;
                } else if (jobKey.equals("core competency")) {
                    this.skill = jobValue;
                }
            }

        Listing l = new Listing(name, position, employer, location, skill);
        this.jobListings.add(l);


        }

        //System.out.println(JobData.findByColumnAndValue(searchType, searchTerm));
        //System.out.println("\n\n\n\n" + jobs +"\n\n\n\n");

        return "search";
    }

    public String toString() {
        return "Name: " + this.name + " ID: " + this.position + " Credits: " + this.location + " GPA: " + this.skill;
    }
}
