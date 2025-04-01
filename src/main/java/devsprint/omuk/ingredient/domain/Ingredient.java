package devsprint.omuk.ingredient.domain;

import lombok.Data;

@Data
public class Ingredient {
    private Long id;
    private Long memberId;
    private String name;
    private String quantity;

    public Ingredient(Long memberId,String name, String quantity) {
        this.memberId = memberId;
        this.name = name;
        this.quantity = quantity;
    }
}
