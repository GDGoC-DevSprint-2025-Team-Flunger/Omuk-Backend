package devsprint.omuk.recipe.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeListResponseDto {
    private Long id;
    private String title;
    private String imageUrl;

    public RecipeListResponseDto(Long id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }
}
