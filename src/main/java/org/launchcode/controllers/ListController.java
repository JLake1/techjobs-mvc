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
@RequestMapping(value = "list")
public class ListController {

    private String name;
    private String position;
    private String employer;
    private String location;
    private String skill;
    static HashMap<String, String> columnChoices = new HashMap<>();
    private ArrayList<Listing> jobListings;

    public ListController () {
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");
    }

    @RequestMapping(value = "")
    public String list(Model model) {

        model.addAttribute("columns", columnChoices);

        return "list";
    }

    @RequestMapping(value = "values")
    public String listColumnValues(Model model, @RequestParam String column) {

        if (column.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            model.addAttribute("jobListings", jobListings);
            model.addAttribute("name", name);
            model.addAttribute("position", position);
            model.addAttribute("employer", employer);
            model.addAttribute("location", location);
            model.addAttribute("skill", skill);

            this.jobListings = new ArrayList<>();


            for (HashMap<String, String> someJob : jobs) {

                for (Map.Entry<String, String> e : someJob.entrySet()) {

                    String jobKey = e.getKey();
                    String jobValue = e.getValue();

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
            return "list-jobs";
        } else {
            ArrayList<String> items = JobData.findAll(column);
            model.addAttribute("title", "All " + columnChoices.get(column) + " Values");
            model.addAttribute("column", column);
            model.addAttribute("items", items);
            return "list-column";
        }

    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model,
            @RequestParam String column, @RequestParam String value) {

        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(column, value);
        model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        model.addAttribute("jobs", jobs);

        return "list-jobs";
    }
}
