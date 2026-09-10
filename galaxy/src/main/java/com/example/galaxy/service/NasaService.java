package com.example.galaxy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class NasaService {

    @Value("${nasa.api.key:DEMO_KEY}")
    private String apiKey;

    private final String BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=";

    public Map<String, Object> getTodaySpaceImage() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(BASE_URL + apiKey, Map.class);
    }

    public Map<String, Object> getRandomSpaceImage() {
        RestTemplate restTemplate = new RestTemplate();
        List<Map<String, Object>> list = restTemplate.getForObject(BASE_URL + apiKey + "&count=1", List.class);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return getTodaySpaceImage();
    }

    public Map<String, Object> getImageByDate(String date) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(BASE_URL + apiKey + "&date=" + date, Map.class);
    }
}