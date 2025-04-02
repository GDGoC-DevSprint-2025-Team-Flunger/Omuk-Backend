package devsprint.omuk.recipe.controller;

import devsprint.omuk.recipe.domain.*;
import devsprint.omuk.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    private MockMvc mockMvc;
    private Recipe recipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

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
    void testGetAllRecipes() throws Exception {
        // Given
        when(recipeService.getAllRecipes()).thenReturn(List.of(recipe));

        // When & Then
        mockMvc.perform(get("/recipe/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("김치볶음밥"));
    }

    @Test
    void testGetRecipeById() throws Exception {
        // Given
        when(recipeService.getRecipeById(1L)).thenReturn(recipe);

        // When & Then
        mockMvc.perform(get("/recipe/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("김치볶음밥"));
    }

    @Test
    void testGetRandomRecommendation() throws Exception {
        // Given
        when(recipeService.getRandomRecipes(4)).thenReturn(List.of(recipe));

        // When & Then
        mockMvc.perform(get("/recipe/recommendation/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("김치볶음밥"));
    }

    @Test
    void testGetRecommendation() throws Exception {
        // Given
        when(recipeService.getRecommendation(
                null, null, "김치", null, null, null
        )).thenReturn(List.of(recipe));

        // When & Then
        mockMvc.perform(get("/recipe/recommendation")
                        .param("keyword", "김치"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("김치볶음밥"));
    }
}
