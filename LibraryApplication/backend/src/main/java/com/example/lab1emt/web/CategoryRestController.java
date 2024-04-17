package com.example.lab1emt.web;

import com.example.lab1emt.models.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryRestController {
    @GetMapping
    public List<Category> getAllCategories() {
        return Arrays.stream(Category.values()).collect(Collectors.toList());
    }
}