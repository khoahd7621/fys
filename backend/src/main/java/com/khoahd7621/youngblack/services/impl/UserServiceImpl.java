package com.khoahd7621.youngblack.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.user.ListUsersWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOChangePasswordRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserDTOResponse;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOUpdateRequest;
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
import com.khoahd7621.youngblack.dtos.request.user.UserDTORegisterRequest;
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
    public SuccessResponse<NoData> userRegister(UserDTORegisterRequest userDTORegisterRequest)
            throws CustomBadRequestException {
        Optional<User> userOpt = userRepository.findByEmail(userDTORegisterRequest.getEmail());
        if (userOpt.isPresent()) {
            throw new CustomBadRequestException("This email already existed.");
        }
        userOpt = userRepository.findByPhone(userDTORegisterRequest.getPhone());
        if (userOpt.isPresent()) {
            throw new CustomBadRequestException("This phone number already existed.");
        }
        User user = userMapper.toUser(userDTORegisterRequest);
        userRepository.save(user);
        return new SuccessResponse<>(NoData.builder().build(), "Register new account successfully.");
    }

    @Override
    public SuccessResponse<UserDTOResponse> getCurrentUser() throws CustomNotFoundException {
        User user = authService.getUserLoggedIn();
        return new SuccessResponse<>(userMapper.toUserDTOResponse(user),
                "Get current user's information successfully.");
    }

    @Override
    public SuccessResponse<UserDTOResponse> updateUser(UserDTOUpdateRequest userDTOUpdateRequest)
            throws CustomBadRequestException, CustomNotFoundException {
        User user = authService.getUserLoggedIn();
        Optional<User> userOptionalWithPhone = userRepository.findByPhone(userDTOUpdateRequest.getPhone());
        if (userOptionalWithPhone.isPresent() && userOptionalWithPhone.get().getId() != user.getId()) {
            throw new CustomBadRequestException("This phone number already existed.");
        }
        user.setFirstName(userDTOUpdateRequest.getFirstName());
        user.setLastName(userDTOUpdateRequest.getLastName());
        user.setPhone(userDTOUpdateRequest.getPhone());
        user.setAddress(userDTOUpdateRequest.getAddress());
        user.setUpdatedAt(new Date());
        userRepository.save(user);
        return new SuccessResponse<>(userMapper.toUserDTOResponse(user), "Update information successfully.");
    }

    @Override
    public SuccessResponse<NoData> changePassword(UserDTOChangePasswordRequest userDTOChangePasswordRequest)
            throws CustomBadRequestException, CustomNotFoundException {
        if (!userDTOChangePasswordRequest.getNewPassword().equals(userDTOChangePasswordRequest.getConfirmPassword())) {
            throw new CustomBadRequestException("Confirm password not match new password.");
        }
        User user = authService.getUserLoggedIn();
        if (!passwordEncoder.matches(userDTOChangePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new CustomBadRequestException("Old password is invalid.");
        }
        if (passwordEncoder.matches(userDTOChangePasswordRequest.getNewPassword(), user.getPassword())) {
            throw new CustomBadRequestException("New password is the same with old password. Nothing change.");
        }
        user.setPassword(passwordEncoder.encode(userDTOChangePasswordRequest.getNewPassword()));
        user.setUpdatedAt(new Date());
        userRepository.save(user);
        return new SuccessResponse<>(NoData.builder().build(), "Change password successfully.");
    }

    @Override
    public SuccessResponse<ListUsersWithPaginateResponse> getListUsers(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserDTOResponse> userDTOResponseList = userPage.getContent().stream().map(userMapper::toUserDTOResponse).collect(Collectors.toList());
        ListUsersWithPaginateResponse listUsersWithPaginateResponse =
                ListUsersWithPaginateResponse.builder()
                        .totalRows(userPage.getTotalElements())
                        .totalPages(userPage.getTotalPages())
                        .listUsers(userDTOResponseList).build();
        return new SuccessResponse<>(listUsersWithPaginateResponse, "Get list users success.");
    }

}
