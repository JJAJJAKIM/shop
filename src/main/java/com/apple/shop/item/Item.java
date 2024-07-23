package com.apple.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // 부트에서 DB 테이블 만들때는 Class 에서 @Entity를 사용하여 생성 할수 있다.
@ToString
@Setter
@Getter
public class Item {
    // 생성된 테이블에 들어갈 컬럼들 정의
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    // 우선 public을 쓰는게 좋다. public을 쓰면 아무곳에서나 가져다 쓸수 있다.
    @Column(length = 200)
    private String title; //public을 안붙이면 같은 폴더의 클래스에서만 가져다 쓸수 있다. protected 와 같다. 상속을 받으면 사용가능하다.
    private Integer price; // private를 쓰면 다른클래스에서 가져다 쓰지 못한다.
    // static을 쓰면 new 생성자를 안써도 된다.


}
