package com.apple.shop.item;

import com.apple.shop.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// 1. repository 만들기
public interface ItemRepository extends JpaRepository<Item, Long> {


}

