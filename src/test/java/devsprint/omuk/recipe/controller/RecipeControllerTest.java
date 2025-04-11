//package devsprint.omuk.recipe.controller;
//
//import devsprint.omuk.recipe.domain.*;
//import devsprint.omuk.recipe.dto.RecipeResponseDto;
//import devsprint.omuk.recipe.service.RecipeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//public class RecipeControllerTest {
//
//    @Mock
//    private RecipeService recipeService;
//
//    @InjectMocks
//    private RecipeController recipeController;
//
//    private MockMvc mockMvc;
//    private RecipeSummaryDto summaryDto;
//    private RecipeResponseDto responseDto;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
//
//        summaryDto = RecipeSummaryDto.builder()
//                .id(1L)
//                .title("김치볶음밥")
//                .imageUrl("https://example.com/image")
//                .build();
//
//        responseDto = RecipeResponseDto.builder()
//                .id(1L)
//                .title("김치볶음밥")
//                .difficulty(2)
//                .cookTime(15)
//                .mealTimes(List.of(MealTime.LUNCH))
//                .seasons(List.of(Season.SPRING))
//                .tasteTags(List.of(TasteType.SPICY, TasteType.SALTY))
//                .allergyTags(List.of(AllergyTag.EGG))
//                .ingredients(List.of("김치", "밥"))
//                .steps(List.of("김치를 썬다", "밥을 볶는다"))
//                .substitutes(null)
//                .imageUrl("https://example.com/image")
//                .videoUrl("https://youtube.com")
//                .averageRating(4.5)
//                .build();
//    }
//
//    @Test
//    void testGetAllRecipes() throws Exception {
//        when(recipeService.getAllRecipeSummaries()).thenReturn(List.of(summaryDto));
//
//        mockMvc.perform(get("/recipe/list"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value("김치볶음밥"));
//    }
//
//    @Test
//    void testGetRecipeById() throws Exception {
//        when(recipeService.getRecipeById(1L)).thenReturn(responseDto);
//
//        mockMvc.perform(get("/recipe/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("김치볶음밥"));
//    }
//
//    @Test
//    void testGetRandomRecommendation() throws Exception {
//        when(recipeService.getRandomRecipeSummaries(4)).thenReturn(List.of(summaryDto));
//
//        mockMvc.perform(get("/recipe/recommendation/random"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value("김치볶음밥"));
//    }
//
//    @Test
//    void testGetRecommendationWithKeyword() throws Exception {
//        when(recipeService.getRecommendation(null, null, "김치", null, null, null))
//                .thenReturn(List.of(summaryDto));
//
//        mockMvc.perform(get("/recipe/recommendation")
//                        .param("keyword", "김치"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value("김치볶음밥"));
//    }
//
//    @Test
//    void testGetFavoriteRecipes() throws Exception {
//        when(recipeService.getFavoriteRecipeSummaries(1)).thenReturn(List.of(summaryDto));
//
//        mockMvc.perform(get("/recipe/favorites")
//                        .param("memberId", "1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value("김치볶음밥"));
//    }
//}
