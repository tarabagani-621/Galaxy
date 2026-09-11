package com.example.galaxy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class NasaService {

    @Value("${nasa.api.key:DEMO_KEY}")
    private String apiKey;

    private final String BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=";

    public Map<String, Object> getTodaySpaceImage() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(BASE_URL + apiKey, Map.class);
        } catch (Exception e) {
            // NASA APIがエラーを返した場合は、空のデータ（またはエラーメッセージ）を返してアプリの崩壊を防ぐ
            Map<String, Object> fallback = new HashMap<>();
            fallback.put("title", "NASA API Temporary Error");
            fallback.put("url", "");
            fallback.put("explanation", "現在NASAのAPIサーバーが応答していません。");
            return fallback;
        }
    }
}
