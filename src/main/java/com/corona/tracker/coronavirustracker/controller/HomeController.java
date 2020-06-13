package com.corona.tracker.coronavirustracker.controller;

import com.corona.tracker.coronavirustracker.model.LocationStats;
import com.corona.tracker.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> locationStatsList = coronavirusDataService.getLocationStatsList();
        long totalCasesGlobal = locationStatsList.stream().mapToLong(rec->rec.getLatestCases()).sum();
        long totalNewCasesGlobal = locationStatsList.stream().mapToLong(rec->rec.getDiffFromPreviousDay()).sum();
        model.addAttribute("locationStats", locationStatsList);
        model.addAttribute("totalCasesGlobal", totalCasesGlobal);
        model.addAttribute("totalNewCasesGlobal", totalNewCasesGlobal);
        return "home";
    }
}
