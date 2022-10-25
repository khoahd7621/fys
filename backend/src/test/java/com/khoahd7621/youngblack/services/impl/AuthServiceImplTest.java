package com.khoahd7621.youngblack.services.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.dtos.response.ExceptionResponse;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.user.UserDTOLoginResponse;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOLoginRequest;
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
    void loginHandler_ShouldReturnData_WhenRequestDataValidAndAccountStatusIsActive() throws CustomBadRequestException {
        UserDTOLoginRequest userDTOLoginRequest = UserDTOLoginRequest.builder()
                .email("email").password("password").build();
        User user = mock(User.class);
        UserDTOLoginResponse userDTOLoginResponse = mock(UserDTOLoginResponse.class);
        SuccessResponse<UserDTOLoginResponse> expected = new SuccessResponse<>(userDTOLoginResponse, "Login successfully");

        when(userRepository.findByEmail(userDTOLoginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userDTOLoginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(user.getStatus()).thenReturn(EAccountStatus.ACTIVE);
        when(userMapper.toUserDTOLoginResponse(user)).thenReturn(userDTOLoginResponse);
        when(jwtTokenUtil.generateAccessToken(user)).thenReturn("Token");
        when(jwtTokenUtil.generateRefreshToken(user)).thenReturn("Refresh_Token");

        SuccessResponse<UserDTOLoginResponse> actual = authServiceImpl.loginHandler(userDTOLoginRequest);

        assertThat(actual.getData(), is(userDTOLoginResponse));
        assertThat(actual.getCode(), is(expected.getCode()));
        assertThat(actual.getMessage(), is(expected.getMessage()));
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataValidAndAccountStatusIsInActive() throws CustomBadRequestException {
        UserDTOLoginRequest userDTOLoginRequest = UserDTOLoginRequest.builder()
                .email("email").password("password").build();
        User user = mock(User.class);
        ExceptionResponse expected = ExceptionResponse.builder()
                .code(-1).message("The account has not been activated").build();

        when(userRepository.findByEmail(userDTOLoginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userDTOLoginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(user.getStatus()).thenReturn(EAccountStatus.INACTIVE);

        CustomBadRequestException actual = assertThrows(CustomBadRequestException.class, () -> {
            authServiceImpl.loginHandler(userDTOLoginRequest);
        });
        assertThat(actual.getError(), is(expected));
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataValidAndAccountStatusIsBlock() throws CustomBadRequestException {
        UserDTOLoginRequest userDTOLoginRequest = UserDTOLoginRequest.builder()
                .email("email").password("password").build();
        User user = mock(User.class);
        ExceptionResponse expected = ExceptionResponse.builder()
                .code(-1).message("Account has been blocked").build();

        when(userRepository.findByEmail(userDTOLoginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userDTOLoginRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(user.getStatus()).thenReturn(EAccountStatus.BLOCK);

        CustomBadRequestException actual = assertThrows(CustomBadRequestException.class, () -> {
            authServiceImpl.loginHandler(userDTOLoginRequest);
        });

        assertThat(actual.getError(), is(expected));
    }

    @Test
    void loginHandler_ShouldReturnData_WhenRequestDataInValid() throws CustomBadRequestException {
        UserDTOLoginRequest userDTOLoginRequest = UserDTOLoginRequest.builder()
                .email("email").password("password").build();
        ExceptionResponse expected = ExceptionResponse.builder()
                .code(-1).message("Email or password is incorrect").build();

        when(userRepository.findByEmail(userDTOLoginRequest.getEmail())).thenReturn(Optional.empty());

        CustomBadRequestException actual = assertThrows(CustomBadRequestException.class, () -> {
            authServiceImpl.loginHandler(userDTOLoginRequest);
        });
        assertThat(actual.getError(), is(expected));
    }
}