package devsprint.omuk.ingredient.controller;

import devsprint.omuk.ingredient.dto.IngredientRequest;
import devsprint.omuk.ingredient.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping()
    public ResponseEntity<Void> addIngredient(@RequestBody IngredientRequest ingredientRequest) {
        ingredientService.save(ingredientRequest);
        return ResponseEntity.ok().build();
    }


}
