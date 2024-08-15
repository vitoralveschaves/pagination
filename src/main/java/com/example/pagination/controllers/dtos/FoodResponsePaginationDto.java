package com.example.pagination.controllers.dtos;

import java.util.List;

public record FoodResponsePaginationDto(
        Integer page,
        Integer pageSize,
        Integer totalPages,
        Boolean first,
        Boolean last,
        List<FoodResponseDto> foods) {
}
