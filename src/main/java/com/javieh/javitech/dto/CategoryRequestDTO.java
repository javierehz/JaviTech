package com.javieh.javitech.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String name;

    public CategoryRequestDTO() {}

}