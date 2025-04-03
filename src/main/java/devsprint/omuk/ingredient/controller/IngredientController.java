package devsprint.omuk.ingredient.controller;

import devsprint.omuk.ingredient.dto.IngredientRequest;
import devsprint.omuk.ingredient.dto.IngredientResponse;
import devsprint.omuk.ingredient.service.IngredientService;
//import devsprint.omuk.ingredient.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;
//    private final S3Service s3Service;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
//        this.s3Service = s3Service;
    }

    @PostMapping()
    public ResponseEntity<Void> addIngredient(@RequestBody IngredientRequest ingredientRequest) {
        ingredientService.save(ingredientRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<IngredientResponse>> getAllIngredients(@PathVariable long memberId) {
        List<IngredientResponse> ingredientResponses = ingredientService.findAll(memberId);
        return ResponseEntity.ok(ingredientResponses);
    }

    @PutMapping("/{memberId}/{id}")
    public ResponseEntity<IngredientResponse> updateIngredient(
            @PathVariable Long memberId,
            @PathVariable Long id,
            @RequestBody IngredientRequest ingredientRequest) {

        IngredientResponse updatedIngredient = ingredientService.updateIngredient(memberId, id, ingredientRequest);
        return ResponseEntity.ok(updatedIngredient);
    }

    @DeleteMapping("/{memberId}/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long memberId, @PathVariable Long id) {
        ingredientService.deleteIngredient(memberId, id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            String fileUrl = s3Service.uploadFile(file);
//            return ResponseEntity.ok("파일 업로드 성공: " + fileUrl);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
//        }
//    }

}
