package com.dreamdaisy.service;

import com.dreamdaisy.domain.Item;
import com.dreamdaisy.domain.Member;
import com.dreamdaisy.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemMapper itemMapper;

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemMapper.findAll();
    }

    @Transactional
    public void save(Item item) {
        itemMapper.save(item);
    }

    @Transactional(readOnly = true)
    public Item findById(Long id) {
        return itemMapper.findById(id).orElse(null);
    }

    @Transactional
    public void update(Item item) {
        itemMapper.update(item);
    }
}
