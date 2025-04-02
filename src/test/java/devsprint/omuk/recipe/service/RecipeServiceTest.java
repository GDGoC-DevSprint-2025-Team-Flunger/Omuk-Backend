package devsprint.omuk.recipe.service;

import devsprint.omuk.recipe.domain.*;
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
                .steps(List.of("김치를 작게 썬다.", "햄과 치즈를 준비한다.", "팬에 김치를 볶는다.", "밥과 햄을 넣고 함께 볶는다.", "치즈를 얹고 섞은 뒤 완성한다."))
                .imageUrl("https://example.com/image")
                .videoUrl("https://youtube.com")
                .averageRating(4.5)
                .build();
    }

    @Test
    void testGetAllRecipes() {
        // Given
        when(recipeRepository.findAll()).thenReturn(List.of(recipe));

        // When
        List<Recipe> recipes = recipeService.getAllRecipes();

        // Then
        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals("김치볶음밥", recipes.get(0).getTitle());
    }

    @Test
    void testGetRecipeById() {
        // Given
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        // When
        Recipe result = recipeService.getRecipeById(1L);

        // Then
        assertNotNull(result);
        assertEquals("김치볶음밥", result.getTitle());
    }

    @Test
    void testGetRecommendationWithKeyword() {
        // Given
        when(recipeRepository.findByTitleContaining("김치")).thenReturn(List.of(recipe));

        // When
        List<Recipe> recipes = recipeService.getRecommendation(
                null, null, "김치", null, null, null);

        // Then
        assertNotNull(recipes);
        assertEquals(1, recipes.size());
        assertEquals("김치볶음밥", recipes.get(0).getTitle());
    }

    @Test
    void testGetRandomRecipes() {
        // Given
        when(recipeRepository.findAll()).thenReturn(List.of(recipe));

        // When
        List<Recipe> randomRecipes = recipeService.getRandomRecipes(1);

        // Then
        assertNotNull(randomRecipes);
        assertEquals(1, randomRecipes.size());
    }
}
