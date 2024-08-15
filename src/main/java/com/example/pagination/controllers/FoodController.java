package com.example.pagination.controllers;

import com.example.pagination.controllers.dtos.FoodRequestDto;
import com.example.pagination.controllers.dtos.FoodResponseDto;
import com.example.pagination.controllers.dtos.FoodResponsePaginationDto;
import com.example.pagination.entities.Food;
import com.example.pagination.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @PostMapping
    public ResponseEntity<Void> addFood(@RequestBody FoodRequestDto dto) {
        Food food = new Food(dto);
        this.foodRepository.save(food);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> listAll(@RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer pageSize) {
        if(page != null && pageSize != null) {
            Page<Food> pageList = this.foodRepository.findAll(PageRequest.of(page, pageSize));
            var foods = pageList.stream()
                    .map(p -> new FoodResponseDto(p.getId(), p.getName(), p.getPrice())).toList();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new FoodResponsePaginationDto(
                            page,
                            pageSize,
                            pageList.getTotalPages(),
                            pageList.isFirst(),
                            pageList.isLast(),
                            foods
                    ));
        }
        var foods = this.foodRepository.findAll().stream()
                .map(FoodResponseDto::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(foods);
    }
}
