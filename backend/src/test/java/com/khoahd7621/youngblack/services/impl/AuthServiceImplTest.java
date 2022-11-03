package com.khoahd7621.youngblack.services.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.user.UserLoginResponse;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.dtos.request.user.UserLoginRequest;
import com.khoahd7621.youngblack.mappers.UserMapper;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.utils.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

class AuthServiceImplTest {

    private AuthServiceImpl authServiceImpl;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;
    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    void beforeEach() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userMapper = mock(UserMapper.class);
        jwtTokenUtil = mock(JwtTokenUtil.class);
        authServiceImpl = new AuthServiceImpl(userRepository, passwordEncoder, userMapper, jwtTokenUtil);
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataValidAndAccountStatusIsActive() throws BadRequestException {
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .email("email").password("password").build();
        User user = mock(User.class);
        UserLoginResponse userLoginResponse = mock(UserLoginResponse.class);
        SuccessResponse<UserLoginResponse> expected = new SuccessResponse<>(userLoginResponse, "Login successfully.");

        when(userRepository.findByEmail(userLoginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(user.getStatus()).thenReturn(EAccountStatus.ACTIVE);
        when(userMapper.toUserDTOLoginResponse(user)).thenReturn(userLoginResponse);
        when(jwtTokenUtil.generateAccessToken(user)).thenReturn("Token");
        when(jwtTokenUtil.generateRefreshToken(user)).thenReturn("Refresh_Token");

        SuccessResponse<UserLoginResponse> actual = authServiceImpl.loginHandler(userLoginRequest);

        assertThat(actual.getData(), is(userLoginResponse));
        assertThat(actual.getCode(), is(expected.getCode()));
        assertThat(actual.getMessage(), is(expected.getMessage()));
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataValidAndAccountStatusIsInActive() throws BadRequestException {
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .email("email").password("password").build();
        User user = mock(User.class);

        when(userRepository.findByEmail(userLoginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(user.getStatus()).thenReturn(EAccountStatus.INACTIVE);

        BadRequestException actual = assertThrows(BadRequestException.class, () -> {
            authServiceImpl.loginHandler(userLoginRequest);
        });
        assertThat(actual.getMessage(), is("The account has not been activated."));
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataValidAndAccountStatusIsBlock() throws BadRequestException {
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .email("email").password("password").build();
        User user = mock(User.class);

        when(userRepository.findByEmail(userLoginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(user.getStatus()).thenReturn(EAccountStatus.BLOCK);

        BadRequestException actual = assertThrows(BadRequestException.class, () -> {
            authServiceImpl.loginHandler(userLoginRequest);
        });

        assertThat(actual.getMessage(), is("Account has been blocked."));
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataInValid() throws BadRequestException {
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .email("email").password("password").build();

        when(userRepository.findByEmail(userLoginRequest.getEmail())).thenReturn(Optional.empty());

        BadRequestException actual = assertThrows(BadRequestException.class, () -> {
            authServiceImpl.loginHandler(userLoginRequest);
        });
        assertThat(actual.getMessage(), is("Email or password is incorrect."));
    }
}