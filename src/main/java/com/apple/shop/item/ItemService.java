package com.apple.shop.item;

import com.apple.shop.member.Member;
import com.apple.shop.member.MemberRepository;
import com.apple.shop.sales.Sales;
import com.apple.shop.sales.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final SalesRepository salesRepository;

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

    public Boolean orderItem(long id, Authentication auth){
        if(id != 0 && auth != null ){
            Item item = itemRepository.findById(id).orElse(null);
            Member member = memberRepository.findByUsername(auth.getName()).orElse(null);
            Sales data = new Sales();
            data.setItemName(item.getTitle());
            data.setPrice(item.getPrice());
            data.setCount(1);
            data.setMemberId(member.getId());
            salesRepository.save(data);
            return true;
        }
        return false;
    }

}
