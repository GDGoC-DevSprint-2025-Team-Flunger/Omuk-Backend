package devsprint.omuk.recipe.repository;

import devsprint.omuk.recipe.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByMemberId(Long memberId);  // memberId로 즐겨찾기 레시피 조회
    boolean existsByMemberIdAndRecipeId(Long memberId, Long recipeId);  // 해당 멤버의 레시피가 즐겨찾기 되어 있는지 확인
}
