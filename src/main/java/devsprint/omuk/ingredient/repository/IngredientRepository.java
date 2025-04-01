package devsprint.omuk.ingredient.repository;

import devsprint.omuk.ingredient.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    List<IngredientEntity> findByMemberId(Long memberId);
}
