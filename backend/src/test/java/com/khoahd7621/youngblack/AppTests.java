package com.khoahd7621.youngblack;

import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.security.CorsConfig;
import com.khoahd7621.youngblack.security.WebSecurityConfig;
import com.khoahd7621.youngblack.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@WebMvcTest(App.class)
@ContextConfiguration(classes = {App.class, WebSecurityConfig.class, CorsConfig.class})
class AppTests {

    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

}
