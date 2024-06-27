package com.dreamdaisy.mapper;

import com.dreamdaisy.domain.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<Item> findAll();

    void save(Item item);
}
