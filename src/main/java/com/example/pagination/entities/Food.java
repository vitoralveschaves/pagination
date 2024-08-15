package com.example.pagination.entities;

import com.example.pagination.controllers.dtos.FoodRequestDto;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    public Food() {
    }

    public Food(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Food(FoodRequestDto dto) {
        this.name = dto.name();
        this.price = dto.price();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
