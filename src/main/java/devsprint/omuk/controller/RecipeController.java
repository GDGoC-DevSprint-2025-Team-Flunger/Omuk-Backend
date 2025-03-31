package devsprint.omuk.controller;

import devsprint.omuk.domain.*;
import devsprint.omuk.repository.RecipeRepository;
import devsprint.omuk.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    //전체 레시피 조회
    @GetMapping("/list")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    //특정 레시피 조회
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return recipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
    }

    //랜덤 추천
    @GetMapping("/recommendation/random")
    public List<Recipe> getRandomRecommendation() {
        return recipeService.getRandomRecipes(4);
    }

    //조건 기반 추천(keyword, mealTime, season)
    @GetMapping("/recommendation")
    public List<Recipe> getRecommendation(
            @RequestParam(required = false) MealTime mealTime,
            @RequestParam(required = false) Season season,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) TasteType tasteTag
    ) {
        return recipeService.getRecommendation(mealTime, season, keyword, tasteTag);
    }
}
