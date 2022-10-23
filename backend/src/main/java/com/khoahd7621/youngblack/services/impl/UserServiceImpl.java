package com.khoahd7621.youngblack.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.models.error.CustomError;
import com.khoahd7621.youngblack.models.user.dto.UserDTOResponse;
import com.khoahd7621.youngblack.models.user.dto.UserDTOUpdateRequest;
import com.khoahd7621.youngblack.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.models.user.dto.UserDTORegisterRequest;
import com.khoahd7621.youngblack.models.user.mapper.UserMapper;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;

    private Map<String, UserDTOResponse> buildUserDTOResponse(User user) {
        Map<String, UserDTOResponse> wrapper = new HashMap<>();
        UserDTOResponse userDTOResponse = userMapper.toUserDTOResponse(user);
        wrapper.put("data", userDTOResponse);
        return wrapper;
    }

    @Override
    public Map<String, UserDTOResponse> userRegister(UserDTORegisterRequest userDTORegisterRequest) throws CustomBadRequestException {
        Optional<User> userOpt = userRepository.findByEmail(userDTORegisterRequest.getEmail());
        if (userOpt.isPresent()) {
            throw new CustomBadRequestException(CustomError.builder().code(HttpStatus.BAD_REQUEST).message("This email already exists").build());
        }
        userOpt = userRepository.findByPhone(userDTORegisterRequest.getPhone());
        if (userOpt.isPresent()) {
            throw new CustomBadRequestException(CustomError.builder().code(HttpStatus.BAD_REQUEST).message("This phone number already exists").build());
        }
        User user = userMapper.toUser(userDTORegisterRequest);
        userRepository.save(user);
        return buildUserDTOResponse(user);
    }

    @Override
    public Map<String, UserDTOResponse> getCurrentUser() throws CustomNotFoundException {
        Optional<User> userOpt = authService.getUserLoggedIn();
        if (userOpt.isPresent()) {
            return buildUserDTOResponse(userOpt.get());
        }
        throw new CustomNotFoundException(CustomError.builder().code(HttpStatus.NOT_FOUND).message("User not exist").build());
    }

    @Override
    public Map<String, UserDTOResponse> updateUser(UserDTOUpdateRequest userDTOUpdateRequest) throws CustomBadRequestException, CustomNotFoundException {
        Optional<User> userOpt = authService.getUserLoggedIn();
        if (userOpt.isPresent()) {
            if (userRepository.findByPhone(userDTOUpdateRequest.getPhone()).isPresent()) {
                throw new CustomBadRequestException(CustomError.builder().code(HttpStatus.BAD_REQUEST).message("Phone already existed").build());
            }
            User user = userOpt.get();
            user.setFirstName(userDTOUpdateRequest.getFirstName());
            user.setLastName(userDTOUpdateRequest.getLastName());
            user.setPhone(userDTOUpdateRequest.getPhone());
            user.setAddress(userDTOUpdateRequest.getAddress());
            userRepository.save(user);
            return buildUserDTOResponse(user);
        }
        throw new CustomNotFoundException(CustomError.builder().code(HttpStatus.NOT_FOUND).message("User does not exist").build());
    }

}
