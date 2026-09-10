package com.example.galaxy.controller;

import com.example.galaxy.entity.FavoritePhoto;
import com.example.galaxy.repository.FavoriteRepository;
import com.example.galaxy.service.NasaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class SpaceController {

    private final NasaService nasaService;
    private final FavoriteRepository favoriteRepository;

    public SpaceController(NasaService nasaService, FavoriteRepository favoriteRepository) {
        this.nasaService = nasaService;
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Map<String, Object> nasaData = nasaService.getTodaySpaceImage();
        model.addAttribute("nasa", nasaData);
        model.addAttribute("favorites", favoriteRepository.findAll());
        return "index";
    }

    @GetMapping("/random")
    public String random(Model model) {
        Map<String, Object> nasaData = nasaService.getRandomSpaceImage();
        model.addAttribute("nasa", nasaData);
        model.addAttribute("favorites", favoriteRepository.findAll());
        return "index";
    }

    // 💡 日付を指定してメイン画面に表示する処理
    @GetMapping("/by-date")
    public String getByDate(@RequestParam String date, Model model) {
        Map<String, Object> nasaData = nasaService.getImageByDate(date);
        model.addAttribute("nasa", nasaData);
        model.addAttribute("favorites", favoriteRepository.findAll());
        return "index";
    }

    // 💡 説明文(explanation)も受け取るように修正
    @PostMapping("/favorites")
    public String addFavorite(@RequestParam String title, 
                              @RequestParam String url, 
                              @RequestParam String date, 
                              @RequestParam(required = false) String explanation) {
        FavoritePhoto photo = new FavoritePhoto();
        photo.setTitle(title);
        photo.setUrl(url);
        photo.setDate(date);
        photo.setExplanation(explanation);
        photo.setMemo("メモなし");
        favoriteRepository.save(photo);
        return "redirect:/";
    }

    @PostMapping("/favorites/{id}/update")
    public String updateMemo(@PathVariable Long id, @RequestParam String memo) {
        FavoritePhoto photo = favoriteRepository.findById(id).orElseThrow();
        photo.setMemo(memo);
        favoriteRepository.save(photo);
        return "redirect:/";
    }

    @PostMapping("/favorites/{id}/delete")
    public String deleteFavorite(@PathVariable Long id) {
        favoriteRepository.deleteById(id);
        return "redirect:/";
    }
}