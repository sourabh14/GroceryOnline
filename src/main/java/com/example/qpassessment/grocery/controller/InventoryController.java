package com.example.qpassessment.grocery.controller;

import com.example.qpassessment.grocery.dto.ItemDto;
import com.example.qpassessment.grocery.dto.request.UpdateInventoryRequest;
import com.example.qpassessment.grocery.service.InventoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateInventory(@Valid @RequestBody UpdateInventoryRequest request)
            throws BadRequestException {
        inventoryService.updateInventory(request);
        return new ResponseEntity<>("Inventory updated successfully", HttpStatus.OK);
    }

}
