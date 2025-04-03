package devsprint.omuk.recipe.controller;

import devsprint.omuk.recipe.domain.*;
import devsprint.omuk.recipe.dto.RecipeResponseDto;
import devsprint.omuk.recipe.dto.RecipeSummaryDto;
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
    public ResponseEntity<List<RecipeSummaryDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipeSummaries());
    }

    //특정 레시피 조회
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Long id) {
        RecipeResponseDto recipe = recipeService.getRecipeById(id);
        return recipe != null ? ResponseEntity.ok(recipe) : ResponseEntity.notFound().build();
    }

    //랜덤 추천
    @GetMapping("/recommendation/random")
    public ResponseEntity<List<RecipeSummaryDto>> getRandomRecommendation() {
        return ResponseEntity.ok(recipeService.getRandomRecipeSummaries(4));
    }

    //조건 기반 추천(keyword, mealTime, season, tasteTag)
    @GetMapping("/recommendation")
    public ResponseEntity<List<RecipeSummaryDto>> getRecommendation(
            @RequestParam(required = false) List<MealTime> mealTimes,
            @RequestParam(required = false) List<Season> seasons,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<TasteType> tasteTags,
            @RequestParam(required = false) List<String> selectedIngredients,
            @RequestParam(required = false) List<AllergyTag> excludeAllergies
    ) {
        return ResponseEntity.ok(
                recipeService.getRecommendation(mealTimes, seasons, keyword, tasteTags, excludeAllergies, selectedIngredients)
        );
    }

    // 즐겨찾기 추가
    @PostMapping("/favorites/add")
    public ResponseEntity<String> addFavorite(
            @RequestParam Integer memberId,
            @RequestParam Long recipeId
    ) {
        recipeService.addFavorite(memberId, recipeId);
        return ResponseEntity.ok("즐겨찾기 추가 완료");
    }

    // 즐겨찾기 레시피 조회
    @GetMapping("/favorites")
    public List<RecipeSummaryDto> getFavoriteRecipes(@RequestParam Integer memberId) {
        return recipeService.getFavoriteRecipeSummaries(memberId);
    }
}
