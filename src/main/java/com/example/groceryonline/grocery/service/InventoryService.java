package com.example.groceryonline.grocery.service;

import com.example.groceryonline.grocery.dto.request.UpdateInventoryRequest;
import org.apache.coyote.BadRequestException;

public interface InventoryService {
    void updateInventory(UpdateInventoryRequest request) throws BadRequestException;
}
