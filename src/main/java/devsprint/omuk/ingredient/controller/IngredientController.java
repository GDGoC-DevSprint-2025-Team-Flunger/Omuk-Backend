package devsprint.omuk.ingredient.controller;

import devsprint.omuk.ingredient.dto.IngredientRequest;
import devsprint.omuk.ingredient.dto.IngredientResponse;
import devsprint.omuk.ingredient.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{memberId}")
    public ResponseEntity<List<IngredientResponse>> getAllIngredients(@PathVariable long memberId) {
        List<IngredientResponse> ingredientResponses = ingredientService.findAll(memberId);
        return ResponseEntity.ok(ingredientResponses);  // 리스트 반환
    }


}
