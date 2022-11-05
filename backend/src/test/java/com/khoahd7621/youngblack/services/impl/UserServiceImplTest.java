package com.khoahd7621.youngblack.services.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.BadRequestException;
import com.khoahd7621.youngblack.dtos.request.user.UserRegisterRequest;
import com.khoahd7621.youngblack.mappers.UserMapper;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

class UserServiceImplTest {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private AuthService authService;
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userServiceImpl;


    @BeforeEach
    void beforeEach() {
        userRepository = mock(UserRepository.class);
        userMapper = mock(UserMapper.class);
        authService = mock(AuthService.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userServiceImpl = new UserServiceImpl(userRepository, userMapper, authService, passwordEncoder);
    }

    @Test
    void userRegister_ShouldReturnData_WhenDataValid() throws BadRequestException {
        User user = mock(User.class);
        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder()
                .email("email")
                .phone("0123123")
                .build();
        NoData noData = NoData.builder().build();
        SuccessResponse<NoData> expected = new SuccessResponse<>(noData, "Register new account successfully.");
        when(userRepository.findByEmail(userRegisterRequest.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(userRegisterRequest.getPhone())).thenReturn(Optional.empty());
        when(userMapper.toUser(userRegisterRequest)).thenReturn(user);

        SuccessResponse<NoData> actual = userServiceImpl.userRegister(userRegisterRequest);

        verify(userRepository).save(user);
        assertThat(actual.getData().getNoData(), is(noData.getNoData()));
        assertThat(actual.getCode(), is(expected.getCode()));
        assertThat(actual.getMessage(), is(expected.getMessage()));
    }

    @Test
    void userRegister_ShouldReturnError_WhenDuplicatedEmail() throws BadRequestException {
        User user = mock(User.class);
        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder()
                .email("email@gmail.com")
                .build();

        when(userRepository.findByEmail(userRegisterRequest.getEmail())).thenReturn(Optional.of(user));

        BadRequestException result = assertThrows(BadRequestException.class, () -> {
            userServiceImpl.userRegister(userRegisterRequest);
        });

        assertThat(result.getMessage(), is("This email already existed."));
    }

    @Test
    void userRegister_ShouldReturnError_WhenDuplicatedPhone() throws BadRequestException {
        User user = mock(User.class);
        UserRegisterRequest userRegisterRequest = UserRegisterRequest.builder()
                .phone("0123123")
                .build();

        when(userRepository.findByPhone(userRegisterRequest.getPhone())).thenReturn(Optional.of(user));

        BadRequestException result = assertThrows(BadRequestException.class, () -> {
            userServiceImpl.userRegister(userRegisterRequest);
        });
        assertThat(result.getMessage(), is("This phone number already existed."));
    }
}