package devsprint.omuk.ingredient.service;

import devsprint.omuk.ingredient.domain.Ingredient;
import devsprint.omuk.ingredient.dto.IngredientRequest;
import devsprint.omuk.ingredient.entity.IngredientEntity;
import devsprint.omuk.ingredient.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
