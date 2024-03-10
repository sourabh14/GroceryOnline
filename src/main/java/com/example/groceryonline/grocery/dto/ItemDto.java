package com.example.groceryonline.grocery.dto;

import java.math.BigDecimal;

import com.example.groceryonline.grocery.entity.Item;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    @NotEmpty
    private String code;

    private String name;

    private BigDecimal price;

    public ItemDto(Item item) {
        this.code = item.getCode();
        this.name = item.getName();
        this.price = item.getPrice();
    }
}
