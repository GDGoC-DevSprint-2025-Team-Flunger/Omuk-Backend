package devsprint.omuk.recipe.controller;

import devsprint.omuk.recipe.domain.*;
import devsprint.omuk.recipe.dto.RecipeResponseDto;
import devsprint.omuk.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    //전체 레시피 조회
    @GetMapping("/list")
    public List<RecipeResponseDto> getAllRecipes() {
        return recipeService.getAllRecipes().stream()
                .map(Recipe::toDto)
                .collect(Collectors.toList());
    }

    //특정 레시피 조회
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return recipe != null ? ResponseEntity.ok(recipe.toDto()) : ResponseEntity.notFound().build();
    }

    //랜덤 추천
    @GetMapping("/recommendation/random")
    public List<RecipeResponseDto> getRandomRecommendation() {
        return recipeService.getRandomRecipes(4).stream()
                .map(Recipe::toDto)
                .collect(Collectors.toList());
    }

    //조건 기반 추천(keyword, mealTime, season, tasteTag)
    @GetMapping("/recommendation")
    public List<RecipeResponseDto> getRecommendation(
            @RequestParam(required = false) MealTime mealTime,
            @RequestParam(required = false) Season season,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) TasteType tasteTag
    ) {
        return recipeService.getRecommendation(mealTime, season, keyword, tasteTag).stream()
                .map(Recipe::toDto)
                .collect(Collectors.toList());
    }
}
