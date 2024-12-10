package com.apple.shop.sales;

import com.apple.shop.member.CustomUser;
import com.apple.shop.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @PostMapping("/order")
    String postOrder(@RequestParam String title, @RequestParam Integer price, @RequestParam Integer count, Authentication auth){
        salesService.postOrder(title, price, count, auth);
        return "list.html";
    }

    @GetMapping("/order/all")
    String getOrderAll(){
        var result = salesService.getOrderAll();
        System.out.println(result);
        return "list.html";
    }
}
