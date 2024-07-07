package com.dreamdaisy.item.controller;

import com.dreamdaisy.item.domain.Item;
import com.dreamdaisy.item.service.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/{id}")
    public String getItemDetails(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "item";
    }

    @GetMapping("/items/itemmodify")
    public String itemmodifyForm() {
        return "/item/itemmodify";
    }

    @PostMapping("/items/itemmodify")
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