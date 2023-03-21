package com.test.timeservice.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TimeService {
    private Map<String, String> countryCodeToName;
    private Map<String, String> timeZoneToCountryCode;

    public TimeService() {
        countryCodeToName = new HashMap<>();
        timeZoneToCountryCode = new HashMap<>();
        loadCountryCodeAndNameFromCSV();
        loadTimeZoneToCountryCodeFromCSV();
    }

    private void loadCountryCodeAndNameFromCSV() {
        Resource resource = new ClassPathResource("country_to_code.csv");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                String countryCode = columns[0];
                String countryName = columns[1];
                countryCodeToName.put(countryCode, countryName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTimeZoneToCountryCodeFromCSV() {
        Resource resource = new ClassPathResource("timezone_to_country_code.csv");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                String timeZone = columns[0];
                String countryCode = columns[1];
                timeZoneToCountryCode.put(timeZone, countryCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentTimeInCountryName(String countryName) {
        String timeZone = null;

        for (Map.Entry<String, String> entry : timeZoneToCountryCode.entrySet()) {
            if (countryCodeToName.get(entry.getValue()).equalsIgnoreCase(countryName)) {
                timeZone = entry.getKey();
                break;
            }
        }

        if (timeZone != null) {
            ZoneId zoneId = ZoneId.of(timeZone);
            ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            return formatter.format(zonedDateTime);
        } else {
            throw new RuntimeException("Country name not found: " + countryName);
        }
    }
}
