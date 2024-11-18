package com.apple.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// 1. repository 만들기
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllBy(Pageable page);
}

