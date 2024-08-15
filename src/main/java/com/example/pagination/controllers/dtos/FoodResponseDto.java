package com.example.pagination.controllers.dtos;

import com.example.pagination.entities.Food;

public record FoodResponseDto(Long id, String name, Double price) {
    public FoodResponseDto(Food food) {
        this(food.getId(), food.getName(), food.getPrice());
    }
}
