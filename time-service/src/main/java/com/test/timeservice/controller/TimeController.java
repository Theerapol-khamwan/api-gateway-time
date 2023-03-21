package com.test.timeservice.controller;

import com.test.timeservice.model.CountryRequest;
import com.test.timeservice.model.CountryTimeResponse;
import com.test.timeservice.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @PostMapping("/")
    public CountryTimeResponse getCapitalCityTime(@RequestBody(required = false) CountryRequest countryRequest) {

        String countryTimezone;

        if (countryRequest == null || countryRequest.getCountryName() == null || countryRequest.getCountryName().isEmpty()) {
            countryTimezone = timeService.getCurrentTimeInCountryName("Thailand");
        } else {
            countryTimezone = timeService.getCurrentTimeInCountryName(countryRequest.getCountryName());
        }

        CountryTimeResponse response = new CountryTimeResponse();
        response.setTime(countryTimezone);

        return response;
    }
}
