package com.javieh.javitech.service;

import com.javieh.javitech.dto.CategoryDTO;
import com.javieh.javitech.dto.CategoryRequestDTO;
import com.javieh.javitech.entity.Category;
import com.javieh.javitech.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> findAll(){
        return categoryRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public CategoryDTO findById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada."));
        return toDto(category);
    }

    public CategoryDTO create(CategoryRequestDTO request){
        Category category = new Category();
        category.setName(request.getName());
        Category saved = categoryRepository.save(category);
        return toDto(saved);
    }

    private CategoryDTO toDto(Category category){
        return new CategoryDTO(category.getId(), category.getName());
    }


}
