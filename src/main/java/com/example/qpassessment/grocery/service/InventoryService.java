package com.example.qpassessment.grocery.service;

import com.example.qpassessment.grocery.dto.request.UpdateInventoryRequest;
import org.apache.coyote.BadRequestException;

public interface InventoryService {
    void updateInventory(UpdateInventoryRequest request) throws BadRequestException;
}
