package com.apple.shop.member;

import com.apple.shop.sales.Sales;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity // 부트에서 DB 테이블 만들때는 Class 에서 @Entity를 사용하여 생성 할수 있다.
@ToString
@Setter
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String displayname;

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Sales> sales =  new ArrayList<>();

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
