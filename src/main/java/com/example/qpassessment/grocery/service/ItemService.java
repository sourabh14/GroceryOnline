package com.example.qpassessment.grocery.service;

import java.util.List;

import com.example.qpassessment.grocery.dto.ItemDto;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto);

    ItemDto getItemByCode(String code);

    List<ItemDto> getAllItems();

    ItemDto updateItem(ItemDto itemDto);

    void deleteItemByCode(String code);
}
