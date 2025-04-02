package devsprint.omuk.recipe.service;

import devsprint.omuk.recipe.domain.*;
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

        List<RecipeResponseDto> recipes = recipeService.getAllRecipes();

        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals("김치볶음밥", recipes.get(0).getTitle());
    }

    @Test
    void testGetRecipeById() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        RecipeResponseDto result = recipeService.getRecipeById(1L);

        assertNotNull(result);
        assertEquals("김치볶음밥", result.getTitle());
    }

    @Test
    void testGetRecommendationWithKeyword() {
        when(recipeRepository.findByTitleContaining("김치")).thenReturn(List.of(recipe));

        List<RecipeResponseDto> recipes = recipeService.getRecommendation(
                null, null, "김치", null, null, null);

        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals("김치볶음밥", recipes.get(0).getTitle());
    }

    @Test
    void testGetRandomRecipes() {
        when(recipeRepository.findAll()).thenReturn(List.of(recipe));

        List<RecipeResponseDto> randomRecipes = recipeService.getRandomRecipes(1);

        assertNotNull(randomRecipes);
        assertEquals(1, randomRecipes.size());
    }

    @Test
    void testGetFavoriteRecipes() {
        Favorite favorite = Favorite.builder()
                .id(1L)
                .memberId(100)
                .recipeId(1L)
                .build();

        when(favoriteRepository.findByMemberId(100)).thenReturn(List.of(favorite));
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        List<RecipeResponseDto> favorites = recipeService.getFavoriteRecipes(100);

        assertNotNull(favorites);
        assertEquals(1, favorites.size());
        assertEquals("김치볶음밥", favorites.get(0).getTitle());
    }
}
