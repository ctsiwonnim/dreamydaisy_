package com.dreamdaisy.mapper;

import com.dreamdaisy.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    List<Item> findAll();

    void save(Item item);

    Optional<Item> findById(@Param("id") Long id);
}
