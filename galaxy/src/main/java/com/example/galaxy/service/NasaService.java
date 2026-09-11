package com.example.galaxy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NasaService {

    @Value("${nasa.api.key:DEMO_KEY}")
    private String apiKey;

    private final String BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=";

    // 今日の画像を取得
    public Map<String, Object> getTodaySpaceImage() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(BASE_URL + apiKey, Map.class);
        } catch (Exception e) {
            return createFallbackMap("NASA API Error: 今日の画像を取得できませんでした。");
        }
    }

    // ランダムな画像を取得（Controller側の期待に合わせて Map を返す形に調整）
    public Map<String, Object> getRandomSpaceImage() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = BASE_URL + apiKey + "&count=1";
            List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
            if (response != null && !response.isEmpty()) {
                return response.get(0);
            }
            return createFallbackMap("データが取得できませんでした。");
        } catch (Exception e) {
            return createFallbackMap("NASA API Error: ランダム画像を取得できませんでした。");
        }
    }

    // 指定した日付の画像を取得
    public Map<String, Object> getImageByDate(String date) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = BASE_URL + apiKey + "&date=" + date;
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            return createFallbackMap("NASA API Error: 指定日の画像を取得できませんでした。");
        }
    }

    // APIエラー時に代わりに返すマップデータの生成
    private Map<String, Object> createFallbackMap(String message) {
        Map<String, Object> fallback = new HashMap<>();
        fallback.put("title", "一時的な通信エラー");
        fallback.put("url", "");
        fallback.put("explanation", message);
        return fallback;
    }
}
