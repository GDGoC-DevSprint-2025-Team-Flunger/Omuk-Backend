package devsprint.omuk.recipe.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;  // memberId 저장

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;  // recipeId만 저장 (Integer 타입)

    public Favorite(Integer memberId, Long recipeId) {
        this.memberId = memberId;
        this.recipeId = recipeId;
    }
}
