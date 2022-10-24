package com.khoahd7621.youngblack.services.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.dtos.request.user.UserDTORegisterRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserDTOResponse;
import com.khoahd7621.youngblack.dtos.user.mapper.UserMapper;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class UserServiceImplTest {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private AuthService authService;
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userServiceImpl;
    private UserDTORegisterRequest userDTORegisterRequest;
    private UserDTOResponse userDTOResponse;
    private User user;


    @BeforeEach
    void beforeEach() {
        userRepository = mock(UserRepository.class);
        userMapper = mock(UserMapper.class);
        authService = mock(AuthService.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userServiceImpl = new UserServiceImpl(userRepository, userMapper, authService, passwordEncoder);
        userDTORegisterRequest = mock(UserDTORegisterRequest.class);
        userDTOResponse = mock(UserDTOResponse.class);
        user = mock(User.class);
    }

    @Test
    void userRegister_ShouldReturnData_WhenDataValid() throws CustomBadRequestException {
        when(userRepository.findByEmail(userDTORegisterRequest.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByPhone(userDTORegisterRequest.getPhone())).thenReturn(Optional.empty());
        when(userMapper.toUser(userDTORegisterRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(null);
        when(userMapper.toUserDTOResponse(user)).thenReturn(userDTOResponse);
        Map<String, UserDTOResponse> expected = new HashMap<>();
        expected.put("data", userDTOResponse);
        Map<String, UserDTOResponse> actual = userServiceImpl.userRegister(userDTORegisterRequest);
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
    void userRegister_ShouldReturnError_WhenDuplicatedEmail() throws CustomBadRequestException {
        when(userRepository.findByEmail(userDTORegisterRequest.getEmail())).thenReturn(Optional.of(user));
        assertThrows(CustomBadRequestException.class, () -> {
            userServiceImpl.userRegister(userDTORegisterRequest);
        });
    }

    @Test
    void userRegister_ShouldReturnError_WhenDuplicatedPhone() throws CustomBadRequestException {
        when(userRepository.findByPhone(userDTORegisterRequest.getPhone())).thenReturn(Optional.of(user));
        assertThrows(CustomBadRequestException.class, () -> {
            userServiceImpl.userRegister(userDTORegisterRequest);
        });
    }
}