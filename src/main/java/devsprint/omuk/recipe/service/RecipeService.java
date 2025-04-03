package devsprint.omuk.recipe.service;

import devsprint.omuk.recipe.domain.*;
import devsprint.omuk.recipe.dto.RecipeResponseDto;
import devsprint.omuk.recipe.dto.RecipeSummaryDto;
import devsprint.omuk.recipe.repository.FavoriteRepository;
import devsprint.omuk.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final FavoriteRepository favoriteRepository;

    public List<RecipeSummaryDto> getAllRecipeSummaries() {
        return recipeRepository.findAll().stream()
                .map(Recipe::toSummaryDto)
                .collect(Collectors.toList());
    }

    public RecipeResponseDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        return recipe != null ? recipe.toDto() : null;    }

    public List<RecipeSummaryDto> getRecommendation(
            List<MealTime> mealTimes,
            List<Season> seasons,
            String keyword,
            List<TasteType> tasteTags,
            List<AllergyTag> excludeAllergies,
            List<String> selectedIngredients
    ) {
        List<Recipe> results;

        //키워드 조건 필터 먼저
        if (keyword != null && !keyword.isBlank()) {
            results = recipeRepository.findByTitleContaining(keyword);
        } else {
            results = recipeRepository.findAll();
        }

        //식사시간 조건 필터
        if (mealTimes != null && !mealTimes.isEmpty()) {
            results = results.stream()
                    .filter(r -> !Collections.disjoint(r.getMealTimes(), mealTimes))
                    .collect(Collectors.toList());
        }

        //계절 조건 필터
        if (seasons != null && !seasons.isEmpty()) {
            results = results.stream()
                    .filter(r -> !Collections.disjoint(r.getSeasons(), seasons))
                    .collect(Collectors.toList());
        }

        //맛 조건 필터
        if (tasteTags != null && !tasteTags.isEmpty()) {
            results = results.stream()
                    .filter(r -> !Collections.disjoint(r.getTasteTags(), tasteTags))
                    .collect(Collectors.toList());
        }

        //알러지 제외 조건 필터
        if (excludeAllergies != null && !excludeAllergies.isEmpty()) {
            results = results.stream()
                    .filter(r -> Collections.disjoint(r.getAllergyTags(), excludeAllergies))
                    .collect(Collectors.toList());
        }

        //재료 기반 필터
        if (selectedIngredients != null && !selectedIngredients.isEmpty()) {
            results = results.stream()
                    .filter(r -> !Collections.disjoint(r.getIngredients(), selectedIngredients))
                    .collect(Collectors.toList());
        }

        return results.stream().map(Recipe::toSummaryDto).collect(Collectors.toList());    }

    public List<RecipeSummaryDto> getRandomRecipeSummaries(int count) {
        List<Recipe> all = recipeRepository.findAll();
        Collections.shuffle(all);
        return all.stream().limit(count)
                .map(Recipe::toSummaryDto)
                .collect(Collectors.toList());
    }

    // 즐겨찾기 추가
    @Transactional
    public void addFavorite(Integer memberId, Long recipeId) {
        // 이미 즐겨찾기 된 레시피는 다시 추가하지 않음
        if (!favoriteRepository.existsByMemberIdAndRecipeId(memberId, recipeId)) {
            Favorite favorite = new Favorite(memberId, recipeId);
            favoriteRepository.save(favorite);
        }
    }

    // 즐겨찾기 레시피 조회
    public List<RecipeSummaryDto> getFavoriteRecipeSummaries(Integer memberId) {
        List<Favorite> favorites = favoriteRepository.findByMemberId(memberId);
        return favorites.stream()
                .map(fav -> recipeRepository.findById(fav.getRecipeId()).orElse(null))
                .filter(Objects::nonNull)
                .map(Recipe::toSummaryDto)
                .collect(Collectors.toList());
    }

}
