package devsprint.omuk.repository;

import devsprint.omuk.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setup() {
        recipeRepository.deleteAll();

        Recipe recipe = Recipe.builder()
                .title("김치볶음밥")
                .difficulty(2)
                .cookTime(15)
                .mealTimes(List.of(MealTime.LUNCH))
                .seasons(List.of(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
                .tasteTags(List.of(TasteType.SPICY, TasteType.SALTY))
                .allergyTags(List.of("계란"))
                .ingredients(List.of("김치", "밥", "햄", "치즈"))
                .steps(List.of(
                        "김치를 작게 썬다.",
                        "햄과 치즈를 준비한다.",
                        "팬에 김치를 볶는다.",
                        "밥과 햄을 넣고 함께 볶는다.",
                        "치즈를 얹고 섞은 뒤 완성한다."
                ))
                .substitutes(Map.of("김치", "깍두기"))
                .imageUrl("http://image.com")
                .videoUrl("http://youtube.com")
                .averageRating(4.5)
                .build();

        recipeRepository.save(recipe);
    }

    @Test
    @DisplayName("식사시간 조건 검색이 잘 되어야 한다")
    void findByMealTime() {
        List<Recipe> results = recipeRepository.findByMealTimesContaining(MealTime.LUNCH);
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).isEqualTo("김치볶음밥");
    }

    @Test
    @DisplayName("계절 조건 검색이 잘 되어야 한다")
    void findBySeason() {
        List<Recipe> results = recipeRepository.findBySeasonsContaining(Season.SPRING);
        assertThat(results).hasSize(1);
    }

    @Test
    @DisplayName("식사시간과 계절을 모두 만족하는 레시피가 조회되어야 한다")
    void findByMealTimeAndSeason() {
        List<Recipe> results = recipeRepository
                .findByMealTimesContainingAndSeasonsContaining(MealTime.LUNCH, Season.SUMMER);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).isEqualTo("김치볶음밥");
    }

    @Test
    @DisplayName("제목 키워드 검색이 잘 되어야 한다")
    void findByTitleContaining() {
        List<Recipe> results = recipeRepository.findByTitleContaining("김치");
        assertThat(results).hasSize(1);
    }
}
