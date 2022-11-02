package com.khoahd7621.youngblack.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.user.ListUsersWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserChangePasswordRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserResponse;
import com.khoahd7621.youngblack.dtos.request.user.UserUpdateRequest;
import com.khoahd7621.youngblack.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.dtos.request.user.UserRegisterRequest;
import com.khoahd7621.youngblack.mappers.UserMapper;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.services.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SuccessResponse<NoData> userRegister(UserRegisterRequest userRegisterRequest)
            throws CustomBadRequestException {
        Optional<User> userOpt = userRepository.findByEmail(userRegisterRequest.getEmail());
        if (userOpt.isPresent()) {
            throw new CustomBadRequestException("This email already existed.");
        }
        userOpt = userRepository.findByPhone(userRegisterRequest.getPhone());
        if (userOpt.isPresent()) {
            throw new CustomBadRequestException("This phone number already existed.");
        }
        User user = userMapper.toUser(userRegisterRequest);
        userRepository.save(user);
        return new SuccessResponse<>(NoData.builder().build(), "Register new account successfully.");
    }

    @Override
    public SuccessResponse<UserResponse> getCurrentUser() throws CustomNotFoundException {
        User user = authService.getUserLoggedIn();
        return new SuccessResponse<>(userMapper.toUserDTOResponse(user),
                "Get current user's information successfully.");
    }

    @Override
    public SuccessResponse<UserResponse> updateUser(UserUpdateRequest userUpdateRequest)
            throws CustomBadRequestException, CustomNotFoundException {
        User user = authService.getUserLoggedIn();
        Optional<User> userOptionalWithPhone = userRepository.findByPhone(userUpdateRequest.getPhone());
        if (userOptionalWithPhone.isPresent() && userOptionalWithPhone.get().getId() != user.getId()) {
            throw new CustomBadRequestException("This phone number already existed.");
        }
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setPhone(userUpdateRequest.getPhone());
        user.setAddress(userUpdateRequest.getAddress());
        user.setUpdatedAt(new Date());
        userRepository.save(user);
        return new SuccessResponse<>(userMapper.toUserDTOResponse(user), "Update information successfully.");
    }

    @Override
    public SuccessResponse<NoData> changePassword(UserChangePasswordRequest userChangePasswordRequest)
            throws CustomBadRequestException, CustomNotFoundException {
        if (!userChangePasswordRequest.getNewPassword().equals(userChangePasswordRequest.getConfirmPassword())) {
            throw new CustomBadRequestException("Confirm password not match new password.");
        }
        User user = authService.getUserLoggedIn();
        if (!passwordEncoder.matches(userChangePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new CustomBadRequestException("Old password is invalid.");
        }
        if (passwordEncoder.matches(userChangePasswordRequest.getNewPassword(), user.getPassword())) {
            throw new CustomBadRequestException("New password is the same with old password. Nothing change.");
        }
        user.setPassword(passwordEncoder.encode(userChangePasswordRequest.getNewPassword()));
        user.setUpdatedAt(new Date());
        userRepository.save(user);
        return new SuccessResponse<>(NoData.builder().build(), "Change password successfully.");
    }

    @Override
    public SuccessResponse<ListUsersWithPaginateResponse> getListUsers(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserResponse> userResponseList = userPage.getContent().stream().map(userMapper::toUserDTOResponse).collect(Collectors.toList());
        ListUsersWithPaginateResponse listUsersWithPaginateResponse =
                ListUsersWithPaginateResponse.builder()
                        .totalRows(userPage.getTotalElements())
                        .totalPages(userPage.getTotalPages())
                        .listUsers(userResponseList).build();
        return new SuccessResponse<>(listUsersWithPaginateResponse, "Get list users success.");
    }

}
