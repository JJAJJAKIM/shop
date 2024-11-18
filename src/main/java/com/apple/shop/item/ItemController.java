package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model){
        model.addAttribute("items", itemService.findItem());
            return "list.html";
    }
        
        // 상품 추가 기능
        // 1.상품, 가격 작성할 수 있는 페이지와 폼
        // 2. 전송버튼 누르면 서버로 보냄
        // 3. 서버는 검사후 db에 저장

   @GetMapping("/write")
   String write(){
            return "write.html";
       }

   @PostMapping("/add")
   String addPost(@ModelAttribute Item item, Authentication auth){
        item.setUsername(auth.getName());
        itemService.saveItem(item);
        return "redirect:/list";
    }

   @GetMapping ("/detail/{id:[0-9]*}")
   String detail(@PathVariable Long id, Model model)  {
        if (itemService.detailItem(id).isPresent()) {
            model.addAttribute("item", itemService.detailItem(id).get());
            return "detail.html";
        } else {
            return  "redirect:/list";
        }
    }
    // 1. 수정버튼 누르면 글 수정 페이지로
    // 2. 수정페이지엔 기존 내용이 채원진 폼 있음
    // 3. 전송 누르면 그걸로 DB 수정
    @GetMapping ("/edit/{id:[0-9]+}")
    public String edit(@PathVariable Long id, Model model){
        if (itemService.detailItem(id).isPresent()) {
            model.addAttribute("item", itemService.detailItem(id).get());
            return "edit.html";
        } else {
            return  "redirect:/list";
        }
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Item item, Model model){
        item = itemService.editItem(item);
        System.out.println(item.getId());
        return "redirect:/detail/" + item.getId();
    }
    @PostMapping ("/test1")
    public String test1(@RequestBody Map<String, Object> body ){
        System.out.println(body.get("name"));
        return "redirect:/list";
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> deleteItem(@RequestParam Long id){
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/test2")
    String memberAdd(){
        var result = new BCryptPasswordEncoder().encode("하잉하잉");
        System.out.println(result);
        return "redirect:/list";
    }

    @GetMapping("/list/page/{pageNo:[0-9]}")
    String getListPage(Model model, @PathVariable Integer pageNo){
        Page<Item> result = itemRepository.findAllBy(PageRequest.of(pageNo,5));
        model.addAttribute("items", result);
        model.addAttribute("totalPages", result.getTotalPages());
        return "list.html";
    }

}
