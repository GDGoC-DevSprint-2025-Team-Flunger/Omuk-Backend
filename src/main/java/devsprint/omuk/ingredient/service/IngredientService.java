package devsprint.omuk.ingredient.service;

import devsprint.omuk.ingredient.domain.Ingredient;
import devsprint.omuk.ingredient.dto.IngredientRequest;
import devsprint.omuk.ingredient.dto.IngredientResponse;
import devsprint.omuk.ingredient.entity.IngredientEntity;
import devsprint.omuk.ingredient.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void save(IngredientRequest ingredientRequest) {
        Ingredient ingredient = ingredientRequest.toDomain();
        ingredientRepository.save(IngredientEntity.of(ingredient));
    }

    public List<IngredientResponse> findAll(Long memberId) {
        List<IngredientEntity> ingredientEntities = ingredientRepository.findByMemberId(memberId);
        return IngredientResponse.fromEntities(ingredientEntities);
    }

    public IngredientResponse updateIngredient(Long memberId, Long id, IngredientRequest ingredientRequest) {
        Optional<IngredientEntity> optionalIngredientEntity = ingredientRepository.findByIdAndMemberId(id, memberId);

        if (optionalIngredientEntity.isPresent()) {
            IngredientEntity ingredientEntity = optionalIngredientEntity.get();
            ingredientEntity.setName(ingredientRequest.getName());
            ingredientEntity.setQuantity(ingredientRequest.getQuantity());

            ingredientRepository.save(ingredientEntity);

            return new IngredientResponse(ingredientEntity);
        } else {
            throw new RuntimeException("Ingredient not found for memberId " + memberId + " and id " + id);
        }
    }
}
