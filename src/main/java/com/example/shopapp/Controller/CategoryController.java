package com.example.shopapp.Controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories") // http://localhost:8088/api/v1/categories
public class CategoryController {

    @GetMapping("")
    public ResponseEntity<String> getAllCategories(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit
    ) {
        return ResponseEntity.ok("Get all of categories");
    }

    @PostMapping("")
    public ResponseEntity<String> createCategory() {
        return ResponseEntity.ok("Create category");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id) {
        return ResponseEntity.ok("Update category with id: " + id);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok("Delete category with id: " + id);
    }

}
