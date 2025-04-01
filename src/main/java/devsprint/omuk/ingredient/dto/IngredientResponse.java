package devsprint.omuk.ingredient.dto;

import devsprint.omuk.ingredient.entity.IngredientEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class IngredientResponse {
    private Long id;
    private Long memberId;
    private String name;
    private String quantity;

    public IngredientResponse(IngredientEntity ingredientEntity ) {
        this.id = ingredientEntity.getId();
        this.memberId = ingredientEntity.getMemberId();
        this.name = ingredientEntity.getName();
        this.quantity = ingredientEntity.getQuantity();
    }

    public static List<IngredientResponse> fromEntities(List<IngredientEntity> ingredientEntities) {
        return ingredientEntities.stream()
                .map(IngredientResponse::new)
                .collect(Collectors.toList());
    }
}
