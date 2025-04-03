package devsprint.omuk.recipe.service;

import devsprint.omuk.recipe.domain.*;
import devsprint.omuk.recipe.dto.RecipeListResponseDto;
import devsprint.omuk.recipe.dto.RecipeResponseDto;
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

    public List<RecipeListResponseDto> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(r -> new RecipeListResponseDto(r.getId(), r.getTitle(), r.getImageUrl()))
                .collect(Collectors.toList());
    }

    public RecipeResponseDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        return recipe != null ? recipe.toDto() : null;    }

    public List<RecipeListResponseDto> getRecommendation(List<MealTime> mealTimes, List<Season> seasons, String keyword, List<TasteType> tasteTags,List<String> selectedIngredient, List<AllergyTag> excludeAllergies) {
        List<Recipe> results = recipeRepository.findAll();

        //키워드 조건 필터 먼저
        if (keyword != null && !keyword.isBlank()) {
            results = results.stream()
                    .filter(r -> r.getTitle().contains(keyword))
                    .collect(Collectors.toList());
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

        if (selectedIngredient != null && !selectedIngredient.isEmpty()) {
            results = results.stream()
                    .filter(r-> !Collections.disjoint(r.getIngredients(),selectedIngredient))
                    .collect(Collectors.toList());
        }

        //알러지 제외 조건 필터
        if (excludeAllergies != null && !excludeAllergies.isEmpty()) {
            results = results.stream()
                    .filter(r -> Collections.disjoint(r.getAllergyTags(), excludeAllergies))
                    .collect(Collectors.toList());
        }

        return results.stream()
                .map(r -> new RecipeListResponseDto(r.getId(), r.getTitle(), r.getImageUrl()))
                .collect(Collectors.toList());
    }

    public List<RecipeListResponseDto> getRandomRecipes(int count) {
        List<Recipe> all = recipeRepository.findAll();
        Collections.shuffle(all);
        return all.stream().limit(count)
                .map(r -> new RecipeListResponseDto(r.getId(), r.getTitle(), r.getImageUrl()))
                .collect(Collectors.toList());
    }

    // 즐겨찾기 추가
    @Transactional
    public void addFavorite(Long memberId, Long recipeId) {
        // 이미 즐겨찾기 된 레시피는 다시 추가하지 않음
        if (!favoriteRepository.existsByMemberIdAndRecipeId(memberId, recipeId)) {
            favoriteRepository.save(new Favorite(memberId, recipeId));
        }
    }

    // 즐겨찾기 레시피 조회
    public List<RecipeListResponseDto> getFavoriteRecipeSummaries(Long memberId) {
        return favoriteRepository.findByMemberId(memberId).stream()
                .map(fav -> recipeRepository.findById(fav.getRecipeId()).orElse(null))
                .filter(Objects::nonNull)
                .map(r -> new RecipeListResponseDto(r.getId(), r.getTitle(), r.getImageUrl())) // ✅ RecipeListResponseDto 사용
                .collect(Collectors.toList());
    }

}
