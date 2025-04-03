package devsprint.omuk.recipe.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecipeSummaryDto {
    private Long id;
    private String title;
    private String imageUrl;
}