package com.khoahd7621.youngblack.services.impl;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.models.error.CustomError;
import com.khoahd7621.youngblack.models.user.dto.UserDTOLoginRequest;
import com.khoahd7621.youngblack.models.user.dto.UserDTOResponse;
import com.khoahd7621.youngblack.models.user.mapper.UserMapper;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.services.AuthService;
import com.khoahd7621.youngblack.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, UserDTOResponse> loginHandler(UserDTOLoginRequest userDTOLoginRequest) throws CustomBadRequestException {
        boolean isAuthed = false;
        Optional<User> userOpt = userRepository.findByEmail(userDTOLoginRequest.getEmail());
        if (userOpt.isPresent()) {
            if (userOpt.get().getStatus() == EAccountStatus.ACTIVE) {
                if (passwordEncoder.matches(userDTOLoginRequest.getPassword(), userOpt.get().getPassword())) {
                    isAuthed = true;
                }
            } else if (userOpt.get().getStatus() == EAccountStatus.INACTIVE) {
                throw new CustomBadRequestException(
                        CustomError.builder()
                                .code(HttpStatus.BAD_REQUEST)
                                .message("The account has not been activated").build());
            } else if (userOpt.get().getStatus() == EAccountStatus.BLOCK) {
                throw new CustomBadRequestException(
                        CustomError.builder()
                                .code(HttpStatus.BAD_REQUEST)
                                .message("Account has been blocked").build());
            }
        }
        if (!isAuthed) {
            throw new CustomBadRequestException(
                    CustomError.builder()
                            .code(HttpStatus.BAD_REQUEST)
                            .message("Email or password is incorrect").build());
        }
        return buildUserDTOResponse(userOpt.get());
    }

    private Map<String, UserDTOResponse> buildUserDTOResponse(User user) {
        Map<String, UserDTOResponse> wrapper = new HashMap<>();
        UserDTOResponse userDTOResponse = userMapper.toUserDTOResponse(user);
        userDTOResponse.setAccessToken(jwtTokenUtil.generateAccessToken(user));
        userDTOResponse.setRefreshToken(jwtTokenUtil.generateRefreshToken(user));
        wrapper.put("data", userDTOResponse);
        return wrapper;
    }

}
