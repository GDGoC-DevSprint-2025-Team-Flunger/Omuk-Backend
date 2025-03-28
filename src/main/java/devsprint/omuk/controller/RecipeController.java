package devsprint.omuk.controller;

import devsprint.omuk.domain.*;
import devsprint.omuk.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeRepository recipeRepository;

    //전체 레시피 조회
    @GetMapping("/list")
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    //특정 레시피 조회
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //랜덤 추천
    @GetMapping("/recommendation/random")
    public List<Recipe> getRandomRecommendation() {
        List<Recipe> all = recipeRepository.findAll();
        Collections.shuffle(all);
        return all.stream().limit(4).toList();
    }

    //조건 기반 추천(keyword, mealTime, season)
    @GetMapping("/recommendation")
    public List<Recipe> getRecommendation(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) MealTime mealTime,
            @RequestParam(required = false) Season season,
            @RequestParam(required = false) TasteType tasteTag
    ) {
        List<Recipe> candidates;

        //키워드 필터 먼저
        if (keyword != null && !keyword.isBlank()) {
            candidates = recipeRepository.findByTitleContaining(keyword);
        } else {
            candidates = recipeRepository.findAll();
        }

        //식사시간 필터
        if (mealTime != null) {
            candidates = candidates.stream()
                    .filter(r -> r.getMealTimes().contains(mealTime))
                    .toList();
        }

        //계절 필터
        if (season != null) {
            candidates = candidates.stream()
                    .filter(r -> r.getSeasons().contains(season))
                    .toList();
        }

        //맛 필터
        if (tasteTag != null) {
            candidates = candidates.stream()
                    .filter(r -> r.getTasteTags().contains(tasteTag))
                    .toList();
        }

        return candidates;
    }
}
