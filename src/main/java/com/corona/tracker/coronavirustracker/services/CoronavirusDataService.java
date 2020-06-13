package com.corona.tracker.coronavirustracker.services;

import com.corona.tracker.coronavirustracker.model.LocationStats;
import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {

    private final static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private final static String REQUEST_TYPE_GET = "GET";
    private static final String USER_AGENT = "Mozilla/5.0";
    @Getter
    private List<LocationStats> locationStatsList = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() {
        try {
            URL url = new URL(VIRUS_DATA_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(REQUEST_TYPE_GET);
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
                for (CSVRecord record : records) {
                    LocationStats locationStats =
                            LocationStats.builder()
                                    .state(record.get("Province/State"))
                                    .country(record.get("Country/Region"))
                                    .latestCases(Integer.parseInt(record.get(record.size() - 1)))
                                    .diffFromPreviousDay(Integer.parseInt(record.get(record.size() - 1))
                                            - Integer.parseInt(record.get(record.size() - 2)))
                            .build();
                    locationStatsList.add(locationStats);
                }
                in.close();
            } else {
                System.out.println("GET request not worked");
            }

        } catch (Exception exs) {
            System.out.println("Exception Occured");

        }

    }
}
