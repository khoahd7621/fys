package com.khoahd7621.youngblack.services.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.models.BaseResponse;
import com.khoahd7621.youngblack.models.user.dto.UserDTORegisterRequest;
import com.khoahd7621.youngblack.models.user.mapper.UserMapper;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Override
    public Map<String, Object> userRegister(UserDTORegisterRequest userDTORegisterRequest) throws CustomBadRequestException {
        Optional<User> userOptWithEmail = userRepository.findByEmail(userDTORegisterRequest.getEmail());
        if (userOptWithEmail.isPresent()) {
            throw new CustomBadRequestException(new BaseResponse(-1, "", "This email already exists"));
        }
        Optional<User> userOptWithPhone = userRepository.findByPhone(userDTORegisterRequest.getPhone());
        if (userOptWithPhone.isPresent()) {
            throw new CustomBadRequestException(new BaseResponse(-1, "", "This phone already exists"));
        }
        User user = userMapper.toUser(userDTORegisterRequest);
        userRepository.save(user);
        return new BaseResponse(0, "", "You have successfully registered a new account").getResponse();
    }
    
}
