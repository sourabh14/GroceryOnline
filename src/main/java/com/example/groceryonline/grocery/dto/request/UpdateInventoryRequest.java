package com.example.groceryonline.grocery.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInventoryRequest {
    @NotEmpty
    private String itemCode;
    @NotEmpty
    private String updateType;
    @Min(value = 0)
    private Integer quantity;
}
