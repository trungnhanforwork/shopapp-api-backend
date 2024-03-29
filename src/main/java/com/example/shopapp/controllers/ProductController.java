package com.example.shopapp.controllers;



import com.example.shopapp.dtos.ProductDTO;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@RequestMapping("${api.prefix}/products") // url: http://localhost:8088/api/v1/product
@RestController
public class ProductController {
    @GetMapping("")
    public ResponseEntity<String> getAllProducts(
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit
    ) {
        return ResponseEntity.ok(String.format("Get all of products with page: %d, limit: %d", page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable("id") String productId
    ){
        return ResponseEntity.status(HttpStatus.OK).body("Get product by id: " + productId);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createProduct(
            // ModelAttribute will directly transform JSON request body correspond to instance Product
            // RequestBody will return raw JSON request body.
            @Valid @ModelAttribute ProductDTO productDTO,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            List<MultipartFile> files = productDTO.getFiles();
            files = files == null ? new ArrayList<MultipartFile>() : files;
            for (MultipartFile file: files) {
                // Validate file: Size of file must be less than or equal to 10MB.
                if (file.getSize() == 0) {
                    continue;
                }
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity
                            .status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large. Please upload image < 10MB!");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/") ){
                    return ResponseEntity
                            .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("Files must be image");
                }
                // storeFile: save file to uploads directory and update thumnail in ProductDTO
                String fileName = storeFile(file);

                // Save instance into database: updating
            }
            /*
            *
            * {
                    "name": "Mouse",
                    "price": 100000,
                    "thumbnail": "",
                    "description": "Mouse uses for Mac",
                    "category_id": 2
            * } -> transform from JSON to form data in order to upload image
            *
            * */
            return ResponseEntity.ok().body("Create product: " + productDTO.toString());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(
            @Valid @PathVariable Long id,
            @RequestBody ProductDTO productDTO
    ) {
        return ResponseEntity.ok("Update product with id: " + id + productDTO.toString());
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteProduct(@Valid @PathVariable Long id) {
        return ResponseEntity.ok("Delete product with id: " + id);
    }

    private String storeFile(MultipartFile file) throws IOException {
        // Get original file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Concatinate with uuid avoid conflict other file having the same name
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

        java.nio.file.Path uploadDirPath = Paths.get("uploads");
        if (!Files.exists(uploadDirPath)) {
            Files.createDirectories(uploadDirPath);
        }

        java.nio.file.Path destination = Paths.get(uploadDirPath.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}
