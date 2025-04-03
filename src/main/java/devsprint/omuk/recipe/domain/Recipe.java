package devsprint.omuk.recipe.domain;

import devsprint.omuk.recipe.dto.RecipeResponseDto;
import devsprint.omuk.recipe.dto.RecipeSummaryDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Min(1)
    @Max(5)
    private int difficulty;

    private int cookTime;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MealTime> mealTimes;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Season> seasons;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<TasteType> tasteTags;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<AllergyTag> allergyTags;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> ingredients;

    @ElementCollection
    @OrderColumn  //순서 보장
    @CollectionTable(name = "recipe_steps", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> steps;

    @ElementCollection
    @CollectionTable(name = "substitute_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @MapKeyColumn(name = "original") //key: 원래 재료
    @Column(name = "substitute") //value: 대체 재료
    private Map<String, String> substitutes;

    private String imageUrl;
    private String videoUrl;
    private Double averageRating;

    public RecipeResponseDto toDto() {
        return RecipeResponseDto.builder()
                .id(this.id)
                .title(this.title)
                .difficulty(this.difficulty)
                .cookTime(this.cookTime)
                .mealTimes(this.mealTimes)
                .seasons(this.seasons)
                .tasteTags(this.tasteTags)
                .allergyTags(this.allergyTags)
                .ingredients(this.ingredients)
                .steps(this.steps)
                .substitutes(this.substitutes)
                .imageUrl(this.imageUrl)
                .videoUrl(this.videoUrl)
                .averageRating(this.averageRating)
                .build();
    }

    public RecipeSummaryDto toSummaryDto() {
        return RecipeSummaryDto.builder()
                .id(this.id)
                .title(this.title)
                .imageUrl(this.imageUrl)
                .build();
    }
}
