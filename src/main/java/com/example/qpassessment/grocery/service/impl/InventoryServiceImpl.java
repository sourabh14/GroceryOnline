package com.example.qpassessment.grocery.service.impl;

import com.example.qpassessment.grocery.dto.request.InventoryUpdateType;
import com.example.qpassessment.grocery.dto.request.UpdateInventoryRequest;
import com.example.qpassessment.grocery.entity.Inventory;
import com.example.qpassessment.grocery.entity.Item;
import com.example.qpassessment.grocery.exception.ApplicationException;
import com.example.qpassessment.grocery.repository.ItemRepository;
import com.example.qpassessment.grocery.service.InventoryService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final ItemRepository itemRepository;

    @Override
    public void updateInventory(UpdateInventoryRequest request) throws BadRequestException {
        Item item = itemRepository.findByCode(request.getItemCode())
                .orElseThrow(() -> new ApplicationException("Item not found: " + request.getItemCode()));
        Inventory inventory = item.getInventory();
        switch (InventoryUpdateType.valueOf(request.getUpdateType())) {
            case ADD:
                inventory.setQuantity(inventory.getQuantity() + request.getQuantity());
                break;
            case REMOVE:
                if (request.getQuantity() > inventory.getQuantity()) {
                    throw new ApplicationException("Quantity to be removed is greater than available quantity");
                }
                inventory.setQuantity(inventory.getQuantity() - request.getQuantity());
                break;
            case REPLACE:
                inventory.setQuantity(request.getQuantity());
                break;
        }
        itemRepository.save(item);
    }

}
