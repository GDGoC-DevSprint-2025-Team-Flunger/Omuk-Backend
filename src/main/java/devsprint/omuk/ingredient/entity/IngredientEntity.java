package devsprint.omuk.ingredient.entity;

import devsprint.omuk.ingredient.domain.Ingredient;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ingredient")
@Data
public class IngredientEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column
    private String quantity;

    public IngredientEntity() {}
    public IngredientEntity(Long memberId, String name, String quantity) {
        this.memberId = memberId;
        this.name = name;
        this.quantity = quantity;
    }

    public static IngredientEntity of(Ingredient ingredient) {
        return new IngredientEntity(ingredient.getMemberId(),ingredient.getName(), ingredient.getQuantity());
    }
}
