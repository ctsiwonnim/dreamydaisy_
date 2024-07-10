package com.dreamdaisy.item.controller;

import com.dreamdaisy.item.domain.Item;
import com.dreamdaisy.item.dto.SaveItemDto;
import com.dreamdaisy.item.service.ItemService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/addItem")
    public String addItemForm(Model model) {
        model.addAttribute("saveItemDto", new SaveItemDto());
        return "/item/add-item";
    }

    @PostMapping("/addItem")
    public String addItem(
            @Valid SaveItemDto saveItemDto,
            BindingResult bindingResult) {
        Item item = Item.builder()
                .name(saveItemDto.getName())
                .price(saveItemDto.getPrice())
                .itemscript(saveItemDto.getDescription())
                .build();

        itemService.save(item);
        return "redirect:/";
    }

    @GetMapping("/items/{id}")
    public String getItemDetails(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "/item/item";
    }

    @GetMapping("/items/{itemId}/update")
    public String itemmodifyForm() {
        return "itme-update";
    }

    @PostMapping("/items/{itemId}update")
    public String mypageModify(
            @RequestParam Long id,
            @RequestParam int price,
            @RequestParam String itemscript,
            @RequestParam String name,
            HttpSession session) {

        Item item = Item.builder()
                .id(id)
                .name(name)
                .price(price)
                .itemscript(itemscript)
                .build();

        itemService.update(item);
        session.setAttribute("item", item);
        return "redirect:/";

    }
}