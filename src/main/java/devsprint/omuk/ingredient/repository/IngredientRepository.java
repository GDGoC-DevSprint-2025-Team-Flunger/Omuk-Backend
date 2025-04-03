package devsprint.omuk.ingredient.repository;

import devsprint.omuk.ingredient.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    List<IngredientEntity> findByMemberId(Long memberId);
    Optional<IngredientEntity> findByIdAndMemberId(Long id, Long memberId);

}
