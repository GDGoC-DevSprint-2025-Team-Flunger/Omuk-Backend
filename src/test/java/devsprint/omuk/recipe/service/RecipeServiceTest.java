package devsprint.omuk.recipe.service;

import devsprint.omuk.recipe.domain.*;
import devsprint.omuk.recipe.dto.RecipeListResponseDto;
import devsprint.omuk.recipe.dto.RecipeResponseDto;
import devsprint.omuk.recipe.repository.FavoriteRepository;
import devsprint.omuk.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipe = Recipe.builder()
                .id(1L)
                .title("김치볶음밥")
                .difficulty(2)
                .cookTime(15)
                .mealTimes(List.of(MealTime.LUNCH))
                .seasons(List.of(Season.SPRING))
                .tasteTags(List.of(TasteType.SPICY, TasteType.SALTY))
                .allergyTags(List.of(AllergyTag.EGG))
                .ingredients(List.of("김치", "밥", "햄", "치즈"))
                .steps(List.of("김치를 작게 썬다.", "햄과 치즈를 준비한다."))
                .imageUrl("https://example.com/image")
                .videoUrl("https://youtube.com")
                .averageRating(4.5)
                .build();
    }

    @Test
    void testGetAllRecipes() {
        when(recipeRepository.findAll()).thenReturn(List.of(recipe));

        List<RecipeListResponseDto> result = recipeService.getAllRecipes();

        assertEquals(1, result.size());
        assertEquals("김치볶음밥", result.get(0).getTitle());
    }

    @Test
    void testGetRecipeById() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        RecipeResponseDto dto = recipeService.getRecipeById(1L);

        assertNotNull(dto);
        assertEquals("김치볶음밥", dto.getTitle());
    }

    @Test
    void testGetRandomRecipes() {
        when(recipeRepository.findAll()).thenReturn(List.of(recipe));

        List<RecipeListResponseDto> result = recipeService.getRandomRecipes(1);

        assertEquals(1, result.size());
        assertEquals("김치볶음밥", result.get(0).getTitle());
    }

    @Test
    void testGetRecommendationWithKeyword() {
        when(recipeRepository.findAll()).thenReturn(List.of(recipe));

        List<RecipeListResponseDto> result = recipeService.getRecommendation(
                null, null, "김치", null, null, null
        );

        assertEquals(1, result.size());
        assertEquals("김치볶음밥", result.get(0).getTitle());
    }

    @Test
    void testAddFavorite() {
        Long memberId = 1L;
        Long recipeId = 1L;

        when(favoriteRepository.existsByMemberIdAndRecipeId(memberId, recipeId)).thenReturn(false);

        recipeService.addFavorite(memberId, recipeId);

        verify(favoriteRepository, times(1)).save(any(Favorite.class));
    }

    @Test
    void testGetFavoriteRecipes() {
        Favorite favorite = new Favorite(1L, 1L);

        when(favoriteRepository.findByMemberId(1L)).thenReturn(List.of(favorite));
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        List<RecipeListResponseDto> result = recipeService.getFavoriteRecipeSummaries(1L);

        assertEquals(1, result.size());
        assertEquals("김치볶음밥", result.get(0).getTitle());
    }
}
