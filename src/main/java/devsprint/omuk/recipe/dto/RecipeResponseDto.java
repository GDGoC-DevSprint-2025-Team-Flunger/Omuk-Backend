package devsprint.omuk.recipe.dto;

import devsprint.omuk.recipe.domain.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class RecipeResponseDto {
    private Long id;
    private String title;
    private int difficulty;
    private int cookTime;
    private List<MealTime> mealTimes;
    private List<Season> seasons;
    private List<TasteType> tasteTags;
    private List<AllergyTag> allergyTags;
    private List<String> ingredients;
    private List<String> steps;
    private Map<String, String> substitutes;
    private String imageUrl;
    private String videoUrl;
    private Double averageRating;
}
