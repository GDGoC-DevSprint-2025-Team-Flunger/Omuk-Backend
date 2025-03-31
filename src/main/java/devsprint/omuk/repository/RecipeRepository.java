package devsprint.omuk.repository;

import devsprint.omuk.domain.MealTime;
import devsprint.omuk.domain.Recipe;
import devsprint.omuk.domain.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByTitleContaining(String keyword);
}
