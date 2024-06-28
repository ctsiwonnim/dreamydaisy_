package com.dreamdaisy.controller;

import com.dreamdaisy.domain.Item;
import com.dreamdaisy.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/{id}")
    public String getItemDetails(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "iteam";
    }
}