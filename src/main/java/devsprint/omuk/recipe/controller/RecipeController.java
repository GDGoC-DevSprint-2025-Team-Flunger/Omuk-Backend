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
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes() {
        List<RecipeResponseDto> recipeList = recipeService.getAllRecipes().stream()
                .map(Recipe::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recipeList);
    }

    //특정 레시피 조회
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return recipe != null ? ResponseEntity.ok(recipe.toDto()) : ResponseEntity.notFound().build();
    }

    //랜덤 추천
    @GetMapping("/recommendation/random")
    public ResponseEntity<List<RecipeResponseDto>> getRandomRecommendation() {
        List<RecipeResponseDto> randomRecipes = recipeService.getRandomRecipes(4).stream()
                .map(Recipe::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(randomRecipes);
    }

    //조건 기반 추천(keyword, mealTime, season, tasteTag)
    @GetMapping("/recommendation")
    public ResponseEntity<List<RecipeResponseDto>> getRecommendation(
            @RequestParam(required = false) List<MealTime> mealTimes,
            @RequestParam(required = false) List<Season> seasons,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<TasteType> tasteTags,
            @RequestParam(required = false) List<String> selectedIngredients,
            @RequestParam(required = false) List<AllergyTag> excludeAllergies
    ) {
        List<RecipeResponseDto> recommendedRecipes = recipeService.getRecommendation(mealTimes, seasons, keyword, tasteTags, excludeAllergies, selectedIngredients).stream()
                .map(Recipe::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recommendedRecipes);
    }
}
