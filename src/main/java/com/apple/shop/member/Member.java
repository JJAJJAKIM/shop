package com.apple.shop.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity // 부트에서 DB 테이블 만들때는 Class 에서 @Entity를 사용하여 생성 할수 있다.
@ToString
@Setter
@Getter
public class Member {

    @Id
    private String username;
    private String password;
    private String displayname;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
