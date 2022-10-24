package com.khoahd7621.youngblack.services.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.dtos.user.dto.UserDTOLoginRequest;
import com.khoahd7621.youngblack.dtos.user.dto.UserDTOResponse;
import com.khoahd7621.youngblack.dtos.user.mapper.UserMapper;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.utils.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class AuthServiceImplTest {

    private AuthServiceImpl authServiceImpl;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;
    private JwtTokenUtil jwtTokenUtil;
    private UserDTOLoginRequest userDTOLoginRequest;
    private User user;
    private UserDTOResponse userDTOResponse;

    @BeforeEach
    void beforeEach() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userMapper = mock(UserMapper.class);
        jwtTokenUtil = mock(JwtTokenUtil.class);
        userDTOLoginRequest = mock(UserDTOLoginRequest.class);
        user = mock(User.class);
        userDTOResponse = mock(UserDTOResponse.class);
        authServiceImpl = new AuthServiceImpl(userRepository, passwordEncoder, userMapper, jwtTokenUtil);
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataValidAndAccountStatusIsActive() throws CustomBadRequestException {
        when(userRepository.findByEmail(userDTOLoginRequest.getEmail())).thenReturn(Optional.of(user));
        when(user.getStatus()).thenReturn(EAccountStatus.ACTIVE);
        when(passwordEncoder.matches(userDTOLoginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(userMapper.toUserDTOResponse(user)).thenReturn(userDTOResponse);
        when(jwtTokenUtil.generateAccessToken(user)).thenReturn("Token");
        when(jwtTokenUtil.generateRefreshToken(user)).thenReturn("Refresh_Token");
        Map<String, UserDTOResponse> expected = new HashMap<>();
        expected.put("data", userDTOResponse);
        Map<String, UserDTOResponse> actual = authServiceImpl.loginHandler(userDTOLoginRequest);
        assertThat(actual.containsKey("data"), is(expected.containsKey("data")));
        assertThat(actual.get("data").getFirstName(), is(expected.get("data").getFirstName()));
        assertThat(actual.get("data").getLastName(), is(expected.get("data").getLastName()));
        assertThat(actual.get("data").getEmail(), is(expected.get("data").getEmail()));
        assertThat(actual.get("data").getPhone(), is(expected.get("data").getPhone()));
        assertThat(actual.get("data").getAddress(), is(expected.get("data").getAddress()));
        assertThat(actual.get("data").getRole(), is(expected.get("data").getRole()));
        assertThat(actual.get("data").getAccessToken(), is(expected.get("data").getAccessToken()));
        assertThat(actual.get("data").getRefreshToken(), is(expected.get("data").getRefreshToken()));
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataValidAndAccountStatusIsInActive() throws CustomBadRequestException {
        when(userRepository.findByEmail(userDTOLoginRequest.getEmail())).thenReturn(Optional.of(user));
        when(user.getStatus()).thenReturn(EAccountStatus.INACTIVE);
        assertThrows(CustomBadRequestException.class, () -> {
            authServiceImpl.loginHandler(userDTOLoginRequest);
        });
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataValidAndAccountStatusIsBlock() throws CustomBadRequestException {
        when(userRepository.findByEmail(userDTOLoginRequest.getEmail())).thenReturn(Optional.of(user));
        when(user.getStatus()).thenReturn(EAccountStatus.BLOCK);
        assertThrows(CustomBadRequestException.class, () -> {
            authServiceImpl.loginHandler(userDTOLoginRequest);
        });
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataInValid() throws CustomBadRequestException {
        when(userRepository.findByEmail(userDTOLoginRequest.getEmail())).thenReturn(Optional.empty());
        assertThrows(CustomBadRequestException.class, () -> {
            authServiceImpl.loginHandler(userDTOLoginRequest);
        });
    }
}