package top.vabook.token;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.vabook.token.mapper")
public class TokenApplication {


    public static void main(String[] args) {
        SpringApplication.run(TokenApplication.class, args);
    }

}
