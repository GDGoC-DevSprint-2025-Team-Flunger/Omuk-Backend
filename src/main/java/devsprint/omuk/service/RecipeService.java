package devsprint.omuk.service;

import devsprint.omuk.domain.*;
import devsprint.omuk.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public List<Recipe> getRecommendation(MealTime mealTime, Season season, String keyword, TasteType tasteTag) {
        List<Recipe> candidates;

        //키워드 조건 필터 먼저
        if (keyword != null && !keyword.isBlank()) {
            candidates = recipeRepository.findByTitleContaining(keyword);
        } else {
            candidates = recipeRepository.findAll();
        }

        //식사시간 조건 필터
        if (mealTime != null) {
            candidates = candidates.stream()
                    .filter(r -> r.getMealTimes().contains(mealTime))
                    .toList();
        }

        //계절 조건 필터
        if (season != null) {
            candidates = candidates.stream()
                    .filter(r -> r.getSeasons().contains(season))
                    .toList();
        }

        //맛 조건 필터
        if (tasteTag != null) {
            candidates = candidates.stream()
                    .filter(r -> r.getTasteTags().contains(tasteTag))
                    .toList();
        }

        return candidates;
    }

    public List<Recipe> getRandomRecipes(int count) {
        List<Recipe> all = recipeRepository.findAll();
        Collections.shuffle(all);
        return all.stream().limit(count).toList();
    }
}
