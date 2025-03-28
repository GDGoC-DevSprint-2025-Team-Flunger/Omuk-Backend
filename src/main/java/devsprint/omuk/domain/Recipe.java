package devsprint.omuk.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Min(1)
    @Max(5)
    private int difficulty;

    private int cookTime;

    @Enumerated(EnumType.STRING)
    private MealTime mealTime;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<TasteType> tasteTags;

    @ElementCollection
    private List<String> allergyTags;

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
}
