package com.dreamdaisy.service;

import com.dreamdaisy.domain.Item;
import com.dreamdaisy.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemMapper mapper;

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return mapper.findAll();
    }
}
