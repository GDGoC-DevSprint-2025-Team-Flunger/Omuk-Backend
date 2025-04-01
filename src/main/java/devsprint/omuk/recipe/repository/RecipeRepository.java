package devsprint.omuk.recipe.repository;

import devsprint.omuk.recipe.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByTitleContaining(String keyword);
    List<Recipe> findByIngredientsContaining(String ingredient);
}
