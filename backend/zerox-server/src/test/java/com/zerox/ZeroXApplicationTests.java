package com.zerox;

import com.zerox.ZeroXApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        classes = ZeroXApplication.class,
        properties = "spring.profiles.active=test"
)
class ZeroXApplicationTests {

    @Test
    void contextLoads() {
    }
}
