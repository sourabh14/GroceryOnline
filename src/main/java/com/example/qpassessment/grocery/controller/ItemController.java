package com.example.qpassessment.grocery.controller;

import java.util.List;

import com.example.qpassessment.grocery.dto.ItemDto;
import com.example.qpassessment.grocery.service.ItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        ItemDto item = itemService.createItem(itemDto);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ItemDto> getItemByCode(@Valid @PathVariable("code") String code) {
        ItemDto item = itemService.getItemByCode(code);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> itemDtos = itemService.getAllItems();
        return new ResponseEntity<>(itemDtos, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ItemDto> updateItem(@Valid @RequestBody ItemDto itemDto) {
        ItemDto updatedItemDto = itemService.updateItem(itemDto);
        return new ResponseEntity<>(updatedItemDto, HttpStatus.OK);
    }

    @DeleteMapping("{code}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteItemByCode(@PathVariable("code") String code) {
        itemService.deleteItemByCode(code);
        return new ResponseEntity<>("Item successfully deleted!", HttpStatus.OK);
    }

}
