package com.example.groceryonline.grocery.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.groceryonline.grocery.dto.ItemDto;
import com.example.groceryonline.grocery.entity.Item;
import com.example.groceryonline.grocery.exception.ApplicationException;
import com.example.groceryonline.grocery.repository.ItemRepository;
import com.example.groceryonline.grocery.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        if (itemRepository.findByCode(itemDto.getCode()).isPresent()) {
            throw new ApplicationException("Item with code " + itemDto.getCode() + " already exists");
        }
        return new ItemDto(itemRepository.save(new Item(itemDto)));
    }

    @Override
    public ItemDto getItemByCode(String code) {
        Item item = itemRepository.findByCode(code)
                .orElseThrow(() -> new ApplicationException("Item not found: " + code));
        return new ItemDto(item);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(ItemDto::new)
                .collect(Collectors.toList());
    }

    public Item checkIfItemExists(String code) {
        return itemRepository.findByCode(code)
                .orElseThrow(() -> new ApplicationException("Item not found: " + code));
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto) {
        Item item = checkIfItemExists(itemDto.getCode());
        copyFromItemDto(item, itemDto);
        return new ItemDto(itemRepository.save(item));
    }

    private void copyFromItemDto(Item item, ItemDto itemDto) {
        item.setName(itemDto.getName() == null ? item.getName() : itemDto.getName());
        item.setPrice(itemDto.getPrice() == null ? item.getPrice() : itemDto.getPrice());
    }

    @Override
    @Transactional
    public void deleteItemByCode(String code) {
        checkIfItemExists(code);
        itemRepository.deleteByCode(code);
    }

}
