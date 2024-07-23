package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item>  findItem(){
        //JPA로 데이터를 입출력하려면 3-step
        //1. repositoty 만들기
        //2. 원하는 클래스에 repository 등록
        //3. repository.입출력()문법 쓰기
        List<Item> result = itemRepository.findAll();
        return result;

    }

    public Optional<Item> detailItem(long id){
        Optional<Item> result = itemRepository.findById(id);
        return result;
    }

    public Item editItem(Item item){
       Item result = itemRepository.save(item);

        return result;
    }

}
