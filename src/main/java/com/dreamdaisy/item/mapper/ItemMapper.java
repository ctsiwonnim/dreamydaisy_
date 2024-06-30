package com.dreamdaisy.item.mapper;

import com.dreamdaisy.item.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    List<Item> findAll();

    void save(Item item);

    Optional<Item> findById(@Param("id") Long id);

    void update(Item item);
}
