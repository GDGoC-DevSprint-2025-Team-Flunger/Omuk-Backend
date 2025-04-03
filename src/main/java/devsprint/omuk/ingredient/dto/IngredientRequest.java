package devsprint.omuk.ingredient.dto;

import devsprint.omuk.ingredient.domain.Ingredient;
import lombok.Data;

@Data
public class IngredientRequest {
    private Long memberId;
    private String name;
    private String quantity;

    public Ingredient toDomain(){
        return new Ingredient(memberId, name, quantity);
    }

}
