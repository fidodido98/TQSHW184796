package com.tqs1.tqs1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@Controller    // This means that this class is a Controller
@RequestMapping(path="/api") // This means URL's start with /weather (after Application path)
public class WeatherController {

    private static final RestTemplate restTemplate = new RestTemplate();

    @Autowired // This means to get the bean called WeatherRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private WeatherRepository repository;

    @Autowired
    private DataRepository repository2;

    @Autowired
    private DailyRepository repository3;

    @GetMapping(path="/weather")
    public @ResponseBody Weather getWeather(@RequestParam double latitude, @RequestParam double longitude) {

        if (repository.existsByLatitudeAndLongitude(latitude, longitude)) {

            return repository.findByLatitudeAndLongitude(latitude,longitude);

        } else {

            Weather weather = restTemplate.getForObject(
                    "https://api.darksky.net/forecast/28a3c3fed03114a110538615e5262cbb/" + latitude + "," + longitude, Weather.class);
            for (Data d : weather.getDaily().getData())
                repository2.save(d);
            repository3.save(weather.getDaily());
            repository.save(weather);

            return weather;

        }

    }

}