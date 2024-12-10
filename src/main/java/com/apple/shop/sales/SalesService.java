package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    public void postOrder(String title, Integer price, Integer count, Authentication auth){
        Sales sales = new Sales();
        sales.setItemName(title);
        sales.setPrice(price);
        sales.setCount(count);
        CustomUser user = (CustomUser) auth.getPrincipal();
        var member = new Member();
        member.setId((Long) user.id);
        sales.setMember(member);
        salesRepository.save(sales);
    }

    public SalesDTO getOrderAll(){
        List<Sales> result = salesRepository.customFindAll();
        var salesDto = new SalesDTO();
        salesDto.setItemName(result.get(0).getItemName());
        salesDto.setPrice(result.get(0).getPrice());
        salesDto.setCount(result.get(0).getCount());
        salesDto.setUserName(result.get(0).getMember().getUsername());
        return salesDto;
    }
}
